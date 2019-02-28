(ns clojurewerkz.ogre.suite.mean-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_age_mean
  "g.V().values('age').mean()"
  [g]
  (traverse g (V)
              (values :age)
              (mean)))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_meanX
  "g.V().hasLabel('software').group().by('name').by(bothE().values('weight').mean())"
  [g]
  (traverse g (V)
              (has-label :software)
              (group)
                (by :name)
                (by (__ (bothE) (values :weight) (mean)))))

(defn get_g_V_age_fold_meanXlocalX
  "g.V().values('age').fold().mean(Scope.local)"
  [g]
  (traverse g
            (V)
            (values :age)
            (fold)
            (mean (scope :local))))

(defn get_g_V_foo_mean
  "g.V().values('foo').mean()"
  [g]
  (traverse g
            (V)
            (values :foo)
            (mean)))

(defn get_g_V_foo_fold_meanXlocalX
  "g.V().values('foo').fold().mean(Scope.local)"
  [g]
  (traverse g
            (V)
            (values :foo)
            (fold)
            (mean (scope :local))))


