(ns clojurewerkz.ogre.suite.range-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Pop)))

(defn get_g_VX1X_out_limitX2X
  "g.V(v1Id).out().limit(2)"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (limit 2)))

(defn get_g_V_localXoutE_limitX1X_inVX_limitX3X
  "g.V().local(outE().limit(1)).inV().limit(3)"
  [g]
  (traverse g (V)
              (local (__ (outE) (limit 2)))
              (inV)
              (limit 3)))

(defn get_g_VX1X_outXknowsX_outEXcreatedX_rangeX0_1X_inV
  "g.V(v1Id).out('knows').outE('created').range(0, 1).inV()"
  [g v1Id]
  (traverse g (V v1Id)
              (out :knows)
              (outE :created)
              (range 0, 1)
              (inV)))

(defn get_g_VX1X_outXknowsX_outXcreatedX_rangeX0_1X
  "g.V(v1Id).out('knows').out('created').range(0, 1)"
  [g v1Id]
  (traverse g (V v1Id)
              (out :knows)
              (out :created)
              (range 0, 1)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_rangeX1_3X
  "g.V(v1Id).out('created').in('created').range(1, 3"
  [g v1Id]
  (traverse g (V v1Id)
              (out :created)
              (in :created)
              (range 1 3)))

(defn get_g_VX1X_outXcreatedX_inEXcreatedX_rangeX1_3X_outV
  "g.V(v1Id).out('created').inE('created').range(1, 3).outV()"
  [g v1Id]
  (traverse g (V v1Id)
              (out :created)
              (inE :created)
              (range 1 3)
              (outV)))

(defn get_g_V_repeatXbothX_timesX3X_rangeX5_11X
  "g.V().repeat(both()).times(3).range(5, 11)"
  [g]
  (traverse g (V)
              (repeat (__ (both)))
                (times 3)
              (range 5 11)))

(defn get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_2X
  "g.V().as('a').in().as('a').in().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).limit(local, 2)"
  [g]
  (traverse g (V) (as :a)
              (in) (as :a)
              (in) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (limit (scope :local) 2)))

(defn get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_1X
  "g.V().as('a').in().as('a').in().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).limit(local, 1)"
  [g]
  (traverse g (V) (as :a)
              (in) (as :a)
              (in) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (limit (scope :local) 1)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_3X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).range(local, 1, 3)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (range (scope :local) 1 3)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_2X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).range(local, 1, 2)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (range (scope :local) 1 2)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_4_5X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).range(local, 4, 5)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :a)
              (out) (as :a)
              (select Pop/mixed :a)
                (by (__ (unfold) (values :name) (fold)))
              (range (scope :local) 4 5)))

(defn get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_2X
  "g.V().as('a').in().as('b').in().as('c').<Map<String, String>>select('a','b','c').by('name').limit(local, 2)"
  [g]
  (traverse g (V) (as :a)
              (in) (as :b)
              (in) (as :c)
              (select :a :b :c)
                (by :name)
              (limit (scope :local) 2)))

(defn get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_1X
  "g.V().as('a').in().as('b').in().as('c').<Map<String, String>>select('a','b','c').by('name').limit(local, 1)"
  [g]
  (traverse g (V) (as :a)
              (in) (as :b)
              (in) (as :c)
              (select :a :b :c)
                (by :name)
              (limit (scope :local) 1)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_3X
  "g.V().as('a').out().as('b').out().as('c').<Map<String, String>>select('a','b','c').by('name').range(local, 1, 3)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (select :a :b :c)
                (by :name)
              (range (scope :local) 1 3)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_2X
  "g.V().as('a').out().as('b').out().as('c').<Map<String, String>>select('a','b','c').by('name').range(local, 1, 2)"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (select :a :b :c)
                (by :name)
              (range (scope :local) 1 2)))

(defn get_g_V_outE_valuesXweightX_fold_orderXlocalX_skipXlocal_2X
  "g.V().outE().values('weight').fold().order(local).skip(local, 2)"
  [g]
  (traverse g (V)
              (outE) (values :weight)
              (fold)
              (order (scope :local))
              (skip (scope :local) 2)))

(defn get_g_V_hasLabelXpersonX_order_byXageX_skipX1X_valuesXnameX
  "g.V().hasLabel('person').order().by('age').skip(1).values('name')"
  [g]
  (traverse g (V)
              (has-label :person)
              (order)
                (by :age)
              (skip 1)
              (values :name)))

(defn get_g_V_hasLabelXpersonX_order_byXageX_valuesXnameX_skipX1X
  "g.V().hasLabel('person').order().by('age').<String>values('name').skip(1)"
  [g]
  (traverse g (V)
              (has-label :person)
              (order)
                (by :age)
              (values :name)
              (skip 1)))