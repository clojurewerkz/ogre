(ns clojurewerkz.ogre.side-effect
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.traversal :as t]
            [clojurewerkz.ogre.util :refer (f-to-function f-to-consumer typed-traversal)]))

;; addIn/Out*E

(defn aggregate
  "The aggregate step is used to aggregate all the objects at a particular point of traversal into a Collection. "
  ([^Traversal t ^String side-effect-key]
   (typed-traversal .aggregate t side-effect-key)))

(defn cap
  "Emits the value of the previous step and not the values that flow through it."
  ([^Traversal t] (typed-traversal .cap t))
  ([^Traversal t k] (typed-traversal .cap t k)))

;; count

(defn get-capped!
  "Returns the value of the previous step."
  ([^Traversal t] (t/next! (cap t)))
  ([^Traversal t k] (t/next! (cap t k))))

(defn side-effect
  "Executes a side effect."
  [^Traversal t f]
  (typed-traversal .sideEffect t (f-to-consumer f)))

;; groupBy
;; groupCount

(defn get-grouped-by!
  "Takes in a key function and processing function. Returns all of the processed objects
  grouped by the value of the key function."
  [^Traversal t key-func value-func]
    (let [results  (-> (typed-traversal .groupBy t (f-to-function key-func) (f-to-function value-func))
                       (.cap)
                       (.toList)
                       seq
                       first)]
      (->> results
        (into {})
        (map (fn [[a b]] [a (vec b)]))
        (into {}))))

(defn get-group-count!
  "Takes in a key function, and optionally, a counting function. Returns the count of the
  objects grouped by the key function."
  [^Traversal t key-func]
    (-> (typed-traversal .groupCount t (f-to-function key-func))
        (.cap)
        (.toList)
        seq
        first
        (#(into {} %))))

;; inject
;; store
;; subgraph
;; timelimit
;; tree
