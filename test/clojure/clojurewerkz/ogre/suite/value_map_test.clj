(ns clojurewerkz.ogre.suite.value-map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_valueMap
  "g.V().valueMap()"
  [g]
  (q/traverse g (q/V) (q/value-map)))

(defn get_g_V_valueMapXname_ageX
  "g.V().valueMap('name', 'age')"
  [g]
  (q/traverse g (q/V) (q/value-map :name :age)))

(defn get_g_VX1X_outXcreatedX_valueMap
  "g.V(v1Id).out('created').valueMap()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/out :created) (q/value-map)))

(defn get_g_V_hasLabelXpersonX_filterXoutEXcreatedXX_valueMapXtrueX
  "g.V().hasLabel('person').filter(__.outE('created')).valueMap(true)"
  [g]
  (q/traverse g
              (q/V) (q/has-label :person)
              (q/filter (q/__ (q/outE :created)))
              (q/value-map true)))

(defn get_g_V_valueMapXtrueX
  "g.V().valueMap(true)"
  [g]
  (q/traverse g (q/V) (q/value-map true)))

(defn get_g_V_valueMapXtrue_name_ageX
  "g.V().valueMap(true, 'name','age')"
  [g]
  (q/traverse g (q/V) (q/value-map true :name :age)))


