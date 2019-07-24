(ns clojurewerkz.ogre.suite.has-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser TextP)))

(defn get_g_V_outXcreatedX_hasXname__mapXlengthX_isXgtX3XXX_name
  "g.V().out('created').has('name', __.map(s -> s.get().length()).is(P.gt(3))).values('name')"
  [g]
  (traverse g (V)
              (out :created)
              (has :name (is (__ (map (fn [^Traverser v] (.length ^String (.get v))))) (P/gt 3)))
              (values :name)))

(defn get_g_VX1X_hasXkeyX
  "g.V(v1Id).has(key)"
  [g v1Id key]
  (traverse g (V v1Id)
              (has key)))

(defn get_g_VX1X_hasXname_markoX
  "g.V(v1Id).has('name', 'marko')"
  [g v1Id]
  (traverse g (V v1Id)
              (has :name "marko")))

(defn get_g_V_hasXname_markoX
  "g.V().has('name', 'marko')"
  [g]
  (traverse g (V)
              (has :name "marko")))

(defn get_g_V_hasXname_blahX
  "return g.V().has('name', 'blah')"
  [g]
  (traverse g (V)
              (has :name "blah")))

(defn get_g_V_hasXblahX
  "return g.V().has('blah')"
  [g]
  (traverse g (V)
              (has "blah")))

(defn get_g_VX1X_hasXage_gt_30X
  "g.V(v1Id).has('age', P.gt(30))"
  [g v1Id]
  (traverse g (V [v1Id])
              (has :age (P/gt 30))))

(defn get_g_VXv1X_hasXage_gt_30X
  "g.V(g.V(v1Id).next()).has('age', P.gt(30))"
  [g V1Id]
  (traverse g (V V1Id)
              (has :age (P/gt 30))))

(defn get_g_VX1X_out_hasXid_lt_3X
  "g.V(v1Id).out().has(T.id, P.lt(v3Id))"
  [g v1Id v3Id]
  (traverse g (V)
              (out)
              (has (T/id) (P/lt v3Id))))

(defn get_g_VX1X_out_hasIdX2X
  "g.V(v1Id).out().hasId(v2Id)"
  [g v1Id v2Id]
  (traverse g (V v1Id)
              (out)
              (has-id v2Id)))

(defn get_g_VX1X_out_hasIdX2_3X
  "g.V(v1Id).out().hasId(v2Id, v3Id)"
  [g v1Id v2Id v3Id]
  (traverse g (V v1Id)
              (out)
              (has-id v2Id v3Id)))

(defn get_g_V_hasXage_gt_30X
  "g.V().has('age', P.gt(30))"
  [g]
  (traverse g (V)
              (has :age (P/gt 30))))

(defn get_g_V_hasXage_isXgt_30XX
  "g.V().has('age', __.is(P.gt(30)))"
  [g]
  (traverse g (V)
              (has :age (__ (is (P/gt 30))))))

(defn get_g_EX7X_hasLabelXknowsX
  "g.E(e7Id).hasLabel('knows')"
  [g e7Id]
  (traverse g (E e7Id)
              (has-label :knows)))

(defn get_g_E_hasLabelXknowsX
  "g.E().hasLabel('knows')"
  [g]
  (traverse g (E)
              (has-label :knows)))

(defn get_g_EX11X_outV_outE_hasXid_10X
  "g.E(e11Id).outV().outE().has(T.id, e8Id)"
  [g e11Id e8Id]
  (traverse g (E e11Id)
              (outV)
              (outE)
              (has T/id e8Id)))

(defn get_g_E_hasLabelXuses_traversesX
  "g.E().hasLabel('uses', 'traverses')"
  [g]
  (traverse g (E)
              (has-label :uses :traverses)))

(defn get_g_V_hasLabelXperson_software_blahX
  "g.V().hasLabel('person', 'software', 'blah')"
  [g]
  (traverse g (V)
              (has-label :person :software :blah)))

(defn get_g_V_hasXperson_name_markoX_age
  "g.V().has('person', 'name', 'marko').values('age')"
  [g]
  (traverse g (V)
              (has :person :name "marko")
              (values :age)))

(defn get_g_VX1X_outE_hasXweight_inside_0_06X_inV
  "g.V(v1Id).outE().has('weight', P.inside(0.0d, 0.6d)).inV()"
  [g v1Id]
  (traverse g (V)
              (outE)
              (has :weight (P/inside (double 0) (double 0.6)))
              (inV)))

(defn get_g_V_hasXlocationX
  "g.V().has('location')"
  [g]
  (traverse g (V)
              (has :location)))

(defn get_g_V_hasLabelXpersonX_hasXage_notXlteX10X_andXnotXbetweenX11_20XXXX_andXltX29X_orXeqX35XXXX_name
  "g.V().hasLabel('person').has('age', P.not(P.lte(10).and(P.not(P.between(11, 20)))).and(P.lt(29).or(P.eq(35)))).values('name')"
  [g]
  (traverse g (V)
              (has-label :person)
              (has :age (.and (P/not (.and (P/lte 10) (P/not (P/between 11 20)))) (.or (P/lt 29) (P/eq 35))))
              (values :name)))

(defn get_g_V_in_hasIdXneqX1XX
  "g.V().in().hasId(P.neq(v1Id))"
  [g v1Id]
  (traverse g (V)
              (in)
              (has-id (P/neq v1Id))))

(defn get_g_V_hasLabelXpersonX_hasLabelXsoftwareX
  "g.V().hasLabel('person').hasLabel('software')"
  [g]
  (traverse g (V)
              (has-label :person)
              (has-label :software)))

(defn get_g_V_hasIdX1X_hasIdX2X
  "g.V().hasId(v1Id).hasId(v2Id)"
  [g v1Id v2Id]
  (traverse g (V)
              (has-id v1Id)
              (has-id v2Id)))

(defn get_g_V_hasNotXageX_name
  "g.V().hasNot('age').values('name')"
  [g]
  (traverse g (V)
              (has-not :age)
              (values :name)))

(defn get_g_V_both_properties_dedup_hasKeyXageX_hasValueXgtX30XX_value
  "g.V().both().properties().dedup().hasKey('age').hasValue(P.gt(30)).value()"
  [g]
  (traverse g (V)
              (both)
              (properties)
              (dedup)
              (has-key :age)
              (has-value (P/gt 30))
              (value)))

(defn get_g_V_both_properties_dedup_hasKeyXageX_value
  "g.V().both().properties().dedup().hasKey('age').value()"
  [g]
  (traverse g (V)
              (both)
              (properties)
              (dedup)
              (has-key :age)
              (value)))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_simplePath_byXlabelX_fromXbX_toXcX_path_byXnameX
  "g.V().as('a').out().as('b').out().as('c').simplePath().by(T.label).from('b').to('c').path().by('name')"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (out) (as :c)
              (simple-path)
                (by T/label)
                (from :b)
                (to :c)
              (path)
                (by :name)))

(defn get_g_V_hasIdXemptyX_count
  "g.V().hasId(Collections.emptyList()).count()"
  [g]
  (traverse g (V)
              (has-id (java.util.Collections/emptyList))
              (count)))

(defn get_g_V_hasIdXwithoutXemptyXX_count
  "g.V().hasId(P.without(Collections.emptyList())).count()"
  [g]
  (traverse g (V)
              (has-id (P/without (java.util.Collections/emptyList)))
              (count)))

(defn get_g_V_hasIdXwithinXemptyXX_count
  "g.V().hasId(P.within(Collections.emptyList())).count()"
  [g]
  (traverse g (V)
              (has-id (P/within (java.util.Collections/emptyList)))
              (count)))

(defn get_g_V_notXhasIdXwithinXemptyXXX_count
  "g.V().not(__.hasId(P.within(Collections.emptyList()))).count()"
  [g]
  (traverse g (V)
              (not (__(has-id (P/within (java.util.Collections/emptyList)))))
              (count)))

(defn get_g_V_hasXage_withoutX27X_count
  "g.V().has('age', P.without(27)).count()"
  [g]
  (traverse g (V)
              (has "age" (P/without [27]))
              (count)))

(defn get_g_V_hasXage_withoutX27_29X_count
  "g.V().has('age', P.without(27, 29)).count()"
  [g]
  (traverse g (V)
              (has "age" (P/without [27 29]))
              (count)))

(defn get_g_V_hasXage_withinX27X_count
  "g.V().has('age', P.within(27)).count()"
  [g]
  (traverse g (V)
              (has "age" (P/within [27]))
              (count)))

(defn get_g_V_hasXage_withinX27_29X_count
  "g.V().has('age', P.within(27, 29)).count()"
  [g]
  (traverse g (V)
              (has "age" (P/within [27 29]))
              (count)))

(defn get_g_V_hasXname_containingXarkXX
  "g.V().has('name', TextP.containing('ark'))"
  [g]
  (traverse g 
            (V)
            (has :name (TextP/containing "ark"))))


(defn get_g_V_hasXname_startingWithXmarXX
  "g.V().has('name', TextP.startingWith('mar'))"
  [g]
  (traverse g
            (V)
            (has :name (TextP/startingWith "mar"))))

(defn get_g_V_hasXname_endingWithXasXX
  "g.V().has('name', TextP.endingWith('as'))"
  [g]
  (traverse g
            (V)
            (has :name (TextP/endingWith "as"))))

(defn get_g_V_hasXperson_name_containingXoX_andXltXmXXX
  "g.V().has('person','name', TextP.containing('o').and(P.lt('m')))"
  [g]
  (traverse g
            (V)
            (has "person" :name (.and (TextP/containing "o") (P/lt "m")))))

(defn get_g_V_hasXname_gtXmX_andXcontainingXoXXX
  "g.V().has('name', P.gt('m').and(TextP.containing('o')))"
  [g]
  (traverse g
            (V)
            (has :name (.and (P/gt "m") (TextP/containing "o")))))

(defn get_g_V_hasXname_not_containingXarkXX
  "g.V().has('name', TextP.notContaining('ark'))"
  [g]
  (traverse g
            (V)
            (has :name (TextP/notContaining "ark"))))

(defn get_g_V_hasXname_not_startingWithXmarXX
  "g.V().has('name', TextP.notStartingWith('mar'))"
  [g]
  (traverse g
            (V)
            (has :name (TextP/notStartingWith "mar"))))

(defn get_g_V_hasXname_not_endingWithXasXX
  "g.V().has('name', TextP.notEndingWith('as'))"
  [g]
  (traverse g
            (V)
            (has :name (TextP/notEndingWith "as"))))