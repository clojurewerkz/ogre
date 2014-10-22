(ns clojurewerkz.ogre.filter.has-not-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-has-not-step
  (testing "test_g_V_hasNotXageX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has-not :age)
                      (q/into-vec!))]
      (is (= 2 (count vs)))
      (is (= (#{"lop" "ripple"} (u/get-names vs)))))))
