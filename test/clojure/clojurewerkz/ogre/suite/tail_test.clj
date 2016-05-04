(ns clojurewerkz.ogre.suite.tail-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Scope)))

(defn get_g_V_valuesXnameX_order_tailX2X
  "g.V().values('name').order().tail(2)"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail (long 2))))

(defn get_g_V_valuesXnameX_order_tail
  "g.V().values('name').order().tail()"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail)))

(defn get_g_V_valuesXnameX_order_tailX7X
  "g.V().values('name').order().tail(7)"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail (long 7))))

(defn get_g_V_repeatXbothX_timesX3X_tailX7X
  "g.V().repeat(both()).times(3).tail(7)"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both)))
                (q/times 3)
                (q/tail (long 7))))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXaX_byXunfold_valuesXnameX_foldX_tailXlocal_2X
  "g.V().as('a').out().as('a').out().as('a').select('a').by(unfold().values('name').fold()).tail(local, 2)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :a)
                (q/out) (q/as :a)
                (q/select :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/tail (Scope/local) (long 2))))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXaX_byXunfold_valuesXnameX_foldX_tailXlocal_1X
  "g.V().as('a').out().as('a').out().as('a').select('a').by(unfold().values('name').fold()).tail(local, 1)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :a)
                (q/out) (q/as :a)
                (q/select :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/tail (Scope/local) (long 1))))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXaX_byXunfold_valuesXnameX_foldX_tailXlocalX
  "g.V().as('a').out().as('a').out().as('a').select('a').by(unfold().values('name').fold()).tail(local)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :a)
                (q/out) (q/as :a)
                (q/select :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/tail (Scope/local))))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXaX_byXlimitXlocal_0XX_tailXlocal_1X
  "g.V().as('a').out().as('a').out().as('a').select('a').by(limit(local, 0)).tail(local, 1)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :a)
                (q/out) (q/as :a)
                (q/select :a)
                (q/by (q/__ (q/limit (Scope/local) 0)))
                (q/tail (Scope/local) (long 1))))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_2X
  "g.V().as('a').out().as('b').out().as('c').select('a','b','c').by('name').tail(local, 2)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/out) (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/tail (Scope/local) (long 2))))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_1X
  "g.V().as('a').out().as('b').out().as('c').select('a','b','c').by('name').tail(local, 1)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/out) (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/tail (Scope/local) (long 1))))

(defn get_g_V_valuesXnameX_order_tailXglobal_2X
  "g.V().values('name').order().tail(global, 2)"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail (Scope/global) (long 2))))

