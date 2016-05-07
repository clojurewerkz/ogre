(ns clojurewerkz.ogre.suite.map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser)))

(defn get_g_VX1X_out_mapXnameX_mapXlengthX
  "g.V(v1Id).out().map(v -> v.get().value('name')).map(n -> n.get().toString().length())"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/map (fn [^Traverser v] (.value (.get v) "name")))
                (q/map (fn [^Traverser n] (.length (.toString (.get n)))))))

(defn get_g_withPath_V_asXaX_out_out_mapXa_name_it_nameX
  "g.withPath().V().as('a').out().out().map(v -> v.path('a').value('name') + v.get().value('name'))"
  [g]
  (q/traverse g (q/with-path) (q/V) (q/as :a)
                (q/out)
                (q/out)
                (q/map (fn [^Traverser v] (str (.value (.path v "a") "name") (.value (.get v) "name"))))))

(defn get_g_withPath_V_asXaX_out_mapXa_nameX
  "g.withPath().V().as('a').out().map(v -> v.path('a').value('name'))"
  [g]
  (q/traverse g (q/with-path) (q/V) (q/as :a)
                (q/out)
                (q/map (fn [^Traverser v] (.value (.path v "a") "name")))))

(defn get_g_VX1X_mapXnameX
  "g.V(v1Id).map(v -> v.get().value('name'))"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/map (fn [^Traverser v] (.value (.get v) "name")))))

(defn get_g_V_mapXselectXaXX
  "g.V().as('a').map(select('a'))"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/map (q/__ (q/select :a)))))

(defn get_g_VX1X_outE_label_mapXlengthX
  "g.V(v1Id).outE().label().map(l -> l.get().length())"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE)
                (q/label)
                (q/map (fn [l] (.length (.get l))))))
