(ns clojurewerkz.ogre.suite.and-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_andXhasXage_gt_27X__outE_count_gte_2X_name
  "g.V().and(has('age', P.gt(27)), outE().count().is(P.gte(2l))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/and (q/__ (q/has :age (P/gt 27)))
                       (q/__ (q/outE) (q/count) (q/is (P/gte (long 2)))))
                (q/values :name)))

(defn get_g_V_andXoutE__hasXlabel_personX_and_hasXage_gte_32XX_name
  "g.V().and(outE(), has(T.label, 'person').and().has('age', P.gte(32))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/and (q/__ (q/outE))
                       (q/__ (q/has T/label :person) (q/and) (q/has :age (P/gte 32))))
                (q/values :name)))

(defn get_g_V_asXaX_outXknowsX_and_outXcreatedX_inXcreatedX_asXaX_name
  "g.V().as('a').out('knows').and().out('created').in('created').as('a').values('name')"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/out :knows)
                (q/and)
                (q/out :created)
                (q/in :created)
                (q/as :a)
                (q/values :name)))

(defn get_g_V_asXaX_andXselectXaX_selectXaXX
  "g.V().as('a').and(select('a'), select('a'))"
  [g]
  (q/traverse g (q/V)
                (q/as :a)
                (q/and (q/__ (q/select :a)) (q/__ (q/select :a)))))
