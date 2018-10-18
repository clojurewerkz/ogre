(ns clojurewerkz.ogre.suite.fold-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Operator)))

(defn get_g_V_fold
  "g.V().fold()"
  [g]
  (traverse g (V)
              (fold)))

(defn get_g_V_fold_unfold
  "g.V().fold().unfold()"
  [g]
  (traverse g (V)
              (fold)
              (unfold)))

(defn get_g_V_age_foldX0_plusX
  "g.V().values('age').fold(0, Operator.sum)"
  [g]
  (traverse g (V)
              (values :age)
              (fold (int 0) (operator :sum))))
