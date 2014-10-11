(ns clojurewerkz.ogre.god-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

;;Adapted from
;;https://github.com/clojurewerkz/titanium/blob/master/test/clojurewerkz/titanium/integration_test.clj
(deftest test-graph-of-gods
  (let [g (u/new-tinkergraph)
        saturn   (v/create! g {:name "Saturn"   :type "titan"})
        jupiter  (v/create! g {:name "Jupiter"  :type "god"})
        hercules (v/create! g {:name "Hercules" :type "demigod"})
        alcmene  (v/create! g {:name "Alcmene"  :type "human"})
        neptune  (v/create! g {:name "Neptune"  :type "god"})
        pluto    (v/create! g {:name "Pluto"    :type "god"})
        sea      (v/create! g {:name "Sea"      :type "location"})
        sky      (v/create! g {:name "Sky"      :type "location"})
        tartarus (v/create! g {:name "Tartarus" :type "location"})
        nemean   (v/create! g {:name "Nemean"   :type "monster"})
        hydra    (v/create! g {:name "Hydra"    :type "monster"})
        cerberus (v/create! g {:name "Cerberus" :type "monster"})]
    (e/connect! neptune :lives sea)
    (e/connect! jupiter :lives sky)
    (e/connect! pluto   :lives tartarus)
    (e/connect! jupiter :father saturn)
    (e/connect! hercules :father jupiter)
    (e/connect! hercules :mother alcmene)
    (e/connect! jupiter :brother pluto)
    (e/connect! pluto :brother jupiter)
    (e/connect! neptune :brother pluto)
    (e/connect! pluto :brother neptune)
    (e/connect! jupiter :brother neptune)
    (e/connect! neptune :brother jupiter)
    (e/connect! cerberus :lives tartarus)
    (e/connect! pluto :pet cerberus)
    (e/connect! hercules :battled nemean {:times 1})
    (e/connect! hercules :battled hydra {:times 2})
    (e/connect! hercules :battled cerberus {:times 12})
    (let [r0 (q/query (v/get-all-vertices g)
                      (q/has :type "human")
                      q/count!)
          r1 (q/query saturn
                      (q/<-- [:father])
                      (q/<-- [:father])
                      q/into-vec!
                      first)
          r2 (q/query hercules
                      (q/--> [:father :mother])
                      (q/property :name)
                      (q/into-set!))
          r3 (q/query hercules
                      (q/-E> [:battled])
                      (q/has  :times > 1)
                      (q/in-vertex)
                      (q/property :name)
                      (q/into-set!))
          c3 (q/query hercules
                      (q/-E> [:battled])
                      (q/has :times > 1)
                      (q/in-vertex)
                      (q/count!))
          r4 (q/query pluto
                      (q/--> [:lives])
                      (q/<-- [:lives])
                      (q/except [pluto])
                      (q/property :name)
                      (q/into-set!))
          r5 (q/query pluto
                      (q/--> [:brother])
                      (q/as  "god")
                      (q/--> [:lives])
                      (q/as  "place")
                      (q/select #(v/get % :name))
                      (q/all-into-maps!))]
      (is (= r0 1))
      (is (= r1 hercules))
      (is (= r2 #{"Alcmene" "Jupiter"}))
      (is (= r3 #{"Cerberus" "Hydra"}))
      (is (= c3 2))
      (is (= r4 #{"Cerberus"}))
      (is (= (sort-by :god r5)
             (sort-by :god '({:god "Neptune" :place "Sea"}
                     {:god "Jupiter" :place "Sky"})))))))
