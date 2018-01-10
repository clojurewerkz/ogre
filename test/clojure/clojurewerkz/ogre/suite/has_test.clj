(ns clojurewerkz.ogre.suite.has-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser)))

(defn get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name
  "g.V().out(\"created\").has(\"name\", __.map(s -> s.get().length()).is(P.gt(3))).values(\"name\")"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/has :name (q/is (q/__ (map (fn [^Traverser v] (.length ^String (.get v))))) (P/gt 3)))
                (q/values :name)))

(defn get_g_VX1X_hasXkeyX
  "g.V(v1Id).has(key)"
  [g v1Id key]
  (q/traverse g (q/V v1Id)
                (q/has key)))

(defn get_g_VX1X_hasXname_markoX
  "g.V(v1Id).has(\"name\", \"marko\")"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/has :name "marko")))

(defn get_g_V_hasXname_markoX
  "g.V().has(\"name\", \"marko\")"
  [g]
  (q/traverse g (q/V)
                (q/has :name "marko")))

(defn get_g_V_hasXname_blahX
  "return g.V().has(\"name\", \"blah\")"
  [g]
  (q/traverse g (q/V)
                (q/has :name "blah")))

(defn get_g_V_hasXblahX
  "return g.V().has(\"blah\")"
  [g]
  (q/traverse g (q/V)
                (q/has "blah")))

(defn get_g_VX1X_hasXage_gt_30X
  "g.V(v1Id).has(\"age\", P.gt(30))"
  [g v1Id]
  (q/traverse g (q/V [v1Id])
                (q/has :age (P/gt 30))))

(defn get_g_VXv1X_hasXage_gt_30X
  "g.V(g.V(v1Id).next()).has(\"age\", P.gt(30))"
  [g V1Id]
  (q/traverse g (q/V V1Id)
                (q/has :age (P/gt 30))))

(defn get_g_VX1X_out_hasXid_lt_3X
  "g.V(v1Id).out().has(T.id, P.lt(v3Id))"
  [g v1Id v3Id]
  (q/traverse g (q/V)
                (q/out)
                (q/has (T/id) (P/lt v3Id))))

(defn get_g_VX1X_out_hasIdX2X
  "g.V(v1Id).out().hasId(v2Id)"
  [g v1Id v2Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/has-id v2Id)))

(defn get_g_VX1X_out_hasIdX2_3X
  "g.V(v1Id).out().hasId(v2Id, v3Id)"
  [g v1Id v2Id v3Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/has-id v2Id v3Id)))

(defn get_g_V_hasXage_gt_30X
  "g.V().has(\"age\", P.gt(30))"
  [g]
  (q/traverse g (q/V)
                (q/has :age (P/gt 30))))

(defn get_g_V_hasXage_isXgt_30XX
  "g.V().has(\"age\", __.is(P.gt(30)))"
  [g]
  (q/traverse g (q/V)
                (q/has :age (q/__ (is (P/gt 30))))))

(defn get_g_EX7X_hasLabelXknowsX
  "g.E(e7Id).hasLabel(\"knows\")"
  [g e7Id]
  (q/traverse g (q/E e7Id)
                (q/has-label :knows)))

(defn get_g_E_hasLabelXknowsX
  "g.E().hasLabel(\"knows\")"
  [g]
  (q/traverse g (q/E)
                (q/has-label :knows)))

(defn get_g_EX11X_outV_outE_hasXid_10X
  "g.E(e11Id).outV().outE().has(T.id, e8Id)"
  [g e11Id e8Id]
  (q/traverse g (q/E e11Id)
                (q/outV)
                (q/outE)
                (q/has T/id e8Id)))

(defn get_g_E_hasLabelXuses_traversesX
  "g.E().hasLabel(\"uses\", \"traverses\")"
  [g]
  (q/traverse g (q/E)
                (q/has-label :uses :traverses)))

(defn get_g_V_hasLabelXperson_software_blahX
  "g.V().hasLabel(\"person\", \"software\", \"blah\")"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person :software :blah)))

(defn get_g_V_hasXperson_name_markoX_age
  "g.V().has(\"person\", \"name\", \"marko\").values(\"age\")"
  [g]
  (q/traverse g (q/V)
                (q/has :person :name "marko")
                (q/values :age)))

(defn get_g_VX1X_outE_hasXweight_inside_0_06X_inV
  "g.V(v1Id).outE().has(\"weight\", P.inside(0.0d, 0.6d)).inV()"
  [g v1Id]
  (q/traverse g (q/V)
                (q/outE)
                (q/has :weight (P/inside (double 0) (double 0.6)))
                (q/inV)))

(defn get_g_V_hasXlocationX
  "g.V().has(\"location\")"
  [g]
  (q/traverse g (q/V)
                (q/has :location)))

(defn get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name
  "g.V().hasLabel('person').has('age', P.not(P.lte(10).and(P.not(P.between(11, 20)))).and(P.lt(29).or(P.eq(35)))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/has :age (.and (P/not (.and (P/lte 10) (P/not (P/between 11 20)))) (.or (P/lt 29) (P/eq 35))))
                (q/values :name)))

(defn get_g_V_in_hasIdXneqX1XX
  "g.V().in().hasId(P.neq(v1Id))"
  [g v1Id]
  (q/traverse g (q/V)
                (q/in)
                (q/has-id (P/neq v1Id))))

(defn get_g_V_hasLabelXpersonX_hasLabelXsoftwareX
  "g.V().hasLabel('person').hasLabel('software')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/has-label :software)))

(defn get_g_V_hasIdX1X_hasIdX2X
  "g.V().hasId(v1Id).hasId(v2Id)"
  [g v1Id v2Id]
  (q/traverse g (q/V)
                (q/has-id v1Id)
                (q/has-id v2Id)))

(defn get_g_V_hasNotXageX_name
  "g.V().hasNot('age').values('name')"
  [g]
  (q/traverse g (q/V)
                (q/has-not :age)
                (q/values :name)))

(defn get_g_V_both_properties_dedup_hasKeyXageX_hasValueXgtX30XX_value
  "g.V().both().properties().dedup().hasKey('age').hasValue(P.gt(30)).value()"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/properties)
                (q/dedup)
                (q/has-key :age)
                (q/has-value (P/gt 30))
                (q/value)))

(defn get_g_V_both_properties_dedup_hasKeyXageX_value
  "g.V().both().properties().dedup().hasKey('age').value()"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/properties)
                (q/dedup)
                (q/has-key :age)
                (q/value)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_simplePath_byXlabelX_fromXbX_toXcX_path_byXnameX
  "g.V().as('a').out().as('b').out().as('c').simplePath().by(T.label).from('b').to('c').path().by('name')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/out) (q/as :c)
                (q/simple-path)
                  (q/by T/label)
                  (q/from :b)
                  (q/to :c)
                (q/path)
                  (q/by :name)))

(defn get_g_V_hasIdXemptyX_count
  "g.V().hasId(Collections.emptyList()).count()"
  [g]
  (q/traverse g (q/V)
                (q/has-id (java.util.Collections/emptyList))
                (q/count)))

(defn get_g_V_hasIdXwithoutXemptyXX_count
  "g.V().hasId(P.without(Collections.emptyList())).count()"
  [g]
  (q/traverse g (q/V)
                (q/has-id (P/without (java.util.Collections/emptyList)))
                (q/count)))

(defn get_g_V_hasIdXwithinXemptyXX_count
  "g.V().hasId(P.within(Collections.emptyList())).count()"
  [g]
  (q/traverse g (q/V)
                (q/has-id (P/within (java.util.Collections/emptyList)))
                (q/count)))

(defn get_g_V_notXhasIdXwithinXemptyXXX_count
  "g.V().not(__.hasId(P.within(Collections.emptyList()))).count()"
  [g]
  (q/traverse g (q/V)
                (q/not (q/__(q/has-id (P/within (java.util.Collections/emptyList)))))
                (q/count)))

