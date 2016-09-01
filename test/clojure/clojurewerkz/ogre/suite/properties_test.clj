(ns clojurewerkz.ogre.suite.properties-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXageX_propertiesXname_ageX_value
  "g.V().has('age').properties('name', 'age').value()"
  [g]
  (q/traverse g (q/V)
                (q/has :age)
                (q/properties :name :age)
                (q/value)))

(defn get_g_V_hasXageX_propertiesXage_nameX_value
  "g.V().has('age').properties('age', 'name').value()"
  [g]
  (q/traverse g (q/V)
                (q/has :age)
                (q/properties :age :name)
                (q/value)))

(defn get_g_V_hasXageX_properties_hasXid_nameIdX_value
  "g.V().has('age').properties().has(T.id, nameId).value()"
  [g nameId]
  (q/traverse g (q/V)
                (q/has :age)
                (q/properties)
                (q/has (T/id) nameId)
                (q/value)))

(defn get_g_V_hasXageX_propertiesXnameX
  "g.V().has('age').<String>properties('name')"
  [g]
  (q/traverse g (q/V)
                (q/has :age)
                (q/properties :name)))

