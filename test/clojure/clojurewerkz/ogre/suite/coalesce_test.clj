(ns clojurewerkz.ogre.suite.coalesce-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_V_coalesceXoutXfooX_outXbarXX
  "g.V().coalesce(out('foo'), out('bar'))"
  [g]
  (traverse g (V)
              (coalesce (__ (out :foo)) (__ (out :bar)))))

(defn get_g_VX1X_coalesceXoutXknowsX_outXcreatedXX_valuesXnameX
  "g.V(v1Id).coalesce(out('knows'), out('created')).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (coalesce (__ (out :knows)) (__ (out :created)))
              (values :name)))

(defn get_g_VX1X_coalesceXoutXcreatedX_outXknowsXX_valuesXnameX
  "g.V(v1Id).coalesce(out('created'), out('knows')).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (coalesce (__ (out :created)) (__ (out :knows)))
              (values :name)))

(defn get_g_V_coalesceXoutXlikesX_outXknowsX_inXcreatedXX_groupCount_byXnameX
  "g.V().coalesce(out('likes'), out('knows'), out('created')).groupCount().by('name')"
  [g]
  (traverse g (V)
              (coalesce (__ (out :likes)) (__ (out :knows)) (__ (out :created)))
              (group-count)
                (by :name)))

(defn get_g_V_coalesceXoutEXknowsX_outEXcreatedXX_otherV_path_byXnameX_byXlabelX
  "g.V().coalesce(outE('knows'), outE('created')).otherV().path().by('name').by(T.label)"
  [g]
  (traverse g (V)
              (coalesce (__ (outE :knows)) (__ (outE :created)))
              (otherV)
              (path)
                (by :name)
                (by T/label)))
