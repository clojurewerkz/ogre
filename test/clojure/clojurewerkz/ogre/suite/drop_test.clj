(ns clojurewerkz.ogre.suite.drop-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_outE_drop
  "g.V().outE().drop()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/drop)))

(defn get_g_V_properties_drop
  "g.V().properties().drop()"
  [g]
  (q/traverse g (q/V)
                (q/properties)
                (q/drop)))

(defn get_g_V_drop
  "g.V().drop()"
  [g]
  (q/traverse g (q/V)
                (q/drop)))
