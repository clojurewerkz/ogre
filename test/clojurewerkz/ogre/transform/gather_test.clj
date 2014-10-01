(ns clojurewerkz.ogre.transform.gather-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

(deftest test-gather-step
  (testing "g(v1).out.gather"
    (is (= #{"2" "3" "4"}
           (q/query (g/find-by-id (g/use-new-tinker-graph!)1)
                    q/-->
                    q/id
                    q/gather
                    q/first-into-set!))))
  (testing "g(v1).out.gather(identity)"
    (is (= #{"2" "3" "4"}
           (q/query (g/find-by-id (g/use-new-tinker-graph!) 1)
                    q/-->
                    q/id
                    (q/gather identity)
                    q/first-into-set!)))))
