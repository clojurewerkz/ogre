(ns clojurewerkz.ogre.tinkergraph
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory)
           (com.tinkerpop.blueprints Vertex Graph Edge Element))
  (:require [clojurewerkz.ogre.core :as q]))


(defn use-new-tinker-graph! []
  (TinkerGraphFactory/createTinkerGraph))

(defn use-clean-graph! []
  (let [graph (use-new-tinker-graph!)]
    (doseq [edge (.getEdges graph)]
      (.removeEdge graph edge))
    (doseq [vertex (.getVertices graph)]
      (.removeVertex graph vertex))
    ))

;;Element mutation

(defn set-property! [^Vertex v key value]
  (.setProperty v (name key) value))

(defn set-properties! [^Vertex v m]
  (doseq [[key value] m] (set-property! v key value))
  v)

;;Element creation 
(def vid (atom 100))

(defn create!
  ([graph]  (create! graph {}))
  ([graph m] (-> (.addVertex graph (swap! vid inc))
           (set-properties! m))))

(defn connect!
  ([graph v1 label v2] (connect! graph v1 label v2 {}))
  ([graph v1 label v2 m]
     (-> (.addEdge graph (swap! vid inc) v1 v2 (name label))
         (set-properties! m))))

;;Element reading

(defn get-property [k ^Vertex v]
  (.getProperty v (name k)))

(defn get-keys [k ^Vertex v]
  (.getProperty v (name k)))

(defn get-label [^Edge e]
  (.getLabel e))

(defn get-id [^Element v]
  (.getId v))

;;Element retriveal
(defn get-vertices [graph]
  (.getVertices graph))

(defn get-edges [graph]
  (.getEdges graph))

(defn find-by-id [graph i]
  (.getVertex graph i))

