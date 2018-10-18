(ns clojurewerkz.ogre.suite.flat-map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_asXaX_flatMapXselectXaXX
  "g.V().as('a').flatMap(select('a'))"
  [g]
  (traverse g (V) (as :a)
              (flat-map (__ (select :a)))))
