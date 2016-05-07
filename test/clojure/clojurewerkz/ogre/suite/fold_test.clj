(ns clojurewerkz.ogre.suite.fold-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Operator P)))

(defn get_g_V_fold
  "g.V().fold()"
  [g]
  (q/traverse g (q/V)
                (q/fold)))

(defn get_g_V_fold_unfold
  "g.V().fold().unfold()"
  [g]
  (q/traverse g (q/V)
                (q/fold)
                (q/unfold)))

(defn get_g_V_age_foldX0_plusX
  "g.V().values('age').fold(0, Operator.sum)"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/fold (int 0) (Operator/sum))))
