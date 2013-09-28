(ns ogre.branch.if-then-else-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.pipe :as op]
            [ogre.test-util :as u]))

(defn print-vals [vals]
  (doseq [v vals]
    (println (list (type v) v))))


(deftest test-if-then-else-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_ifThenElseXlang_eq_java__it__outX_name"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/if-then-else (partial u/prop-pred :lang = "java")
                                      identity
                                      #(q/query % q/--> q/to-list!))
                      q/into-vec!)]
      (is (= 3 (count vs)))
      (is (= 2 (count (set vs)))))))

(deftest test-if-then-else-step-2
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_ifThenElseXlang_eq_java__it__outX_name"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/if-then-else (partial u/prop-pred :lang = "java")
                                      identity
                                      ;; explicit use of to-java-array-list! which is interpretable by the
                                      ;; pipes/gremlin graph walking code.
                                      #(q/query % q/--> op/to-java-array-list!))
                      op/into-vec!)]
      (is (= 3 (count vs)))
      (is (= 2 (count (set vs)))))))                        


(defn show-the-problem-above []
  (g/use-new-tinker-graph!)
  (let [vs (q/query (g/find-by-id 1)
                    q/-->
                    (q/if-then-else (partial u/prop-pred :lang = "java")
                                    identity
                                    #(q/query % q/--> op/to-java-array-list!))
                    q/path
                    q/into-vec!)
        vs2 (q/query (g/find-by-id 1)
                     q/-->
                     (q/if-then-else (partial u/prop-pred :lang = "java")
                                     identity
                                     #(q/query % q/--> op/to-list!))
                     q/path
                     op/into-vec!)]
    ;; When to-list return the empty clojure list,  the pipes/gremlin code does not realize
    ;; that this is a failure,  so it merily returns this as a path with a null vertex.
    (println "First case correctly determines that the path can be pruned away.")
    (println (type vs))
    (print-vals vs)
    (println "Second case keeps it around.")
    (println (type vs2))
    (print-vals vs2)))
