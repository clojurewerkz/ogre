(ns clojurewerkz.ogre.suite.value-map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_valueMap
  "g.V().valueMap()"
  [g]
  (traverse g (V) (value-map)))

(defn get_g_V_valueMapXname_ageX
  "g.V().valueMap('name', 'age')"
  [g]
  (traverse g (V) (value-map :name :age)))

(defn get_g_VX1X_outXcreatedX_valueMap
  "g.V(v1Id).out('created').valueMap()"
  [g v1Id]
  (traverse g (V v1Id) (out :created) (value-map)))

(defn get_g_V_hasLabelXpersonX_filterXoutEXcreatedXX_valueMapXtrueX
  "g.V().hasLabel('person').filter(__.outE('created')).valueMap(true)"
  [g]
  (traverse g (V) (has-label :person)
              (filter (__ (outE :created)))
              (value-map true)))

(defn get_g_V_valueMapXtrueX
  "g.V().valueMap(true)"
  [g]
  (traverse g (V) (value-map true)))

(defn get_g_V_valueMapXtrue_name_ageX
  "g.V().valueMap(true, 'name','age')"
  [g]
  (traverse g (V) (value-map true :name :age)))


