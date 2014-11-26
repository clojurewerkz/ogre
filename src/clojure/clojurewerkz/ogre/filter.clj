(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (java.util Collection))
  (:require [clojurewerkz.ogre.util :refer (convert-symbol-to-compare f-to-function f-to-predicate typed-traversal)]))

(defn cyclic-path
  "The step analyzes the path of the traverser thus far and if there are any repeats, the traverser
  is filtered out over the traversal computation."
  [^Traversal t]
    (typed-traversal .cyclicPath t))

(defn dedup
  "Filters out repeated objects. A function can be supplied that provides the
  values that the traversal will consider when filtering."
  ([^Traversal t]
    (typed-traversal .dedup t))
  ([^Traversal t f]
    (typed-traversal .dedup t (f-to-function f))))

(defn except
  "Filters out the given objects."
  [^Traversal t excepter]
    (cond
      (instance? String excepter) (typed-traversal .except t ^String excepter)
      (instance? Collection excepter) (typed-traversal .except t ^Collection excepter)
      :else (except t [excepter])))

(defn filter
  "Filters using a predicate that determines whether an object should pass."
  [^Traversal t f] (typed-traversal .filter t (f-to-predicate f)))

(defmacro has
  "Allows an element if it has the given property. Supports the standard
  clojure symbolic comparison operators."
  ([^Traversal t k]
    `(.has ~t ~(name k)))
  ([^Traversal t k v]
    `(.has ~t ~(name k) ~v))
  ([^Traversal t k c v]
    `(.has ~t ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defn has-not
  "Allows an element if it does not the given property."
  ([^Traversal t k]
    (.hasNot t (name k))))

(defn interval
  "Allows elements to pass that have their property in the given start and end interval."
  [^Traversal t key ^Comparable start ^Comparable end]
  (typed-traversal .interval t (name key) start end))

(defn limit
  "Limit the number of elements to pass through Traversal."
  [^Traversal t l] (typed-traversal .limit t l))

(defn local-limit
  "Limit the number of elements to pass through Traversal."
  [^Traversal t l] (typed-traversal .localLimit t l))

(defn local-range
  "Allows elements to pass that are within the given range."
  [^Traversal t low high] (typed-traversal .localRange t low high))

(defn random
  "Allows elements to pass with the given probability."
  [^Traversal t probability] (typed-traversal .random t probability))

(defn range
  "Allows elements to pass that are within the given range."
  [^Traversal t low high] (typed-traversal .range t low high))

(defn retain
  "Only allows the given objects to pass."
  [^Traversal t retainer]
    (cond
      (instance? String retainer) (typed-traversal .retain t ^String retainer)
      (instance? Collection retainer) (typed-traversal .retain t ^Collection retainer)
      :else (retain t [retainer])))

(defn simple-path
  "Allows an element if the current path has no repeated elements."
  [^Traversal t] (.simplePath t))

;; where
