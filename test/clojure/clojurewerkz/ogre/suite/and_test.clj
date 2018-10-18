(ns clojurewerkz.ogre.suite.and-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_andXhasXage_gt_27X__outE_count_gte_2X_name
  "g.V().and(has('age', P.gt(27)), outE().count().is(P.gte(2l))).values('name')"
  [g]
  (traverse g (V)
              (and (__ (has :age (P/gt 27)))
                   (__ (outE) (count) (is (P/gte (long 2)))))
              (values :name)))

(defn get_g_V_andXoutE__hasXlabel_personX_and_hasXage_gte_32XX_name
  "g.V().and(outE(), has(T.label, 'person').and().has('age', P.gte(32))).values('name')"
  [g]
  (traverse g (V)
              (and (__ (outE))
                   (__ (has T/label :person) (and) (has :age (P/gte 32))))
              (values :name)))

(defn get_g_V_asXaX_outXknowsX_and_outXcreatedX_inXcreatedX_asXaX_name
  "g.V().as('a').out('knows').and().out('created').in('created').as('a').values('name')"
  [g]
  (traverse g (V)
              (as :a)
              (out :knows)
              (and)
              (out :created)
              (in :created)
              (as :a)
              (values :name)))

(defn get_g_V_asXaX_andXselectXaX_selectXaXX
  "g.V().as('a').and(select('a'), select('a'))"
  [g]
  (traverse g (V)
              (as :a)
              (and (__ (select :a)) (__ (select :a)))))
