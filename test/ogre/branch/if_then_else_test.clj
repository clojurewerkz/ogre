(ns ogre.branch.if-then-else-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-if-then-else-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_ifThenElseXlang_eq_java__it__outX_name"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/if-then-else (partial u/prop-pred :lang = "java")
                                      identity
                                      #(q/query % q/--> q/into-vec!))
                      q/into-vec!)]
      (is (= 3 (count vs)))
      (is (= 2 (count (set vs)))))))                        
