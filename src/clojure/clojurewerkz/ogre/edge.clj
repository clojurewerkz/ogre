(ns clojurewerkz.ogre.edge
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import (com.tinkerpop.gremlin.structure Vertex Edge)
           (com.tinkerpop.gremlin.process T))
  (:require [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :refer (*element-id-key* *edge-label-key*)]
            [clojurewerkz.ogre.util :refer (to-edge-direction prop-map-to-array)]
            [clojurewerkz.ogre.element :as el]
            [clojurewerkz.ogre.traversal :as t]
            [potemkin :as po]))

(po/import-fn el/get)
(po/import-fn el/keys)
(po/import-fn el/vals)
(po/import-fn el/id-of)
(po/import-fn el/assoc!)
(po/import-fn el/dissoc!)
(po/import-fn el/clear!)

;;
;;Transaction management
;;

(defn refresh
  "Goes and grabs the edge from the graph again. Useful for \"refreshing\" stale edges."
  [g ^Edge edge]
  (.e g (.id edge)))

;;
;; Removal methods
;;

(defn remove!
  "Removes an edge."
  [^Edge edge]
  (.remove edge))

;;
;; Information getters
;;

(defn label-of
  "Returns the label of the edge."
  [^Edge edge]
  (keyword (.label edge)))

(defn to-map
  "Returns a persistent map representing the edge."
  [^Edge edge]
  (->> (keys edge)
       (map #(vector (keyword %) (get edge %)))
       (into {*element-id-key* (id-of edge) *edge-label-key* (label-of edge)})))

(defn find-by-id
  "Retrieves edges by id from the graph."
  [g & ids]
  (if (= 1 (count ids))
    (.e g (first ids))
    (seq (for [id ids] (.e g id)))))

(defn get-all-edges
  "Returns all edges."
  [g]
  (.E g))

(defn ^Vertex get-vertex
  "Get the vertex of the edge in a certain direction."
  [^Edge e direction]
  (.toV e (to-edge-direction direction)))

(defn ^Vertex head-vertex
  "Get the head vertex of the edge."
  [^Edge e]
  (.inV e))

(defn ^Vertex tail-vertex
  "Get the tail vertex of the edge."
  [^Edge e]
  (.outV e))

(defn endpoints
  "Returns the endpoints of the edge in array with the order [starting-node,ending-node]."
  [^Edge edge]
  [(tail-vertex edge) (head-vertex edge)])

(defn edges-between
  "Returns a set of the edges between two vertices, optionally with the given label."
  ([^Vertex v1 ^Vertex v2]
    (edges-between v1 nil v2))
  ([^Vertex v1 label ^Vertex v2]
    ;; Source for these edge queries:
    ;; https://groups.google.com/forum/?fromgroups=#!topic/gremlin-users/R2RJxJc1BHI
    (let [^Edge edges (t/into-set! (if label (v/outgoing-edges-of v1 label) (v/outgoing-edges-of v1)))
          v2-id (.id v2)
          edge-set (set (filter #(= v2-id (-> % head-vertex t/first-of! (.id))) edges))]
      (when (not (empty? edge-set))
        edge-set))))

(defn connected?
  "Returns whether or not two vertices are connected with an optional third
   argument specifying the label of the edge."
  ([^Vertex v1 ^Vertex v2]
    (connected? v1 nil v2))
  ([^Vertex v1 label ^Vertex v2]
    (not (empty? (edges-between v1 label v2)))))

;;
;; Creation methods
;;

(defn connect!
  "Connects two vertices with the given label, optionally with the given properties."
  ([^Vertex v1 label ^Vertex v2]
    (connect! v1 label v2 {}))
  ([^Vertex v1 label ^Vertex v2 data]
    (.addEdge v1 ^String (name label) v2 (prop-map-to-array data))))

(defn connect-with-id!
  "Connects two vertices with the given id and label, optionally with the given properties."
  ([id ^Vertex v1 label ^Vertex v2]
    (connect-with-id! id v1 label v2 {}))
  ([id ^Vertex v1 label ^Vertex v2 data]
    (.addEdge v1 ^String (name label) v2 (prop-map-to-array (assoc data T/id id)))))

(defn upconnect!
  "Takes all the edges between the given vertices with the given label
   and, if the data is provided, merges the data with the current
   properties of the edge. If no such edge exists, then an edge is
   created with the given data."
  ([^Vertex v1 label ^Vertex v2]
    (upconnect! v1 label v2 {}))
  ([^Vertex v1 label ^Vertex v2 data]
    (if-let [^Edge edges (edges-between v1 label v2)]
      (do
        (doseq [^Edge edge edges] (assoc! edge data)) edges)
       #{(connect! v1 label v2 data)})))

(defn unique-upconnect!
  "Like upconnect!, but throws an error when more than element is returned."
  [& args]
  (let [upconnected (apply upconnect! args)]
    (if (= 1 (count upconnected))
      (first upconnected)
      (throw (Throwable.
               (str
                 "Don't call unique-upconnect! when there is more than one element returned.\n"
                 "There were " (count upconnected) " edges returned.\n"
                 "The arguments were: " args "\n"))))))

(defn upconnect-with-id!
  "Takes all the edges between the given vertices with the given id and label
   and, if the data is provided, merges the data with the current properties
   of the edge. If no such edge exists, then an edge is created with the
   given data."
  ([id ^Vertex v1 label ^Vertex v2]
    (upconnect-with-id! id v1 label v2 {}))
  ([id ^Vertex v1 label ^Vertex v2 data]
    (if-let [^Edge edges (edges-between v1 label v2)]
      (do
        (doseq [^Edge edge edges] (assoc! edge data)) edges)
       #{(connect-with-id! id v1 label v2 data)})))

(defn unique-upconnect-with-id!
  "Like upconnect-with-id!, but throws an error when more than element is returned."
  [& args]
  (let [upconnected (apply upconnect-with-id! args)]
    (if (= 1 (count upconnected))
      (first upconnected)
      (throw (Throwable.
               (str
                 "Don't call unique-upconnect! when there is more than one element returned.\n"
                 "There were " (count upconnected) " edges returned.\n"
                 "The arguments were: " args "\n"))))))
