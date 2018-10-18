(ns clojurewerkz.ogre.suite.subgraph-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_withSideEffectXsgX_repeatXbothEXcreatedX_subgraphXsgX_outVX_timesX5X_name_dedup
  "g.withSideEffect('sg', () -> subgraph).V().repeat(bothE('created').subgraph('sg').outV()).times(5).values('name').dedup()"
  [g sg]
  (traverse g (with-side-effect :sg (fn [] sg))
              (V)
              (repeat (__ (bothE :created) (subgraph :sg) (outV)))
                (times 5)
              (values :name)
              (dedup)))

(defn get_g_V_withSideEffectXsgX_outEXknowsX_subgraphXsgX_name_capXsgX
  "g.withSideEffect('sg', () -> subgraph).V(v1Id).outE('knows').subgraph('sg').values('name').cap('sg')"
  [g v1Id sg]
  (traverse g (with-side-effect :sg (fn [] sg))
              (V v1Id)
              (outE :knows)
              (subgraph :sg)
              (values :name)
              (cap :sg)))

(defn get_g_withSideEffectXsgX_V_hasXname_danielX_outE_subgraphXsgX_inV
  "g.withSideEffect('sg', () -> subgraph).V().has('name','daniel').outE().subgraph('sg').inV()"
  [g sg]
  (traverse g (with-side-effect :sg (fn [] sg))
              (V)
              (has :name "daniel")
              (outE)
              (subgraph :sg)
              (inV)))

