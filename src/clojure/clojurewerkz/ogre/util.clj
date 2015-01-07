(ns clojurewerkz.ogre.util
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.process.graph GraphTraversal VertexTraversal EdgeTraversal)
           (com.tinkerpop.gremlin.structure Compare Direction Contains)
           (java.util.function Function Consumer Predicate BiPredicate BiFunction)))

(defmacro typed-traversal
  [method ^Traversal t & args]
    `(cond
       (instance? GraphTraversal ~t) (~method ~(vary-meta t assoc :tag `GraphTraversal) ~@args)
       (instance? VertexTraversal ~t) (~method ~(vary-meta t assoc :tag `VertexTraversal) ~@args)
       (instance? EdgeTraversal ~t) (~method ~(vary-meta t assoc :tag `EdgeTraversal) ~@args)))

(defn as
  "Assigns a name to the previous step in a traversal."
  [^Traversal t ^String label]
  (typed-traversal .as t label))

(defmacro query [xs & body]
  "Starts a query."
  `(-> ~xs ~@body))

(defmacro subquery
  "Starts a subquery."
  [& body]
  `(-> ~@body))

(defn ^"[Ljava.lang.String;" str-array [strs]
  "Converts a collection of strings to a java String array."
  (into-array String strs))

(defn keywords-to-str-array [strs]
  "Converts a collection of keywords to a java String array."
  (str-array (map name strs)))

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
