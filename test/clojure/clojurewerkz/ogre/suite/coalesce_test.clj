(ns clojurewerkz.ogre.suite.coalesce-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_coalesceXoutXfooX_outXbarXX
  "g.V().coalesce(out('foo'), out('bar'))"
  [g]
  (q/traverse g (q/V)
                (q/coalesce (q/__ (q/out :foo)) (q/__ (q/out :bar)))))

(defn get_g_VX1X_coalesceXoutXknowsX_outXcreatedXX_valuesXnameX
  "g.V(v1Id).coalesce(out('knows'), out('created')).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/coalesce (q/__ (q/out :knows)) (q/__ (q/out :created)))
                (q/values :name)))

(defn get_g_VX1X_coalesceXoutXcreatedX_outXknowsXX_valuesXnameX
  "g.V(v1Id).coalesce(out('created'), out('knows')).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/coalesce (q/__ (q/out :created)) (q/__ (q/out :knows)))
                (q/values :name)))

(defn get_g_V_coalesceXoutXlikesX_outXknowsX_inXcreatedXX_groupCount_byXnameX
  "g.V().coalesce(out('likes'), out('knows'), out('created')).groupCount().by('name')"
  [g]
  (q/traverse g (q/V)
                (q/coalesce (q/__ (q/out :likes)) (q/__ (q/out :knows)) (q/__ (q/out :created)))
                (q/group-count)
                (q/by :name)))

(defn get_g_V_coalesceXoutEXknowsX_outEXcreatedXX_otherV_path_byXnameX_byXlabelX
  "g.V().coalesce(outE('knows'), outE('created')).otherV().path().by('name').by(T.label)"
  [g]
  (q/traverse g (q/V)
                (q/coalesce (q/__ (q/outE :knows)) (q/__ (q/outE :created)))
                (q/otherV)
                (q/path)
                (q/by :name)
                (q/by T/label)))
