(ns clojurewerkz.ogre.suite.sample-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_E_sampleX1X
  "g.E().sample(1)"
  [g]
  (traverse g (E)
              (sample 1)))

(defn get_g_E_sampleX2X_byXweightX
  "g.E().sample(2).by('weight')"
  [g]
  (traverse g (E)
              (sample 2)
                (by :weight)))

(defn get_g_V_localXoutE_sampleX1X_byXweightXX
  "g.V().local(outE().sample(1).by('weight'))"
  [g]
  (traverse g (V)
              (local (__ (outE) (sample 1) (by :weight)))))

(defn get_g_V_group_byXlabelX_byXbothE_weight_sampleX2X_foldX
  "g.V().group().by(T.label).by(bothE().values('weight').sample(2).fold())"
  [g]
  (traverse g (V)
              (group)
                (by (T/label))
                (by (__ (bothE) (values :weight) (sample 2) (fold)))))

(defn get_g_V_group_byXlabelX_byXbothE_weight_fold_sampleXlocal_5XX
  "g.V().group().by(T.label).by(bothE().values('weight').fold().sample(Scope.local, 5))"
  [g]
  (traverse g (V)
              (group)
                (by (T/label))
                (by (__ (bothE) (values :weight) (fold) (sample (scope :local) 5)))))
