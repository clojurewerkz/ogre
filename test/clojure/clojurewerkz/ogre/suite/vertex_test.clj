(ns clojurewerkz.ogre.suite.vertex-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure Direction T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

;; this implementation is hacked a bit because the test on the TinkerPop side should be passing the
;; vertices as an argument to the test. look for that fix in 3.2.1.
(defn get_g_VXlistX1_2_3XX_name
  "g.V(Arrays.asList(convertToVertex(graph, 'marko'), convertToVertex(graph, 'vadas'), convertToVertex(graph, 'lop'))).values('name')"
  [g]
  (q/traverse g (q/V 1 2 3) (q/values :name)))

(defn get_g_V
  "g.V()"
  [g]
  (q/traverse g (q/V)))

(defn get_g_VX1X_out
  "g.V(v1Id).out()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/out)))

(defn get_g_E
  "g.E()"
  [g]
  (q/traverse g (q/E)))

(defn get_g_VX1X_outE
  "g.V(v1Id).outE()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/outE)))

(defn get_g_VX2X_inE
  "g.V(v2Id).inE()"
  [g v2Id]
  (q/traverse g (q/V v2Id) (q/inE)))

(defn get_g_VX4X_bothE
  "g.V(v4Id).bothE()"
  [g v4Id]
  (q/traverse g (q/V v4Id) (q/bothE)))

(defn get_g_VX4X_bothEXcreatedX
  "g.V(v4Id).bothE('created')"
  [g v4Id]
  (q/traverse g (q/V v4Id) (q/bothE :created)))

(defn get_g_VX1X_outE_inV
  "g.V(v1Id).outE().inV()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/outE) (q/inV)))

(defn get_g_VX2X_inE_outV
  "g.V(v1Id).inE().outV()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/inE) (q/outV)))

(defn get_g_V_outE_hasXweight_1X_outV
  "g.V().outE().has('weight', 1.0d).outV()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/has :weight 1.0)
                (q/outV)))

(defn get_g_V_out_outE_inV_inE_inV_both_name
  "g.V().out().outE().inV().inE().inV().both().values('name')"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/outE)
                (q/inV)
                (q/inE)
                (q/inV)
                (q/both)
                (q/values :name)))

(defn get_g_VX1X_outEXknowsX_bothV_name
  "g.V(v1Id).outE('knows').bothV().values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE :knows)
                (q/bothV)
                (q/values :name)))

(defn get_g_VX1X_outXknowsX
  "g.V(v1Id).out('knows')"
  [g v1Id]
  (q/traverse g (q/V) (q/out :knows)))

(defn get_g_VX1X_outXknows_createdX
  "g.V(v1Id).out('knows', 'created')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/out :knows :created)))

(defn get_g_VX1X_outEXknowsX_inV
  "g.V(v1Id).outE('knows').inV()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE :knows)
                (q/inV)))

(defn get_g_VX1X_outEXknows_createdX_inV
  "g.V(v1Id).outE('knows', 'created').inV()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE :knows :created)
                (q/inV)))

(defn get_g_VX1X_outE_otherV
  "g.V(v1Id).outE().otherV()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE) (q/otherV)))

(defn get_g_VX4X_bothE_otherV
  "g.V(v4Id).bothE().otherV()"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/bothE) (q/otherV)))

(defn get_g_VX4X_bothE_hasXweight_lt_1X_otherV
  "g.V(v4Id).bothE().has('weight', P.lt(1d)).otherV()"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/bothE)
                (q/has :weight (P/lt 1.0))
                (q/otherV)))

(defn get_g_VX2X_in
  "g.V(v2Id).in()"
  [g v2Id]
  (q/traverse g (q/V v2Id) (q/in)))

(defn get_g_VX4X_both
  "g.V(v4Id).both()"
  [g v4Id]
  (q/traverse g (q/V v4Id) (q/both)))

(defn get_g_V_out_out
  "g.V().out().out()"
  [g]
  (q/traverse g (q/V) (q/out) (q/out)))

(defn get_g_VX1X_out_out_out
  "g.V(v1Id).out().out().out()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/out) (q/out) (q/out)))

(defn get_g_VX1X_out_name
  "g.V(v1Id).out().values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/out) (q/values :name)))

(defn get_g_VX1X_to_XOUT_knowsX
  "g.V(v1Id).to(Direction.OUT, 'knows')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/to (Direction/OUT) :knows)))

(defn get_g_EX11X
  "g.E(e11Id)"
  [g e11Id]
  (q/traverse g (q/E e11Id)))

(defn get_g_VX1_2_3_4X_name
  "g.V(v1Id, v2Id, v3Id, v4Id).values('name')"
  [g v1 v2 v3 v4]
  (q/traverse g (q/V [v1 v2 v3 v4]) (q/values :name)))

(defn get_g_VXlistX1_2_3XX_name
  "g.V(Arrays.asList(v1Id, v2Id, v3Id)).values('name')"
  [g v1 v2 v3]
  (q/traverse g (q/V [v1 v2 v3]) (q/values :name)))

(defn get_g_VXlistXv1_v2_v3XX_name
  "g.V(Arrays.asList(v1, v2, v3)).values('name')"
  [g v1 v2 v3]
  (q/traverse g (q/V [v1 v2 v3]) (q/values :name)))

(defn get_g_V_hasLabelXpersonX_V_hasLabelXsoftwareX_name
  "g.V().hasLabel('person').V().hasLabel('software').values('name')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/midV)
                (q/has-label :software)
                (q/values :name)))

