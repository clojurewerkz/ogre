(ns clojurewerkz.ogre.suite.path-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_VX1X_name_path
  "g.V(v1Id).values('name').path()"
  [g v1Id]
  (traverse g (V v1Id)
              (values :name)
              (path)))

(defn get_g_V_repeatXoutX_timesX2X_path_by_byXnameX_byXlangX
  "g.V().repeat(out()).times(2).path().by().by('name').by('lang')"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 2)
              (path)
                (by)
                (by :name)
                (by :lang)))

(defn get_g_V_out_out_path_byXnameX_byXageX
  "g.V().out().out().path().by('name').by('age')"
  [g]
  (traverse g (V)
              (out)
              (out)
              (path)
                (by :name)
                (by :age)))

(defn get_g_V_asXaX_hasXname_markoX_asXbX_hasXage_29X_asXcX_path
  "g.V().as('a').has('name', 'marko').as('b').has('age', 29).as('c').path()"
  [g]
  (traverse g (V) (as :a)
              (has :name "marko") (as :b)
              (has :age (int 29)) (as :c)
              (path)))

(defn get_g_VX1X_out_path_byXageX_byXnameX
  "g.V(v1Id).out().path().by('age').by('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (path)
                (by :age)
                (by :name)))

(defn get_g_VX1X_outEXcreatedX_inV_inE_outV_path
  "g.V(v1Id).outE('created').inV().inE().outV().path()"
  [g v1Id]
  (traverse g (V v1Id)
             (outE :created)
             (inV)
             (inE)
             (outV)
             (path)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_path_fromXbX_toXcX_byXnameX
  "g.V().as('a').out().as('b').out().as('c').path().from('b').to('c').by('name')"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (path)
                (from :b)
                (to :c)
                (by :name)))
