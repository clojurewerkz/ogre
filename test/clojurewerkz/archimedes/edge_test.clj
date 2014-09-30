(ns clojurewerkz.archimedes.edge-test
  (:use [clojure.test :only [deftest testing is]])
  (:require [clojurewerkz.archimedes.graph :as gr]
            [clojurewerkz.archimedes.edge :as e]
            [clojurewerkz.archimedes.vertex :as v]))

(deftest test-delete
  (let [g (gr/clean-tinkergraph)
        u (v/create-with-id!  g 100)
        w (v/create-with-id!  g 101)
        a (e/connect-with-id! g 102 u :test w)
        a-id (e/id-of a)]
    (e/remove! g a)
    (is (=  nil (e/find-by-id a-id)))))

(deftest test-connect
  (let [g (gr/clean-tinkergraph)
        u (v/create! g)
        v (v/create! g)
        e (e/connect! g u :test v)]
    (is (e/connected? u v))
    (is (e/connected? u :test v))))

(deftest test-simple-property-mutation
  (let [g  (gr/clean-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :test v2 {:a 1})]
    (e/assoc! edge :b 2)
    (e/dissoc! edge :a)
    (is (= 2   (e/get edge :b)))
    (is (= nil (e/get edge :a)))))

(deftest test-multiple-property-mutation
  (let [g  (gr/clean-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :test v2  {:a 0})]
    (e/merge! edge {:a 1 :b 2 :c 3})
    (is (= 1 (e/get edge :a)))
    (is (= 2 (e/get edge :b)))
    (is (= 3 (e/get edge :c)))))

(deftest test-get-all-edges
  (let [g (gr/clean-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :test v2  {:a 0})
        edge (e/connect-with-id! g 103 v1 :test v2  {:a 1})
        edge (e/connect-with-id! g 104 v1 :test v2  {:a 2})]
    (is (= 3 (count (e/get-all-edges g))))))

(deftest test-to-map
  (let [g (gr/clean-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :test v2 {:a 1 :b 2 :c 3})
        prop-map (e/to-map edge)]
    (is (= {:a 1 :b 2 :c 3} (dissoc prop-map :__id__ :__label__)))))

(deftest test-to-map-id
  (let [id :ID label :LABEL]
    (try
      (gr/set-element-id-key! id)
      (gr/set-edge-label-key! label)
      (let [g (gr/clean-tinkergraph)
            v1 (v/create-with-id! g 100 {:name "v1"})
            v2 (v/create-with-id! g 101 {:name "v2"})
            edge (e/connect-with-id! g 102 v1 :test v2 {:a 1 :b 2 :c 3})
            prop-map (e/to-map edge)]
      (is (= {:a 1 :b 2 :c 3 id "102" label :test}  prop-map)))
      (finally
        (gr/set-element-id-key! :__id__)
        (gr/set-edge-label-key! :__label__)))))

(deftest test-endpoints
  (let [g (gr/clean-tinkergraph)
        v1   (v/create-with-id! g 100 {:name "v1"})
        v2   (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :connexion v2)]
    (is (= ["v1" "v2"] (map #(e/get % :name) (e/endpoints edge))))))

(deftest test-get-vertex
  (let [g    (gr/clean-tinkergraph)
        v1   (v/create-with-id! g 100 {:name "v1"})
        v2   (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :connexion v2)]
    (is (= v1 (e/get-vertex edge :out)))
    (is (= v2 (e/get-vertex edge :in)))))

(deftest test-tail-vertex
  (let [g    (gr/clean-tinkergraph)
        v1   (v/create-with-id! g 100 {:name "v1"})
        v2   (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :connexion v2)]
    (is (= v1 (e/tail-vertex edge)))))

(deftest test-edges-between
  (let [g    (gr/clean-tinkergraph)
        v1   (v/create-with-id!  g 100 {:name "v1"})
        v2   (v/create-with-id!  g 101 {:name "v2"})
        v3   (v/create-with-id!  g 102 {:name "v3"})
        e1   (e/connect-with-id! g 103 v1 :connexion v2)
        e2   (e/connect-with-id! g 104 v1 :testing   v2)
        e3   (e/connect-with-id! g 105 v2 :connexion v1)
        e4   (e/connect-with-id! g 106 v2 :testing   v1)
        e5   (e/connect-with-id! g 107 v1 :testing   v3)
        e5   (e/connect-with-id! g 108 v2 :testing   v3)]
    (is (= #{e1 e2} (e/edges-between v1 v2)))
    (is (= #{e1} (e/edges-between v1 :connexion v2)))
    (is (= nil (e/edges-between v1 :wrong v2)))
    (is (= nil (e/edges-between v3 :wrong v1)))))

(deftest test-head-vertex
  (let [g    (gr/clean-tinkergraph)
        v1   (v/create-with-id! g 100 {:name "v1"})
        v2   (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :connexion v2)]
    (is (= v2 (e/head-vertex edge)))))

(deftest test-refresh
  (let [g    (gr/clean-tinkergraph)
        v1 (v/create-with-id! g 100 {:name "v1"})
        v2 (v/create-with-id! g 101 {:name "v2"})
        edge (e/connect-with-id! g 102 v1 :connexion v2 )
        fresh-edge (e/refresh g edge)]
    (is fresh-edge)
    (is (= (.getId edge) (.getId fresh-edge)))
    (is (= (e/to-map edge) (e/to-map fresh-edge)))))

(deftest test-upconnect!
  (testing "Upconnecting once without data"
    (let [g    (gr/clean-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect-with-id! g 102 v1 :connexion v2)]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connexion v2))
      (is (not (e/connected? v2 v1)))
      (is (= 1 (count (seq (.getEdges g)))))))
  (testing "Upconnecting once"
    (let [g    (gr/clean-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect-with-id! g 102 v1 :connexion v2
                                            {:name "the edge"})]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connexion v2))
      (is (not (e/connected? v2 v1)))
      (is (= "the edge" (e/get edge :name)))
      (is (= 1 (count (seq (.getEdges g)))))))

  (testing "Upconnecting multiple times"
    (let [g  (gr/clean-tinkergraph)
          v1 (v/create-with-id! g 100 {:name "v1"})
          v2 (v/create-with-id! g 101 {:name "v2"})
          edge (e/unique-upconnect-with-id! g 102 v1 :connexion v2 {:name "the edge"})
          edge (e/unique-upconnect-with-id! g 103 v1 :connexion v2 {:a 1 :b 2})
          edge (e/unique-upconnect-with-id! g 104 v1 :connexion v2 {:b 0})]
      (is (e/connected? v1 v2))
      (is (e/connected? v1 :connexion v2))
      (is (not (e/connected? v2 v1)))
      (is (= "the edge" (e/get edge :name)))
      (is (= 1 (e/get edge :a)))
      (is (= 0 (e/get edge :b)))
      (is (= 1 (count (seq (.getEdges g))))))))

(deftest test-get-false-val
  (let [graph (gr/clean-tinkergraph)
        v1    (v/create-with-id! graph 100)
        v2    (v/create-with-id! graph 101)
        e     (e/connect-with-id! graph 102 v1 :connexion v2 {:foo false})]
    (is (= (e/get e :foo) false))
    (is (= (e/get e :foo 1) false))
    (is (nil? (e/get e :bar)))
    (is (= (e/get e :bar 1) 1))))
