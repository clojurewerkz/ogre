(ns clojurewerkz.ogre.suite.map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_VX1X_out_mapXnameX_mapXlengthX
  "g.V(v1Id).out().map(v -> v.get().value('name')).map(n -> n.get().toString().length())"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (map (fn [^Traverser v] (.value ^Vertex (.get v) "name")))
              (map (fn [^Traverser n] (.length ^String (.toString ^Object (.get n)))))))

(defn get_g_withPath_V_asXaX_out_out_mapXa_name_it_nameX
  "g.withPath().V().as('a').out().out().map(v -> v.path('a').value('name') + v.get().value('name'))"
  [g]
  (traverse g (with-path) (V) (as :a)
              (out)
              (out)
              (map (fn [^Traverser v] (str (.value ^Vertex (.path v "a") "name") (.value  ^Vertex (.get v) "name"))))))

(defn get_g_withPath_V_asXaX_out_mapXa_nameX
  "g.withPath().V().as('a').out().map(v -> v.path('a').value('name'))"
  [g]
  (traverse g (with-path) (V) (as :a)
              (out)
              (map (fn [^Traverser v] (.value ^Vertex (.path v "a") "name")))))

(defn get_g_VX1X_mapXnameX
  "g.V(v1Id).map(v -> v.get().value('name'))"
  [g v1Id]
  (traverse g (V v1Id)
              (map (fn [^Traverser v] (.value ^Vertex (.get v) "name")))))

(defn get_g_V_mapXselectXaXX
  "g.V().as('a').map(select('a'))"
  [g]
  (traverse g (V) (as :a)
              (map (__ (select :a)))))

(defn get_g_VX1X_outE_label_mapXlengthX
  "g.V(v1Id).outE().label().map(l -> l.get().length())"
  [g v1Id]
  (traverse g (V v1Id)
              (outE)
              (label)
              (map (fn [^Traverser l] (.length ^String (.get l))))))
