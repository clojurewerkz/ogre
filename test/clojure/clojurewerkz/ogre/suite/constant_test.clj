(ns clojurewerkz.ogre.suite.constant-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_chooseXhasLabelXpersonX_valuesXnameX_constantXinhumanXX
  "g.V().choose(hasLabel('person'), values('name'), constant('inhuman'))"
  [g]
  (traverse g (V)
              (choose (__ (has-label :person)) (__ (values :name)) (__ (constant "inhuman")))))

(defn get_g_V_constantX123X
  "g.V().constant(123)"
  [g]
  (traverse g (V)
              (constant (int 123))))
