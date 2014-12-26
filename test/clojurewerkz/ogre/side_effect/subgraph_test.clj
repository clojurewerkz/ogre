(ns clojurewerkz.ogre.side-effect.subgraph-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-subgraph-step
  (testing "g.v(1).outE.subgraph(e->e.label()=='knows').cap()"
    (let [g (u/classic-tinkergraph)
          elem (v/find-by-id g (int 1))
          sg (q/query elem
               q/-E>
               (q/subgraph #(= (e/label-of %) :knows))
              q/get-capped!)
          labels (q/query (e/get-all-edges sg)
                          q/label
                          q/into-set!)]
      (is (= #{"knows"} labels)))))






