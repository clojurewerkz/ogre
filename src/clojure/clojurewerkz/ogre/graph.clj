(ns clojurewerkz.ogre.graph
  (:import (com.tinkerpop.gremlin.structure Element Graph Graph$Features$GraphFeatures)
           (com.tinkerpop.gremlin.tinkergraph.structure TinkerFactory TinkerGraph)))

(defn get-graph-features
  "Get a map of graph features for the given graph."
  ^Graph$Features$GraphFeatures
  [^Graph g]
  (-> g (.features) (.graph)))

(defn supports-computer
  "Determines if the graph supports GraphComputer-based processing."
  [^Graph g]
  (.supportsComputer (get-graph-features g)))

(defn supports-persistence
  "Determines if the graph supports persisting its contents natively to disk."
  [^Graph g]
  (.supportsPersistence (get-graph-features g)))

(defn supports-threaded-transactions
  "Determines if the graph supports threaded transactions."
  [^Graph g]
  (.supportsThreadedTransactions (get-graph-features g)))

(defn supports-transactions
  "Determines if the graph supports transactions."
  [^Graph g]
  (.supportsTransactions (get-graph-features g)))

(defn new-transaction
  "Creates a new transaction based on the given graph object."
  [^Graph g]
  (-> g (.tx) (.create)))

(defn commit
  "Commits all changes to the graph."
  [^Graph g]
  (-> g (.tx) (.commit)))

(defn close
  "Closes the graph."
  [^Graph g]
  (.close g))

(defn rollback
  "Stops the current transaction and rolls back any changes made."
  [^Graph g]
  (-> g (.tx) (.rollback)))

(defn with-transaction*
  [graph f & {:keys [threaded? rollback?]}]
  {:pre [(supports-transactions graph)]}
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
