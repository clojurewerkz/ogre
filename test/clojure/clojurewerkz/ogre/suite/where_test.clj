(ns clojurewerkz.ogre.suite.where-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_eqXbXX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where('a', eq('b'))"
  [g]
  (q/traverse g (q/V)
                (q/has :age) (q/as :a)
                (q/out)
                (q/in)
                (q/has :age) (q/as :b)
                (q/select :a :b)
                (q/where :a (P/eq "b"))))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_neqXbXX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where('a', neq('b'))"
  [g]
  (q/traverse g (q/V)
                (q/has :age) (q/as :a)
                (q/out)
                (q/in)
                (q/has :age) (q/as :b)
                (q/select :a :b)
                (q/where :a (P/neq "b"))))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXb_hasXname_markoXX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where(as('b').has('name', 'marko'))"
  [g]
  (q/traverse g (q/V)
                (q/has :age) (q/as :a)
                (q/out)
                (q/in)
                (q/has :age) (q/as :b)
                (q/select :a :b)
                (q/where (q/__ (q/as :b) (q/has :name "marko")))))

(defn get_g_V_hasXageX_asXaX_out_in_hasXageX_asXbX_selectXa_bX_whereXa_outXknowsX_bX
  "g.V().has('age').as('a').out().in().has('age').as('b').select('a','b').where(as('a').out('knows').as('b'))"
  [g]
  (q/traverse g (q/V)
                (q/has :age) (q/as :a)
                (q/out)
                (q/in)
                (q/has :age) (q/as :b)
                (q/select :a :b)
                (q/where (q/__ (q/as :a) (q/out :knows) (q/as :b)))))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXa_neqXbXX_name
  "g.V(v1Id).as('a').out('created').in('created').as('b').where('a', neq('b')).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/in :created) (q/as :b)
                (q/where :a (P/neq "b"))
                (q/values :name)))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_asXbX_whereXasXbX_outXcreatedX_hasXname_rippleXX_valuesXage_nameX
  "g.V(v1Id).as('a').out('created').in('created').as('b').where(as('b').out('created').has('name', 'ripple')).values('age', 'name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/in :created) (q/as :b)
                (q/where (q/__ (q/as :b) (q/out :created) (q/has :name "ripple")))
                (q/values :age :name)))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXeqXaXX_name
  "g.V(v1Id).as('a').out('created').in('created').where(eq('a')).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/in :created)
                (q/where (P/eq "a"))
                (q/values :name)))

(defn get_g_VX1X_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_name
  "g.V(v1Id).as('a').out('created').in('created').where(neq('a')).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/in :created)
                (q/where (P/neq "a"))
                (q/values :name)))

(defn get_g_VX1X_out_aggregateXxX_out_whereXnotXwithinXaXXX
  "g.V(v1Id).out().aggregate('x').out().where(not(within('x')))"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/aggregate :x)
                (q/out)
                (q/where (P/not (P/within ["x"])))))

(defn get_g_withSideEffectXa_graph_verticesX2XX_VX1X_out_whereXneqXaXX
  "g.withSideEffect('a', g.V(v2Id).next()).V(v1Id).out().where(neq('a'))"
  [g v1Id v2Id]
  (q/traverse g (q/with-side-effect :a (.next (q/traverse g (q/V v2Id))))
                (q/V v1Id)
                (q/out)
                (q/where (P/neq "a"))))

(defn get_g_VX1X_repeatXbothEXcreatedX_whereXwithoutXeXX_aggregateXeX_otherVX_emit_path
  "g.V(v1Id).repeat(bothE('created').where(without('e')).aggregate('e').otherV()).emit().path()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/bothE :created) (q/where (P/without ["e"])) (q/aggregate :e) (q/otherV)))
                (q/emit)
                (q/path)))

(defn get_g_V_whereXnotXoutXcreatedXXX_name
  "g.V().where(not(out('created'))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/where (q/__ (q/not (q/__ (q/out :created)))))
                (q/values :name)))

(defn get_g_V_asXaX_out_asXbX_whereXandXasXaX_outXknowsX_asXbX__orXasXbX_outXcreatedX_hasXname_rippleX__asXbX_inXknowsX_count_isXnotXeqX0XXXXX_selectXa_bX
  "g.V().as('a').out().as('b').where(and(as('a').out('knows').as('b'),
                                         or(as('b').out('created').has('name', 'ripple'),
                                            as('b').in('knows').count().is(not(eq(0)))))).select('a','b')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/where (q/__ (q/and (q/__ (q/as :a)
                                            (q/out :knows) (q/as :b))
                                      (q/__ (q/or (q/__ (q/as :b) (q/out :created) (q/has :name "ripple"))
                                                  (q/__ (q/as :b)
                                                        (q/in :knows)
                                                        (q/count)
                                                        (q/is (P/not (P/eq 0)))))))))
                (q/select :a :b)))

(defn get_g_V_whereXoutXcreatedX_and_outXknowsX_or_inXknowsXX_valuesXnameX
  "g.V().where(out('created').and().out('knows').or().in('knows')).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/where (q/__ (q/out :created)
                               (q/and)
                               (q/out :knows)
                               (q/or)
                               (q/in :knows)))
                (q/values :name)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_whereXandXasXbX_in__notXasXaX_outXcreatedX_hasXname_rippleXXX_selectXa_bX
  "g.V().as('a').out('created').as('b').where(and(as('b').in(), not(as('a').out('created').has('name', 'ripple')))).select('a','b')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :created) (q/as :b)
                (q/where (q/__ (q/and (q/__ (q/as :b) (q/in))
                                      (q/__ (q/not (q/__ (q/as :a) (q/out :created) (q/has :name "ripple")))))))
                (q/select :a :b)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_bothXknowsX_bothXknowsX_asXdX_whereXc__notXeqXaX_orXeqXdXXXX_selectXa_b_c_dX
  "g.V().as('a').out('created').as('b').in('created').as('c').both('knows').both('knows').as('d').
        where('c', P.not(P.eq('a').or(P.eq('d')))).select('a','b','c','d')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :created) (q/as :b)
                (q/in :created) (q/as :c)
                (q/both :knows)
                (q/both :knows) (q/as :d)
                (q/where :c (P/not (.or (P/eq "a") (P/eq "d"))))
                (q/select :a :b :c :d)))

(defn get_g_V_asXaX_out_asXbX_whereXin_count_isXeqX3XX_or_whereXoutXcreatedX_and_hasXlabel_personXXX_selectXa_bX
  "g.V().as('a').out().as('b').where(as('b').in().count().is(eq(3)).or().where(as('b').out('created').and().as('b').has(T.label, 'person'))).select('a','b')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/where (q/__ (q/as :b) (q/in) (q/count) (q/is (P/eq 3))
                               (q/or)
                               (q/where (q/__ (q/as :b) (q/out :created) (q/and) (q/as :b) (q/has (T/label) "person")))))
                (q/select :a :b)))
