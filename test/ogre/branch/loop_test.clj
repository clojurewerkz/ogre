(ns ogre.branch.loop-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-loop-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_loopX1_loops_lt_3X_propertyXnameX"
    (let [names (q/query (g/find-by-id 1)
                         (q/-->)
                         (q/loop 1
                                 (fn [l o p] (< l 3)))
                         (q/property :name)
                         (q/into-vec))]
      (is (= 2 (count names)))
      (is (= #{"ripple" "lop"} (set names)))))
  (testing "test_g_v1_asXhereX_out_loopXhere_loops_lt_3X_propertyXnameX"
    (let [names (q/query (g/find-by-id 1)
                         (q/as "here")
                         (q/-->)
                         (q/loop-to "here"
                                    (fn [l o p] (< l 3)))
                         (q/property :name)
                         (q/into-vec))]
      (is (= 2 (count names)))
      (is (= #{"ripple" "lop"} (set names)))))
  (testing "  test_g_V_out_loopX1_loops_lt_3X_propertyXnameX()"
    (let [names (q/query (g/get-vertices)
                         (q/-->)
                         (q/loop 1 (fn [l o p] (< l 3)))
                         (q/property :name)
                         (q/into-vec))]
      (is (= 2 (count names)))
      (is (= #{"ripple" "lop"} (set names)))))

  (testing "test_g_V_out_loopX1_loops_lt_3X_propertyXnameX"
    (let [names (q/query (g/get-vertices)
                         (q/as "here")
                         (q/-->)
                         (q/loop-to "here" (fn [l o p] (< l 3)))
                         (q/property :name)
                         (q/into-vec))]
      (is (= 2 (count names)))
      (is (= #{"ripple" "lop"} (set names))))))                        