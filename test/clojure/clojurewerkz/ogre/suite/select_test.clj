(ns clojurewerkz.ogre.suite.select-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure Column T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Order P Scope Pop)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX
  "g.V(v1Id).as('a').out('knows').as('b').select('a', 'b')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :knows) (q/as :b)
                (q/select :a :b)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX_byXnameX
  "g.V(v1Id).as('a').out('knows').as('b').select('a', 'b').by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :knows) (q/as :b)
                (q/select :a :b)
                (q/by :name)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX
  "g.V(v1Id).as('a').out('knows').as('b').select('a')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :knows) (q/as :b)
                (q/select :a)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX_byXnameX
  "g.V(v1Id).as('a').out('knows').as('b').select('a').by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :knows) (q/as :b)
                (q/select :a)
                (q/by :name)))

(defn get_g_V_asXaX_out_asXbX_selectXa_bX_byXnameX
  "g.V().as('a').out().as('b').select('a', 'b').by('name')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/select :a :b)
                (q/by :name)))

(defn get_g_V_asXaX_out_aggregateXxX_asXbX_selectXa_bX_byXnameX
  "g.V().as('a').out().aggregate('x').as('b').select('a', 'b').by('name')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out)
                (q/aggregate :x) (q/as :b)
                (q/select :a :b)
                (q/by :name)))

(defn get_g_V_asXaX_name_order_asXbX_selectXa_bX_byXnameX_by_XitX
  "g.V().as('a').values('name').order().as('b').select('a', 'b').by('name').by()"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/values :name)
                (q/order) (q/as :b)
                (q/select :a :b)
                (q/by :name)
                (q/by)))

(defn get_g_V_selectXaX
  "final GraphTraversal<Vertex, Vertex> root = g.V();
   return null == pop ? root.select('a') : root.select(pop, 'a');"
  [g pop]
  (if (nil? pop)
    (q/traverse g (q/V) (q/select :a))
    (q/traverse g (q/V) (q/select pop :a))))

(defn get_g_V_selectXa_bX
  "final GraphTraversal<Vertex, Vertex> root = g.V();
   return null == pop ? root.select('a', 'b') : root.select(pop, 'a', 'b');"
  [g pop]
  (if (nil? pop)
    (q/traverse g (q/V) (q/select :a :b))
    (q/traverse g (q/V) (q/select pop :a :b))))

(defn get_g_V_valueMap_selectXpop_aX
  "final GraphTraversal<Vertex, Map<String, Object>> root = g.V().valueMap();
   return null == pop ? root.select('a') : root.select(pop, 'a');"
  [g pop]
  (if (nil? pop)
    (q/traverse g (q/V) (q/value-map) (q/select :a))
    (q/traverse g (q/V) (q/value-map) (q/select pop :a))))

(defn get_g_V_valueMap_selectXpop_a_bX
  "final GraphTraversal<Vertex, Vertex> root = g.V().valueMap();
   return null == pop ? root.select('a', 'b') : root.select(pop, 'a', 'b');"
  [g pop]
  (if (nil? pop)
    (q/traverse g (q/V) (q/value-map) (q/select :a :b))
    (q/traverse g (q/V) (q/value-map) (q/select pop :a :b))))

(defn get_g_V_untilXout_outX_repeatXin_asXaX_in_asXbXX_selectXa_bX_byXnameX
  "g.V().until(__.out().out()).repeat(__.in().as('a').in().as('b')).select('a', 'b').by('name')"
  [g]
  (q/traverse g (q/V)
                (q/until (q/__ (q/out) (q/out)))
                (q/repeat (q/__ (q/in) (q/as :a) (q/in) (q/as :b)))
                (q/select :a :b)
                (q/by :name)))

(defn get_g_V_untilXout_outX_repeatXin_asXaXX_selectXaX_byXtailXlocalX_nameX
  "g.V().until(__.out().out()).repeat(__.in().as('a')).select('a').by(__.tail(local).values('name'))"
  [g]
  (q/traverse g (q/V)
                (q/until (q/__ (q/out) (q/out)))
                (q/repeat (q/__ (q/in) (q/as :a)))
                (q/select :a)
                (q/by (q/__ (q/tail (q/scope :local)) (q/values :name)))))

(defn get_g_V_outE_weight_groupCount_unfold_selectXvaluesX_unfold
  "g.V().outE().values('weight').groupCount().unfold().select(values).unfold()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/values :weight)
                (q/group-count)
                (q/unfold)
                (q/select (Column/valueOf "values"))
                (q/unfold)))

(defn get_g_V_outE_weight_groupCount_selectXvaluesX_unfold_groupCount_selectXvaluesX_unfold
  "g.V().outE().values('weight').groupCount().select(values).unfold().groupCount().select(values).unfold()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/values :weight)
                (q/group-count)
                (q/select (Column/valueOf "values"))
                (q/unfold)
                (q/group-count)
                (q/select (Column/valueOf "values"))
                (q/unfold)))

(defn get_g_V_outE_weight_groupCount_selectXkeysX_unfold
  "g.V().outE().values('weight').groupCount().select(keys).unfold()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/values :weight)
                (q/group-count)
                (q/select (Column/keys))
                (q/unfold)))

(defn get_g_V_outE_weight_groupCount_unfold_selectXkeysX_unfold
  "g.V().outE().values('weight').groupCount().unfold().select(keys).unfold()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/values :weight)
                (q/group-count)
                (q/unfold)
                (q/select (Column/keys))
                (q/unfold)))

(defn get_g_V_asXa_bX_out_asXcX_path_selectXkeysX
  "g.V().as('a', 'b').out().as('c').path().select(keys)"
  [g]
  (q/traverse g (q/V) (q/as :a :b)
                (q/out) (q/as :c)
                (q/path)
                (q/select (Column/keys))))

(defn get_g_V_asXaX_outXknowsX_asXbX_localXselectXa_bX_byXnameXX
  "g.V().as('a').out('knows').as('b').local(__.select('a', 'b').by('name'))"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :knows) (q/as :b)
                (q/local (q/__ (q/select :a :b) (q/by :name)))))

(defn get_g_V_hasXname_gremlinX_inEXusesX_order_byXskill_incrX_asXaX_outV_asXbX_selectXa_bX_byXskillX_byXnameX
  "g.V().has('name', 'gremlin').inE('uses').order().by('skill', Order.incr).as('a')
                                           .outV().as('b')
                                           .select('a', 'b').by('skill').by('name')"
  [g]
  (q/traverse g (q/V)
                (q/has :name "gremlin")
                (q/inE :uses)
                (q/order)
                (q/by :skill (Order/incr)) (q/as :a)
                (q/outV) (q/as :b)
                (q/select :a :b)
                (q/by :skill)
                (q/by :name)))

(defn get_g_V_hasXname_isXmarkoXX_asXaX_selectXaX
  "g.V().has('name', __.is('marko')).as('a').select('a')"
  [g]
  (q/traverse g (q/V)
                (q/has :name (q/__ (q/is "marko"))) (q/as :a)
                (q/select :a)))

(defn get_g_V_hasLabelXpersonX_asXpX_mapXbothE_label_groupCountX_asXrX_selectXp_rX
  "g.V().hasLabel('person').as('p').map(__.bothE().label().groupCount()).as('r').select('p', 'r')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person) (q/as :p)
                (q/map (q/__ (q/bothE) (q/label) (q/group-count))) (q/as :r)
                (q/select :p :r)))

(defn get_g_V_chooseXoutE_count_isX0X__asXaX__asXbXX_chooseXselectXaX__selectXaX__selectXbXX
  "g.V().choose(__.outE().count().is(0L), __.as('a'),
                                          __.as('b')).choose(__.select('a'), __.select('a'), __.select('b'))"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/outE) (q/count) (q/is (long 0)))
                          (q/__ (q/as :a))
                          (q/__ (q/as :b) (q/choose (q/__ (q/select :a)) (q/__ (q/select :a)) (q/__ (q/select :b)))))))

(defn get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX
  "g.V(v4Id).out().as('here').has('lang', 'java').select('here')"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/out) (q/as :here)
                (q/has :lang "java")
                (q/select :here)))

(defn get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX_name
  "g.V(v4Id).out().as('here').has('lang', 'java').select('here').values('name')"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/out) (q/as :here)
                (q/has :lang "java")
                (q/select :here)
                (q/values :name)))

(defn get_g_VX1X_outE_asXhereX_inV_hasXname_vadasX_selectXhereX
  "g.V(v1Id).outE().as('here').inV().has('name', 'vadas').select('here')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE) (q/as :here)
                (q/inV)
                (q/has :name "vadas")
                (q/select :here)))

(defn get_g_VX1X_outEXknowsX_hasXweight_1X_asXhereX_inV_hasXname_joshX_selectXhereX
  "g.V(v1Id).outE('knows').has('weight', 1.0d).as('here').inV().has('name', 'josh').select('here')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE :knows)
                (q/has :weight 1.0) (q/as :here)
                (q/inV)
                (q/has :name "josh")
                (q/select :here)))

(defn get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_inV_hasXname_joshX_selectXhereX
  "g.V(v1Id).outE('knows').as('here').has('weight', 1.0d).inV().has('name', 'josh').select('here')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE :knows) (q/as :here)
                (q/has :weight 1.0)
                (q/inV)
                (q/has :name "josh")
                (q/select :here)))

(defn get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_asXfakeX_inV_hasXname_joshX_selectXhereX
  "g.V(v1Id).outE('knows').as('here').has('weight', 1.0d).as('fake').inV().has('name', 'josh').select('here')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/outE :knows) (q/as :here)
                (q/has :weight 1.0) (q/as :fake)
                (q/inV)
                (q/has :name "josh")
                (q/select :here)))

(defn get_g_V_asXhereXout_name_selectXhereX
  "g.V().as('here').out().values('name').select('here')"
  [g]
  (q/traverse g (q/V) (q/as :here)
                (q/out)
                (q/values :name)
                (q/select :here)))

(defn get_g_V_outXcreatedX_unionXasXprojectX_inXcreatedX_hasXname_markoX_selectXprojectX__asXprojectX_inXcreatedX_inXknowsX_hasXname_markoX_selectXprojectXX_groupCount_byXnameX
  "g.V().out('created')
        .union(as('project').in('created').has('name', 'marko').select('project'),
               as('project').in('created').in('knows').has('name', 'marko').select('project'))
        .groupCount().by('name')"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/union (q/__ (q/as :project) (q/in :created) (q/has :name "marko") (q/select :project))
                         (q/__ (q/as :project) (q/in :created) (q/in :knows) (q/has :name "marko") (q/select :project)))
                (q/group-count)
                (q/by :name)))

(defn get_g_V_asXaX_hasXname_markoX_asXbX_asXcX_selectXa_b_cX_by_byXnameX_byXageX
  "g.V().as('a').has('name', 'marko').as('b').as('c').select('a', 'b', 'c').by().by('name').by('age')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/has :name "marko") (q/as :b) (q/as :c)
                (q/select :a :b :c)
                (q/by)
                (q/by :name)
                (q/by :age)))

(defn get_g_V_hasLabelXsoftwareX_asXnameX_asXlanguageX_asXcreatorsX_selectXname_language_creatorsX_byXnameX_byXlangX_byXinXcreatedX_name_fold_orderXlocalXX
  "g.V().hasLabel('software').as('name').as('language').as('creators').select('name', 'language', 'creators').by('name').by('lang').
                    by(__.in('created').values('name').fold().order(local))"
  [g]
  (q/traverse g (q/V)
                (q/has-label :software) (q/as :name) (q/as :language) (q/as :creators)
                (q/select :name :language :creators)
                (q/by :name)
                (q/by :lang)
                (q/by (q/__ (q/in :created) (q/values :name) (q/fold) (q/order (q/scope :local))))))

(defn get_g_V_asXaX_whereXoutXknowsXX_selectXaX
  "g.V().as('a').where(__.out('knows')).select('a')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/where (q/__ (q/out :knows)))
                (q/select :a)))

(defn get_g_V_outE_weight_groupCount_selectXvaluesX_unfold
  "g.V().outE().values('weight').groupCount().select(values).unfold()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/values :weight)
                (q/group-count)
                (q/select (Column/valueOf "values"))
                (q/unfold)))

(defn get_g_V_label_groupCount_asXxX_selectXxX
  "g.V().label().groupCount().as('x').select('x')"
  [g]
  (q/traverse g (q/V)
                (q/label)
                (q/group-count) (q/as :x)
                (q/select :x)))

(defn get_g_VX1X_asXhereX_out_selectXhereX
  "g.V(v1Id).as('here').out().select('here')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :here)
                (q/out)
                (q/select :here)))

(defn get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXlast_aX
  "g.V(v1Id).as('a').repeat(__.out().as('a')).times(2).select(Pop.last, 'a')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/repeat (q/__ (q/out) (q/as :a)))
                  (q/times 2)
                (q/select (Pop/last) :a)))


(defn get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXfirst_aX
  "g.V(v1Id).as('a').repeat(__.out().as('a')).times(2).select(Pop.first, 'a')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/repeat (q/__ (q/out) (q/as :a)))
                  (q/times 2)
                (q/select (Pop/first) :a)))
