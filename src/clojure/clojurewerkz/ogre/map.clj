(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:use clojurewerkz.ogre.util))

(defn map
  ([t & keys]
    (.map ^GraphTraversal t (keywords-to-strings keys))))

(defn select
  ([t]
    (.select ^GraphTraversal t))
  ([t & labels]
    (.select t (java.util.ArrayList. labels) (into-array []))))

(defn select-only
  ([t cols]
    (select-only t cols identity))
  ([t ^java.util.Collection cols & fs]
    (.select ^GraphTraversal t cols (into-array fs))))

(defn properties
  ([t & keys]
    (.properties ^GraphTraversal t (keywords-to-strings keys))))

(defn values
  ([t & keys]
    (.values ^GraphTraversal t (keywords-to-strings keys))))
