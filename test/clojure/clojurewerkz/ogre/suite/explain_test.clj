(ns clojurewerkz.ogre.suite.explain-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_outE_identity_inV_explain
  "g.V().outE().identity().inV().explain()"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/identity)
                (q/inV)
                (q/explain)))
