(ns clojurewerkz.archimedes.graph
  (:import (com.tinkerpop.blueprints Element Graph TransactionalGraph
                                     ThreadedTransactionalGraph
                                     TransactionalGraph$Conclusion)
           (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory)))

(def ^{:dynamic true} *element-id-key* :__id__)

(def ^{:dynamic true} *edge-label-key* :__label__)


(defn set-element-id-key!
  [new-id]
  (alter-var-root (var *element-id-key*) (constantly new-id)))

(defn set-edge-label-key!
  [new-id]
  (alter-var-root (var *edge-label-key*) (constantly new-id)))

(defn new-tinkergraph
  []
  (TinkerGraphFactory/createTinkerGraph))

(defn clean-tinkergraph
  []
  (let [g (new-tinkergraph)]
  (doseq [e (seq (.getEdges g))] (.removeEdge g e))
  (doseq [v (seq (.getVertices g))] (.removeVertex g v))
  g))

(defn get-features
  "Get a map of features for a graph.
  (http://tinkerpop.com/docs/javadocs/blueprints/2.1.0/com/tinkerpop/blueprints/Features.html)"
  [g]
  (.. g getFeatures toMap))

(defn get-feature
  "Gets the value of the feature for a graph."
  [g s]
  (get ^java.util.Map (get-features g) s))

;;TODO Transactions need to be much more fine grain in terms of
;;control. And expections as well. new-transaction will only work on a
;;ThreadedTransactionalGraph.
(defn new-transaction
  "Creates a new transaction based on the given graph object."
  [g]
  (.newTransaction g))

(defn commit
  "Commit all changes to the graph."
  [g]
  (.commit g))

(defn shutdown
  "Shutdown the graph."
  [g]
  (.shutdown g))

(defn rollback
  "Stops the current transaction and rolls back any changes made."
  [g]
  (.rollback g))

(defn with-transaction*
  [graph f & {:keys [threaded? rollback?]}]
  {:pre [(get-feature graph "supportsTransactions")]}
  (let [tx (if threaded? (new-transaction graph) graph)]
    (try
      (let [result (f tx)]
        (if rollback?
          (rollback tx)
          (commit tx))
        result)
      (catch Throwable t
        (try (rollback tx) (catch Exception _))
        (throw t)))))

;; This approach is copied from clojure.java.jdbc. The ^:once metadata and use of fn*
;; is explained by Christophe Grand in this blog post:
;; http://clj-me.cgrand.net/2013/09/11/macros-closures-and-unexpected-object-retention/
(defmacro with-transaction
  "Evaluates body in the context of a transaction on the specified graph, which must
   support transactions.  The binding provides the graph for the transaction and the
   name to which the transactional graph is bound for evaluation of the body.

   (with-transaction [tx graph]
     (vertex/create! tx)
     ...)

   If the graph supports threaded transactions, the binding may also specify that the
   body be executed in a threaded transaction.

   (with-transaction [tx graph :threaded? true]
      (vertex/create! tx)
      ...)

   Note that `commit` and `rollback` should not be called explicitly inside
   `with-transaction`. If you want to force a rollback, you must throw an
   exception or specify rollback in the `with-transaction` call:

   (with-transaction [tx graph :rollback? true]
      (vertex/create! tx)
      ...)"
  [binding & body]
  `(with-transaction*
     ~(second binding)
     (^{:once true} fn* [~(first binding)] ~@body)
     ~@(rest (rest binding))))

;; When we move to Blueprints 2.5, this can be reimplemented using TransactionRetryHelper

(defn with-transaction-retry*
  [graph f & {:keys [max-attempts wait-time threaded? rollback?]}]
  {:pre [(integer? max-attempts) (or (integer? wait-time) (ifn? wait-time))]}
  (let [wait-fn (if (integer? wait-time) (constantly wait-time) wait-time)
        retry (fn [attempt]
                (let [res (try
                            (with-transaction* graph f :threaded? threaded? :rollback? rollback?)
                            (catch Throwable t
                              (if (< attempt max-attempts)
                                ::retry
                                (throw t))))]
                  (if (= res ::retry)
                    (let [ms (wait-fn attempt)]
                      (Thread/sleep ms)
                      (recur (inc attempt)))
                    res)))]
    (retry 1)))

(defmacro with-transaction-retry
  [binding & body]
  `(with-transaction-retry*
     ~(second binding)
     (^{:once true} fn* [~(first binding)] ~@body)
     ~@(rest (rest binding))))
