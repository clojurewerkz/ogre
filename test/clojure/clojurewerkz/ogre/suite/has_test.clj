(ns clojurewerkz.ogre.suite.has-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(defn get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name
  [g]
  "g.V().out(\"created\").has(\"name\", __.<String, Integer>map(s -> s.get().length()).is(P.gt(3))).values(\"name\")"
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_hasXkeyX
  [g v1Id key]
  "g.V(v1Id).has(key)"
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_hasXname_markoX
  [g v1Id]
  "g.V(v1Id).has(\"name\", \"marko\")"
  (q/query (v/get-all-vertices g)
           (q/has :name :marko)))

(defn get_g_V_hasXname_markoX
  [g]
  "g.V().has(\"name\", \"marko\")"
  (q/query (v/get-all-vertices g)
           (q/has :name :marko)))

(defn get_g_V_hasXname_blahX
  [g]
  "return g.V().has(\"name\", \"blah\")"
  (q/query (v/get-all-vertices g)
           (q/has :name :blah)))

(defn get_g_V_hasXblahX
  [g]
  "return g.V().has(\"blah\")"
  (q/query (v/get-all-vertices g)
           (q/has :blah)))

(defn get_g_VX1X_hasXage_gt_30X
  [g v1Id]
  "g.V(v1Id).has(\"age\", P.gt(30))"
  (q/query (v/get-all-vertices g)))

(defn get_g_VXv1X_hasXage_gt_30X
  [g V1Id]
  "g.V(g.V(v1Id).next()).has(\"age\", P.gt(30))"
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_out_hasXid_lt_3X
  [g v1Id v3Id]
  "g.V(v1Id).out().has(T.id, P.lt(v3Id))"
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_out_hasIdX2X
  [g v1Id v2Id]
  "g.V(v1Id).out().hasId(v2Id)"
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_out_hasIdX2_3X
  [g v1Id v2Id v3Id]
  "g.V(v1Id).out().hasId(v2Id, v3Id)"
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXage_gt_30X
  [g]
  "g.V().has(\"age\", P.gt(30))"
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXage_isXgt_30XX
  [g]
  "g.V().has(\"age\", __.is(P.gt(30)))"
  (q/query (v/get-all-vertices g)))

(defn get_g_EX7X_hasLabelXknowsX
  [g e7Id]
  "g.E(e7Id).hasLabel(\"knows\")"
  (q/query (v/get-all-vertices g)))

(defn get_g_E_hasLabelXknowsX
  [g]
  "g.E().hasLabel(\"knows\")"
  (q/query (v/get-all-vertices g)))

(defn get_g_EX11X_outV_outE_hasXid_10X
  [g e11Id e8Id]
  "g.E(e11Id).outV().outE().has(T.id, e8Id)"
  (q/query (v/get-all-vertices g)))

(defn get_g_E_hasLabelXuses_traversesX
  [g]
  "g.E().hasLabel(\"uses\", \"traverses\")"
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasLabelXperson_software_blahX
  [g]
  "g.V().hasLabel(\"person\", \"software\", \"blah\")"
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXperson_name_markoX_age
  [g]
  "g.V().has(\"person\", \"name\", \"marko\").values(\"age\")"
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_outE_hasXweight_inside_0_06X_inV
  [g v1Id]
  "g.V(v1Id).outE().has(\"weight\", P.inside(0.0d, 0.6d)).inV()"
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXlocationX
  [g]
  "g.V().has(\"location\")"
  (q/query (v/get-all-vertices g)
           (q/has :location)))
