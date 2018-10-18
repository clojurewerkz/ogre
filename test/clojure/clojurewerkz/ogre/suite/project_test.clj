(ns clojurewerkz.ogre.suite.project-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_hasLabelXpersonX_projectXa_bX_byXoutE_countX_byXageX
  "g.V().hasLabel('person').project('a', 'b').by(__.outE().count()).by('age')"
  [g]
  (traverse g (V)
              (has-label :person)
              (project :a :b)
                (by (__ (outE) (count)))
                (by :age)))


(defn get_g_V_outXcreatedX_projectXa_bX_byXnameX_byXinXcreatedX_countX_order_byXselectXbX__decrX_selectXaX
  "g.V().out('created').project('a', 'b').by('name').by(__.in('created').count())
        .order().by(__.select('b'), Order.decr).select('a')"
  [g]
  (traverse g (V)
              (out :created)
              (project :a :b)
                (by :name)
                (by (__ (in :created) (count)))
              (order)
                (by (__ (select :b)) (sort :decr))
              (select :a)))
