(ns clojurewerkz.ogre.filter.local-limit-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-local-limit-step
  (testing "g.V.outE().localLimit(1).inV().limit(3)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      q/-E>
                      (q/local-limit 1)
                      q/in-vertex
                      (q/limit 3)
                      q/into-vec!)]
      (is (= 3 (count vs))))))
