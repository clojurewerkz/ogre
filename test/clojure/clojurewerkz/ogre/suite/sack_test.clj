(ns clojurewerkz.ogre.suite.sack-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)))

(defn get_g_withSackX1_sumX_VX1X_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack
  "g.withSack(1.0d, Operator.sum).V(v1Id).local(out('knows').barrier(SackFunctions.Barrier.normSack)).in('knows').barrier().sack()"
  [g v1Id]
  (traverse g (with-sack 1.0 {:merge (operator :sum)})
              (V v1Id)
              (local (__ (out :knows) (barrier (sack-barrier :normSack))))
              (in :knows)
              (barrier)
              (sack)))

(defn get_g_withBulkXfalseX_withSackX1_sumX_V_out_barrier_sack
  "g.withBulk(false).withSack(1, Operator.sum).V().out().barrier().sack()"
  [g]
  (traverse g (with-bulk false)
              (with-sack (int 1) {:merge (operator :sum)})
              (V)
              (out)
              (barrier)
              (sack)))

(defn get_g_withSackXhelloX_V_outE_sackXassignX_byXlabelX_inV_sack
  "g.withSack('hello').V().outE().sack(Operator.assign).by(T.label).inV().sack()"
  [g]
  (traverse g (with-sack "hello")
              (V)
              (outE)
              (sack (operator :assign))
              (by (T/label))
              (inV)
              (sack)))

(defn get_g_withSackX0X_V_outE_sackXsumX_byXweightX_inV_sack_sum
  "g.withSack(0.0f).V().outE().sack(Operator.sum).by('weight').inV().sack().sum()"
  [g]
  (traverse g (with-sack (float 0.0))
              (V)
              (outE)
              (sack (operator :sum))
              (by :weight)
              (inV)
              (sack)
              (sum)))

(defn get_g_withSackX0X_V_repeatXoutE_sackXsumX_byXweightX_inVX_timesX2X_sack
  "g.withSack(0.0f).V().repeat(outE().sack(Operator.sum).by('weight').inV()).times(2).sack()"
  [g]
  (traverse g (with-sack (float 0.0))
              (V)
              (repeat (__ (outE) (sack (operator :sum)) (by :weight) (inV)))
                (times 2)
              (sack)))

(defn get_g_withSackXmap__map_cloneX_V_out_out_sackXmap_a_nameX_sack
  "g.withSack(HashMap::new, m -> (HashMap) m.clone()).V().out().out().sack((map, vertex) -> {
                map.put('a', vertex.value('name'));
                return map;
            }).sack()"
  [g]
  (traverse g (with-sack (new java.util.HashMap) {:split (fn [m] (.clone ^java.util.HashMap m))})
              (V)
              (out)
              (out)
              (sack (fn [m v]
                        (.put ^java.util.Map m "a" (.value ^Vertex v "name"))
                        m))
              (sack)))

(defn get_g_withSackXBigInteger_TEN_powX1000X_assignX_V_localXoutXknowsX_barrierXnormSackXX_inXknowsX_barrier_sack
  "g.withSack(BigInteger.TEN.pow(1000), Operator.assign).V().local(out('knows').barrier(SackFunctions.Barrier.normSack)).in('knows').barrier().sack()"
  [g]
  (traverse g (with-sack (.pow (java.math.BigInteger/TEN) 1000) {:merge (operator :assign)})
              (V)
              (local (__ (out :knows) (barrier (sack-barrier :normSack))))
              (in :knows)
              (barrier)
              (sack)))

(defn get_g_withBulkXfalseX_withSackX1_sumX_VX1X_localXoutEXknowsX_barrierXnormSackX_inVX_inXknowsX_barrier_sack
  "g.withBulk(false).withSack(1.0d, sum).V(v1Id).local(outE('knows').barrier(normSack).inV()).in('knows').barrier().sack()"
  [g v1Id]
  (traverse g (with-bulk false)
              (with-sack 1.0 {:merge (operator :sum)})
              (V v1Id)
              (local (__ (outE :knows) (barrier (sack-barrier :normSack)) (inV)))
              (in :knows)
              (barrier)
              (sack)))

(defn get_g_withSackX2X_V_sackXdivX_byXconstantX3_0XX_sack
  "g.withSack(2).V().sack(Operator.div).by(constant(3.0)).sack()"
  [g]
  (traverse g (with-sack 2)
              (V)
              (sack (operator :div))
                (by (__ (constant 3.0)))
              (sack)))