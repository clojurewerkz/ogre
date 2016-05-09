(ns clojurewerkz.ogre.suite.min-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_repeatXbothX_timesX5X_age_min
  "g.V().repeat(both()).times(5).values('age').min()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both)))
                (q/times 5)
                (q/values :age)
                (q/min)))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_minX
  "g.V().hasLabel('software').group().by('name').by(bothE().values('weight').min())"
  [g]
  (q/traverse g (q/V)
                (q/has-label :software)
                (q/group)
                (q/by :name)
                (q/by (q/__ (q/bothE) (q/values :weight) (q/min)))))

(defn get_g_V_age_min
  "g.V().values('age').min()"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/min)))
