(ns ogre.transform.gather-test
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory))
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-gather-step
  (g/use-new-tinker-graph!)
  (testing "g(v1).out.gather"
    (is (= #{"2" "3" "4"}
           (q/query (g/find-by-id 1)
                    q/-->
                    q/id
                    q/gather
                    q/first-into-set))))
  (testing "g(v1).out.gather(identity)"
    (is (= #{"2" "3" "4"}
           (q/query (g/find-by-id 1)
                    q/-->
                    q/id
                    (q/gather identity)
                    q/first-into-set)))))
