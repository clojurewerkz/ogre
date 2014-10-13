(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [memoize map])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:use clojurewerkz.ogre.util))

(defn map
  ([t & keys]
    (.map ^GraphTraversal t (keywords-to-strings keys))))

(defn select
  ([t]
    (.select ^GraphTraversal t))
  ([t & fs]
    (.select ^GraphTraversal t (into-array fs))))

(defn select-only
  ([t cols]
    (select-only t cols identity))
  ([t ^java.util.Collection cols & fs]
    (.select ^GraphTraversal t cols (into-array fs))))
