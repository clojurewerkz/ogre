(ns clojurewerkz.ogre.suite.value-map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal.step.util WithOptions)))

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

(defn get_g_V_valueMapXtrueX
  "g.V().valueMap(true)"
  [g]
  (traverse g (V) (value-map true)))

(defn get_g_V_valueMapXtrue_name_ageX
  "g.V().valueMap(true, 'name','age')"
  [g]
  (traverse g (V) (value-map true :name :age)))

(defn get_g_V_valueMap_withXtokensX
  "g.V().valueMap().with(WithOptions.tokens)"
  [g]
  (traverse g (V) (value-map) (with (WithOptions/tokens))))

(defn get_g_V_hasLabelXpersonX_filterXoutEXcreatedXX_valueMap_withXtokensX
  "g.V().hasLabel(\"person\").filter(__.outE(\"created\")).valueMap().with(WithOptions.tokens)"
  [g]
  (traverse g
            (V)
            (has-label :person)
            (filter (__ (outE :created)))
            (value-map) (with (WithOptions/tokens))))

(defn get_g_V_valueMapXname_ageX_withXtokensX
  "g.V().valueMap(\"name\", \"age\").with(WithOptions.tokens)"
  [g]
  (traverse g
            (V)
            (value-map :name :age) (with (WithOptions/tokens))))

(defn get_g_V_valueMapXname_ageX_withXtokens_labelsX_byXunfoldX
  "g.V().valueMap(\"name\", \"age\").with(WithOptions.tokens, WithOptions.labels).by(__.unfold())"
  [g]
  (traverse g
            (V)
            (value-map :name :age) (with (WithOptions/tokens) (WithOptions/labels)) (by (__ (unfold)))))

(defn get_g_VX1X_valueMapXname_locationX_byXunfoldX_by
  "g.V(v1Id).valueMap(\"name\",\"location\").by(__.unfold()).by()"
  [g v1Id]
  (traverse g
            (V v1Id)
            (value-map :name :location) (by (__ (unfold))) (by)))