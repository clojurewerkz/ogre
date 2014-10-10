(ns clojurewerkz.ogre.element
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import com.tinkerpop.gremlin.structure.Element)
  (:require [clojurewerkz.ogre.util :refer (keywords-to-str-array)]))

(defn mget
  "Returns all properties of a vertex with the given key."
  ([^Element elem key]
    (mget elem key nil))
  ([^Element elem key not-found]
    (let [properties (-> elem (.iterators) (.properties (keywords-to-str-array [key])))]
      (if (.hasNext properties) (map #(.value %) (iterator-seq properties)) (list not-found)))))

(defn get
  "Returns the first property of a vertex with the given key."
  ([^Element elem key]
    (first (mget elem key nil)))
  ([^Element elem key not-found]
    (first (mget elem key not-found))))

(defn keys
  [^Element elem]
  (set (map keyword (.keys elem))))

(defn vals
  [^Element elem]
  (set (map #(-> elem (.property %) (.value)) (.keys elem))))

(defn id-of
  [^Element elem]
  (.id elem))

(defn assoc!
  [^Element elem map]
  (doseq [kv map]
    (.property elem (name (key kv)) (val kv)))
  elem)

(defn dissoc!
  [^Element elem & keys]
  (doseq [key keys] (.remove (.property elem (name key))))
  elem)

(defn clear!
  [^Element elem]
  (apply dissoc! (cons elem (keys elem))))
