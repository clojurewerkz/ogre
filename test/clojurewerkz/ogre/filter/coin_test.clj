(ns clojurewerkz.ogre.filter.coin-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-coin-step
  (testing "g.v(1).out().coin(0)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/coin 0)
                      q/into-vec!)]
      (is (= 0 (count vs)))))

  (testing "g.v(1).out().coin(1)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
               q/-->
               (q/coin 1)
               q/into-vec!)]
      (is (= 3 (count vs)))))

  (testing "g.v(1).out().coin(0.5)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
               q/-->
               (q/coin 0.5)
               q/into-vec!)
          count (count vs)]
      (is (and (>= count 0) (<= count 3))))))
