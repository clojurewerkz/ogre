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

(defn get_g_V_asXaX_out_asXbX_out_asXcX_simplePath_byXlabelX_fromXbX_toXcX_path_byXnameX
  "g.V().as('a').out().as('b').out().as('c').simplePath().by(T.label).from('b').to('c').path().by('name')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/out) (q/as :c)
                (q/simple-path)
                  (q/by T/label)
                  (q/from :b)
                  (q/to :c)
                (q/path)
                  (q/by :name)))
