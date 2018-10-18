(ns clojurewerkz.ogre.suite.count-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat  sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_out_count
  "g.V().out().count()"
  [g]
  (traverse g (V)
              (out)
              (count)))

(defn get_g_V_count
  "g.V().count()"
  [g]
  (traverse g (V)
              (count)))

(defn get_g_V_both_both_count
  "g.V().both().both().count()"
  [g]
  (traverse g (V)
              (both)
              (both)
              (count)))

(defn get_g_V_repeatXoutX_timesX3X_count
  "g.V().repeat(out()).times(3).count()"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 3)
              (count)))

(defn get_g_V_repeatXoutX_timesX8X_count
  "g.V().repeat(out()).times(8).count()"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 8)
              (count)))

(defn get_g_V_repeatXoutX_timesX5X_asXaX_outXwrittenByX_asXbX_selectXa_bX_count
  "g.V().repeat(out()).times(5).as('a').out('writtenBy').as('b').select('a', 'b').count()"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 5) (as :a)
              (out :writtenBy) (as :b)
              (select :a :b)
              (count)))

(defn get_g_V_hasXnoX_count
  "g.V().has('no').count()"
  [g]
  (traverse g (V)
              (has :no)
              (count)))

(defn get_g_V_fold_countXlocalX
  "g.V().fold().count(Scope.local)"
  [g]
  (traverse g (V)
              (fold)
              (count (scope :local))))

 (defn get_g_V_whereXinXknowsX_outXcreatedX_count_is_0XX_name
   "g.V().where(in('knows').out('created').count().is(0)).values('name')"
   [g]
   (traverse g (V)
               (where (__ (in :knows) (out :created) (count) (is 0)))
               (values :name)))
