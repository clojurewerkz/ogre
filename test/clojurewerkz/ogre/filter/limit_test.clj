(ns clojurewerkz.ogre.filter.limit-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-limit-step
  (testing "g.v(1).out().limit(2)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/limit 2)
                      q/into-vec!)]
      (is (= 2 (count vs))))))
