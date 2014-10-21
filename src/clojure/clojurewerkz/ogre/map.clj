(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.structure Order))
  (:use clojurewerkz.ogre.util))

(defn id
  ([^Traversal t] (.id t)))

(defn fold
  ([^Traversal t] (.fold t)))

(defn label
  ([^Traversal t] (.label t)))

(defn map
  ([^Traversal t f]
    (.map t (f-to-function f))))

(defn order
  ([^Traversal t] (order t Order/incr))
  ([^Traversal t c] (.order ^Traversal t c)))

(defn path
  [^Traversal t & fns]
    (.path t (fs-to-function-array fns)))

(defn properties
  ([^Traversal t & keys]
    (.properties t (keywords-to-strings keys))))

(defn select
  ([^Traversal t]
    (select t #(identity %)))
  ([^Traversal t & f]
    (.select t (fs-to-function-array f))))

(defn select-only
  ([^Traversal t cols]
    (select-only t cols identity))
  ([^Traversal t ^java.util.Collection cols & fs]
    (.select t cols (fs-to-function-array fs))))

(defn unfold
  ([^Traversal t] (.unfold t)))

(defn values
  ([^Traversal t & keys]
    (.values t (keywords-to-strings keys))))
