(ns clojurewerkz.ogre.element
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import com.tinkerpop.gremlin.structure.Element)
  (:require [clojurewerkz.ogre.util :refer (keywords-to-str-array)]))

(defn get
  "Returns properties of a vertex with the given key."
  ([^Element elem key]
    (get elem key nil))
  ([^Element elem key not-found]
    (let [prop-iter (-> elem (.iterators) (.properties (keywords-to-str-array [key])))
          prop (if (.hasNext prop-iter) (map #(.value %) (iterator-seq prop-iter)) (list not-found))]
      (if (= (count prop) 1) (first prop) prop))))

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
