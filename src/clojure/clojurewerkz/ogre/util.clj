(ns clojurewerkz.ogre.util
  (:import (org.apache.tinkerpop.gremlin.structure  Direction  Graph Vertex)
           (java.util.function Function Consumer Predicate BiPredicate BiFunction)
           (org.apache.tinkerpop.gremlin.process.traversal.dsl.graph  GraphTraversal)
           (org.apache.tinkerpop.gremlin.process.traversal Traversal P)))

(defmacro traverse
  "Starts a traversal."
  [xs & body]
  `(-> ~xs ~@body))

(defn ^"[Ljava.lang.String;" str-array [strs]
  "Converts a collection of strings to a java String array."
  (into-array String strs))

(defn keywords-to-str-array [strs]
  "Converts a single keyword or a collection of keywords to a java String array."
  (let [to-convert (if (keyword? strs) [strs] strs)]
    (str-array (map name to-convert))))

(defn cast-param
  "Value is either a T, String, or keyword. If it's a keyword, pass the name."
  [value]
  (if (keyword value)
    (name value)
    value))

(defn map-every-nth [f coll n]
  (map-indexed #(if (zero? (mod (inc %1) n)) (f %2) %2) coll))

(defn cast-every-other-param
  [coll]
  (to-array (map-every-nth (fn [i] (cast-param i)) coll 2)))

(defn string-or-keyword
  "Checks if the given value is either a string or keyword."
  [value]
  (clojure.core/or (string? value) (keyword? value)))

(defn ^Function f-to-function [f]
  "Converts a function to java.util.function.Function."
  (reify Function
    (apply [this arg] (f arg))))

(defn ^BiFunction f-to-bifunction [f]
  "Converts a function to java.util.function.BiFunction."
  (reify BiFunction
    (apply [this arg1 arg2] (f arg1 arg2))))

(defn ^"[Ljava.util.function.Function;" fs-to-function-array
  "Converts a collection of functions to a java.util.function.Function array."
  [fs]
  (into-array Function (map f-to-function fs)))

(defn ^Consumer f-to-consumer [f]
  "Converts a function to java.util.function.Consumer."
  (reify Consumer
    (accept [this arg] (f arg))))

(defn ^Predicate f-to-predicate [f]
  "Converts a function to java.util.function.Predicate."
  (reify Predicate
    (test [this arg] (f arg))))

(defn ^BiPredicate f-to-bipredicate [f]
  "Converts a function to java.util.function.BiPredicate."
  (reify BiPredicate
    (test [this a b] (f a b))))
