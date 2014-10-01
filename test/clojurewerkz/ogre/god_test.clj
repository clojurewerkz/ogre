(ns clojurewerkz.ogre.god-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

;;Adapted from
;;https://github.com/clojurewerkz/titanium/blob/master/test/clojurewerkz/titanium/integration_test.clj
(deftest test-graph-of-gods
  (g/use-clean-graph!)
  (let [g (g/use-new-tinker-graph!)
        saturn   (g/create! g {:name "Saturn"   :type "titan"})
        jupiter  (g/create! g {:name "Jupiter"  :type "god"})
        hercules (g/create! g {:name "Hercules" :type "demigod"})
        alcmene  (g/create! g {:name "Alcmene"  :type "human"})
        neptune  (g/create! g {:name "Neptune"  :type "god"})
        pluto    (g/create! g {:name "Pluto"    :type "god"})
        sea      (g/create! g {:name "Sea"      :type "location"})
        sky      (g/create! g {:name "Sky"      :type "location"})
        tartarus (g/create! g {:name "Tartarus" :type "location"})
        nemean   (g/create! g {:name "Nemean"   :type "monster"})
        hydra    (g/create! g {:name "Hydra"    :type "monster"})
        cerberus (g/create! g {:name "Cerberus" :type "monster"})]
    (g/connect! g neptune :lives sea)
    (g/connect! g jupiter :lives sky)
    (g/connect! g pluto   :lives tartarus)
    (g/connect! g jupiter :father saturn)
    (g/connect! g hercules :father jupiter)
    (g/connect! g hercules :mother alcmene)
    (g/connect! g jupiter :brother pluto)
    (g/connect! g pluto :brother jupiter)
    (g/connect! g neptune :brother pluto)
    (g/connect! g pluto :brother neptune)
    (g/connect! g jupiter :brother neptune)
    (g/connect! g neptune :brother jupiter)
    (g/connect! g cerberus :lives tartarus)
    (g/connect! g pluto :pet cerberus)
    (g/connect! g hercules :battled nemean {:times 1})
    (g/connect! g hercules :battled hydra {:times 2})
    (g/connect! g hercules :battled cerberus {:times 12})
    (let [r0 (q/query (g/get-vertices g)
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
                      (q/select (partial g/get-property :name))
                      (q/all-into-maps!))]
      (is (= r0 1))
      (is (= r1 hercules))
      (is (= r2 #{"Alcmene" "Jupiter"}))
      (is (= r3 #{"Cerberus" "Hydra"}))
      (is (= c3 2))
      (is (= r4 #{"Cerberus"}))
      (is (= r5
             '({:god "Neptune" :place "Sea"}
               {:god "Jupiter" :place "Sky"}))))))
