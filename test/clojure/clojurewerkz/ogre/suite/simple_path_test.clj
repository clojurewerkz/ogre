(ns clojurewerkz.ogre.suite.simple-path-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_simplePath
  "g.V(v1Id).out('created').in('created').simplePath()"
  [g v1Id]
  (traverse g (V v1Id)
              (out :created)
              (in :created)
              (simple-path)))

(defn get_g_V_repeatXboth_simplePathX_timesX3X_path
  "g.V().repeat(both().simplePath()).times(3).path()"
  [g]
  (traverse g (V)
              (repeat (__ (both) (simple-path)))
                (times 3)
              (path)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_simplePath_byXlabelX_fromXbX_toXcX_path_byXnameX
  "g.V().as('a').out().as('b').out().as('c').simplePath().by(T.label).from('b').to('c').path().by('name')"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (simple-path)
                (by T/label)
                (from :b)
                (to :c)
              (path)
                (by :name)))
