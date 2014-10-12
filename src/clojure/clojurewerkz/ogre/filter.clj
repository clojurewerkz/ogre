(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:require [clojurewerkz.ogre.util :refer (convert-symbol-to-compare)]))

(defn dedup
  ([t]
    (.dedup t))
  ([t f]
    (.dedup t f)))

(defmacro has
  ([t k]
    `(.has ~t ~(name k)))
  ([t k v]
    `(.has ~t ~(name k) ~v))
  ([t k c v]
    `(.has ~t ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defmacro has-not
  ([t k]
    `(.hasNot ~t ~(name k))))

(defn interval
  [t key start end]
  (.interval ^GraphTraversal t ^String (name key) ^Float (float start) ^Float (float end)))
