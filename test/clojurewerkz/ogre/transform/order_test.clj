(ns clojurewerkz.ogre.transform.order-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]))

(deftest test-order-step
  (testing "g(g.getVertices).property('name').order"
    (let [g (g/new-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/property :name)
                         q/order
                         q/into-vec!)]
      (is (= ["josh" "lop" "marko""peter" "ripple" "vadas"] names))))
  (testing "g(g.getVertices).property('name').order(ab)"
    (let [g (g/new-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/property :name)
                         (q/order #(compare %2 %1))
                         q/into-vec!)]
      (is (= ["vadas""ripple""peter" "marko" "lop" "josh"] names))))

  (testing "g(g.getVertices).order(a.name>b.name).property('name')"
    (let [g (g/new-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/order (fn [a b]
                                    (compare (v/get a :name)
                                             (v/get b :name))))
                         (q/property :name)
                         q/into-vec!)]
      (is (= (sort ["vadas" "ripple" "peter" "marko" "lop" "josh"]) (sort names)))))

  (testing "g(g.getVertices).property('name').order(decr)"
    (let [g (g/new-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/property :name)
                         q/reverse
                         q/into-vec!)]
      (is (= ["vadas""ripple""peter" "marko" "lop" "josh"] names)))))
