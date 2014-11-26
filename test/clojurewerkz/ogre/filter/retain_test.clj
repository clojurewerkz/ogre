(ns clojurewerkz.ogre.filter.retain-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-retain-step
  (testing "g.v(1).out().retain([g.v(2)])"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/retain [(v/find-by-id g (int 2))])
                      q/into-vec!)]
      (is (= 1 (count vs)))
      (is (= "vadas" (v/get (first vs) :name)))))
  (testing "g.v(1).out().retain(g.v(2))"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/retain (v/find-by-id g (int 2)))
                      q/into-vec!)]
      (is (= 1 (count vs)))
      (is (= "vadas" (v/get (first vs) :name)))))
  (testing "g.v(1).out().aggregate().retain([g.v(2)])"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/retain [(v/find-by-id g (int 2))])
                      q/into-vec!)]
      ;;TODO finish this once aggregrate is finished
      )))
