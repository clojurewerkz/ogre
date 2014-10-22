(ns clojurewerkz.ogre.filter.random-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-random-step
  (testing "test_g_v1_out_random0X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/-->)
                      (q/random 0)
                      (q/into-vec!))]
      (is (= 0 (count vs)))))

  (testing "test_g_v1_out_random1X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
               (q/-->)
               (q/random 1)
               (q/into-vec!))]
      (is (= 3 (count vs)))))

  (testing "test_g_v1_out_random0.5X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
               (q/-->)
               (q/random 0.5)
               (q/into-vec!))
          count (count vs)]
      (is (and (>= count 0) (<= count 3))))))