(ns clojurewerkz.ogre.suite.min-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_repeatXbothX_timesX5X_age_min
  "g.V().repeat(both()).times(5).values('age').min()"
  [g]
  (traverse g (V)
              (repeat (__ (both)))
                (times 5)
              (values :age)
              (min)))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_minX
  "g.V().hasLabel('software').group().by('name').by(bothE().values('weight').min())"
  [g]
  (traverse g (V)
              (has-label :software)
              (group)
                (by :name)
                (by (__ (bothE) (values :weight) (min)))))

(defn get_g_V_age_min
  "g.V().values('age').min()"
  [g]
  (traverse g (V)
              (values :age)
              (min)))

(defn get_g_V_foo_injectX9999999999X_min
  "g.V().values('foo').inject(9999999999L).min()"
  [g]
  (traverse g (V)
              (values :foo)
              (inject (long 9999999999))
              (min)))
