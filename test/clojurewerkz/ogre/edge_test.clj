(ns clojurewerkz.ogre.edge-test
  (:use [clojure.test :only [deftest testing is]])
  (:import (com.tinkerpop.gremlin.structure Edge)
           (com.tinkerpop.gremlin.process T))
  (:require [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.traversal :as t]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-delete
  (let [g (u/new-tinkergraph)
        u (v/create-with-id!  g 100)
        w (v/create-with-id!  g 101)
        a (e/connect-with-id! 102 u :test w)
        a-id (e/id-of a)]
    (e/remove! a)
    (is (=  nil (e/find-by-id a-id)))))

(deftest test-connect
  (let [g (u/new-tinkergraph)
        u (v/create! g)
        v (v/create! g)
        e (e/connect! u :test v)]
    (is (e/connected? u v))
    (is (e/connected? u :test v))))

(deftest test-simple-property-mutation
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :test v2 {:a 1})]
    (e/assoc! edge {:b 2})
    (e/dissoc! edge :a)
    (is (= 2   (e/get edge :b)))
    (is (= nil (e/get edge :a)))))

(deftest test-multiple-property-mutation
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :test v2  {:a 0})]
    (e/assoc! edge {:a 1 :b 2 :c 3})
    (is (= 1 (e/get edge :a)))
    (is (= 2 (e/get edge :b)))
    (is (= 3 (e/get edge :c)))))

(deftest test-get-all-edges
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :test v2  {:a 0})
        edge (e/connect-with-id! 103 v1 :test v2  {:a 1})
        edge (e/connect-with-id! 104 v1 :test v2  {:a 2})]
    (is (= 3 (t/count! (e/get-all-edges g))))))

(deftest test-to-map
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :test v2 {:a 1 :b 2 :c 3})
        prop-map (e/to-map edge)]
    (is (= {:a 1 :b 2 :c 3} (dissoc prop-map T/id T/label)))))

(deftest test-endpoints
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :connection v2)]
    (is (= ["v1" "v2"] (map #(-> % t/first-of! (e/get :name)) (e/endpoints edge))))))

(deftest test-get-vertex
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :connection v2)]
    (is (= v1 (t/first-of! (e/tail-vertex edge))))
    (is (= v2 (t/first-of! (e/head-vertex edge))))))

(deftest test-tail-vertex
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :connection v2)]
    (is (= v1 (t/first-of! (e/tail-vertex edge))))))

(deftest test-edges-between
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id!  g 100 {:name "v1"})
        v2 (v/create-with-id!  g 101 {:name "v2"})
        v3 (v/create-with-id!  g 102 {:name "v3"})
        e1 (e/connect-with-id! 103 v1 :connection v2)
        e2 (e/connect-with-id! 104 v1 :testing   v2)
        e3 (e/connect-with-id! 105 v2 :connection v1)
        e4 (e/connect-with-id! 106 v2 :testing   v1)
        e5 (e/connect-with-id! 107 v1 :testing   v3)
        e5 (e/connect-with-id! 108 v2 :testing   v3)]
    (is (= #{e1 e2} (e/edges-between v1 v2)))
    (is (= #{e1} (e/edges-between v1 :connection v2)))
    (is (= nil (e/edges-between v1 :wrong v2)))
    (is (= nil (e/edges-between v3 :wrong v1)))))

(deftest test-head-vertex
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :connection v2)]
    (is (= v2 (t/first-of! (e/head-vertex edge))))))

(deftest test-refresh
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! 102 v1 :connection v2 )
        fresh-edge (e/refresh g edge)]
    (is fresh-edge)
    (is (= (.id ^Edge edge) (.id ^Edge fresh-edge)))
    (is (= (e/to-map edge) (e/to-map fresh-edge)))))

(deftest test-upconnect!
  (testing "Upconnecting without id once without data"
    (let [g (u/new-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect! v1 :connection v2)]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connection v2))
      (is (not (e/connected? v2 v1)))
      (is (= 1 (t/count! (e/get-all-edges g))))))

  (testing "Upconnecting without id once"
    (let [g (u/new-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect! v1 :connection v2
                 {:name "the edge"})]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connection v2))
      (is (not (e/connected? v2 v1)))
      (is (= "the edge" (e/get edge :name)))
      (is (= 1 (t/count! (e/get-all-edges g))))))

  (testing "Upconnecting without id multiple times"
    (let [g (u/new-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect! v1 :connection v2 {:name "the edge"})
          edge (e/unique-upconnect! v1 :connection v2 {:a 1 :b 2})
          edge (e/unique-upconnect! v1 :connection v2 {:b 0})]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connection v2))
      (is (not (e/connected? v2 v1)))
      (is (= "the edge" (e/get edge :name)))
      (is (= 1 (e/get edge :a)))
      (is (= 0 (e/get edge :b)))
      (is (= 1 (t/count! (e/get-all-edges g))))))

  (testing "Upconnecting with id once without data"
    (let [g (u/new-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect-with-id! 102 v1 :connection v2)]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connection v2))
      (is (not (e/connected? v2 v1)))
      (is (= 1 (t/count! (e/get-all-edges g))))))

  (testing "Upconnecting with id once"
    (let [g (u/new-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect-with-id! 102 v1 :connection v2
                                            {:name "the edge"})]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connection v2))
      (is (not (e/connected? v2 v1)))
      (is (= "the edge" (e/get edge :name)))
      (is (= 1 (t/count! (e/get-all-edges g))))))

  (testing "Upconnecting with id multiple times"
    (let [g (u/new-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect-with-id! 102 v1 :connection v2 {:name "the edge"})
          edge (e/unique-upconnect-with-id! 103 v1 :connection v2 {:a 1 :b 2})
          edge (e/unique-upconnect-with-id! 104 v1 :connection v2 {:b 0})]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connection v2))
      (is (not (e/connected? v2 v1)))
      (is (= "the edge" (e/get edge :name)))
      (is (= 1 (e/get edge :a)))
      (is (= 0 (e/get edge :b)))
      (is (= 1 (t/count! (e/get-all-edges g)))))))

(deftest test-get-false-val
  (let [g (u/new-tinkergraph)
        v1 (v/create-with-id! g 100)
        v2 (v/create-with-id! g 101)
        e (e/connect-with-id! 102 v1 :connection v2 {:foo false})]
    (is (= (e/get e :foo) false))
    (is (= (e/get e :foo 1) false))
    (is (nil? (e/get e :bar)))
    (is (= (e/get e :bar 1) 1))))
