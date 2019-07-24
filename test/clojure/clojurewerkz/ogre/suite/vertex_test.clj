(ns clojurewerkz.ogre.suite.vertex-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure Direction)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VXlistX1_2_3XX_name
  "g.V(Arrays.asList(convertToVertex(graph, 'marko'), convertToVertex(graph, 'vadas'), convertToVertex(graph, 'lop'))).values('name')"
  [g v1 v2 v3]
  (traverse g (V [v1 v2 v3]) (values :name)))

(defn get_g_V
  "g.V()"
  [g]
  (traverse g (V)))

(defn get_g_VX1X_out
  "g.V(v1Id).out()"
  [g v1Id]
  (traverse g (V v1Id) (out)))

(defn get_g_E
  "g.E()"
  [g]
  (traverse g (E)))

(defn get_g_VX1X_outE
  "g.V(v1Id).outE()"
  [g v1Id]
  (traverse g (V v1Id) (outE)))

(defn get_g_VX2X_inE
  "g.V(v2Id).inE()"
  [g v2Id]
  (traverse g (V v2Id) (inE)))

(defn get_g_VX4X_bothE
  "g.V(v4Id).bothE()"
  [g v4Id]
  (traverse g (V v4Id) (bothE)))

(defn get_g_VX4X_bothEXcreatedX
  "g.V(v4Id).bothE('created')"
  [g v4Id]
  (traverse g (V v4Id) (bothE :created)))

(defn get_g_VX1X_outE_inV
  "g.V(v1Id).outE().inV()"
  [g v1Id]
  (traverse g (V v1Id) (outE) (inV)))

(defn get_g_VX2X_inE_outV
  "g.V(v1Id).inE().outV()"
  [g v1Id]
  (traverse g (V v1Id) (inE) (outV)))

(defn get_g_V_outE_hasXweight_1X_outV
  "g.V().outE().has('weight', 1.0d).outV()"
  [g]
  (traverse g (V)
              (outE)
              (has :weight 1.0)
              (outV)))

(defn get_g_V_out_outE_inV_inE_inV_both_name
  "g.V().out().outE().inV().inE().inV().both().values('name')"
  [g]
  (traverse g (V)
              (out)
              (outE)
              (inV)
              (inE)
              (inV)
              (both)
              (values :name)))

(defn get_g_VX1X_outEXknowsX_bothV_name
  "g.V(v1Id).outE('knows').bothV().values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (outE :knows)
              (bothV)
              (values :name)))

(defn get_g_VX1X_outXknowsX
  "g.V(v1Id).out('knows')"
  [g v1Id]
  (traverse g (V) (out :knows)))

(defn get_g_VX1X_outXknows_createdX
  "g.V(v1Id).out('knows', 'created')"
  [g v1Id]
  (traverse g (V v1Id) (out :knows :created)))

(defn get_g_VX1X_outEXknowsX_inV
  "g.V(v1Id).outE('knows').inV()"
  [g v1Id]
  (traverse g (V v1Id)
              (outE :knows)
              (inV)))

(defn get_g_VX1X_outEXknows_createdX_inV
  "g.V(v1Id).outE('knows', 'created').inV()"
  [g v1Id]
  (traverse g (V v1Id)
              (outE :knows :created)
              (inV)))

(defn get_g_VX1X_outE_otherV
  "g.V(v1Id).outE().otherV()"
  [g v1Id]
  (traverse g (V v1Id)
              (outE) (otherV)))

(defn get_g_VX4X_bothE_otherV
  "g.V(v4Id).bothE().otherV()"
  [g v4Id]
  (traverse g (V v4Id)
              (bothE) (otherV)))

(defn get_g_VX4X_bothE_hasXweight_lt_1X_otherV
  "g.V(v4Id).bothE().has('weight', P.lt(1d)).otherV()"
  [g v4Id]
  (traverse g (V v4Id)
              (bothE)
              (has :weight (P/lt 1.0))
              (otherV)))

(defn get_g_VX2X_in
  "g.V(v2Id).in()"
  [g v2Id]
  (traverse g (V v2Id) (in)))

(defn get_g_VX4X_both
  "g.V(v4Id).both()"
  [g v4Id]
  (traverse g (V v4Id) (both)))

(defn get_g_V_out_out
  "g.V().out().out()"
  [g]
  (traverse g (V) (out) (out)))

(defn get_g_VX1X_out_out_out
  "g.V(v1Id).out().out().out()"
  [g v1Id]
  (traverse g (V v1Id) (out) (out) (out)))

(defn get_g_VX1X_out_name
  "g.V(v1Id).out().values('name')"
  [g v1Id]
  (traverse g (V v1Id) (out) (values :name)))

(defn get_g_VX1X_to_XOUT_knowsX
  "g.V(v1Id).to(Direction.OUT, 'knows')"
  [g v1Id]
  (traverse g (V v1Id) (to (Direction/OUT) :knows)))

(defn get_g_EX11X
  "g.E(e11Id)"
  [g e11Id]
  (traverse g (E e11Id)))

(defn get_g_VX1_2_3_4X_name
  "g.V(v1Id, v2Id, v3Id, v4Id).values('name')"
  [g v1 v2 v3 v4]
  (traverse g (V [v1 v2 v3 v4]) (values :name)))

(defn get_g_VXlistX1_2_3XX_name
  "g.V(Arrays.asList(v1Id, v2Id, v3Id)).values('name')"
  [g v1 v2 v3]
  (traverse g (V [v1 v2 v3]) (values :name)))

(defn get_g_VXlistXv1_v2_v3XX_name
  "g.V(Arrays.asList(v1, v2, v3)).values('name')"
  [g v1 v2 v3]
  (traverse g (V [v1 v2 v3]) (values :name)))

(defn get_g_V_hasLabelXpersonX_V_hasLabelXsoftwareX_name
  "g.V().hasLabel('person').V().hasLabel('software').values('name')"
  [g]
  (traverse g (V)
              (has-label :person)
              (V)
              (has-label :software)
              (values :name)))

(defn get_g_V_hasLabelXloopsX_bothEXselfX
  "g.V().hasLabel('loops').bothE('self')"
  [g]
  (traverse g (V)
              (has-label :loops)
              (bothE :self)))

(defn get_g_V_hasLabelXloopsX_bothXselfX
  "g.V().hasLabel('loops').both('self')"
  [g]
  (traverse g (V)
              (has-label :loops)
              (both :self)))

(defn get_g_VXv1X_out
  "g.V(v1).out()"
  [g v1]
  (traverse g (V v1) (out)))

(defn get_g_EXe11X
  "g.E(e11)"
  [g e11]
  (traverse g (E e11)))

(defn get_g_EXlistXe7_e11XX
  "g.E([e7,e11])"
  [g e7 e11]
  (traverse g (E [e7 e11])))

(defn get_g_EXe7_e11X
  "g.E(e7,e11)"
  [g e7 e11]
  (traverse g (E e7 e11)))

