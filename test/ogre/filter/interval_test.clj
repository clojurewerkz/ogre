(ns ogre.filter.interval-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-interval-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_outE_intervalXweight_0_06X_inV"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--E>)
                      (q/interval :weight 0 0.6)
                      (q/in-vertex)
                      (q/into-vec))]
      (is (= #{"lop" "vadas"} (g/get-names-set vs))))))