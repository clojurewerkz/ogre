(ns ogre.transform.gather-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-gather-step
  (g/use-new-tinker-graph!)
  (testing "g(v1).out.gather"
    (is (= #{"2" "3" "4"}
           (q/query (g/find-by-id 1)
                    q/-->
                    q/id
                    q/gather
                    q/first-into-set!))))
  (testing "g(v1).out.gather(identity)"
    (is (= #{"2" "3" "4"}
           (q/query (g/find-by-id 1)
                    q/-->
                    q/id
                    (q/gather identity)
                    q/first-into-set!)))))
