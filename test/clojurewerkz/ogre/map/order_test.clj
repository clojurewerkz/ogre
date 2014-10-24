(ns clojurewerkz.ogre.map.order-test
  (:use [clojure.test])
  (:import (com.tinkerpop.gremlin.structure Order))
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-order-step
  (testing "g.V().values('name').order()"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/values :name)
                         q/order
                         q/into-vec!)]
      (is (= ["josh" "lop" "marko""peter" "ripple" "vadas"] names))))

  (testing "g.V().values('name').order{a,b -> b.compareTo(a)}"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/values :name)
                         (q/order #(compare %2 %1))
                         q/into-vec!)]
      (is (= ["vadas" "ripple" "peter" "marko" "lop" "josh"] names))))

  (testing "g.V().order{a,b -> a.get().value('name').compareTo(b.get().value('name'))}.values('name')"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/order (fn [a b]
                                    (compare (v/get (.get a) :name)
                                             (v/get (.get b) :name))))
                         (q/values :name)
                         q/into-vec!)]
      (is (= (sort ["vadas" "ripple" "peter" "marko" "lop" "josh"]) (sort names)))))

  (testing "g.V().values('name').order(Order.decr)"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         (q/values :name)
                         (q/order Order/decr)
                         q/into-vec!)]
      (is (= ["vadas" "ripple" "peter" "marko" "lop" "josh"] names)))))
