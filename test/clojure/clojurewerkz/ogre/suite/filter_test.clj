(ns clojurewerkz.ogre.suite.filter-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure Vertex Property)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_V_filterXfalseX
  "g.V().filter(v -> false)"
  [g]
  (traverse g (V)
              (filter (fn [x] false))))

(defn get_g_E_filterXfalseX
  "g.E().filter(v -> false)"
  [g]
  (traverse g (E)
              (filter (fn [x] false))))

(defn get_g_V_filterXtrueX
  "g.V().filter(v -> true)"
  [g]
  (traverse g (V)
              (filter (fn [x] true))))

(defn get_g_V_filterXlang_eq_javaX
  "g.V().filter(v -> v.get().property('lang').orElse('none').equals('java'))"
  [g]
  (traverse g (V)
              (filter #(= (.orElse ^Property (.property ^Vertex (.get ^Traverser %1) "lang") "none") "java"))))

(defn get_g_VX1X_filterXage_gt_30X
  "g.V(v1Id).filter(v -> v.get().property('age').orElse(0) > 30)"
  [g v1Id]
  (traverse g (V v1Id)
              (filter #(> (.orElse ^Property (.property ^Vertex (.get ^Traverser %1) "age") 0) 30))))

(defn get_g_VX1X_out_filterXage_gt_30X
  "g.V(v1Id).out().filter(v -> v.get().property('age').orElse(0) > 30)"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (filter #(> (.orElse ^Property (.property ^Vertex (.get ^Traverser %1) "age") 0) 30))))

(defn get_g_V_filterXname_startsWith_m_OR_name_startsWith_pX
  "g.V().filter(v -> {
    final String name = v.get().value('name');
    return name.startsWith('m') || name.startsWith('p');
  })"
  [g]
  (traverse g (V)
              (filter (fn [v]
                        (let [n ^String (.value ^Vertex (.get ^Traverser v) "name")]
                          (clojure.core/or (.startsWith n "m") (.startsWith n "p")))))))

(defn get_g_E_filterXtrueX
  "g.V().filter(v -> true)"
  [g]
  (traverse g (E)
              (filter (fn [x] true))))
