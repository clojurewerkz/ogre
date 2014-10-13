(ns clojurewerkz.ogre.io-test
  (:use [clojure.test :only (deftest testing is)])
  (:require [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.io :as io]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojure.java.io :as clj-io]
            [clojurewerkz.ogre.test-util :as u])
  (:import [java.io File]))

(defn- has-n-vertices [graph n]
  (is (= n (count (seq (.getVertices graph))))))

(defn- has-n-edges [graph n]
  (is (= n (count (seq (.getEdges graph))))))

(defn- make-test-graph
  []
  (let [graph (u/new-tinkergraph)
        vertex-1 (v/create-with-id! graph 100)
        vertex-2 (v/create-with-id! graph 101)
        edge (e/connect-with-id! 102 vertex-1 :edge vertex-2)]
    graph))

(defn- make-test-graph-with-types
  []
  (let [graph (u/new-tinkergraph)
        vertex-1 (v/create-with-id! graph 100 {:my-int (int 1)
                                               :my-long (long 2)
                                               :my-float (float 3)
                                               :my-double (double 4)
                                               :my-boolean true})
        vertex-2 (v/create-with-id! graph 101 {:my-int (int 10)
                                               :my-long (long 20)
                                               :my-float (float 30)
                                               :my-double (double 40)
                                               :my-boolean false})
        edge (e/connect-with-id! 102 vertex-1 :edge vertex-2)]
    graph))

(deftest test-loading-and-saving-graphs-graphml
  (let [graph (make-test-graph)
        tmp   (File/createTempFile "my-test-graph" ".graphml")]
    (io/write-graph-graphml graph tmp)
    ;; Open new graph and read it
    (let [graph2 (u/new-tinkergraph)]
      (io/read-graph-graphml graph2 tmp)
      (has-n-vertices graph2 2)
      (has-n-edges graph2 1))))

(deftest test-loading-and-saving-graphs-graphson
  (testing "Without type information"
    (let [graph (make-test-graph)
          tmp   (File/createTempFile "my-test-graph" ".graphson")]
      (io/write-graph-graphson graph tmp)
      ;; Open new graph and read it
      (let [graph2 (u/new-tinkergraph)]
        (io/read-graph-graphson graph2 tmp)
        (has-n-vertices graph2 2)
        (has-n-edges graph2 1))))

  (testing "With a graph with type information"
    (let [graph (make-test-graph-with-types)
          tmp-typed (File/createTempFile "my-test-graph-typed" ".graphson")
          tmp-untyped (File/createTempFile "my-test-graph-untyped" ".graphson")]
      (io/write-graph-graphson graph tmp-typed true)
      (io/write-graph-graphson graph tmp-untyped false)
      (testing "Loading a graphson with type infomation"
        (let [graph2 (u/new-tinkergraph)]
          (io/read-graph-graphson graph2 tmp-typed)
          (has-n-vertices graph2 2)
          (has-n-edges graph2 1)))
      (testing "Loading a graphson without type infomation"
        (let [graph2 (u/new-tinkergraph)]
          (io/read-graph-graphson graph2 tmp-untyped)
          (has-n-vertices graph2 2)
          (has-n-edges graph2 1))))))