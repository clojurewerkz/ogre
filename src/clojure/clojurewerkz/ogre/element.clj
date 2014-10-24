(ns clojurewerkz.ogre.element
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import (com.tinkerpop.gremlin.structure Element)
           (com.tinkerpop.gremlin.process Traverser))
  (:require [clojurewerkz.ogre.util :refer (keywords-to-str-array)]))

(defprotocol GetItemProperties
  "Returns properties of an item with the given key and with optional default value."
  (get [item key] [item key not-found]))

(extend-protocol GetItemProperties
  com.tinkerpop.gremlin.structure.Element
  (get
    ([item key]
      (get item key nil))
    ([item key not-found]
      (let [prop-iter (-> item (.iterators) (.properties (keywords-to-str-array [key])))
            prop (if (.hasNext prop-iter) (map #(.value %) (iterator-seq prop-iter)) (list not-found))]
        (if (= (count prop) 1) (first prop) prop))))

  com.tinkerpop.gremlin.process.Traverser
  (get
    ([item key]
      (get (.get item) key nil))
    ([item key not-found]
      (get (.get item) key not-found))))

(defn prop-pred
  "Returns a predicate to match the given property key and value."
  [key pred value item]
  (pred value (get item key)))

(defn keys
  "Returns the keys of an element."
  [^Element elem]
  (set (map keyword (.keys elem))))

(defn vals
  "Returns the values of an element."
  [^Element elem]
  (set (map #(-> elem (.property %) (.value)) (.keys elem))))

(defn id-of
  "Returns the id of an element."
  [^Element elem]
  (.id elem))

(defn assoc!
  "Adds properties with the specified keys and values to an element."
  [^Element elem map]
  (doseq [kv map]
    (.property elem (name (key kv)) (val kv)))
  elem)

(defn dissoc!
  "Removes properties with the specified keys from an element."
  [^Element elem & keys]
  (doseq [key keys] (.remove (.property elem (name key))))
  elem)

(defn clear!
  "Removes all properties from an element."
  [^Element elem]
  (apply dissoc! (cons elem (keys elem))))
