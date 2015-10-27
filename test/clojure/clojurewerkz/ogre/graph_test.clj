(ns clojurewerkz.ogre.graph-test
  (:require [clojure.test :refer [deftest use-fixtures testing is]]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.traversal :as t]
            [clojurewerkz.support.io :as sio]
            [clojurewerkz.ogre.test-util :as u])
  (:import  [org.apache.tinkerpop.gremlin.tinkergraph.structure TinkerFactory TinkerGraph]
            [org.apache.commons.io FileUtils]))

(def ^:dynamic *graph*)

(defn temp-db-fixture
  [f])

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
