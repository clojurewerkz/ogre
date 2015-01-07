(ns clojurewerkz.ogre.traversal
  (:refer-clojure :exclude [iterate])
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.util :refer (convert-to-map typed-traversal)]))

(defn iterate!
  "Iterates the traversal."
  [^Traversal t]
  (.iterate t))

(defn next!
  "Returns the next object in the traversal."
  ([^Traversal t]
   (.next t))
  ([^Traversal t i]
   (.next t i)))

(defmacro ^{:private true}
  to-java-list!
  [^Traversal t]
  "Returns the objects in the traversal as a java list."
  `(.toList ~t))

(defn into-vec!
  "Returns the objects in the traversal as a vector."
  [^Traversal t]
  (into [] (to-java-list! t)))

(defn into-set!
  "Returns the objects in the traversal as a set."
  [^Traversal t]
  (into #{} (to-java-list! t)))

(defn into-list!
  "Returns the objects in the traversal as a list."
  [^Traversal t]
  (into '() (to-java-list! t)))

(defn into-lazy-seq!
  "Returns the objects of the traversal as a lazy sequence."
  [^Traversal t]
  (let [f (fn [_] (first (next! t 1)))]
    (clojure.core/iterate f (f nil))))

(defn first-of!
  "Returns the first object of the traversal."
  [^Traversal t]
  (next! t))

(defn first-into-vec!
  "Returns the first object of the traversal as a vector."
  [^Traversal t]
  (vec (first-of! t)))

(defn first-into-set!
  "Returns the first object of the traversal as a set."
  [^Traversal t]
  (set (first-of! t)))

(defn first-into-map!
  "Returns the first object of the traversal as a map."
  [^Traversal t]
  (convert-to-map (first-of! t)))

(defn all-into-vecs!
  "Returns a sequence consisting of vectors of each object in the traversal."
  [^Traversal t]
  (map vec (into-vec! t)))

(defn all-into-sets!
  "Returns a sequence consisting of sets of each object in the traversal."
  [^Traversal t]
  (map set (into-vec! t)))

(defn all-into-maps!
  "Returns a sequence consisting of maps of each object in the traversal."
  [^Traversal t]
  (map convert-to-map (into-vec! t)))

(defn count!
  "Returns the number of objects currently in the traversal."
  [^Traversal t]
  (next! (typed-traversal .count t)))
