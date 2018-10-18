(ns clojurewerkz.ogre.suite.aggregate-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_name_aggregateXxX_capXxX
  "g.V().values('name').aggregate('x').cap('x')"
  [g]
  (traverse g (V)
              (values :name)
              (aggregate :x)
              (cap :x)))

(defn get_g_V_out_aggregateXaX_path
  "g.V().out().aggregate('a').path()"
  [g]
  (traverse g (V)
              (out)
              (aggregate :a)
              (path)))

(defn get_g_V_aggregateXxX_byXnameX_capXxX
  "g.V().aggregate('x').by('name').cap('x')"
  [g]
  (traverse g (V)
              (aggregate :x)
                (by :name)
              (cap :x)))

(defn get_g_V_hasLabelXpersonX_aggregateXxX_byXageX_capXxX_asXyX_selectXyX
  "g.V().hasLabel('person').aggregate('x').by('age').cap('x').as('y').select('y')"
  [g]
  (traverse g (V)
              (has-label :person)
              (aggregate :x)
                (by :age)
              (cap :x)
              (as :y)
              (select :y)))
