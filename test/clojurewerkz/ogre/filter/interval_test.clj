(ns clojurewerkz.ogre.filter.interval-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-interval-step
  (testing "test_g_v1_outE_intervalXweight_0_06X_inV"
    (let [g (g/new-tinkergraph)
          vs (q/query (v/find-by-id g 1)
                      (q/-E>)
                      (q/interval :weight 0 0.6)
                      (q/in-vertex)
                      (q/into-vec!))]
      (is (= #{"lop" "vadas"} (u/get-names-set vs))))))