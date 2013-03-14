(ns ogre.filter.range-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-range-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_rangeX0_1X"
    (let [vs (q/query (g/find-by-id 1)
                      (q/-->)
                      (q/range 0 1)
                      (q/into-vec))]
      (is (= 2 (count vs)))))
  (testing "test_g_v1_outXknowsX_outEXcreatedX_rangeX0_0X_inV"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--> :knows)
                      (q/--E> :created)
                      (q/range 0 0)
                      (q/in-vertex)
                      (q/into-vec))]
      (is (some #{"ripple" "lop"} (g/get-names vs)))
      (is (= 1 (count vs)))))
  (testing "test_g_v1_outXknowsX_outXcreatedX_rangeX0_0X"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--> :knows)
                      (q/--> :created)
                      (q/range 0 0)
                      (q/into-vec))]
      (is (some #{"ripple" "lop"} (g/get-names vs)))
      (is (= 1 (count vs)))))
  (testing "test_g_v1_outXcreatedX_inXcreatedX_rangeX1_2X"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--> :created)
                      (q/<-- :created)
                      (q/range 1 2)
                      (q/into-vec))]
      (is (some #{"josh" "peter" "marko"} (g/get-names vs)))
      (is (= 2 (count vs)))))
  (testing "test_g_v1_outXcreatedX_inEXcreatedX_rangeX1_2X_outV"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--> :created)
                      (q/<E-- :created)
                      (q/range 1 2)
                      q/out-vertex
                      (q/into-vec))]
      (is (some #{"josh" "peter" "marko"} (g/get-names vs)))
      (is (= 2 (count vs))))))