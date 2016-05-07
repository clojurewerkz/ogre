(ns clojurewerkz.ogre.suite.mean-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_age_mean
  "g.V().values('age').mean()"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/mean)))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_meanX
  "g.V().hasLabel('software').group().by('name').by(bothE().values('weight').mean())"
  [g]
  (q/traverse g (q/V)
                (q/has-label :software)
                (q/group)
                (q/by :name)
                (q/by (q/__ (q/bothE) (q/values :weight) (q/mean)))))


