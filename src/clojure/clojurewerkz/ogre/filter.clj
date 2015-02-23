(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.structure Compare)
           (java.util Collection))
  (:require [clojurewerkz.ogre.util :refer (f-to-function f-to-predicate typed-traversal f-to-bipredicate anon-traversal f-to-compare)]))

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
    (typed-traversal #(.by (.dedup %) (f-to-function f)) t )))

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

(defn has
  "Allows an element if it has the given property or it satisfies given predicate."
  ([^Traversal t k]
    (typed-traversal .has t (name k)))
  ([^Traversal t k v-or-pred]
   (if (ifn? v-or-pred)
     (has t k (fn [v _] (v-or-pred v)) :dummy)
     (typed-traversal .has t (name k) v-or-pred)))
  ([^Traversal t k pred v]
   (if-let [c (f-to-compare pred)]
     (typed-traversal .has t (name k) ^Compare c v)
     (typed-traversal .has t (name k) (f-to-bipredicate pred) v))))

(defn has-not
  "Allows an element if it does not have the given property."
  ([^Traversal t k]
    (typed-traversal .hasNot t (name k)))
  ([^Traversal t k v-or-pred]
   (if (ifn? v-or-pred)
     (has t k (complement v-or-pred))
     (has t k not= v-or-pred)))
  ([^Traversal t k pred v]
   (has t k (complement pred) v)))

(defn interval
  "Allows elements to pass that have their property in the given start and end interval."
  [^Traversal t key ^Comparable start ^Comparable end]
  (typed-traversal .between t (name key) start end))

(defn limit
  "Limit the number of elements to pass through Traversal."
  [^Traversal t l] (typed-traversal .limit t l))

(defn coin
  "Allows elements to pass with the given probability."
  [^Traversal t probability] (typed-traversal .coin t probability))

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
  [^Traversal t] (typed-traversal .simplePath t))

(defmacro where
  "Further constrain results from match with a binary predicate or traversal."
  ([^Traversal t pred a b]
   `(typed-traversal .where ~t (name ~a) (name ~b) (f-to-bipredicate ~pred)))
  ([^Traversal t constraint]
   `(typed-traversal .where ~t (-> (anon-traversal) ~constraint))))
