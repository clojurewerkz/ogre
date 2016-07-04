(ns clojurewerkz.ogre.suite.count-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Scope)))

(defn get_g_V_out_count
  "g.V().out().count()"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/count)))

(defn get_g_V_count
  "g.V().count()"
  [g]
  (q/traverse g (q/V)
                (q/count)))

(defn get_g_V_both_both_count
  "g.V().both().both().count()"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/both)
                (q/count)))

(defn get_g_V_repeatXoutX_timesX3X_count
  "g.V().repeat(out()).times(3).count()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 3)
                (q/count)))

(defn get_g_V_repeatXoutX_timesX8X_count
  "g.V().repeat(out()).times(8).count()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 8)
                (q/count)))

(defn get_g_V_repeatXoutX_timesX5X_asXaX_outXwrittenByX_asXbX_selectXa_bX_count
  "g.V().repeat(out()).times(5).as('a').out('writtenBy').as('b').select('a', 'b').count()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 5) (q/as :a)
                (q/out :writtenBy) (q/as :b)
                (q/select :a :b)
                (q/count)))

(defn get_g_V_hasXnoX_count
  "g.V().has('no').count()"
  [g]
  (q/traverse g (q/V)
                (q/has :no)
                (q/count)))

(defn get_g_V_fold_countXlocalX
  "g.V().fold().count(Scope.local)"
  [g]
  (q/traverse g (q/V)
                (q/fold)
                (q/count (q/scope :local))))
