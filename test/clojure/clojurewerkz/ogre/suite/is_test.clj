(ns clojurewerkz.ogre.suite.is-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_valuesXageX_isX32X
  "g.V().values('age').is(32)"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/is 32)))

(defn get_g_V_valuesXageX_isXlte_30X
  "g.V().values('age').is(P.lte(30))"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/is (P/lte 30))))

(defn get_g_V_valuesXageX_isXgte_29X_isXlt_34X
  "g.V().values('age').is(P.gte(29)).is(P.lt(34))"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/is (P/gte 29))
                (q/is (P/lt 34))))

(defn get_g_V_whereXinXcreatedX_count_isX1XX_valuesXnameX
  "g.V().where(in('created').count().is(1)).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/where (q/__ (q/in :created) (q/count) (q/is 1)))
                (q/values :name)))

(defn get_g_V_whereXinXcreatedX_count_isXgte_2XX_valuesXnameX
  "g.V().where(in('created').count().is(P.gte(2l))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/where (q/__ (q/in :created) (q/count) (q/is (P/gte (long 2)))))
                (q/values :name)))
