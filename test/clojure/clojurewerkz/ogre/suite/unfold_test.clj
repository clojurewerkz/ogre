(ns clojurewerkz.ogre.suite.unfold-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_V_localXoutE_foldX_unfold
  "g.V().local(outE().fold()).unfold()"
  [g]
  (traverse g (V)
              (local (__ (outE) (fold)))
              (unfold)))

(defn get_g_V_valueMap_unfold_mapXkeyX
  "g.V().valueMap().unfold().map(m -> m.get().getKey())"
  [g]
  (traverse g (V)
              (value-map)
              (unfold)
              (map (fn [m] (.getKey ^java.util.Map$Entry (.get ^Traverser m))))))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasIdX6XX_path_byXnameX_unfold
  "g.V(v1Id).repeat(both().simplePath()).until(hasId(v6Id)).path().by('name').unfold()"
  [g v1Id v6Id]
  (traverse g (V v1Id)
              (repeat (__ (both) (simple-path)))
                (until (__ (has-id v6Id)))
              (path)
                (by :name)
              (unfold)))

