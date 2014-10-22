(ns clojurewerkz.ogre.element-test
  (:use [clojure.test :only [deftest is]])
  (:require [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-get
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100 {:a 1})
        b (v/create-with-id! g 101)
        c (e/connect-with-id! 102 a :label b {:a 1})]
    (is (= 1 (v/get a :a)))
    (is (= 1 (v/get c :a)))))

(deftest test-keys
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100 {:name "v1" :a 1 :b 1})
        b (v/create-with-id! g 101 {:name "v2" :a 1 :b 1})
        c (e/connect-with-id! 102 a :label b {:name "e1" :a 1 :b 1})
        coll-a (v/keys a)
        coll-b (v/keys b)
        coll-c (v/keys c)]
    (is (= #{:name :a :b} coll-a coll-b coll-c))
    (is (= clojure.lang.PersistentHashSet (type coll-a)))))

(deftest test-vals
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100 {:a 1 :b 2})
        b (v/create-with-id! g 101 {:a 1 :b 2})
        c (e/connect-with-id! 102 a :label b {:a 1 :b 2})
        coll-a (v/vals a)
        coll-b (v/vals b)
        coll-c (v/vals c)]
    (is (= #{1 2} coll-a coll-b coll-c))
    (is (= clojure.lang.PersistentHashSet (type coll-a)))))

(deftest test-id-of
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100)
        b (v/create-with-id! g 101)
        c (e/connect-with-id! 102 a :label b )]
    (is (= java.lang.Long (type (v/id-of a))))
    (is (= java.lang.Long (type (e/id-of c))))))

(deftest test-assoc!
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100 {:a 1})
        b (v/create-with-id! g 101)
        c (e/connect-with-id! 102 a :label b {:a 1})]
    (v/assoc! a {:a 10})
    (e/assoc! c {:a 10})
    (is (= (list 1 10) (v/get a :a)))
    (is (= 10 (v/get c :a)))))

(deftest test-dissoc!
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100 {:a 1})
        b (v/create-with-id! g 101)
        c (e/connect-with-id! 102 a :label b {:a 1})]
    (v/dissoc! a :a)
    (e/dissoc! c :a)
    (is (nil? (:a (v/to-map a))))
    (is (nil? (:a (v/to-map c))))))

(deftest test-clear!
  (let [g (u/new-tinkergraph)
        a (v/create-with-id! g 100 {:a 1})
        b (v/create-with-id! g 101)
        c (e/connect-with-id! 102 a :label b {:a 1})]
    (v/clear! a)
    (e/clear! c)
    (is (empty? (v/keys a)))
    (is (empty? (e/keys c)))))


