(ns ogre.god-test
  (:use [clojure.test])
  (:require [ogre.core :as q]))

;;Adapted from
;;https://github.com/clojurewerkz/titanium/blob/master/test/clojurewerkz/titanium/integration_test.clj
;; (deftest test-gremlin-graph-of-gods
;;   (clear-db)
;;   (g/open conf)
;;   (g/transact!
;;    (let [saturn   (v/create! {:name "Saturn"   :type "titan"})
;;          jupiter  (v/create! {:name "Jupiter"  :type "god"})
;;          hercules (v/create! {:name "Hercules" :type "demigod"})
;;          alcmene  (v/create! {:name "Alcmene"  :type "human"})
;;          neptune  (v/create! {:name "Neptune"  :type "god"})
;;          pluto    (v/create! {:name "Pluto"    :type "god"})
;;          sea      (v/create! {:name "Sea"      :type "location"})
;;          sky      (v/create! {:name "Sky"      :type "location"})
;;          tartarus (v/create! {:name "Tartarus" :type "location"})
;;          nemean   (v/create! {:name "Nemean"   :type "monster"})
;;          hydra    (v/create! {:name "Hydra"    :type "monster"})
;;          cerberus (v/create! {:name "Cerberus" :type "monster"})]
;;      (e/connect! neptune :lives sea)
;;      (e/connect! jupiter :lives sky)
;;      (e/connect! pluto   :lives tartarus)
;;      (e/connect! jupiter :father saturn)
;;      (e/connect! hercules :father jupiter)
;;      (e/connect! hercules :mother alcmene)
;;      (e/connect! jupiter :brother pluto)
;;      (e/connect! pluto :brother jupiter)
;;      (e/connect! neptune :brother pluto)
;;      (e/connect! pluto :brother neptune)
;;      (e/connect! jupiter :brother neptune)
;;      (e/connect! neptune :brother jupiter)
;;      (e/connect! cerberus :lives tartarus)
;;      (e/connect! pluto :pet cerberus)
;;      (e/connect! hercules :battled nemean {:times 1})
;;      (e/connect! hercules :battled hydra {:times 2})
;;      (e/connect! hercules :battled cerberus {:times 12})
;;      (let [r1 (q/query saturn
;;                        (q/<-- :father)
;;                        (q/<-- :father)
;;                        q/into-vec
;;                        first)
;;            r2 (q/query hercules
;;                        (q/--> :father :mother)
;;                        (q/property :name)
;;                        (q/into-set))
;;            r3 (q/query hercules
;;                        (q/--E> :battled)
;;                        (q/has :times > 1)
;;                        (q/in-vertex)
;;                        (q/property :name)
;;                        (q/into-set))
;;            c3 (q/query hercules
;;                        (q/--E> :battled)
;;                        (q/has :times > 1)
;;                        (q/in-vertex)
;;                        (q/count))
;;            r4 (q/query pluto
;;                        (q/--> :lives)
;;                        (q/<--  :lives)
;;                        (q/except [pluto])
;;                        (q/property :name)
;;                        (q/into-set))
;;            r5 (->> (q/query pluto
;;                             (q/--> :brother)
;;                             (q/as  "god")
;;                             (q/--> :lives)
;;                             (q/as  "place")
;;                             (q/select (fn [v1] (v/get-property v1 :name)))
;;                             (q/into-set))
;;                    (map (fn [row] (into [] row))))
;;            ]
;;        (is (= r1 hercules))
;;        (is (= r2 #{"Alcmene" "Jupiter"}))
;;        (is (= r3 #{"Cerberus" "Hydra"}))
;;        (is (= c3 2))
;;        (is (= r4 #{"Cerberus"}))
;;        ;; when https://github.com/tinkerpop/pipes/issues/75 is fixed,
;;        ;; we will be able to turn tables into vectors of maps, as they
;;        ;; should be represented (Neocons does it for Cypher responses). MK.
;;        (is (= '(["Jupiter" "Sky"] ["Neptune" "Sea"]) r5)))))
;;   (g/shutdown))