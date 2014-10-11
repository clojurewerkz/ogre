(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:require [clojurewerkz.ogre.util :refer (convert-symbol-to-compare)]))

(defn dedup
  ([p]
    (.dedup p))
  ([p f]
    (.dedup p f)))

(defmacro has
  ([p k]
    `(.has ~p ~(name k)))
  ([p k v]
    `(.has ~p ~(name k) ~v))
  ([p k c v]
    `(.has ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defmacro has-not
  ([p k]
    `(.hasNot ~p ~(name k))))

(defn interval
  [p key start end]
  (.interval ^GraphTraversal p ^String (name key) ^Float (float start) ^Float (float end)))
