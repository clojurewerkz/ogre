(ns clojurewerkz.ogre.test-util
  (:require [clojurewerkz.ogre.tinkergraph :refer (get-property)]))

(defn prop-pred [key pred value v]
  (pred value (get-property key v)))

(defn get-names [vs]
  (map (partial get-property :name) vs))

(defn get-names-set [vs]
  (set (get-names vs)))

(defn get-ages [vs]
  (map (partial get-property :age) vs))

(defn get-ages-set [vs]
  (set (get-ages vs)))