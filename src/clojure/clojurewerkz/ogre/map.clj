(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:use clojurewerkz.ogre.util))

(defn map
  ([t f]
    (.map t (f-to-function f))))

(defn select
  ([t]
    (.select t))
  ([t & labels]
    (.select t (java.util.ArrayList. labels) (into-array []))))

(defn select-only
  ([t cols]
    (select-only t cols identity))
  ([t ^java.util.Collection cols & fs]
    (.select ^GraphTraversal t cols (into-array fs))))

(defn properties
  ([t & keys]
    (.properties t (keywords-to-strings keys))))

(defn values
  ([t & keys]
    (.values t (keywords-to-strings keys))))
