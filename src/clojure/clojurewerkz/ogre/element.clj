(ns clojurewerkz.ogre.element
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import com.tinkerpop.gremlin.structure.Element)
  (:require [clojurewerkz.ogre.util :refer (keywords-to-str-array)]))

(defn get-multiple
  ([^Element elem key]
    (get-multiple elem key nil))
  ([^Element elem key not-found]
    (let [properties (-> elem (.iterators) (.properties (keywords-to-str-array [key])))]
      (if (.hasNext properties) (map #(.value %) (iterator-seq properties)) (list not-found)))))

(defn get
  ([^Element elem key]
    (first (get-multiple elem key nil)))
  ([^Element elem key not-found]
    (first (get-multiple elem key not-found))))

(defn keys
  [^Element elem]
  (set (map keyword (.keys elem))))

(defn vals
  [^Element elem]
  (set (map #(.property elem %) (.keys elem))))

(defn id-of
  [^Element elem]
  (.id elem))

(defn assoc!
  [^Element elem & kvs]
  ;;Avoids changing keys that shouldn't be changed.
  ;;Important when using types. You aren't ever going to change a
  ;;user's id for example.
  (doseq [[key value] (partition 2 kvs)]
      (.property elem (name key) value))
  elem)

(defn merge!
  [^Element elem & maps]
  (doseq [d maps]
    (apply assoc! (cons elem (flatten (into [] d)))))
  elem)

(defn dissoc!
  [^Element elem & keys]
  (doseq [key keys] (.remove (.property elem (name key))))
  elem)

(defn clear!
  [^Element elem]
  (apply dissoc! (cons elem (keys elem))))
