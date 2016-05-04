(ns clojurewerkz.ogre.suite.dedup-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex Property)
           (org.apache.tinkerpop.gremlin.process.traversal P Order)))

(defn get_g_V_both_both_dedup_byXoutE_countX_name
  "g.V().both().both().dedup().by(__.outE().count()).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/both)
                (q/dedup)
                (q/by (q/__ (q/outE) (q/count)))
                (q/values :name)))

(defn get_g_V_both_dedup_name
  "g.V().both().dedup().values('name')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/dedup)
                (q/values :name)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_dedupXa_bX_path
  "g.V().as('a').out('created').as('b').in('created').as('c').dedup('a', 'b').path()"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out :created)
                (q/as :b)
                (q/in :created)
                (q/as :c)
                (q/dedup :a :b)
                (q/path)))

(defn get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name
  "g.V().both().has(T.label, 'software').dedup().by('lang').values('name')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/has T/label "software")
                (q/dedup)
                (q/by "lang")
                (q/values :name)))

(defn get_g_V_both_name_order_byXa_bX_dedup_value
  "g.V().both().properties('name').order().by((a, b) -> a.value().compareTo(b.value())).dedup().value()"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/properties :name)
                (q/order)
                (q/by #(compare (.value ^Property %1) (.value ^Property %2)))
                (q/dedup)
                (q/value)))

(defn get_g_V_both_both_name_dedup
  "g.V().both().both().values('name').dedup()"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/both)
                (q/values :name)
                (q/dedup)))

(defn get_g_V_both_both_dedup
  "g.V().both().both().dedup()"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/both)
                (q/dedup)))

(defn get_g_V_both_both_dedup_byXlabelX
  "g.V().both().both().dedup().by(T.label)"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/both)
                (q/dedup)
                (q/by T/label)))

(defn get_g_V_group_byXlabelX_byXbothE_weight_dedup_foldX
  "g.V().group().by(T.label).by(bothE().values('weight').dedup().fold())"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by T/label)
                (q/by (q/__ (q/bothE) (q/values :weight) (q/dedup) (q/fold)))))

(defn get_g_V_asXaX_both_asXbX_dedupXa_bX_byXlabelX_selectXa_bX
  "g.V().as('a').both().as('b').dedup('a', 'b').by(T.label).select('a', 'b')"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/both)
                (q/as :b)
                (q/dedup :a :b)
                (q/by T/label)
                (q/select :a :b)))

(defn get_g_V_outE_asXeX_inV_asXvX_selectXeX_order_byXweight_incrX_selectXvX_valuesXnameX_dedup
  "g.V().outE().as('e').inV().as('v').select('e').order().by('weight', Order.incr).select('v').values('name').dedup()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/as :e)
                (q/inV)
                (q/as :v)
                (q/select :e)
                (q/order)
                (q/by "weight" Order/incr)
                (q/select :v)
                (q/values :name)
                (q/dedup)))
