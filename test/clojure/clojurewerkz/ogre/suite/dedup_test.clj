(ns clojurewerkz.ogre.suite.dedup-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T Property)))

(defn get_g_V_both_both_dedup_byXoutE_countX_name
  "g.V().both().both().dedup().by(__.outE().count()).values('name')"
  [g]
  (traverse g (V)
              (both)
              (both)
              (dedup)
                (by (__ (outE) (count)))
              (values :name)))

(defn get_g_V_both_dedup_name
  "g.V().both().dedup().values('name')"
  [g]
  (traverse g (V)
              (both)
              (dedup)
              (values :name)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_dedupXa_bX_path
  "g.V().as('a').out('created').as('b').in('created').as('c').dedup('a', 'b').path()"
  [g]
  (traverse g (V) (as :a)
              (out :created) (as :b)
              (in :created) (as :c)
              (dedup :a :b)
              (path)))

(defn get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name
  "g.V().both().has(T.label, 'software').dedup().by('lang').values('name')"
  [g]
  (traverse g (V)
              (both)
              (has T/label "software")
              (dedup)
                (by "lang")
              (values :name)))

(defn get_g_V_both_name_order_byXa_bX_dedup_value
  "g.V().both().properties('name').order().by((a, b) -> a.value().compareTo(b.value())).dedup().value()"
  [g]
  (traverse g (V)
              (both)
              (properties :name)
              (order)
                (by #(compare (.value ^Property %1) (.value ^Property %2)))
              (dedup)
              (value)))

(defn get_g_V_both_both_name_dedup
  "g.V().both().both().values('name').dedup()"
  [g]
  (traverse g (V)
              (both)
              (both)
              (values :name)
              (dedup)))

(defn get_g_V_both_both_dedup
  "g.V().both().both().dedup()"
  [g]
  (traverse g (V)
              (both)
              (both)
              (dedup)))

(defn get_g_V_both_both_dedup_byXlabelX
  "g.V().both().both().dedup().by(T.label)"
  [g]
  (traverse g (V)
              (both)
              (both)
              (dedup)
              (by T/label)))

(defn get_g_V_group_byXlabelX_byXbothE_weight_dedup_foldX
  "g.V().group().by(T.label).by(bothE().values('weight').dedup().fold())"
  [g]
  (traverse g (V)
              (group)
                (by T/label)
                (by (__ (bothE) (values :weight) (dedup) (fold)))))

(defn get_g_V_asXaX_both_asXbX_dedupXa_bX_byXlabelX_selectXa_bX
  "g.V().as('a').both().as('b').dedup('a', 'b').by(T.label).select('a', 'b')"
  [g]
  (traverse g (V) (as :a)
              (both) (as :b)
              (dedup :a :b)
                (by T/label)
              (select :a :b)))

(defn get_g_V_outE_asXeX_inV_asXvX_selectXeX_order_byXweight_ascX_selectXvX_valuesXnameX_dedup
  "g.V().outE().as('e').inV().as('v').select('e').order().by('weight', Order.asc).select('v').values('name').dedup()"
  [g]
  (traverse g (V)
              (outE) (as :e)
              (inV)
              (as :v) (select :e)
              (order)
                (by :weight (sort :asc))
              (select :v)
              (values :name)
              (dedup)))

(defn get_g_V_groupCount_selectXvaluesX_unfold_dedup
  "g.V().groupCount().select(values).<Long>unfold().dedup()"
  [g]
  (traverse g (V)
              (group-count)
              (select (column :values))
              (unfold)
              (dedup)))

(defn get_g_V_out_asXxX_in_asXyX_selectXx_yX_byXnameX_fold_dedupXlocal_x_yX_unfold
  "g.V().out().as('x').in().as('y').select('x', 'y').by('name').fold().dedup(Scope.local, 'x', 'y').unfold()"
  [g]
  (traverse g (V)
              (out) (as :x)
              (in) (as :y)
              (select :x :y)
                (by :name)
              (fold)
              (dedup (scope :local) :x :y)
              (unfold)))

(defn get_g_V_out_in_valuesXnameX_fold_dedupXlocalX_unfold
  "g.V().out().in().values('name').fold().dedup(Scope.local).unfold()"
  [g]
  (traverse g (V)
              (out)
              (in)
              (values :name)
              (fold)
              (dedup (scope :local))
              (unfold)))

(defn get_g_V_repeatXdedupX_timesX2X_count
  "g.V().repeat(dedup()).times(2).count()"
  [g]
  (traverse g (V)
              (repeat (__ (dedup)))
                (times 2)
              (count)))

(defn get_g_V_asXaX_repeatXbothX_timesX3X_emit_name_asXbX_group_byXselectXaXX_byXselectXbX_dedup_order_foldX_selectXvaluesX_unfold_dedup
  "g.V().as('a').repeat(both()).times(3).emit().values('name').as('b').group().by(select('a')).by(select('b').dedup().order().fold()).select(values).unfold().dedup()"
  [g]
  (traverse g (V) (as :a)
              (repeat (__ (both)))
                (times 3)
                (emit)
              (values :name) (as :b)
              (group)
                (by (__ (select :a)))
                (by (__ (select :b) (dedup) (order) (fold)))
              (select (column :values))
              (unfold)
              (dedup)))
