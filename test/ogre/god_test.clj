(ns ogre.god-test
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory))
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

;;Adapted from
;;https://github.com/clojurewerkz/titanium/blob/master/test/clojurewerkz/titanium/integration_test.clj
(deftest test-graph-of-gods
  (g/use-clean-graph!)
  (let [saturn   (g/create! {:name "Saturn"   :type "titan"})
        jupiter  (g/create! {:name "Jupiter"  :type "god"})
        hercules (g/create! {:name "Hercules" :type "demigod"})
        alcmene  (g/create! {:name "Alcmene"  :type "human"})
        neptune  (g/create! {:name "Neptune"  :type "god"})
        pluto    (g/create! {:name "Pluto"    :type "god"})
        sea      (g/create! {:name "Sea"      :type "location"})
        sky      (g/create! {:name "Sky"      :type "location"})
        tartarus (g/create! {:name "Tartarus" :type "location"})
        nemean   (g/create! {:name "Nemean"   :type "monster"})
        hydra    (g/create! {:name "Hydra"    :type "monster"})
        cerberus (g/create! {:name "Cerberus" :type "monster"})]
    (g/connect! neptune :lives sea)
    (g/connect! jupiter :lives sky)
    (g/connect! pluto   :lives tartarus)
    (g/connect! jupiter :father saturn)
    (g/connect! hercules :father jupiter)
    (g/connect! hercules :mother alcmene)
    (g/connect! jupiter :brother pluto)
    (g/connect! pluto :brother jupiter)
    (g/connect! neptune :brother pluto)
    (g/connect! pluto :brother neptune)
    (g/connect! jupiter :brother neptune)
    (g/connect! neptune :brother jupiter)
    (g/connect! cerberus :lives tartarus)
    (g/connect! pluto :pet cerberus)
    (g/connect! hercules :battled nemean {:times 1})
    (g/connect! hercules :battled hydra {:times 2})
    (g/connect! hercules :battled cerberus {:times 12})
    (let [r1 (q/query saturn
                      (q/<-- :father)
                      (q/<-- :father)
                      q/into-vec
                      first)
          r2 (q/query hercules
                      (q/--> :father :mother)
                      (q/property :name)
                      (q/into-set))
          r3 (q/query hercules
                      (q/--E> :battled)
                      (q/has  :times > 1)
                      (q/in-vertex)
                      (q/property :name)
                      (q/into-set))
          c3 (q/query hercules
                      (q/--E> :battled)
                      (q/has  :times > 1)
                      (q/in-vertex)
                      (q/count))
          r4 (q/query pluto
                      (q/--> :lives)
                      (q/<-- :lives)
                      (q/except [pluto])
                      (q/property :name)
                      (q/into-set))
          r5 (q/query pluto
                           (q/--> :brother)
                           (q/as  "god")
                           (q/--> :lives)
                           (q/as  "place")
                           (q/select (fn [v1] (g/get-property v1 :name)))
                           (q/into-set)
                           ((partial map (partial into []))))]
      (is (= r1 hercules))
      (is (= r2 #{"Alcmene" "Jupiter"}))
      (is (= r3 #{"Cerberus" "Hydra"}))
      (is (= c3 2))
      (is (= r4 #{"Cerberus"}))
      ;; when https://github.com/tinkerpop/pipes/issues/75 is fixed,
      ;; we will be able to turn tables into vectors of maps, as they
      ;; should be represented (Neocons does it for Cypher responses). MK.
      (is (= '(["Jupiter" "Sky"] ["Neptune" "Sea"]) r5)))))