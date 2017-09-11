(ns clojurewerkz.ogre.suite.range-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P Scope Pop)))

(defn get_g_VX1X_out_limitX2X
  "g.V(v1Id).out().limit(2)"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/limit 2)))

(defn get_g_V_localXoutE_limitX1X_inVX_limitX3X
  "g.V().local(outE().limit(1)).inV().limit(3)"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/outE) (q/limit 2)))
                (q/inV)
                (q/limit 3)))

(defn get_g_VX1X_outXknowsX_outEXcreatedX_rangeX0_1X_inV
  "g.V(v1Id).out('knows').outE('created').range(0, 1).inV()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :knows)
                (q/outE :created)
                (q/range 0, 1)
                (q/inV)))

(defn get_g_VX1X_outXknowsX_outXcreatedX_rangeX0_1X
  "g.V(v1Id).out('knows').out('created').range(0, 1)"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :knows)
                (q/out :created)
                (q/range 0, 1)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_rangeX1_3X
  "g.V(v1Id).out('created').in('created').range(1, 3"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :created)
                (q/in :created)
                (q/range 1 3)))

(defn get_g_VX1X_outXcreatedX_inEXcreatedX_rangeX1_3X_outV
  "g.V(v1Id).out('created').inE('created').range(1, 3).outV()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :created)
                (q/inE :created)
                (q/range 1 3)
                (q/outV)))

(defn get_g_V_repeatXbothX_timesX3X_rangeX5_11X
  "g.V().repeat(both()).times(3).range(5, 11)"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both)))
                (q/times 3)
                (q/range 5 11)))

(defn get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_2X
  "g.V().as('a').in().as('a').in().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).limit(local, 2)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/in)
                (q/as :a)
                (q/in)
                (q/as :a)
                (q/select Pop/mixed :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/limit (q/scope :local) 2)))

(defn get_g_V_asXaX_in_asXaX_in_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_limitXlocal_1X
  "g.V().as('a').in().as('a').in().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).limit(local, 1)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/in)
                (q/as :a)
                (q/in)
                (q/as :a)
                (q/select Pop/mixed :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/limit (q/scope :local) 1)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_3X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).range(local, 1, 3)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out)
                (q/as :a)
                (q/out)
                (q/as :a)
                (q/select Pop/mixed :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/range (q/scope :local) 1 3)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_1_2X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).range(local, 1, 2)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out)
                (q/as :a)
                (q/out)
                (q/as :a)
                (q/select Pop/mixed :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/range (q/scope :local) 1 2)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_rangeXlocal_4_5X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).range(local, 4, 5)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out)
                (q/as :a)
                (q/out)
                (q/as :a)
                (q/select Pop/mixed :a)
                (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
                (q/range (q/scope :local) 4 5)))

(defn get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_2X
  "g.V().as('a').in().as('b').in().as('c').<Map<String, String>>select('a','b','c').by('name').limit(local, 2)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/in)
                (q/as :b)
                (q/in)
                (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/limit (q/scope :local) 2)))

(defn get_g_V_asXaX_in_asXbX_in_asXcX_selectXa_b_cX_byXnameX_limitXlocal_1X
  "g.V().as('a').in().as('b').in().as('c').<Map<String, String>>select('a','b','c').by('name').limit(local, 1)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/in)
                (q/as :b)
                (q/in)
                (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/limit (q/scope :local) 1)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_3X
  "g.V().as('a').out().as('b').out().as('c').<Map<String, String>>select('a','b','c').by('name').range(local, 1, 3)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out)
                (q/as :b)
                (q/out)
                (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/range (q/scope :local) 1 3)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_rangeXlocal_1_2X
  "g.V().as('a').out().as('b').out().as('c').<Map<String, String>>select('a','b','c').by('name').range(local, 1, 2)"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out)
                (q/as :b)
                (q/out)
                (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/range (q/scope :local) 1 2)))

(defn get_g_V_outE_valuesXweightX_fold_orderXlocalX_skipXlocal_2X
  "g.V().outE().values('weight').fold().order(local).skip(local, 2)"
  [g]
  (q/traverse g
              (q/V)
              (q/outE) (q/values :weight)
              (q/fold)
              (q/order (q/scope :local))
              (q/skip (q/scope :local) 2)))

(defn get_g_V_hasLabelXpersonX_order_byXageX_skipX1X_valuesXnameX
  "g.V().hasLabel('person').order().by('age').skip(1).values('name')"
  [g]
  (q/traverse g
              (q/V)
              (q/has-label :person)
              (q/order)
              (q/by :age)
              (q/skip 1)
              (q/values :name)))

(defn get_g_V_hasLabelXpersonX_order_byXageX_valuesXnameX_skipX1X
  "g.V().hasLabel('person').order().by('age').<String>values('name').skip(1)"
  [g]
  (q/traverse g
              (q/V)
              (q/has-label :person)
              (q/order)
              (q/by :age)
              (q/values :name)
              (q/skip 1)))

