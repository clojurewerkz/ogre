(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:require [clojurewerkz.ogre.util :refer (convert-symbol-to-compare f-to-function)]))

(defn dedup
  ([t]
    (.dedup t))
  ([t f]
    (.dedup t (f-to-function f))))

(defmacro has
  ([t k]
    `(.has ~t ~(name k)))
  ([t k v]
    `(.has ~t ~(name k) ~v))
  ([t k c v]
    `(.has ~t ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defn has-not
  ([t k]
    (.hasNot t (name k))))

(defn interval
  [t key ^Comparable start ^Comparable end]
  (.interval t (name key) start end))
