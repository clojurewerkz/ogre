(ns clojurewerkz.ogre.side-effect.group-count-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-group-count-step
  (testing "test_g_V_outXcreatedX_groupCountXm__nameX"
    (let [g (u/classic-tinkergraph)
          group (q/query (v/get-all-vertices g)
                         (q/--> [:created])
                         (q/get-group-count! #(v/get % :name)))]
      (is (= group {"lop" 3 "ripple" 1}))))
  (testing "test_g_V_outXcreatedX_groupCountXm__name__plus_2X"
    (let [g (u/classic-tinkergraph)
          group (q/query (v/get-all-vertices g)
                         (q/--> [:created])
                         (q/get-group-count! #(v/get % :name)
                                            (fn [a b] (+ 2 b))))]
      (is (= group {"lop" 6 "ripple" 2})))))
