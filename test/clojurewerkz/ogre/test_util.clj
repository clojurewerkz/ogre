(ns clojurewerkz.ogre.test-util
  (:require [clojurewerkz.ogre.element :as el]))

(defn prop-pred [key pred value v]
  (pred value (el/get v key)))

(defn get-names [vs]
  (map #(el/get % :name) vs))

(defn get-names-set [vs]
  (set (get-names vs)))

(defn get-ages [vs]
  (map #(el/get % :age) vs))

(defn get-ages-set [vs]
  (set (get-ages vs)))