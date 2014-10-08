(ns clojurewerkz.ogre.branch.if-then-else-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-if-then-else-step
  (testing "test_g_v1_out_ifThenElseXlang_eq_java__it__outX_name"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g 1)
                      q/-->
                      (q/if-then-else (partial u/prop-pred :lang = "java")
                                      identity
                                      #(q/query % q/--> q/into-vec!))
                      q/into-vec!)]
      (is (= 3 (count vs)))
      (is (= 2 (count (set vs)))))))
