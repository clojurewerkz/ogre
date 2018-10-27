(ns clojurewerkz.ogre.suite.select-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Pop)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX
  "g.V(v1Id).as('a').out('knows').as('b').select('a', 'b')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :knows) (as :b)
              (select :a :b)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXa_bX_byXnameX
  "g.V(v1Id).as('a').out('knows').as('b').select('a', 'b').by('name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :knows) (as :b)
              (select :a :b)
                (by :name)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX
  "g.V(v1Id).as('a').out('knows').as('b').select('a')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :knows) (as :b)
              (select :a)))

(defn get_g_VX1X_asXaX_outXknowsX_asXbX_selectXaX_byXnameX
  "g.V(v1Id).as('a').out('knows').as('b').select('a').by('name')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :knows) (as :b)
              (select :a)
                (by :name)))

(defn get_g_V_asXaX_out_asXbX_selectXa_bX_byXnameX
  "g.V().as('a').out().as('b').select('a', 'b').by('name')"
  [g]
  (traverse g (V) (as :a)
              (out) (as :b)
              (select :a :b)
                (by :name)))

(defn get_g_V_asXaX_out_aggregateXxX_asXbX_selectXa_bX_byXnameX
  "g.V().as('a').out().aggregate('x').as('b').select('a', 'b').by('name')"
  [g]
  (traverse g (V) (as :a)
              (out)
              (aggregate :x) (as :b)
              (select :a :b)
                (by :name)))

(defn get_g_V_asXaX_name_order_asXbX_selectXa_bX_byXnameX_by_XitX
  "g.V().as('a').values('name').order().as('b').select('a', 'b').by('name').by()"
  [g]
  (traverse g (V) (as :a)
              (values :name)
              (order) (as :b)
              (select :a :b)
                (by :name)
                (by)))

(defn get_g_V_selectXaX
  "g.V().select('a')"
  [g]
  (traverse g (V) (select :a)))

(defn get_g_V_selectXa_bX
  "g.V().select('a','b')"
  [g]
  (traverse g (V) (select :a :b)))

(defn get_g_V_valueMap_selectXaX
  "g.V().valueMap().select('a')"
  [g]
  (traverse g (V) (value-map) (select :a)))

(defn get_g_V_valueMap_selectXa_bX
  "g.V().valueMap().select('a','b')"
  [g]
  (traverse g (V) (value-map) (select :a :b)))

(defn get_g_V_selectXfirst_aX
  "g.V().select(first, 'a')"
  [g]
  (traverse g (V) (select Pop/first :a)))

(defn get_g_V_selectXfirst_a_bX
  "g.V().select(first, 'a','b')"
  [g]
  (traverse g (V) (select Pop/first :a :b)))

(defn get_g_V_valueMap_selectXfirst_aX
  "g.V().valueMap().select(first,'a')"
  [g]
  (traverse g (V) (value-map) (select Pop/first :a)))

(defn get_g_V_valueMap_selectXfirst_a_bX
  "g.V().valueMap().select(first,'a','b')"
  [g]
  (traverse g (V) (value-map) (select Pop/first :a :b)))

(defn get_g_V_selectXlast_aX
  "g.V().select(last, 'a')"
  [g]
  (traverse g (V) (select Pop/last :a)))

(defn get_g_V_selectXlast_a_bX
  "g.V().select(last, 'a','b')"
  [g]
  (traverse g (V) (select Pop/last :a :b)))

(defn get_g_V_valueMap_selectXlast_aX
  "g.V().valueMap().select(last,'a')"
  [g]
  (traverse g (V) (value-map) (select Pop/last :a)))

(defn get_g_V_valueMap_selectXlast_a_bX
  "g.V().valueMap().select(last,'a','b')"
  [g]
  (traverse g (V) (value-map) (select Pop/last :a :b)))

(defn get_g_V_selectXall_aX
  "g.V().select(all, 'a')"
  [g]
  (traverse g (V) (select Pop/all :a)))

(defn get_g_V_selectXall_a_bX
  "g.V().select(all, 'a','b')"
  [g]
  (traverse g (V) (select Pop/all :a :b)))

(defn get_g_V_valueMap_selectXall_aX
  "g.V().valueMap().select(all,'a')"
  [g]
  (traverse g (V) (value-map) (select Pop/all :a)))

(defn get_g_V_valueMap_selectXall_a_bX
  "g.V().valueMap().select(all,'a','b')"
  [g]
  (traverse g (V) (value-map) (select Pop/all :a :b)))

(defn get_g_V_selectXaX_count
  "g.V().select('a').count()"
  [g]
  (traverse g (V) (select :a) (count)))

(defn get_g_V_untilXout_outX_repeatXin_asXaX_in_asXbXX_selectXa_bX_byXnameX
  "g.V().until(__.out().out()).repeat(__.in().as('a').in().as('b')).select('a', 'b').by('name')"
  [g]
  (traverse g (V)
              (until (__ (out) (out)))
              (repeat (__ (in) (as :a) (in) (as :b)))
              (select :a :b)
                (by :name)))

(defn get_g_V_untilXout_outX_repeatXin_asXaXX_selectXaX_byXtailXlocalX_nameX
  "g.V().until(__.out().out()).repeat(__.in().as('a')).select('a').by(__.tail(local).values('name'))"
  [g]
  (traverse g (V)
              (until (__ (out) (out)))
              (repeat (__ (in) (as :a)))
              (select :a)
                (by (__ (tail (scope :local)) (values :name)))))

(defn get_g_V_outE_weight_groupCount_unfold_selectXvaluesX_unfold
  "g.V().outE().values('weight').groupCount().unfold().select(values).unfold()"
  [g]
  (traverse g (V)
              (outE)
              (values :weight)
              (group-count)
              (unfold)
              (select (column :values))
              (unfold)))

(defn get_g_V_outE_weight_groupCount_selectXvaluesX_unfold_groupCount_selectXvaluesX_unfold
  "g.V().outE().values('weight').groupCount().select(values).unfold().groupCount().select(values).unfold()"
  [g]
  (traverse g (V)
              (outE)
              (values :weight)
              (group-count)
              (select (column :values))
              (unfold)
              (group-count)
              (select (column :values))
              (unfold)))

(defn get_g_V_outE_weight_groupCount_selectXkeysX_unfold
  "g.V().outE().values('weight').groupCount().select(keys).unfold()"
  [g]
  (traverse g (V)
              (outE)
              (values :weight)
              (group-count)
              (select (column :keys))
              (unfold)))

(defn get_g_V_outE_weight_groupCount_unfold_selectXkeysX_unfold
  "g.V().outE().values('weight').groupCount().unfold().select(keys).unfold()"
  [g]
  (traverse g (V)
              (outE)
              (values :weight)
              (group-count)
              (unfold)
              (select (column :keys))
              (unfold)))

(defn get_g_V_asXa_bX_out_asXcX_path_selectXkeysX
  "g.V().as('a', 'b').out().as('c').path().select(keys)"
  [g]
  (traverse g (V) (as :a :b)
              (out) (as :c)
              (path)
              (select (column :keys))))

(defn get_g_V_asXaX_outXknowsX_asXbX_localXselectXa_bX_byXnameXX
  "g.V().as('a').out('knows').as('b').local(__.select('a', 'b').by('name'))"
  [g]
  (traverse g (V) (as :a)
              (out :knows) (as :b)
              (local (__ (select :a :b) (by :name)))))

(defn get_g_V_hasXname_gremlinX_inEXusesX_order_byXskill_ascX_asXaX_outV_asXbX_selectXa_bX_byXskillX_byXnameX
  "g.V().has('name', 'gremlin').inE('uses').order().by('skill', Order.asc).as('a')
                                           .outV().as('b')
                                           .select('a', 'b').by('skill').by('name')"
  [g]
  (traverse g (V)
              (has :name "gremlin")
              (inE :uses)
              (order)
                (by :skill (sort :asc)) (as :a)
              (outV) (as :b)
              (select :a :b)
                (by :skill)
                (by :name)))

(defn get_g_V_hasXname_isXmarkoXX_asXaX_selectXaX
  "g.V().has('name', __.is('marko')).as('a').select('a')"
  [g]
  (traverse g (V)
              (has :name (__ (is "marko"))) (as :a)
              (select :a)))

(defn get_g_V_hasLabelXpersonX_asXpX_mapXbothE_label_groupCountX_asXrX_selectXp_rX
  "g.V().hasLabel('person').as('p').map(__.bothE().label().groupCount()).as('r').select('p', 'r')"
  [g]
  (traverse g (V)
              (has-label :person) (as :p)
              (map (__ (bothE) (label) (group-count))) (as :r)
              (select :p :r)))

(defn get_g_V_chooseXoutE_count_isX0X__asXaX__asXbXX_chooseXselectXaX__selectXaX__selectXbXX
  "g.V().choose(__.outE().count().is(0L), __.as('a'),
                                          __.as('b')).choose(__.select('a'), __.select('a'), __.select('b'))"
  [g]
  (traverse g (V)
              (choose (__ (outE) (count) (is (long 0)))
                      (__ (as :a))
                      (__ (as :b) (choose (__ (select :a)) (__ (select :a)) (__ (select :b)))))))

(defn get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX
  "g.V(v4Id).out().as('here').has('lang', 'java').select('here')"
  [g v4Id]
  (traverse g (V v4Id)
              (out) (as :here)
              (has :lang "java")
              (select :here)))

(defn get_g_VX4X_out_asXhereX_hasXlang_javaX_selectXhereX_name
  "g.V(v4Id).out().as('here').has('lang', 'java').select('here').values('name')"
  [g v4Id]
  (traverse g (V v4Id)
              (out) (as :here)
              (has :lang "java")
              (select :here)
              (values :name)))

(defn get_g_VX1X_outE_asXhereX_inV_hasXname_vadasX_selectXhereX
  "g.V(v1Id).outE().as('here').inV().has('name', 'vadas').select('here')"
  [g v1Id]
  (traverse g (V v1Id)
              (outE) (as :here)
              (inV)
              (has :name "vadas")
              (select :here)))

(defn get_g_VX1X_outEXknowsX_hasXweight_1X_asXhereX_inV_hasXname_joshX_selectXhereX
  "g.V(v1Id).outE('knows').has('weight', 1.0d).as('here').inV().has('name', 'josh').select('here')"
  [g v1Id]
  (traverse g (V v1Id)
              (outE :knows)
              (has :weight 1.0) (as :here)
              (inV)
              (has :name "josh")
              (select :here)))

(defn get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_inV_hasXname_joshX_selectXhereX
  "g.V(v1Id).outE('knows').as('here').has('weight', 1.0d).inV().has('name', 'josh').select('here')"
  [g v1Id]
  (traverse g (V v1Id)
              (outE :knows) (as :here)
              (has :weight 1.0)
              (inV)
              (has :name "josh")
              (select :here)))

(defn get_g_VX1X_outEXknowsX_asXhereX_hasXweight_1X_asXfakeX_inV_hasXname_joshX_selectXhereX
  "g.V(v1Id).outE('knows').as('here').has('weight', 1.0d).as('fake').inV().has('name', 'josh').select('here')"
  [g v1Id]
  (traverse g (V v1Id)
              (outE :knows) (as :here)
              (has :weight 1.0) (as :fake)
              (inV)
              (has :name "josh")
              (select :here)))

(defn get_g_V_asXhereXout_name_selectXhereX
  "g.V().as('here').out().values('name').select('here')"
  [g]
  (traverse g (V) (as :here)
              (out)
              (values :name)
              (select :here)))

(defn get_g_V_outXcreatedX_unionXasXprojectX_inXcreatedX_hasXname_markoX_selectXprojectX__asXprojectX_inXcreatedX_inXknowsX_hasXname_markoX_selectXprojectXX_groupCount_byXnameX
  "g.V().out('created')
        .union(as('project').in('created').has('name', 'marko').select('project'),
               as('project').in('created').in('knows').has('name', 'marko').select('project'))
        .groupCount().by('name')"
  [g]
  (traverse g (V)
              (out :created)
              (union (__ (as :project) (in :created) (has :name "marko") (select :project))
                     (__ (as :project) (in :created) (in :knows) (has :name "marko") (select :project)))
              (group-count)
                (by :name)))

(defn get_g_V_asXaX_hasXname_markoX_asXbX_asXcX_selectXa_b_cX_by_byXnameX_byXageX
  "g.V().as('a').has('name', 'marko').as('b').as('c').select('a', 'b', 'c').by().by('name').by('age')"
  [g]
  (traverse g (V) (as :a)
              (has :name "marko") (as :b) (as :c)
              (select :a :b :c)
                (by)
                (by :name)
                (by :age)))

(defn get_g_V_hasLabelXsoftwareX_asXnameX_asXlanguageX_asXcreatorsX_selectXname_language_creatorsX_byXnameX_byXlangX_byXinXcreatedX_name_fold_orderXlocalXX
  "g.V().hasLabel('software').as('name').as('language').as('creators').select('name', 'language', 'creators').by('name').by('lang').
                    by(__.in('created').values('name').fold().order(local))"
  [g]
  (traverse g (V)
              (has-label :software) (as :name) (as :language) (as :creators)
              (select :name :language :creators)
                (by :name)
                (by :lang)
                (by (__ (in :created) (values :name) (fold) (order (scope :local))))))

(defn get_g_V_asXaX_whereXoutXknowsXX_selectXaX
  "g.V().as('a').where(__.out('knows')).select('a')"
  [g]
  (traverse g (V) (as :a)
              (where (__ (out :knows)))
              (select :a)))

(defn get_g_V_outE_weight_groupCount_selectXvaluesX_unfold
  "g.V().outE().values('weight').groupCount().select(values).unfold()"
  [g]
  (traverse g (V)
              (outE)
              (values :weight)
              (group-count)
              (select (column :values))
              (unfold)))

(defn get_g_V_label_groupCount_asXxX_selectXxX
  "g.V().label().groupCount().as('x').select('x')"
  [g]
  (traverse g (V)
              (label)
              (group-count) (as :x)
              (select :x)))

(defn get_g_VX1X_asXhereX_out_selectXhereX
  "g.V(v1Id).as('here').out().select('here')"
  [g v1Id]
  (traverse g (V v1Id) (as :here)
              (out)
              (select :here)))

(defn get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXlast_aX
  "g.V(v1Id).as('a').repeat(__.out().as('a')).times(2).select(Pop.last, 'a')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (repeat (__ (out) (as :a)))
                (times 2)
              (select (Pop/last) :a)))


(defn get_g_VX1X_asXaX_repeatXout_asXaXX_timesX2X_selectXfirst_aX
  "g.V(v1Id).as('a').repeat(__.out().as('a')).times(2).select(Pop.first, 'a')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (repeat (__ (out) (as :a)))
                (times 2)
              (select (Pop/first) :a)))

(defn get_g_V_asXaX_outXknowsX_asXaX_selectXall_constantXaXX
  "g.V().as('a').out('knows').as('a').select(Pop.all, (Traversal) __.constant('a'))"
  [g]
  (traverse g V (as :a)
              (out :knows) (as :a)
              (select (Pop/all) (__ (constant "a")))))

(defn get_g_V_asXaX_groupXmX_by_byXbothE_countX_barrier_selectXmX_selectXselectXaXX_byXmathX_plus_XX
  "g.V().as('a').group('m').by().by(__.bothE().count()).barrier().select('m').<Double>select(__.select('a')).by(__.math('_+_'))"
  [g]
  (traverse g (V) (as :a)
              (group :m)
                (by)
                (by (__ (bothE) (count)))
              (barrier)
              (select :m)
              (select (__ (select :a)))
              (by (__ (math "_+_")))))

(defn get_g_V_asXaX_groupXmX_by_byXbothE_countX_barrier_selectXmX_selectXselectXaXX
  "g.V().as('a').group('m').by().by(__.bothE().count()).barrier().select('m').select(__.select('a'))"
  [g]
  (traverse g (V) (as :a)
              (group :m)
                (by)
                (by (__ (bothE) (count)))
              (barrier)
              (select :m)
              (select (__ (select :a)))))
