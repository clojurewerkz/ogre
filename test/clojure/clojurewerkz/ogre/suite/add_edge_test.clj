(ns clojurewerkz.ogre.suite.add-edge-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X
  "g.V(v1Id).as('a').out('created').addE('createdBy').to('a').property('weight', 2.0d)"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created)
              (add-E :createdBy)
                (to :a)
              (property :weight 2.0)))

(defn get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX
  "g.V(v1Id).as('a').out('created').addE('createdBy').to('a')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created)
              (add-E :createdBy)
                (to :a)))

(defn get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX
  "g.V().aggregate('x').as('a').select('x').unfold().addE('existsWith').to('a').property('time', 'now')"
  [g]
  (traverse g (V)
              (aggregate :x) (as :a)
              (select :x)
              (unfold)
              (add-E :existsWith)
                (to :a)
              (property :time "now")))

(defn get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X
  "g.V().as('a').out('created').in('created').where(P.neq('a')).as('b').addE('co-developer').from('a').to('b').property('year', 2009)"
  [g]
  (traverse g (V) (as :a)
              (out :created)
              (in :created)
              (where (P/neq "a")) (as :b)
              (add-E :codeveloper)
                (from :a)
                (to :b)
              (property :year (int 2009))))

(defn get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX
  "g.V().as('a').in('created').addE('createdBy').from('a').property('year', 2009).property('acl', 'public')"
  [g]
  (traverse g (V) (as :a)
              (in :created)
              (add-E :createdBy)
                (from :a)
              (property :year (int 2009))
              (property :acl "public")))

(defn get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX
  "g.addV().as('first').repeat(__.addE('next').to(__.addV()).inV()).times(5).addE('next').to(select('first'))"
  [g]
  (traverse g (add-V) (as :first)
              (repeat (__ (add-E :next) (to (__ (add-V))) (inV)))
                (times 5)
              (add-E :next) (to (__ (select :first)))))


(defn get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X
  "g.withSideEffect('b', b).V(a).addE('knows').to('b').property('weight', 0.5d)"
  [g a b]
    (traverse g (with-side-effect :b b) (V a) (add-E :knows) (to :b) (property :weight 0.5)))

(defn get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X
  "g.addE('knows').from(a).to(b).property('weight', 0.1d)"
  [g a b]
    (traverse g
                (add-E :knows)
                (from a)
                (to b)
                (property :weight 0.1)))

(defn get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X
  "g.V(a).addE('knows').to(b).property('weight', 0.1d)"
  [g a b]
    (traverse g
                (V a)
                (add-E :knows)
                  (to b)
                (property :weight 0.1)))

(defn get_g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_decrX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX
  "g.addE(V().outE().label().groupCount().order(local).by(values, decr).select(keys).unfold().limit(1)).
       from(V().has('name', 'vadas')).
       to(V().has('name', 'lop'))"
  [g]
  (traverse g
              (add-E (__ (V) (outE) (label) (group-count) (order (scope :local)) (by (column :values) (sort :decr)) (select (column :keys)) (unfold) (limit 1)))
                (from (__ (V) (has :name "vadas")))
                (to (__ (V) (has :name "lop")))))

(defn get_g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX
  "g.V().has('name', 'marko').as('a').outE('created').as('b').inV().addE(select('b').label()).to('a')"
  [g]
  (traverse g
              (V)
              (has :name "marko") (as :a)
              (outE :created) (as :b)
              (inV)
              (add-E (__ (select :b) (label)))
                (to :a)))
