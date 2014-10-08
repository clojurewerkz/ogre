(ns clojurewerkz.ogre.branch.loop-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-loop-step
  (testing "test_g_v1_out_loopX1_loops_lt_3X_propertyXnameX"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/find-by-id g 1)
                         q/-->
                         (q/loop 1
                                 (fn [l o p] (< l 3)))
                         (q/property :name)
                         (q/into-vec!))]
      (is (= 2 (count names)))
      (is (= #{"ripple" "lop"} (set names)))))
  (testing "test_g_v1_asXhereX_out_loopXhere_loops_lt_3X_propertyXnameX"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/find-by-id g 1)
                         (q/as "here")
                         q/-->
                         (q/loop-to "here"
                                    (fn [l o p] (< l 3)))
                         (q/property :name)
                         (q/into-vec!))]
      (is (= 2 (count names)))
      (is (= #{"ripple" "lop"} (set names))))))
