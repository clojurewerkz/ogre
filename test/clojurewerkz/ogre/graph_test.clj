(ns clojurewerkz.ogre.graph-test
  (:use [clojure.test :only (use-fixtures deftest testing is)])
  (:require [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.traversal :as t]
            [clojurewerkz.support.io :as sio]
            [clojurewerkz.ogre.test-util :as u])
  (:import  [com.tinkerpop.gremlin.tinkergraph.structure TinkerFactory TinkerGraph]
            [com.tinkerpop.gremlin.neo4j.structure Neo4jGraph]
            [org.apache.commons.io FileUtils]))

(def ^:dynamic *graph*)

(defn temp-db-fixture
  [f]
  (let [tmp (sio/create-temp-dir)]
    (try
      (binding [*graph* (Neo4jGraph/open (str (.getPath tmp) "/neo4j"))]
        (try
          (f)
          (finally
            (.close *graph*))))
      (finally
        (FileUtils/deleteQuietly tmp)))))

(use-fixtures :each temp-db-fixture)

(deftest test-opening-a-graph-in-memory
  (testing "Graph in memory"
    (is (= (type (u/new-tinkergraph))
           TinkerGraph))))

(deftest test-tinkergraph-does-not-support-transactions
  (testing "We cannot perform a transaction on a Tinkergraph"
    (is (thrown? java.lang.AssertionError
                 (g/with-transaction [g (u/new-tinkergraph)] nil)))))

(deftest test-transaction-rollback-on-exception
  (testing "Uncaught exception reverts added vertex"
    (try
      (g/with-transaction [tx *graph*]
        (v/create! tx {:name "Mallory"})
        (is (= (t/count! (v/get-all-vertices tx)) 1))
        (throw (Exception. "Died")))
      (catch Exception e
        (is (= (.getMessage e) "Died"))))
    (is (empty? (t/into-vec! (v/get-all-vertices *graph*))))))

(deftest test-transaction-explicit-rollback
  (testing "Setting :rollback? option reverts added vertex"
    (g/with-transaction [tx *graph* :rollback? true]
      (v/create! tx {:name "Mallory"})
      (is (= (t/count! (v/get-all-vertices tx)) 1)))
    (is (empty? (t/into-vec! (v/get-all-vertices *graph*))))))

(def num-attempts (atom 0))

(deftest test-transaction-retry
  (testing "Retry transaction"
    (reset! num-attempts 0)
    (is (thrown-with-msg? java.lang.Exception #"Died"
                          (g/with-transaction-retry [tx *graph* :max-attempts 3 :wait-time 100]
                            (v/create! tx {:name "Mallory"})
                            (swap! num-attempts inc)
                            (throw (Exception. "Died")))))
    (is (= @num-attempts 3))))

(deftest test-transaction-commit
  (testing "Commit edit to graph"
    (g/with-transaction [tx *graph*]
      (v/create! tx {:name "Bob"}))
    (is (= (t/count! (v/get-all-vertices *graph*)) 1))))
