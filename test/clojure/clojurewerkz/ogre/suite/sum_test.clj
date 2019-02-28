(ns clojurewerkz.ogre.suite.sum-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_age_sum
  "g.V().values('age').sum()"
  [g]
  (traverse g (V)
              (values :age)
              (sum)))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_sumX
  "g.V().hasLabel('software').<String, Number>group().by('name').by(bothE().values('weight').sum())"
  [g]
  (traverse g (V)
              (has-label :software)
              (group)
                (by :name)
                (by (__ (bothE) (values :weight) (sum)))))

(defn get_g_V_age_fold_sumXlocalX
  "g.V().values('age').fold().sum(Scope.local)"
  [g]
  (traverse g 
            (V)
            (values :age)
            (fold)
            (sum (scope :local))))

(defn get_g_V_foo_sum
  "g.V().values('foo').sum()"
  [g]
  (traverse g
            (V)
            (values :foo)
            (sum)))

(defn get_g_V_foo_fold_sumXlocalX
  "g.V().values('foo').fold().sum(Scope.local)"
  [g]
  (traverse g
            (V)
            (values :foo)
            (fold)
            (sum (scope :local))))