(ns clojurewerkz.ogre.filter.retain-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-range-step
  (testing "test_g_v1_out_retainXg_v2X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g 1)
                      (q/-->)
                      (q/retain [(v/find-by-id g 2)])
                      (q/into-vec!))]
      (is (= 1 (count vs)))
      (is (= "vadas" (v/get (first vs) :name)))))

  (testing "test_g_v1_out_aggregateXxX_out_retainXxX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g 1)
                      (q/-->)
                      (q/retain [(v/find-by-id g 2)])
                      (q/into-vec!))]
      ;;TODO finish this once aggregrate is finished
)))
