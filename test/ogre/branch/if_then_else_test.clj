(ns ogre.branch.if-then-else-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-if-then-else-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_ifThenElseXlang_eq_java__it__outX_name"
    (let [vs (q/query (g/find-by-id 1)
                      (q/-->)
                      (q/if-then-else (partial g/prop-pred :lang = "java")
                                      identity
                                      #(q/query % q/--> q/to-list))
                      (q/into-vec))]
      (= 3 (count vs))
      (= 2 (count (set vs))))))                        