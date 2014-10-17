(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal)
           (com.tinkerpop.gremlin.structure Order))
  (:use clojurewerkz.ogre.util))

(defn map
  ([t f]
    (.map t (f-to-function f))))

(defn fold
  ([t] (.fold ^GraphTraversal t)))

(defn order
  ([t] (order t Order/incr))
  ([t c] (.order ^GraphTraversal t c)))

(defn select
  ([t]
    (select t #(identity %)))
  ([t & f]
    (.select t (fs-to-function-array f))))

(defn select-only
  ([t cols]
    (select-only t cols identity))
  ([t ^java.util.Collection cols & fs]
    (.select t cols (fs-to-function-array fs))))

(defn properties
  ([t & keys]
    (.properties t (keywords-to-strings keys))))

(defn values
  ([t & keys]
    (.values t (keywords-to-strings keys))))

(defn path
  [t & fns]
    (.path t (fs-to-function-array fns)))
