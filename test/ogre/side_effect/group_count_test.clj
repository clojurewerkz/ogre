(ns ogre.side-effect.group-count-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-group-count-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_V_outXcreatedX_groupCountXm__nameX"
    (let [group (q/query (g/get-vertices)
                         (q/--> [:created])                         
                         (q/get-group-count! (partial g/get-property :name)))]
      (is (= group {"lop" 3 "ripple" 1}))))  
  (testing "test_g_V_outXcreatedX_groupCountXm__name__plus_2X"
    (let [group (q/query (g/get-vertices)
                         (q/--> [:created])
                         (q/get-group-count! (partial g/get-property :name)
                                            (fn [a b] (+ 2 b))))]
      (is (= group {"lop" 6 "ripple" 2})))))
