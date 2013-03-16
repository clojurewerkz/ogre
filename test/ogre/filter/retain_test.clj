(ns ogre.filter.retain-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-range-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_retainXg_v2X"
    (let [vs (q/query (g/find-by-id 1)
                      (q/-->)
                      (q/retain [(g/find-by-id 2)])
                      (q/into-vec!))]
      (is (= 1 (count vs)))
      (is (= "vadas" (g/get-property :name (first vs))))))

  (testing "test_g_v1_out_aggregateXxX_out_retainXxX"
    (let [vs (q/query (g/find-by-id 1)
                      (q/-->)
                      (q/retain [(g/find-by-id 2)])
                      (q/into-vec!))]
      ;;TODO finish this once aggregrate is finished
)))                        