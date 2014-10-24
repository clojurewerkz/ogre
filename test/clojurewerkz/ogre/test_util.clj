(ns clojurewerkz.ogre.test-util
  (:require [clojurewerkz.ogre.element :as el])
  (:import (com.tinkerpop.gremlin.tinkergraph.structure TinkerFactory TinkerGraph)))

(defn get-names [vs]
  (map #(el/get % :name) vs))

(defn get-names-set [vs]
  (set (get-names vs)))

(defn get-ages [vs]
  (map #(el/get % :age) vs))

(defn get-ages-set [vs]
  (set (get-ages vs)))

(defn new-tinkergraph
  []
  (TinkerGraph/open))

(defn classic-tinkergraph
  []
  (TinkerFactory/createClassic))
