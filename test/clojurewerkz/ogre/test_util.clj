(ns clojurewerkz.ogre.test-util
  (:require [clojurewerkz.ogre.element :as el])
  (:import (com.tinkerpop.gremlin.tinkergraph.structure TinkerFactory TinkerGraph)))

(defn get-names [vs]
  "Returns a map of names of the given vertices."
  (map #(el/get % :name) vs))

(defn get-names-set [vs]
  "Returns a set of names of the given vertices."
  (set (get-names vs)))

(defn get-ages [vs]
  "Returns a map of ages of the given vertices."
  (map #(el/get % :age) vs))

(defn get-ages-set [vs]
  "Returns a set of ages of the given vertices."
  (set (get-ages vs)))

(defn new-tinkergraph []
  "Returns a new empty TinkerGraph."
  (TinkerGraph/open))

(defn classic-tinkergraph []
  "Returns a new TinkerGraph with classic data."
  (TinkerFactory/createClassic))

(defn modern-tinkergraph []
  "Returns a new TinkerGraph with modern data."
  (TinkerFactory/createModern))

(defn crew-tinkergraph []
  "Returns a new TinkerGraph with 'The Crew' data."
  (TinkerFactory/createTheCrew))
