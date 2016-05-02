(ns clojurewerkz.ogre.suite.sum-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_valuesXageX_sum
  "g.V().values('age').sum()"
  [g]
  (q/traverse g (q/V)
                (q/values :age)
                (q/sum)))

(defn get_g_V_hasLabelXsoftwareX_group_byXnameX_byXbothE_weight_sumX
  "g.V().hasLabel('software').<String, Number>group().by('name').by(bothE().values('weight').sum())"
  [g]
  (q/traverse g (q/V)
                (q/has-label :software)
                (q/group)
                (q/by :name)
                (q/by (q/__ (q/bothE) (q/values :weight) (q/sum)))))
