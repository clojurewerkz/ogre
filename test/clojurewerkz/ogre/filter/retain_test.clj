(ns clojurewerkz.ogre.filter.retain-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

(deftest test-range-step
  (testing "test_g_v1_out_retainXg_v2X"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/find-by-id g 1)
                      (q/-->)
                      (q/retain [(g/find-by-id g 2)])
                      (q/into-vec!))]
      (is (= 1 (count vs)))
      (is (= "vadas" (g/get-property :name (first vs))))))

  (testing "test_g_v1_out_aggregateXxX_out_retainXxX"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/find-by-id g 1)
                      (q/-->)
                      (q/retain [(g/find-by-id g 2)])
                      (q/into-vec!))]
      ;;TODO finish this once aggregrate is finished
)))                        