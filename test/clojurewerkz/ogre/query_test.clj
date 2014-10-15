(ns clojurewerkz.ogre.query-test
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph  :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge   :as e]
            [clojurewerkz.ogre.test-util :as u])
  (:use [clojure.test :only (deftest is)]))

;; todo: this test suite can probably have its tests distributed elsewhere - there really isn't an exact
;; notion of "Blueprints Query" anymore.  it's all just traversals.

(deftest test-basic-vertices-query
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b)
        _     (e/connect-with-id! 104 a :friend c)
        vs    (q/query a
                 (q/--> [:friend])
                 (q/into-set!))]
    (is (= 2 (count vs)))
    (is (= #{b c} vs))))

(deftest test-edge-count
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b)
        _     (e/connect-with-id! 104 a :friend c)
        _     (e/connect-with-id! 105 a :remembers c)
        _     (e/connect-with-id! 106 c :remembers a)
        n     (q/query a
                 (q/--> [:friend :remembers])
                 (q/count!))]
    (is (= 3 n))))

(deftest test-edge-count-with-default-comparator
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b {:age 28})
        _     (e/connect-with-id! 104 a :friend c {:age 30})
        n1    (q/query a
                 (q/--> [:friend])
                 (q/has :age 38)
                 (q/count!))
        n2    (q/query a
                 (q/-->  [:friend])
                 (q/has :age 29)
                 (q/count!))
        n3    (q/query a
                 (q/--> [:hates])
                 (q/has :age 28)
                 (q/count!))]
    (is (= n1 1))
    (is (= n2 0))
    (is (= n3 0))))

(deftest test-edge-count-with-gte-comparator
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b {:age 28})
        _     (e/connect-with-id! 104 a :friend c {:age 30})
        n1    (q/query a
                 (q/--> [:friend])
                 (q/has :age >= 28)
                 (q/count!))
        n2    (q/query a
                 (q/--> [:friend])
                 (q/has :age >= 33)
                 (q/count!))
        n3    (q/query a
                 (q/--> [:hates])
                 (q/has :age >= 28)
                 (q/count!))]
    (is (= n1 2))
    (is (= n2 1))
    (is (= n3 0))))

(deftest test-edge-count-with-lte-comparator
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b {:age 28})
        _     (e/connect-with-id! 104 a :friend c {:age 30})
        n1    (q/query a
                 (q/--> [:friend])
                 (q/has :age <= 40)
                 (q/count!))
        n2    (q/query a
                 (q/--> [:friend])
                 (q/has :age <= 37)
                 (q/count!))
        n3    (q/query a
                 (q/--> [:hates])
                 (q/has :age <= 28)
                 (q/count!))]
    (is (= n1 2))
    (is (= n2 1))
    (is (= n3 0))))

(deftest test-has-property-key
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        d     (v/create-with-id!  graph 103 {:name "Claire"})
        _     (e/connect-with-id! 104 a :friend b {:age 28})
        _     (e/connect-with-id! 105 a :friend c {:age 30})
        _     (e/connect-with-id! 106 a :friend d)
        n1    (q/query a
                 (q/--> [:friend])
                 (q/count!))
        n2    (q/query a
                 (q/--> [:friend])
                 (q/has :age)
                 (q/count!))]
    (is (= n1 3))
    (is (= n2 2))))

(defn scratch
  []
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b)
        _     (e/connect-with-id! 104 a :friend c)]
    (q/query a (q/--> [:friend]) (q/into-set!))))

(scratch)
