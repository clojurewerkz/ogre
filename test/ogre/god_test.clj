(ns ogre.god-test
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory))
  (:require [ogre.core :as q]))

(defn clean-graph []
  (let [graph (TinkerGraphFactory/createTinkerGraph)]
    (map #(.removeEdge graph %) (seq (.getEdges graph)))
    (map #(.removeVertex graph %) (seq (.getVertices graph)))
    graph))

(defn set-properties [w m]
  (doseq [[k v] m]
    (.setProperty w (name k) v)))

(def vid (atom 100))

(defn new-vertex [graph m]
  (let [v (.addVertex graph (swap! vid inc))]
    (set-properties v m)
    v))

(defn connect
  ([graph v1 label v2] (connect graph v1 label v2 {}))
  ([graph v1 label v2 m]
     (let [edge (.addEdge graph (swap! vid inc) v1 v2 (name label))]
       (set-properties edge m)
       edge)))

;;Adapted from
;;https://github.com/clojurewerkz/titanium/blob/master/test/clojurewerkz/titanium/integration_test.clj
(deftest test-graph-of-gods
  (let [graph (clean-graph)
        saturn   (new-vertex graph {:name "Saturn"   :type "titan"})
        jupiter  (new-vertex graph {:name "Jupiter"  :type "god"})
        hercules (new-vertex graph {:name "Hercules" :type "demigod"})
        alcmene  (new-vertex graph {:name "Alcmene"  :type "human"})
        neptune  (new-vertex graph {:name "Neptune"  :type "god"})
        pluto    (new-vertex graph {:name "Pluto"    :type "god"})
        sea      (new-vertex graph {:name "Sea"      :type "location"})
        sky      (new-vertex graph {:name "Sky"      :type "location"})
        tartarus (new-vertex graph {:name "Tartarus" :type "location"})
        nemean   (new-vertex graph {:name "Nemean"   :type "monster"})
        hydra    (new-vertex graph {:name "Hydra"    :type "monster"})
        cerberus (new-vertex graph {:name "Cerberus" :type "monster"})]
    (connect graph neptune :lives sea)
    (connect graph jupiter :lives sky)
    (connect graph pluto   :lives tartarus)
    (connect graph jupiter :father saturn)
    (connect graph hercules :father jupiter)
    (connect graph hercules :mother alcmene)
    (connect graph jupiter :brother pluto)
    (connect graph pluto :brother jupiter)
    (connect graph neptune :brother pluto)
    (connect graph pluto :brother neptune)
    (connect graph jupiter :brother neptune)
    (connect graph neptune :brother jupiter)
    (connect graph cerberus :lives tartarus)
    (connect graph pluto :pet cerberus)
    (connect graph hercules :battled nemean {:times 1})
    (connect graph hercules :battled hydra {:times 2})
    (connect graph hercules :battled cerberus {:times 12})
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
                      (q/has :times > 1)
                      (q/in-vertex)
                      (q/property :name)
                      (q/into-set))
          c3 (q/query hercules
                      (q/--E> :battled)
                      (q/has :times > 1)
                      (q/in-vertex)
                      (q/count))
          r4 (q/query pluto
                      (q/--> :lives)
                      (q/<--  :lives)
                      (q/except [pluto])
                      (q/property :name)
                      (q/into-set))
          r5 (->> (q/query pluto
                           (q/--> :brother)
                           (q/as  "god")
                           (q/--> :lives)
                           (q/as  "place")
                           (q/select (fn [v1] (.getProperty v1 "name")))
                           (q/into-set))
                  (map (fn [row] (into [] row))))
          ]
      (is (= r1 hercules))
      (is (= r2 #{"Alcmene" "Jupiter"}))
      (is (= r3 #{"Cerberus" "Hydra"}))
      (is (= c3 2))
      (is (= r4 #{"Cerberus"}))
      ;; when https://github.com/tinkerpop/pipes/issues/75 is fixed,
      ;; we will be able to turn tables into vectors of maps, as they
      ;; should be represented (Neocons does it for Cypher responses). MK.
      (is (= '(["Jupiter" "Sky"] ["Neptune" "Sea"]) r5)))))