(ns ogre.tinkergraph
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory))
  (:require [ogre.core :as q]))


;;Tinkergraph manipulation
(def ^{:dynamic true} *graph*)

(defn use-new-tinker-graph! []
  (alter-var-root (var *graph*) (fn [_]
                                  (TinkerGraphFactory/createTinkerGraph))))

(defn use-clean-graph! []
  (use-new-tinker-graph!)
  (map #(.removeEdge *graph* %) (seq (.getEdges *graph*)))
  (map #(.removeVertex *graph* %) (seq (.getVertices *graph*))))

;;Element mutation

(defn set-property! [v key value]
  (.setProperty v (name key) value))

(defn set-properties! [v m]
  (doseq [[key value] m] (set-property! v key value))
  v)

;;Element creation 
(def vid (atom 100))

(defn create!
  ([]  (create! {}))
  ([m] (-> (.addVertex *graph* (swap! vid inc))
           (set-properties! m))))

(defn connect!
  ([v1 label v2] (connect! v1 label v2 {}))
  ([v1 label v2 m]
     (-> (.addEdge *graph* (swap! vid inc) v1 v2 (name label)) 
         (set-properties! m))))

;;Element reading

(defn get-property [k v]
  (.getProperty v (name k)))

(defn get-keys [k v]
  (.getProperty v (name k)))

(defn get-label [e]
  (.getLabel e))

(defn get-id [v]
  (.getId v))

(defn find-by-kv [k v])
;;Element retriveal
(defn get-vertices []
  (.getVertices *graph*))

(defn get-edges []
  (.getEdges *graph*))

(defn find-by-id [i]
  (.getVertex *graph* i))

