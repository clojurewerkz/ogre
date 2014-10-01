(ns clojurewerkz.ogre.transform.order-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

(deftest test-order-step
  (testing "g(g.getVertices).property('name').order"
    (let [g (g/use-new-tinker-graph!)
          names (q/query (g/get-vertices g)
                         (q/property :name)
                         q/order
                         q/into-vec!)]
      (is (= ["josh" "lop" "marko""peter" "ripple" "vadas"] names))))
  (testing "g(g.getVertices).property('name').order(ab)"
    (let [g (g/use-new-tinker-graph!)
          names (q/query (g/get-vertices g)
                         (q/property :name)
                         (q/order #(compare %2 %1))
                         q/into-vec!)]
      (is (= ["vadas""ripple""peter" "marko" "lop" "josh"] names))))

  (testing "g(g.getVertices).order(a.name>b.name).property('name')"
    (let [g (g/use-new-tinker-graph!)
          names (q/query (g/get-vertices g)
                         (q/order (fn [a b]
                                    (compare (g/get-property :name b)
                                             (g/get-property :name a))))
                         (q/property :name)
                         q/into-vec!)]
      (is (= ["vadas""ripple""peter" "marko" "lop" "josh"] names))))

  (testing "g(g.getVertices).property('name').order(decr)"
    (let [g (g/use-new-tinker-graph!)
          names (q/query (g/get-vertices g)
                         (q/property :name)
                         q/reverse
                         q/into-vec!)]
      (is (= ["vadas""ripple""peter" "marko" "lop" "josh"] names)))))
