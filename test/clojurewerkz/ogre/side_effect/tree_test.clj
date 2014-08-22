(ns clojurewerkz.ogre.side-effect.tree-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-tree-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_v1_out_out_treeXnameX_cap"
    (let [tree (q/query (g/find-by-id 1)
                         q/-->
                         q/-->
                         (q/get-tree! (partial g/get-property :name)))]
      (is (= "marko" (:value tree)))
      (is (= "josh" (get-in tree [:children 0 :value])))
      (is (= "lop" (get-in tree [:children 0 :children 0 :value])))
      (is (= "ripple" (get-in tree [:children 0 :children 1 :value]))))))