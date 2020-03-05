(ns clojurewerkz.ogre.suite.element-map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_elementMap
  "g.V().elementMap()"
  [g]
  (traverse g (V)
              (element-map)))

(defn get_g_V_elementMapXname_ageX
  "g.V().elementMap('name', 'age')"
  [g]
  (traverse g (V)
              (element-map :name :age)))

(defn get_g_EX11X_elementMap
  "g.E(11).elementMap()"
  [g e11Id]
  (traverse g (E e11Id)
              (element-map)))