(ns clojurewerkz.ogre.suite.sack-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Operator P)))

(defn get_g_withSackX1_sumX_VX1X_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack
  "g.withSack(1.0d, Operator.sum).V(v1Id).local(out('knows').barrier(SackFunctions.Barrier.normSack)).in('knows').barrier().sack()"
  [g v1Id]
  (q/traverse g (q/with-sack 1.0 {::q/merge (Operator/sum)})
                (q/V v1Id)
                (q/local (q/__ (q/out :knows) (q/barrier ::q/norm-sack)))
                (q/in :knows)
                (q/barrier)
                (q/sack)))

(defn get_g_withBulkXfalseX_withSackX1_sumX_V_out_barrier_sack
  "g.withBulk(false).withSack(1, Operator.sum).V().out().barrier().sack()"
  [g]
  (q/traverse g (q/with-bulk false)
                (q/with-sack (int 1) {::q/merge (Operator/sum)})
                (q/V)
                (q/out)
                (q/barrier)
                (q/sack)))

(defn get_g_withSackXhelloX_V_outE_sackXassignX_byXlabelX_inV_sack
  "g.withSack('hello').V().outE().sack(Operator.assign).by(T.label).inV().sack()"
  [g]
  (q/traverse g (q/with-sack "hello")
                (q/V)
                (q/outE)
                (q/sack (Operator/assign))
                (q/by (T/label))
                (q/inV)
                (q/sack)))

(defn get_g_withSackX0X_V_outE_sackXsumX_byXweightX_inV_sack_sum
  "g.withSack(0.0f).V().outE().sack(Operator.sum).by('weight').inV().sack().sum()"
  [g]
  (q/traverse g (q/with-sack (float 0.0))
                (q/V)
                (q/outE)
                (q/sack (Operator/sum))
                (q/by :weight)
                (q/inV)
                (q/sack)
                (q/sum)))

(defn get_g_withSackX0X_V_repeatXoutE_sackXsumX_byXweightX_inVX_timesX2X_sack
  "g.withSack(0.0f).V().repeat(outE().sack(Operator.sum).by('weight').inV()).times(2).sack()"
  [g]
  (q/traverse g (q/with-sack (float 0.0))
                (q/V)
                (q/repeat (q/__ (q/outE) (q/sack (Operator/sum)) (q/by :weight) (q/inV)))
                (q/times 2)
                (q/sack)))

(defn get_g_withSackX0X_V_outE_sackXsum_weightX_inV_sack_sum
  "g.withSack(0.0f).V().outE().sack(Operator.sum, 'weight').inV().sack().sum()"
  [g]
  (q/traverse g (q/with-sack (float 0.0))
                (q/V)
                (q/outE)
                (q/sack (Operator/sum))
                (q/by :weight)
                (q/inV)
                (q/sack)
                (q/sum)))

(defn get_g_withSackX0X_V_repeatXoutE_sackXsum_weightX_inVX_timesX2X_sack
  "g.withSack(0.0f).V().repeat(outE().sack(Operator.sum, 'weight').inV()).times(2).sack()"
  [g]
  (q/traverse g (q/with-sack (float 0.0))
                (q/V)
                (q/repeat (q/__ (q/outE) (q/sack (Operator/sum)) (q/by :weight) (q/inV)))
                (q/times 2)
                (q/sack)))

(defn get_g_withSackXmap__map_cloneX_V_out_out_sackXmap_a_nameX_sack
  "g.withSack(HashMap::new, m -> (HashMap) m.clone()).V().out().out().sack((map, vertex) -> {
                map.put('a', vertex.value('name'));
                return map;
            }).sack()"
  [g]
  (q/traverse g (q/with-sack (new java.util.HashMap) {:split (fn [m] (.clone ^java.util.HashMap m))})
                (q/V)
                (q/out)
                (q/out)
                (q/sack (fn [m v]
                          (.put ^java.util.Map m "a" (.value ^Vertex v "name"))
                          m))
                (q/sack)))

(defn get_g_withSackXBigInteger_TEN_powX1000X_assignX_V_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack
  "g.withSack(BigInteger.TEN.pow(1000), Operator.assign).V().local(out('knows').barrier(SackFunctions.Barrier.normSack)).in('knows').barrier().sack()"
  [g]
  (q/traverse g (q/with-sack (.pow (java.math.BigInteger/TEN) 1000) {::q/merge (Operator/assign)})
                (q/V)
                (q/local (q/__ (q/out :knows) (q/barrier ::q/norm-sack)))
                (q/in :knows)
                (q/barrier)
                (q/sack)))

(defn get_g_withBulkXfalseX_withSackX1_sumX_VX1X_localXoutEXknowsX_barrierXnormSackX_inVX_inXknowsX_barrier_sack
  "g.withBulk(false).withSack(1.0d, sum).V(v1Id).local(outE('knows').barrier(normSack).inV()).in('knows').barrier().sack()"
  [g v1Id]
  (q/traverse g (q/with-bulk false)
                (q/with-sack 1.0 {::q/merge (Operator/sum)})
                (q/V v1Id)
                (q/local (q/__ (q/outE :knows) (q/barrier ::q/norm-sack) (q/inV)))
                (q/in :knows)
                (q/barrier)
                (q/sack)))

(defn get_g_withSackX2X_V_sackXdivX_byXconstantX3_0XX_sack
  "g.withSack(2).V().sack(Operator.div).by(constant(3.0)).sack()"
  [g]
  (q/traverse g (q/with-sack 2)
                (q/V)
                (q/sack Operator/div)
                  (q/by (q/__ (q/constant 3.0)))
                (q/sack)))