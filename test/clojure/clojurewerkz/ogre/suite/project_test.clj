(ns clojurewerkz.ogre.suite.project-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Order P)))

(defn get_g_V_hasLabelXpersonX_projectXa_bX_byXoutE_countX_byXageX
  "g.V().hasLabel('person').project('a', 'b').by(__.outE().count()).by('age')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/project :a :b)
                (q/by (q/__ (q/outE) (q/count)))
                (q/by :age)))


(defn get_g_V_outXcreatedX_projectXa_bX_byXnameX_byXinXcreatedX_countX_order_byXselectXbX__decrX_selectXaX
  "g.V().out('created').project('a', 'b').by('name').by(__.in('created').count())
        .order().by(__.select('b'), Order.decr).select('a')"
  [g]
  (q/traverse g (q/V)
                (q/out :created)
                (q/project :a :b)
                (q/by :name)
                (q/by (q/__ (q/in :created) (q/count)))
                (q/order)
                (q/by (q/__ (q/select :b)) (q/sort :decr))
                (q/select :a)))
