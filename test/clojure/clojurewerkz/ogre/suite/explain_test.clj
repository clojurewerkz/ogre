(ns clojurewerkz.ogre.suite.explain-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_outE_identity_inV_explain
  "g.V().outE().identity().inV().explain()"
  [g]
  (traverse g (V)
              (outE)
              (identity)
              (inV)
              (explain)))
