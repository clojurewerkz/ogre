(ns clojurewerkz.ogre.io-test
  (:use [clojure.test :only (deftest testing is)])
  (:require [clojurewerkz.ogre.io :as io]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.test-util :as u])
  (:import [java.io File]))

(defn- has-n-vertices [g n]
  (is (= n (count (seq (.toList (.V g)))))))

(defn- has-n-edges [g n]
  (is (= n (count (seq (.toList (.E g)))))))

(defn- make-test-graph
  []
  (let [g (u/new-tinkergraph)
        vertex-1 (v/create-with-id! g 100)
        vertex-2 (v/create-with-id! g 101)
        edge (e/connect-with-id! 102 vertex-1 :edge vertex-2)]
    g))

(defn- make-test-graph-with-types
  []
  (let [g (u/new-tinkergraph)
        vertex-1 (v/create-with-id! g 100 {:my-int (int 1)
                                           :my-long (long 2)
                                           :my-float (float 3)
                                           :my-double (double 4)
                                           :my-boolean true})
        vertex-2 (v/create-with-id! g 101 {:my-int (int 10)
                                           :my-long (long 20)
                                           :my-float (float 30)
                                           :my-double (double 40)
                                           :my-boolean false})
        edge (e/connect-with-id! 102 vertex-1 :edge vertex-2)]
    g))

(deftest test-loading-and-saving-graphs-graphml
  (let [g-write (make-test-graph)
        tmp (File/createTempFile "my-test-graph" ".graphml")]
    (io/write-graph-graphml g-write tmp)
    ;; Open new graph and read it
    (let [g-read (u/new-tinkergraph)]
      (io/read-graph-graphml g-read tmp)
      (has-n-vertices g-read 2)
      (has-n-edges g-read 1))))

(deftest test-loading-and-saving-graphs-graphson
  (testing "Without type information"
    (let [g-write (make-test-graph)
          tmp (File/createTempFile "my-test-graph" ".graphson")]
      (io/write-graph-graphson g-write tmp)
      ;; Open new graph and read it
      (let [g-read (u/new-tinkergraph)]
        (io/read-graph-graphson g-read tmp)
        (has-n-vertices g-read 2)
        (has-n-edges g-read 1)))))
