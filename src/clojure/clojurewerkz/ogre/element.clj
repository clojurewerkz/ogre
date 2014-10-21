(ns clojurewerkz.ogre.element
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import com.tinkerpop.gremlin.structure.Element)
  (:require [clojurewerkz.ogre.util :refer (keywords-to-str-array)]))

(defn get
  "Returns properties of an element with the given key."
  ([^Element elem key]
    (get elem key nil))
  ([^Element elem key not-found]
    (let [prop-iter (-> elem (.iterators) (.properties (keywords-to-str-array [key])))
          prop (if (.hasNext prop-iter) (map #(.value %) (iterator-seq prop-iter)) (list not-found))]
      (if (= (count prop) 1) (first prop) prop))))

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
