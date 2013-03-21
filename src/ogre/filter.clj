(ns ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:require [ogre.util :refer (convert-symbol-to-compare f-to-pipef)]))

(defn filter [p f]
  (.filter p (f-to-pipef f)))

(defn dedup
  ([p] (.dedup p))
  ([p f] (.dedup p (f-to-pipef f))))

(defn except [p xs]
  (.except p xs))

(defmacro has
  ([p k v] `(.has ~p ~(name k) ~v))
  ([p k c v] `(.has ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defmacro has-not
  ([p k v] `(.hasNot ~p ~(name k) ~v))
  ([p k c v] `(.hasNot ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defn interval [p key start end]
  (.interval p (name key) (float start) (float end)))

(defn random [p bias]
  (.random p bias))

(defn range [p low high]
  (.range p low high))

(defn retain [p coll]
  (.retain p coll))

(defn simple-path [p]
  (.simplePath p))
