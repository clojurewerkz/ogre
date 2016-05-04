(ns clojurewerkz.ogre.suite.simple-path-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_simplePath
  "g.V(v1Id).out('created').in('created').simplePath()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :created)
                (q/in :created)
                (q/simple-path)))

(defn get_g_V_repeatXboth_simplePathX_timesX3X_path
  "g.V().repeat(both().simplePath()).times(3).path()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both) (q/simple-path)))
                (q/times 3)
                (q/path)))
