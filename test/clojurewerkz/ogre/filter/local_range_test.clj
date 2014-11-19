(ns clojurewerkz.ogre.filter.local-range-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-local-range-step
  (testing "g.V().localRange(2)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      q/-E>
                      (q/local-range 0 2)
                      q/into-vec!)]
      (is (= 5 (count vs))))))
