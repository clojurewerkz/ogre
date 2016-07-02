(ns clojurewerkz.ogre.suite.unfold-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser)))

(defn get_g_V_localXoutE_foldX_unfold
  "g.V().local(outE().fold()).unfold()"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/outE) (q/fold)))
                (q/unfold)))

(defn get_g_V_valueMap_unfold_mapXkeyX
  "g.V().valueMap().unfold().map(m -> m.get().getKey())"
  [g]
  (q/traverse g (q/V)
                (q/value-map)
                (q/unfold)
                (q/map (fn [m] (.getKey ^java.util.Map$Entry (.get ^Traverser m))))))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasIdX6XX_path_byXnameX_unfold
  "g.V(v1Id).repeat(both().simplePath()).until(hasId(v6Id)).path().by('name').unfold()"
  [g v1Id v6Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/both) (q/simple-path)))
                (q/until (q/__ (q/has-id v6Id)))
                (q/path)
                (q/by :name)
                (q/unfold)))

