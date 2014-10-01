(ns clojurewerkz.ogre.side-effect.group-count-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-group-count-step
  (testing "test_g_V_outXcreatedX_groupCountXm__nameX"
    (let [g (g/use-new-tinker-graph!)
          group (q/query (g/get-vertices g)
                         (q/--> [:created])                         
                         (q/get-group-count! (partial g/get-property :name)))]
      (is (= group {"lop" 3 "ripple" 1}))))  
  (testing "test_g_V_outXcreatedX_groupCountXm__name__plus_2X"
    (let [g (g/use-new-tinker-graph!)
          group (q/query (g/get-vertices g)
                         (q/--> [:created])
                         (q/get-group-count! (partial g/get-property :name)
                                            (fn [a b] (+ 2 b))))]
      (is (= group {"lop" 6 "ripple" 2})))))
