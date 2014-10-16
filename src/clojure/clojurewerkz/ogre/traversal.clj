(ns clojurewerkz.ogre.traversal
  (:refer-clojure :exclude [iterate])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal)
           (com.tinkerpop.gremlin.structure Vertex)
           (java.util.function Function))
  (:use clojurewerkz.ogre.util))

(defn iterate!
  [t]
  (.iterate ^GraphTraversal t))

(defn next!
  ([t]
   (.next t))
  ([t i]
   (.next t i)))

(defmacro ^{:private true}
  to-java-list!
  [t]
  `(.toList ~t))

(defn into-vec!
  [t]
  (into [] (to-java-list! t)))

(defn into-set!
  [t]
  (into #{} (to-java-list! t)))

(defn into-list!
  [t]
  (into '() (to-java-list! t)))

(defn into-lazy-seq!
  [t]
  (let [f (fn [_] (first (.next t 1)))]
    (clojure.core/iterate f (f nil))))

;;Inspired by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defmulti convert-to-map class)

(defmethod convert-to-map java.util.HashMap
  [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defn first-of!
  [t]
  (next! t))

(defn first-into-vec!
  [t]
  (vec (first-of! t)))

(defn first-into-set!
  [t]
  (set (first-of! t)))

(defn first-into-map!
  [t]
  (convert-to-map (first-of! t)))

(defn all-into-vecs!
  [t]
  (map vec (into-vec! t)))

(defn all-into-sets!
  [t]
  (map set (into-vec! t)))

(defn all-into-maps!
  [t]
  (map convert-to-map (into-vec! t)))

(defn count!
  [t]
  (next! (.count ^GraphTraversal t)))

(defn path
  [t & fns]
    (.path t (fs-to-function-array fns)))

