(ns clojurewerkz.ogre.transform.fold-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-fold-step
  (testing "g(v1).out.fold"
    (is (= #{2 3 4}
           (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                    q/-->
                    q/id
                    q/fold
                    q/first-into-set!))))
  (testing "g(v1).out.fold"
    (is (= 1
           (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                    q/-->
                    q/id
                    q/fold
                    q/count!)))))
