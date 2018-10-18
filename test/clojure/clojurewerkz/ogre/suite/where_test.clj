(ns clojurewerkz.ogre.suite.where-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all]
            [clojurewerkz.ogre.util :as util])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_eqXbXX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where('a', eq('b'))"
  [g]
  (traverse g (V)
              (has :age) (as :a)
              (out)
              (in)
              (has :age) (as :b)
              (select :a :b)
              (where :a (P/eq "b"))))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_neqXbXX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where('a', neq('b'))"
  [g]
  (traverse g (V)
                (has :age) (as :a)
                (out)
                (in)
                (has :age) (as :b)
                (select :a :b)
                (where :a (P/neq "b"))))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXb_hasXname_markoXX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where(as('b').has('name', 'marko'))"
  [g]
  (traverse g (V)
              (has :age) (as :a)
              (out)
              (in)
              (has :age) (as :b)
              (select :a :b)
              (where (__ (as :b) (has :name "marko")))))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_outXknowsX_bX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where(as('a').out('knows').as('b'))"
  [g]
  (traverse g (V)
              (has :age) (as :a)
              (out)
              (in)
              (has :age) (as :b)
              (select :a :b)
              (where (__ (as :a) (out :knows) (as :b)))))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_neqXbXX_name
  "g.V(v1Id).as('a').out('created').in('created').as('b').where('a', neq('b')).values('name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created)
              (in :created) (as :b)
              (where :a (P/neq "b"))
              (values :name)))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXasXbX_outXcreatedX_hasXname_rippleXX_valuesXage_nameX
  "g.V(v1Id).as('a').out('created').in('created').as('b').where(as('b').out('created').has('name', 'ripple')).values('age', 'name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created)
              (in :created) (as :b)
              (where (__ (as :b) (out :created) (has :name "ripple")))
              (values :age :name)))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXeqXaXX_name
  "g.V(v1Id).as('a').out('created').in('created').where(eq('a')).values('name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created)
              (in :created)
              (where (P/eq "a"))
              (values :name)))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_name
  "g.V(v1Id).as('a').out('created').in('created').where(neq('a')).values('name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created)
              (in :created)
              (where (P/neq "a"))
              (values :name)))

(defn get_g_VX1X_out_aggregateXxX_out_whereXnotXwithinXaXXX
  "g.V(v1Id).out().aggregate('x').out().where(not(within('x')))"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (aggregate :x)
              (out)
              (where (P/not (P/within ["x"])))))

(defn get_g_withSideEffectXa_graph_verticesX2XX_VX1X_out_whereXneqXaXX
  "g.withSideEffect('a', g.V(v2Id).next()).V(v1Id).out().where(neq('a'))"
  [g v1Id v2Id]
  (traverse g (with-side-effect :a ^Vertex (traverse g (V v2Id) (next!)))
              (V v1Id)
              (out)
              (where (P/neq "a"))))

(defn get_g_VX1X_repeatXbothEXcreatedX_whereXwithoutXeXX_aggregateXeX_otherVX_emit_path
  "g.V(v1Id).repeat(bothE('created').where(without('e')).aggregate('e').otherV()).emit().path()"
  [g v1Id]
  (traverse g (V v1Id)
              (repeat (__ (bothE :created) (where (P/without ["e"])) (aggregate :e) (otherV)))
                (emit)
              (path)))

(defn get_g_V_whereXnotXoutXcreatedXXX_name
  "g.V().where(not(out('created'))).values('name')"
  [g]
  (traverse g (V)
              (where (__ (not (__ (out :created)))))
              (values :name)))

(defn get_g_V_asXaX_out_asXbX_whereXandXasXaX_outXknowsX_asXbX__orXasXbX_outXcreatedX_hasXname_rippleX__asXbX_inXknowsX_count_isXnotXeqX0XXXXX_selectXa_bX
  "g.V().as('a').out().as('b').where(and(as('a').out('knows').as('b'),
                                         or(as('b').out('created').has('name', 'ripple'),
                                            as('b').in('knows').count().is(not(eq(0)))))).select('a','b')"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (where (__ (and (__ (as :a) (out :knows) (as :b))
                              (__ (or (__ (as :b) (out :created) (has :name "ripple"))
                                      (__ (as :b) (in :knows) (count) (is (P/not (P/eq 0)))))))))
              (select :a :b)))

(defn get_g_V_whereXoutXcreatedX_and_outXknowsX_or_inXknowsXX_valuesXnameX
  "g.V().where(out('created').and().out('knows').or().in('knows')).values('name')"
  [g]
  (traverse g (V)
              (where (__ (out :created)
                         (and)
                         (out :knows)
                         (or)
                         (in :knows)))
              (values :name)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_whereXandXasXbX_in__notXasXaX_outXcreatedX_hasXname_rippleXXX_selectXa_bX
  "g.V().as('a').out('created').as('b').where(and(as('b').in(), not(as('a').out('created').has('name', 'ripple')))).select('a','b')"
  [g]
  (traverse g (V) (as :a)
              (out :created) (as :b)
              (where (__ (and (__ (as :b) (in))
                              (__ (not (__ (as :a) (out :created) (has :name "ripple")))))))
              (select :a :b)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_bothXknowsX_bothXknowsX_asXdX_whereXc__notXeqXaX_orXeqXdXXXX_selectXa_b_c_dX
  "g.V().as('a').out('created').as('b').in('created').as('c').both('knows').both('knows').as('d').
        where('c', P.not(P.eq('a').or(P.eq('d')))).select('a','b','c','d')"
  [g]
  (traverse g (V) (as :a)
              (out :created) (as :b)
              (in :created) (as :c)
              (both :knows)
              (both :knows) (as :d)
              (where :c (P/not (.or (P/eq "a") (P/eq "d"))))
              (select :a :b :c :d)))

(defn get_g_V_asXaX_out_asXbX_whereXin_count_isXeqX3XX_or_whereXoutXcreatedX_and_hasXlabel_personXXX_selectXa_bX
  "g.V().as('a').out().as('b').where(as('b').in().count().is(eq(3)).or().where(as('b').out('created').and().as('b').has(T.label, 'person'))).select('a','b')"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (where (__ (as :b) (in) (count) (is (P/eq 3))
                         (or)
                         (where (__ (as :b) (out :created) (and) (as :b) (has (T/label) "person")))))
              (select :a :b)))

(defn get_g_V_asXaX_outEXcreatedX_asXbX_inV_asXcX_inXcreatedX_asXdX_whereXa_ltXbX_orXgtXcXX_andXneqXdXXX_byXageX_byXweightX_byXinXcreatedX_valuesXageX_minX_selectXa_c_dX
  "g.V().as('a').outE('created').as('b').
     inV().as('c').
     in('created').as('d').
     where('a', lt('b').or(gt('c')).and(neq('d'))).
       by('age').
       by('weight').
       by(in('created').values('age').min()).
     select('a', 'c', 'd').by('name')"
  [g]
  (traverse g (V) (as :a)
              (outE :created) (as :b)
              (inV) (as :c)
              (in :created) (as :d)
              (where :a (.and (.or (P/lt "b") (P/gt "c")) (P/neq "d")))
                (by :age)
                (by :weight)
                (by (__ (in :created) (values :age) (min)))
              (select :a :c :d)
                (by :name)))

(defn get_g_V_asXaX_outEXcreatedX_asXbX_inV_asXcX_whereXa_gtXbX_orXeqXbXXX_byXageX_byXweightX_byXweightX_selectXa_cX_byXnameX
  "g.V().as('a').outE('created').as('b').inV().as('c').where('a', gt('b').or(eq('b'))).by('age').by('weight').by('weight').<String>select('a', 'c').by('name')"
  [g]
  (traverse g (V) (as :a)
              (outE :created) (as :b)
              (inV) (as :c)
              (where :a (.or (P/gt "b") (P/eq "b")))
                (by :age)
                (by :weight)
                (by :weight)
              (select :a :c)
                (by :name)))

(defn get_g_V_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_gtXbXX_byXageX_selectXa_bX_byXnameX
  "g.V().as('a').out('created').in('created').as('b').where('a', gt('b')).by('age').<String>select('a', 'b').by('name')"
  [g]
  (traverse g (V) (as :a)
              (out :created)
              (in :created) (as :b)
              (where :a (P/gt "b"))
                (by :age)
              (select :a :b)
                (by :name)))

(defn get_g_VX1X_asXaX_out_hasXageX_whereXgtXaXX_byXageX_name
  "g.V(v1Id).as('a').out().has('age').where(gt('a')).by('age').values('name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out)
              (has :age)
              (where (P/gt "a"))
                (by :age)
              (values :name)))

(defn get_g_withSideEffectXa_josh_peterX_VX1X_outXcreatedX_inXcreatedX_name_whereXwithinXaXX
  "g.withSideEffect('a', Arrays.asList('josh', 'peter')).V(v1Id).out('created').in('created').values('name').where(P.within('a'))"
  [g v1Id]
  (traverse g (with-side-effect :a (java.util.ArrayList. ["josh", "peter"]))
              (V v1Id)
              (out :created)
              (in :created)
              (values :name)
              (where (P/within (util/str-array ["a"])))))

(defn get_g_V_asXaX_outXcreatedX_whereXasXaX_name_isXjoshXX_inXcreatedX_name
  "g.V().as('a').out('created').where(as('a').values('name').is('josh')).in('created').values('name')"
  [g]
  (traverse g (V) (as :a)
              (out :created)
              (where (__ (as :a) (values :name) (is "josh")))
              (in :created)
              (values :name)))

