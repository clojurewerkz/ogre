(ns ogre.test-util
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory))
  (:require [ogre.core :as q]))


(def ^{:dynamic true} *graph*)

(defn use-new-tinker-graph! []
  (alter-var-root (var *graph*) (fn [_]
                                  (TinkerGraphFactory/createTinkerGraph))))

(defn use-clean-graph! []
  (use-new-tinker-graph!)
  (map #(.removeEdge *graph* %) (seq (.getEdges *graph*)))
  (map #(.removeVertex *graph* %) (seq (.getVertices *graph*))))

(def vid (atom 100))

(defn set-property! [v key value]
  (.setProperty v (name key) value))

(defn set-properties! [v m]
  (doseq [[key value] m] (set-property! v key value))
  v)

(defn create!
  ([]  (create! {}))
  ([m] (-> (.addVertex *graph* (swap! vid inc))
           (set-properties! m))))

(defn connect!
  ([v1 label v2] (connect! v1 label v2 {}))
  ([v1 label v2 m]
     (-> (.addEdge *graph* (swap! vid inc) v1 v2 (name label)) 
         (set-properties! m))))

(defn get-property [v k]
  (.getProperty v (name k)))