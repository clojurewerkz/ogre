(ns clojurewerkz.ogre.suite.subgraph-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_withSideEffectXsgX_repeatXbothEXcreatedX_subgraphXsgX_outVX_timesX5X_name_dedup
  "g.withSideEffect('sg', () -> subgraph).V().repeat(bothE('created').subgraph('sg').outV()).times(5).values('name').dedup()"
  [g subgraph]
  (q/traverse g (q/with-side-effect :sg (fn [] subgraph))
                (q/V)
                (q/repeat (q/__ (q/bothE :created) (q/subgraph :sg) (q/outV)))
                (q/times 5)
                (q/values :name)
                (q/dedup)))

(defn get_g_V_withSideEffectXsgX_outEXknowsX_subgraphXsgX_name_capXsgX
  "g.withSideEffect('sg', () -> subgraph).V(v1Id).outE('knows').subgraph('sg').values('name').cap('sg')"
  [g v1Id subgraph]
  (q/traverse g (q/with-side-effect :sg (fn [] subgraph))
                (q/V v1Id)
                (q/outE :knows)
                (q/subgraph :sg)
                (q/values :name)
                (q/cap :sg)))

(defn get_g_withSideEffectXsgX_V_hasXname_danielX_outE_subgraphXsgX_inV
  "g.withSideEffect('sg', () -> subgraph).V().has('name','daniel').outE().subgraph('sg').inV()"
  [g subgraph]
  (q/traverse g (q/with-side-effect :sg (fn [] subgraph))
                (q/V)
                (q/has :name "daniel")
                (q/outE)
                (q/subgraph :sg)
                (q/inV)))

