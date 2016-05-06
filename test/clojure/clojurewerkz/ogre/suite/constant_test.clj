(ns clojurewerkz.ogre.suite.constant-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_chooseXhasLabelXpersonX_valuesXnameX_constantXinhumanXX
  "g.V().choose(hasLabel('person'), values('name'), constant('inhuman'))"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/has-label :person)) (q/__ (q/values :name)) (q/__ (q/constant "inhuman")))))

(defn get_g_V_constantX123X
  "g.V().constant(123)"
  [g]
  (q/traverse g (q/V)
                (q/constant (int 123))))
