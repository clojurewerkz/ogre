(ns clojurewerkz.ogre.suite.is-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_valuesXageX_isX32X
  "g.V().values('age').is(32)"
  [g]
  (traverse g (V)
              (values :age)
              (is 32)))

(defn get_g_V_valuesXageX_isXlte_30X
  "g.V().values('age').is(P.lte(30))"
  [g]
  (traverse g (V)
              (values :age)
              (is (P/lte 30))))

(defn get_g_V_valuesXageX_isXgte_29X_isXlt_34X
  "g.V().values('age').is(P.gte(29)).is(P.lt(34))"
  [g]
  (traverse g (V)
              (values :age)
              (is (P/gte 29))
              (is (P/lt 34))))

(defn get_g_V_whereXinXcreatedX_count_isX1XX_valuesXnameX
  "g.V().where(in('created').count().is(1)).values('name')"
  [g]
  (traverse g (V)
              (where (__ (in :created) (count) (is 1)))
              (values :name)))

(defn get_g_V_whereXinXcreatedX_count_isXgte_2XX_valuesXnameX
  "g.V().where(in('created').count().is(P.gte(2l))).values('name')"
  [g]
  (traverse g (V)
              (where (__ (in :created) (count) (is (P/gte (long 2)))))
              (values :name)))
