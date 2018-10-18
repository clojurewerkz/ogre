(ns clojurewerkz.ogre.suite.tail-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Pop)))

(defn get_g_V_valuesXnameX_order_tailX2X
  "g.V().values('name').order().tail(2)"
  [g]
  (traverse g (V)
              (values :name)
              (order)
              (tail (long 2))))

(defn get_g_V_valuesXnameX_order_tail
  "g.V().values('name').order().tail()"
  [g]
  (traverse g (V)
              (values :name)
              (order)
              (tail)))

(defn get_g_V_valuesXnameX_order_tailX7X
  "g.V().values('name').order().tail(7)"
  [g]
  (traverse g (V)
              (values :name)
              (order)
              (tail (long 7))))

(defn get_g_V_repeatXbothX_timesX3X_tailX7X
  "g.V().repeat(both()).times(3).tail(7)"
  [g]
  (traverse g (V)
              (repeat (__ (both)))
              (times 3)
              (tail (long 7))))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_2X
  "g.V().as('a').out().as('b').out().as('c').select('a','b','c').by('name').tail(local, 2)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (select :a :b :c)
                (by :name)
              (tail (scope :local) (long 2))))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_1X
  "g.V().as('a').out().as('b').out().as('c').select('a','b','c').by('name').tail(local, 1)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (select :a :b :c)
                (by :name)
              (tail (scope :local) (long 1))))

(defn get_g_V_valuesXnameX_order_tailXglobal_2X
  "g.V().values('name').order().tail(global, 2)"
  [g]
  (traverse g (V)
              (values :name)
              (order)
              (tail (scope :global) (long 2))))

(defn get_g_V_repeatXin_outX_timesX3X_tailX7X_count
  "g.V().repeat(in().out()).times(3).tail(7).count()"
  [g]
  (traverse g (V)
              (repeat (__ (in) (out)))
                (times 3)
              (tail 7)
              (count)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXlimitXlocal_0XX_tailXlocal_1X
  "g.V().as('a').out().as('a').out().as('a').<String>select(Pop.mixed, 'a').by(limit(local, 0)).tail(local, 1);"
  [g]
  (traverse g
              (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
              (by (__ (limit (scope :local) 0)))
              (tail (scope :local) 1)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocalX
  "g.V().as('a').out().as('a').out().as('a').<String>select(Pop.mixed, 'a').by(unfold().values('name').fold()).tail(local);"
  [g]
  (traverse g
              (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (tail (scope :local))))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_1X
  "g.V().as('a').out().as('a').out().as('a').<String>select(Pop.mixed, 'a').by(unfold().values('name').fold()).tail(local, 1);"
  [g]
  (traverse g
              (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (tail (scope :local) 1)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_2X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).tail(local, 2);"
  [g]
  (traverse g
              (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (tail (scope :local) 2)))
