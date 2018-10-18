(ns clojurewerkz.ogre.suite.group-count-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXnoX_groupCount
  "g.V().has('no').groupCount()"
  [g]
  (traverse g (V)
              (has :no)
              (group-count)))

(defn get_g_V_outXcreatedX_groupCount_byXnameX
  "g.V().out('created').groupCount().by('name')"
  [g]
  (traverse g (V)
              (out :created)
              (group-count)
                (by :name)))

(defn get_g_V_outXcreatedX_groupCountXaX_byXnameX_capXaX
  "g.V().out('created').groupCount('a').by('name').cap('a')"
  [g]
  (traverse g (V)
              (out :created)
              (group-count :a)
                (by :name)
              (cap :a)))

(defn get_g_V_outXcreatedX_name_groupCount
  "g.V().out('created').values('name').groupCount()"
  [g]
  (traverse g (V)
              (out :created)
              (values :name)
              (group-count)))

(defn get_g_V_outXcreatedX_groupCountXxX_capXxX
  "g.V().out('created').groupCount('x').cap('x')"
  [g]
  (traverse g (V)
              (out :created)
              (group-count :x)
              (cap :x)))

(defn get_g_V_outXcreatedX_name_groupCountXaX_capXaX
  "g.V().out('created').values('name').groupCount('a').cap('a')"
  [g]
  (traverse g (V)
              (out :created)
              (values :name)
              (group-count :a)
              (cap :a)))

(defn get_g_V_hasXnoX_groupCountXaX_capXaX
  "g.V().has('no').groupCount('a').cap('a')"
  [g]
  (traverse g (V)
              (has :no)
              (group-count :a)
              (cap :a)))

(defn get_g_V_repeatXout_groupCountXaX_byXnameXX_timesX2X_capXaX
  "g.V().repeat(out().groupCount('a').by('name')).times(2).cap('a')"
  [g]
  (traverse g (V)
              (repeat (__ (out) (group-count :a) (by :name)))
                (times 2)
              (cap :a)))

(defn get_g_V_unionXrepeatXoutX_timesX2X_groupCountXmX_byXlangXX__repeatXinX_timesX2X_groupCountXmX_byXnameXX_capXmX
  "g.V().union(repeat(out()).times(2).groupCount('m').by('lang'),
               repeat(in()).times(2).groupCount('m').by('name')).cap('m')"
  [g]
  (traverse g (V)
              (union (__ (repeat (__ (out))) (times 2) (group-count :m) (by :lang))
                     (__ (repeat (__ (in))) (times 2) (group-count :m) (by :name)))
              (cap :m)))

(defn get_g_V_groupCount_byXbothE_countX
  "g.V().groupCount().by(bothE().count())"
  [g]
  (traverse g (V)
              (group-count)
                (by (__ (bothE) (count)))))

(defn get_g_V_unionXoutXknowsX__outXcreatedX_inXcreatedXX_groupCount_selectXvaluesX_unfold_sum
  "g.V().union(out('knows'), out('created').in('created')).groupCount().select(Column.values).unfold().sum()"
  [g]
  (traverse g (V)
              (union (__ (out :knows))
                     (__ (out :created) (in :created)))
              (group-count)
              (select (column :values))
              (unfold)
              (sum)))

(defn get_g_V_both_groupCountXaX_out_capXaX_selectXkeysX_unfold_both_groupCountXaX_capXaX
  "g.V().both().groupCount('a').out().cap('a').select(Column.keys).unfold().both().groupCount('a').cap('a')"
  [g]
  (traverse g (V)
              (both)
              (group-count :a)
              (out)
              (cap :a)
              (select (column :keys))
              (unfold)
              (both)
              (group-count :a)
              (cap :a)))

(defn get_g_V_both_groupCountXaX_byXlabelX_asXbX_barrier_whereXselectXaX_selectXsoftwareX_isXgtX2XXX_selectXbX_name
  "g.V().both().groupCount('a').by(T.label).as('b').barrier().
         where(__.select('a').select('software').is(gt(2))).select('b').values('name')"
  [g]
  (traverse g (V)
              (both)
              (group-count :a)
                (by (T/label)) (as :b)
              (barrier)
              (where (__ (select :a) (select :software) (is (P/gt 2))))
              (select :b)
              (values :name)))
