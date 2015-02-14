(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map key shuffle])
  (:import (com.tinkerpop.gremlin.process Traversal Traverser)
           (com.tinkerpop.gremlin.process.graph GraphTraversal)
           (com.tinkerpop.gremlin.process.graph.step.map MapStep)
           (com.tinkerpop.gremlin.structure Order Element))
  (:require [clojurewerkz.ogre.util :refer (f-to-function fs-to-function-array keywords-to-str-array keywords-to-str-list f-to-bifunction typed-traversal anon-traversal as)]))

(defn back
  "Goes back to the results of a named step."
  ([^Traversal t step-label] (typed-traversal .back t (name step-label))))

;; flatMap
;; fold(BiFunction)

(defn fold
  "Collects all objects up to the current step."
  ([^Traversal t] (typed-traversal .fold t)))

(defn id
  "Gets the unique identifier of the element."
  ([^GraphTraversal t] (.id t)))

;; identity

(defn key
  "Gets the key name of a Property."
  ([^GraphTraversal t] (.key t)))

(defn label
  "Gets the label of an element."
  ([^GraphTraversal t]
   (let [step (doto (MapStep. t)
                (.setFunction (f-to-function
                               (fn [^Traverser t]
                                 (keyword (.label ^Element (.get t)))))))]
     (.addStep t step))))

(defn map
  "Gets the property map of an element."
  ([^Traversal t f]
    (typed-traversal .map t (f-to-function f))))

(defmacro match
  "Pattern match traversals from current step onwards. Can introduce new labels."
  [^Traversal t start-label & matches]
  `(typed-traversal .match ~t (name ~start-label)
                    (into-array ~(vec (for [[label m] (partition 2 matches)]
                                        `(-> (anon-traversal)
                                           (as ~label)
                                           ~m))))))


;; todo: how best to resolve varargs overload to order
(defn order
  "Orders the items in the traversal according to the specified comparator
  or the default order if not specified."
  [^Traversal t] (.order t))

(defn by
  ([^Traversal t]
    (typed-traversal .by t))
  ([^Traversal t arg]
    (typed-traversal .by t arg)))

;; orderBy

(defn other-v
  "Gets the other vertex of an edge depending on which vertex a traversal started on."
  ([^Traversal t] (typed-traversal .otherV t)))

(defn path
  "Gets the path through the traversal up to the current step. If functions are provided
  they are applied round robin to each of the objects in the path."
  [^Traversal t]
    (typed-traversal .path t))

(defn properties
  "Gets the properties of an element."
  ([^Traversal t & keys]
    (typed-traversal .properties t (keywords-to-str-array keys))))

;; propertyMap

;; select overloads

(defn select
  "Get a list of named steps, with optional functions for post processing round robin style."
  ([^Traversal t]
    (.select t (into-array [])))
  ([^Traversal t & f]
    (typed-traversal .select t (fs-to-function-array f))))

(defn select-only
  "Select the named steps to emit, with optional functions for post processing round robin style."
  ([^Traversal t cols]
    (typed-traversal .select t (keywords-to-str-array cols))))

(defn shuffle
  "Collect all items in the traversal and randomize their order before emitting."
  ([^Traversal t] (typed-traversal .shuffle t)))

;; to

(defn unfold
  "Unroll all objects in the iterable at the current step."
  ([^Traversal t] (typed-traversal .unfold t)))

;; value
;; valueMap

(defn values
  "Gets the property values of an element."
  ([^Traversal t & keys]
    (typed-traversal .values t (keywords-to-str-array keys))))
