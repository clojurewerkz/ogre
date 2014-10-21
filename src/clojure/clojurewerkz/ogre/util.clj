(ns clojurewerkz.ogre.util
  (:require [clojure.reflect :as r])
  (:use [clojure.pprint :only (pprint)])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.structure Compare Direction)
           (java.util.function Function Consumer Predicate)))

(defn as
  [^Traversal t label] (.as t label))

(defmacro query [xs & body]
  `(-> ~xs ~@body))

(defmacro subquery
  ""
  [& body]
  `(-> ~@body))

(defn ^Compare convert-symbol-to-compare [s]
  (case s
    =    Compare/eq
    not= Compare/neq
    >=   Compare/gte
    >    Compare/gt
    <=   Compare/lte
    <    Compare/lt))

(defn ^"[Ljava.lang.String;" str-array [strs]
  (into-array String strs))

(defn ^"[Ljava.lang.String;" keywords-to-strings [labels]
  (->> labels
    (filter identity)
    (map name)
    str-array))

(defn keywords-to-str-array [strs]
  (into-array String (map name strs)))

(defn prop-map-to-array [m]
  (into-array Object
    (into []
      (flatten
        (map #(let [key (first %)
                    value (second %)]
            (vector (if (keyword? key) (name key) key) value)) m)))))

(defn ^Function f-to-function [f]
  (reify Function
    (apply [this arg] (f arg))))

(defn ^"[Ljava.util.function.Function;" fs-to-function-array
  [fs]
  (into-array Function (map f-to-function fs)))

(defn ^Consumer f-to-consumer [f]
  (reify Consumer
    (accept [this arg] (f arg))))

(defn ^Predicate f-to-predicate [f]
  (reify Predicate
    (test [this arg] (f arg))))

(defprotocol EdgeDirectionConversion
  (to-edge-direction [input] "Converts input to a Gremlin Structure edge direction"))

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
