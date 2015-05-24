(ns clojurewerkz.ogre.util
  (:import (org.apache.tinkerpop.gremlin.structure Compare Direction Contains )
           (java.util.function Function Consumer Predicate BiPredicate BiFunction)
           (org.apache.tinkerpop.gremlin.process.traversal.dsl.graph  GraphTraversal)
           (org.apache.tinkerpop.gremlin.process.traversal Traversal)))

(defmacro typed-traversal
  [method ^Traversal t & args]
    `(cond
       (instance? GraphTraversal ~t) (~method ~(vary-meta t assoc :tag `GraphTraversal) ~@args)))

(defn as
  "Assigns a name to the previous step in a traversal."
  [^Traversal t label]
  (typed-traversal .as t ^String (name label)))

(defmacro query
  "Starts a query."
  [xs & body]
  `(-> ~xs ~@body))

(defmacro subquery
  "Starts a subquery."
  [& body]
  `(-> ~@body))

(def f-to-compare {=         Compare/eq
                   not=      Compare/neq
                   >=        Compare/gte
                   >         Compare/gt
                   <=        Compare/lte
                   <         Compare/lt
                   contains? Contains/within})

;; todo: this should be temporary - anon-traversal is kinda ugly
(defn anon-traversal
  [] )

(defn ^"[Ljava.lang.String;" str-array [strs]
  "Converts a collection of strings to a java String array."
  (into-array String strs))

(defn keywords-to-str-array [strs]
  "Converts a collection of keywords to a java String array."
  (str-array (map name strs)))

(defn ^java.util.ArrayList keywords-to-str-list [strs]
  "Converts a collection of keywords to a java List of Strings."
  (java.util.ArrayList. ^java.util.Collection  (mapv name strs)))

(defn prop-map-to-array [m]
  "Converts a property map to a java Object array."
  (into-array Object
    (into []
      (flatten
        (map #(let [key (first %)
                    value (second %)]
            (vector (if (keyword? key) (name key) key) value)) m)))))

(defmulti convert-to-map "Converts objects to a map." class)

(defmethod convert-to-map java.util.HashMap
  [m]
  (into {} (for [[k v] m] [(keyword k) v])))

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

(defprotocol EdgeDirectionConversion
  (to-edge-direction [input] "Converts input to a Gremlin structure edge direction"))

(extend-protocol EdgeDirectionConversion
  clojure.lang.Named
  (to-edge-direction [input]
    (to-edge-direction (name input)))

  String
  (to-edge-direction [input]
    (case (.toLowerCase input)
      "in"    Direction/IN
      "out"   Direction/OUT
      "both"  Direction/BOTH))

  Direction
  (to-edge-direction [input]
    input))
