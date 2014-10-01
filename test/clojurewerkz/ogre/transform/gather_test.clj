(ns clojurewerkz.ogre.transform.gather-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]))

(deftest test-gather-step
  (testing "g(v1).out.gather"
    (is (= #{"2" "3" "4"}
           (q/query (v/find-by-id (g/new-tinkergraph)1)
                    q/-->
                    q/id
                    q/gather
                    q/first-into-set!))))
  (testing "g(v1).out.gather(identity)"
    (is (= #{"2" "3" "4"}
           (q/query (v/find-by-id (g/new-tinkergraph) 1)
                    q/-->
                    q/id
                    (q/gather identity)
                    q/first-into-set!)))))
