(ns clojurewerkz.ogre.suite.or-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_orXoutEXknowsX__hasXlabel_softwareX_or_hasXage_gte_35XX_name
  "g.V().or(outE('knows'), has(T.label, 'software').or().has('age', P.gte(35))).values('name')"
  [g]
  (traverse g (V)
              (or (__ (outE :knows))
                  (__ (has (T/label) "software") (or) (has :age (P/gte 35))))
              (values :name)))

(defn get_g_V_asXaX_orXselectXaX_selectXaXX
  "g.V().as('a').or(select('a'), select('a'))"
  [g]
  (traverse g (V) (as :a)
              (or (__ (select :a) (select :a)))))

(defn get_g_V_orXhasXage_gt_27X__outE_count_gte_2X_name
  "g.V().or(has('age', P.gt(27)), outE().count().is(P.gte(2l))).values('name')"
  [g]
  (traverse g (V)
              (or (__ (has :age (P/gt 27)))
                  (__ (outE) (count) (is (P/gte (long 2)))))
              (values :name)))
