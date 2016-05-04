(ns clojurewerkz.ogre.suite.coin-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_coinX1X
  "g.V().coin(1.0d)"
  [g]
  (q/traverse g (q/V)
                (q/coin 1.0)))

(defn get_g_V_coinX0X
  "g.V().coin(0.0d)"
  [g]
  (q/traverse g (q/V)
                (q/coin 0.0)))
