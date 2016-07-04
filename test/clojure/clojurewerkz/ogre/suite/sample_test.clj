(ns clojurewerkz.ogre.suite.sample-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Scope)))

(defn get_g_E_sampleX1X
  "g.E().sample(1)"
  [g]
  (q/traverse g (q/E)
                (q/sample 1)))

(defn get_g_E_sampleX2X_byXweightX
  "g.E().sample(2).by('weight')"
  [g]
  (q/traverse g (q/E)
                (q/sample 2)
                (q/by :weight)))

(defn get_g_V_localXoutE_sampleX1X_byXweightXX
  "g.V().local(outE().sample(1).by('weight'))"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/outE) (q/sample 1) (q/by :weight)))))

(defn get_g_V_group_byXlabelX_byXbothE_weight_sampleX2X_foldX
  "g.V().group().by(T.label).by(bothE().values('weight').sample(2).fold())"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by (T/label))
                (q/by (q/__ (q/bothE) (q/values :weight) (q/sample 2) (q/fold)))))

(defn get_g_V_group_byXlabelX_byXbothE_weight_fold_sampleXlocal_5XX
  "g.V().group().by(T.label).by(bothE().values('weight').fold().sample(Scope.local, 5))"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by (T/label))
                (q/by (q/__ (q/bothE) (q/values :weight) (q/fold) (q/sample (q/scope :local) 5)))))
