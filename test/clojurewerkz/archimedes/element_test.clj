(ns clojurewerkz.archimedes.element-test
  (:use [clojure.test :only [deftest is]])
  (:require [clojurewerkz.archimedes.graph :refer (clean-tinkergraph)]
            [clojurewerkz.archimedes.vertex :as v]
            [clojurewerkz.archimedes.edge :as e]))

(deftest test-get-keys
  (let [g (clean-tinkergraph)
        a (v/create-with-id!  g 100 {:name "v1" :a 1 :b 1})
        b (v/create-with-id!  g 101 {:name "v2" :a 1 :b 1})
        c (e/connect-with-id! g 102 a :label b {:name "e1" :a 1 :b 1})
        coll-a (v/keys a)
        coll-b (v/keys b)
        coll-c (v/keys c)]
    (is (= #{:name :a :b} coll-a coll-b coll-c))
    (is (= clojure.lang.PersistentHashSet (type coll-a)))))

(deftest test-get-id
  (let [g (clean-tinkergraph)
        a (v/create-with-id!  g 100)
        b (v/create-with-id!  g 101)
        c (e/connect-with-id! g 102 a :label b )]
    (is (= java.lang.String (type (v/id-of a))))
    (is (= java.lang.String (type (e/id-of c))))))

(deftest test-remove-property!
  (let [g (clean-tinkergraph)
        a (v/create-with-id!  g 100 {:a 1})
        b (v/create-with-id!  g 101)
        c (e/connect-with-id! g 102 a :label b {:a 1})]
    (v/dissoc! a :a)
    (v/dissoc! c :a)
    (is (nil? (:a (v/to-map a))))
    (is (nil? (:a (v/to-map c))))))


(deftest test-clear!
  (let [g (clean-tinkergraph)
        a (v/create-with-id!  g 100 {:a 1})
        b (v/create-with-id!  g 101)
        c (e/connect-with-id! g 102 a :label b {:a 1})]
    (v/clear! a)
    (e/clear! c)
    (is (empty? (v/keys a)))
    (is (empty? (e/keys c)))))


(deftest test-update!
  (let [g (clean-tinkergraph)
        a (v/create-with-id!  g 100 {:a 1})
        b (v/create-with-id!  g 101)
        c (e/connect-with-id! g 102 a :label b {:a 1})]
    (v/update! a :a + 9)
    (v/update! a :b (constantly 10))
    (e/update! c :a + 9)
    (e/update! c :b (constantly 10))
    (is (= 10 (v/get a :a)))
    (is (= 10 (v/get c :a)))
    (is (= 10 (v/get a :b)))
    (is (= 10 (v/get c :b)))))
