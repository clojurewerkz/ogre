(ns clojurewerkz.ogre.suite.drop-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_outE_drop
  "g.V().outE().drop()"
  [g]
  (traverse g (V)
              (outE)
              (drop)))

(defn get_g_V_properties_drop
  "g.V().properties().drop()"
  [g]
  (traverse g (V)
              (properties)
              (drop)))

(defn get_g_V_drop
  "g.V().drop()"
  [g]
  (traverse g (V)
              (drop)))
