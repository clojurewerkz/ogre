(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.util :refer (convert-symbol-to-compare f-to-function f-to-predicate)]))

(defn dedup
  ([^Traversal t]
    (.dedup t))
  ([^Traversal t f]
    (.dedup t (f-to-function f))))

(defn except
  [^Traversal t exception-object] (.except t exception-object))

(defn filter
  [^Traversal t f] (.filter t (f-to-predicate f)))

(defmacro has
  ([^Traversal t k]
    `(.has ~t ~(name k)))
  ([^Traversal t k v]
    `(.has ~t ~(name k) ~v))
  ([^Traversal t k c v]
    `(.has ~t ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defn has-not
  ([^Traversal t k]
    (.hasNot t (name k))))

(defn interval
  [^Traversal t key ^Comparable start ^Comparable end]
  (.interval t (name key) start end))

(defn random
  [^Traversal t probability] (.random t probability))

(defn range
  [^Traversal t low high] (.range t low high))
