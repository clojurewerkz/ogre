(ns clojurewerkz.ogre.suite.group-count-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXnoX_groupCount
  "g.V().has('no').groupCount()"
  [g]
  (q/traverse g (q/V)
                (q/has :no)
                (q/group-count)))

(defn get_g_V_outXcreatedX_groupCount_byXnameX
  "g.V().out('created').groupCount().by('name')"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/group-count)
                (q/by :name)))

(defn get_g_V_outXcreatedX_groupCountXaX_byXnameX_capXaX
  "g.V().out('created').groupCount('a').by('name').cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/group-count :a)
                (q/by :name)
                (q/cap :a)))

(defn get_g_V_outXcreatedX_name_groupCount
  "g.V().out('created').values('name').groupCount()"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/values :name)
                (q/group-count)))

(defn get_g_V_outXcreatedX_groupCountXxX_capXxX
  "g.V().out('created').groupCount('x').cap('x')"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/group-count :x)
                (q/cap :x)))

(defn get_g_V_outXcreatedX_name_groupCountXaX_capXaX
  "g.V().out('created').values('name').groupCount('a').cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/values :name)
                (q/group-count :a)
                (q/cap :a)))

(defn get_g_V_hasXnoX_groupCountXaX_capXaX
  "g.V().has('no').groupCount('a').cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/has :no)
                (q/group-count :a)
                (q/cap :a)))

(defn get_g_V_repeatXout_groupCountXaX_byXnameXX_timesX2X_capXaX
  "g.V().repeat(out().groupCount('a').by('name')).times(2).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out) (q/group-count :a) (q/by :name)))
                (q/times 2)
                (q/cap :a)))

(defn get_g_V_unionXrepeatXoutX_timesX2X_groupCountXmX_byXlangXX__repeatXinX_timesX2X_groupCountXmX_byXnameXX_capXmX
  "g.V().union(repeat(out()).times(2).groupCount('m').by('lang'),
               repeat(in()).times(2).groupCount('m').by('name')).cap('m')"
  [g]
  (q/traverse g (q/V)
                (q/union (q/__ (q/repeat (q/__ (q/out))) (q/times 2) (q/group-count :m) (q/by :lang))
                         (q/__ (q/repeat (q/__ (q/in))) (q/times 2) (q/group-count :m) (q/by :name)))
                (q/cap :m)))

(defn get_g_V_groupCount_byXbothE_countX
  "g.V().groupCount().by(bothE().count())"
  [g]
  (q/traverse g (q/V)
                (q/group-count)
                (q/by (q/__ (q/bothE) (q/count)))))

(defn get_g_V_unionXoutXknowsX__outXcreatedX_inXcreatedXX_groupCount_selectXvaluesX_unfold_sum
  "g.V().union(out('knows'), out('created').in('created')).groupCount().select(Column.values).unfold().sum()"
  [g]
  (q/traverse g (q/V)
                (q/union (q/__ (q/out :knows))
                         (q/__ (q/out :created) (q/in :created)))
                (q/group-count)
                (q/select (q/column :values))
                (q/unfold)
                (q/sum)))

(defn get_g_V_both_groupCountXaX_out_capXaX_selectXkeysX_unfold_both_groupCountXaX_capXaX
  "g.V().both().groupCount('a').out().cap('a').select(Column.keys).unfold().both().groupCount('a').cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/group-count :a)
                (q/out)
                (q/cap :a)
                (q/select (q/column :keys))
                (q/unfold)
                (q/both)
                (q/group-count :a)
                (q/cap :a)))

(defn get_g_V_both_groupCountXaX_byXlabelX_asXbX_barrier_whereXselectXaX_selectXsoftwareX_isXgtX2XXX_selectXbX_name
  "g.V().both().groupCount('a').by(T.label).as('b').barrier().
         where(__.select('a').select('software').is(gt(2))).select('b').values('name')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/group-count :a)
                (q/by (T/label)) (q/as :b)
                (q/barrier)
                (q/where (q/__ (q/select :a) (q/select :software) (q/is (P/gt 2))))
                (q/select :b)
                (q/values :name)))
