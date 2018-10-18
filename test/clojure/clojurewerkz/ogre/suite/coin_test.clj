(ns clojurewerkz.ogre.suite.coin-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_coinX1X
  "g.V().coin(1.0d)"
  [g]
  (traverse g (V)
              (coin 1.0)))

(defn get_g_V_coinX0X
  "g.V().coin(0.0d)"
  [g]
  (traverse g (V)
              (coin 0.0)))
