(ns clojurewerkz.ogre.suite.properties-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_V_hasXageX_propertiesXname_ageX_value
  "g.V().has('age').properties('name', 'age').value()"
  [g]
  (traverse g (V)
              (has :age)
              (properties :name :age)
              (value)))

(defn get_g_V_hasXageX_propertiesXage_nameX_value
  "g.V().has('age').properties('age', 'name').value()"
  [g]
  (traverse g (V)
              (has :age)
              (properties :age :name)
              (value)))

(defn get_g_V_hasXageX_properties_hasXid_nameIdX_value
  "g.V().has('age').properties().has(T.id, nameId).value()"
  [g nameId]
  (traverse g (V)
              (has :age)
              (properties)
              (has (T/id) nameId)
              (value)))

(defn get_g_V_hasXageX_propertiesXnameX
  "g.V().has('age').<String>properties('name')"
  [g]
  (traverse g (V)
              (has :age)
              (properties :name)))

(defn get_g_injectXg_VX1X_propertiesXnameX_nextX_value
  "g.<VertexProperty<String>>inject((VertexProperty) g.V(v1Id).properties('name').next()).value()"
  [g v1Id]
  (let [p (traverse g (V v1Id) (properties :name) (next!))]
    (traverse g (inject p)
                (value))))

