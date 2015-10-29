(ns clojurewerkz.ogre.suite.has-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name
  "g.V().out(\"created\").has(\"name\", __.<String, Integer>map(s -> s.get().length()).is(P.gt(3))).values(\"name\")"
  [g]
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_hasXkeyX
  "g.V(v1Id).has(key)"
  [g v1Id key]
  (q/query (q/V g v1Id)
           (q/has key)))

(defn get_g_VX1X_hasXname_markoX
  "g.V(v1Id).has(\"name\", \"marko\")"
  [g v1Id]
  (q/query (q/V g v1Id)
           (q/has :name :marko)))

(defn get_g_V_hasXname_markoX
  "g.V().has(\"name\", \"marko\")"
  [g]
  (q/query (q/V g)
           (q/has :name :marko)))

(defn get_g_V_hasXname_blahX
  "return g.V().has(\"name\", \"blah\")"
  [g]
  (q/query (q/V g)
           (q/has :name :blah)))

(defn get_g_V_hasXblahX
  "return g.V().has(\"blah\")"
  [g]
  (q/query (q/V g)
           (q/has :blah)))

(defn get_g_VX1X_hasXage_gt_30X
  "g.V(v1Id).has(\"age\", P.gt(30))"
  [g v1Id]
  (q/query (v/get-all-vertices g)))

(defn get_g_VXv1X_hasXage_gt_30X
  "g.V(g.V(v1Id).next()).has(\"age\", P.gt(30))"
  [g V1Id]
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_out_hasXid_lt_3X
  "g.V(v1Id).out().has(T.id, P.lt(v3Id))"
  [g v1Id v3Id]
  (q/query (v/get-all-vertices g)))

(defn get_g_VX1X_out_hasIdX2X
  "g.V(v1Id).out().hasId(v2Id)"
  [g v1Id v2Id]
  (q/query (q/V g v1Id)
           q/out
           (q/has-id v2Id)))

(defn get_g_VX1X_out_hasIdX2_3X
  "g.V(v1Id).out().hasId(v2Id, v3Id)"
  [g v1Id v2Id v3Id]
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXage_gt_30X
  "g.V().has(\"age\", P.gt(30))"
  [g]
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXage_isXgt_30XX
  "g.V().has(\"age\", __.is(P.gt(30)))"
  [g]
  (q/query (v/get-all-vertices g)))

(defn get_g_EX7X_hasLabelXknowsX
  "g.E(e7Id).hasLabel(\"knows\")"
  [g e7Id]
  (q/query (q/E g e7Id)
           (q/has-label :knows)))

(defn get_g_E_hasLabelXknowsX
  "g.E().hasLabel(\"knows\")"
  [g]
  (q/query (q/E g)
           (q/has-label :knows)))

(defn get_g_EX11X_outV_outE_hasXid_10X
  "g.E(e11Id).outV().outE().has(T.id, e8Id)"
  [g e11Id e8Id]
  (q/query (q/E g e11Id)
           q/outV
           q/outE
           (q/has T/id e8Id)))
(defn get_g_E_hasLabelXuses_traversesX
  "g.E().hasLabel(\"uses\", \"traverses\")"
  [g]
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasLabelXperson_software_blahX
  "g.V().hasLabel(\"person\", \"software\", \"blah\")"
  [g]
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXperson_name_markoX_age
  "g.V().has(\"person\", \"name\", \"marko\").values(\"age\")"
  [g]
  (q/query (v/get-all-vertices g)
           (q/has :person :name :marko)
           (q/values :age)))

(defn get_g_VX1X_outE_hasXweight_inside_0_06X_inV
  "g.V(v1Id).outE().has(\"weight\", P.inside(0.0d, 0.6d)).inV()"
  [g v1Id]
  (q/query (v/get-all-vertices g)))

(defn get_g_V_hasXlocationX
  "g.V().has(\"location\")"
  [g]
  (q/query (v/get-all-vertices g)
           (q/has :location)))
