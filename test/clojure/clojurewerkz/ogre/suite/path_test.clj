(ns clojurewerkz.ogre.suite.path-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX1X_name_path
  "g.V(v1Id).values('name').path()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/values :name)
                (q/path)))

(defn get_g_V_repeatXoutX_timesX2X_path_by_byXnameX_byXlangX
  "g.V().repeat(out()).times(2).path().by().by('name').by('lang')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 2)
                (q/path)
                (q/by)
                (q/by :name)
                (q/by :lang)))

(defn get_g_V_out_out_path_byXnameX_byXageX
  "g.V().out().out().path().by('name').by('age')"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/out)
                (q/path)
                (q/by :name)
                (q/by :age)))

(defn get_g_V_asXaX_hasXname_markoX_asXbX_hasXage_29X_asXcX_path
  "g.V().as('a').has('name', 'marko').as('b').has('age', 29).as('c').path()"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/has :name "marko")
                (q/as :b)
                (q/has :age (int 29))
                (q/as :c)
                (q/path)))

(defn get_g_VX1X_out_path_byXageX_byXnameX
  "g.V(v1Id).out().path().by('age').by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/path)
                (q/by :age)
                (q/by :name)))

(defn get_g_VX1X_outEXcreatedX_inV_inE_outV_path
  "g.V(v1Id).outE('created').inV().inE().outV().path()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
               (q/outE :created)
               (q/inV)
               (q/inE)
               (q/outV)
               (q/path)))
