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
      (print tree)
      (is (= "marko" (:value tree)))
      (is (= "josh" (get-in tree [:children 0 :value])))
      (is (=
            (sort-by :value [{:value "lop"} {:value "ripple"}])
            (sort-by :value (get-in tree [:children 0 :children])))))))