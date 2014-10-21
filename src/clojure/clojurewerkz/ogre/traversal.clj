(ns clojurewerkz.ogre.traversal
  (:refer-clojure :exclude [iterate])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.structure Vertex)
           (java.util.function Function))
  (:use clojurewerkz.ogre.util))

(defn iterate!
  [^Traversal t]
  (.iterate t))

(defn next!
  ([^Traversal t]
   (.next t))
  ([^Traversal t i]
   (.next t i)))

(defmacro ^{:private true}
  to-java-list!
  [^Traversal t]
  `(.toList ~t))

(defn into-vec!
  [^Traversal t]
  (into [] (to-java-list! t)))

(defn into-set!
  [^Traversal t]
  (into #{} (to-java-list! t)))

(defn into-list!
  [^Traversal t]
  (into '() (to-java-list! t)))

(defn into-lazy-seq!
  [^Traversal t]
  (let [f (fn [_] (first (.next t 1)))]
    (clojure.core/iterate f (f nil))))

;;Inspired by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defmulti convert-to-map class)

(defmethod convert-to-map java.util.HashMap
  [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defn first-of!
  [^Traversal t]
  (next! t))

(defn first-into-vec!
  [^Traversal t]
  (vec (first-of! t)))

(defn first-into-set!
  [^Traversal t]
  (set (first-of! t)))

(defn first-into-map!
  [^Traversal t]
  (convert-to-map (first-of! t)))

(defn all-into-vecs!
  [^Traversal t]
  (map vec (into-vec! t)))

(defn all-into-sets!
  [^Traversal t]
  (map set (into-vec! t)))

(defn all-into-maps!
  [^Traversal t]
  (map convert-to-map (into-vec! t)))

(defn count!
  [^Traversal t]
  (next! (.count t)))

