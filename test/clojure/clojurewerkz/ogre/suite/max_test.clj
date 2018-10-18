(ns clojurewerkz.ogre.suite.max-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_maxX
  "g.V().hasLabel('software').group().by('name').by(bothE().values('weight').max())"
  [g]
  (traverse g (V)
              (has-label :software)
              (group)
                (by :name)
                (by (__ (bothE) (values :weight) (max)))))

(defn get_g_V_repeatXbothX_timesX5X_age_max
  "g.V().repeat(both()).times(5).values('age').max()"
  [g]
  (traverse g (V)
              (repeat (__ (both)))
                (times 5)
              (values :age)
              (max)))

(defn get_g_V_age_max
  "g.V().values('age').max()"
  [g]
  (traverse g (V)
              (values :age)
              (max)))
