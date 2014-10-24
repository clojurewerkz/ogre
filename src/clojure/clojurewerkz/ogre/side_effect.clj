(ns clojurewerkz.ogre.side-effect
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.traversal :as t]
            [clojurewerkz.ogre.util :refer (f-to-function f-to-consumer)]))

;; addIn/Out*E
;; aggregate

(defn cap
  ([^Traversal t] (.cap t))
  ([^Traversal t k] (.cap t k)))

;; count

(defn get-capped!
  ([^Traversal t] (t/next! (cap t)))
  ([^Traversal t k] (t/next! (cap t k))))

(defn side-effect
  [^Traversal t f]
  (.sideEffect t (f-to-consumer f)))

;; groupBy
;; groupCount

(defn get-grouped-by!
  [^Traversal t key-func value-func]
    (let [results  (-> (.groupBy t (f-to-function key-func) (f-to-function value-func))
                       (.cap)
                       (.toList)
                       seq
                       first)]
      (->> results
        (into {})
        (map (fn [[a b]] [a (vec b)]))
        (into {}))))

(defn get-group-count!
  [^Traversal t key-func]
    (-> (.groupCount t (f-to-function key-func))
        (.cap)
        (.toList)
        seq
        first
        (#(into {} %))))

;; store
;; subgraph
;; timelimit
;; tree
