(ns clojurewerkz.ogre.suite.or-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_orXoutEXknowsX__hasXlabel_softwareX_or_hasXage_gte_35XX_name
  "g.V().or(outE('knows'), has(T.label, 'software').or().has('age', P.gte(35))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/or (q/__ (q/outE :knows))
                      (q/__ (q/has (T/label) "software") (q/or) (q/has :age (P/gte 35))))
                (q/values :name)))

(defn get_g_V_asXaX_orXselectXaX_selectXaXX
  "g.V().as('a').or(select('a'), select('a'))"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/or (q/__ (q/select :a) (q/select :a)))))

(defn get_g_V_orXhasXage_gt_27X__outE_count_gte_2X_name
  "g.V().or(has('age', P.gt(27)), outE().count().is(P.gte(2l))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/or (q/__ (q/has :age (P/gt 27)))
                      (q/__ (q/outE) (q/count) (q/is (P/gte (long 2)))))
                (q/values :name)))
