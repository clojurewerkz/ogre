(ns clojurewerkz.ogre.side-effect.tree-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-tree-step
  (testing "test_g_v1_out_out_treeXnameX_cap"
    (let [g (g/new-tinkergraph)
          tree (q/query (v/find-by-id g 1)
                         q/-->
                         q/-->
                         (q/get-tree! #(v/get % :name)))]
      (is (= "marko" (:value tree)))
      (is (= "josh" (get-in tree [:children 0 :value])))
      (is (=
            (sort-by :value [{:value "lop"} {:value "ripple"}])
            (sort-by :value (get-in tree [:children 0 :children])))))))