(ns clojurewerkz.archimedes.edge
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import (com.tinkerpop.blueprints Vertex Edge Direction Graph)
           (com.tinkerpop.blueprints.impls.tg TinkerGraph))
  (:require [clojurewerkz.archimedes.vertex :as v]
            [clojurewerkz.archimedes.graph :refer (*element-id-key* *edge-label-key*)]
            [clojurewerkz.archimedes.conversion :refer (to-edge-direction)]
            [clojurewerkz.archimedes.query :as q]
            [clojurewerkz.archimedes.element :as ele]
            [potemkin :as po]))

(po/import-fn ele/get)
(po/import-fn ele/keys)
(po/import-fn ele/vals)
(po/import-fn ele/id-of)
(po/import-fn ele/assoc!)
(po/import-fn ele/merge!)
(po/import-fn ele/dissoc!)
(po/import-fn ele/update!)
(po/import-fn ele/clear!)

;;
;;Transaction management
;;

(defn refresh
  "Goes and grabs the edge from the graph again. Useful for \"refreshing\" stale edges."
  [g ^Edge edge]
   (.getEdge g (.getId edge)))

;;
;; Removal methods
;;

(defn remove!
  "Remove an edge."
  [g ^Edge edge]
  (.removeEdge g edge))

;;
;; Information getters
;;

(defn label-of
  "Get the label of the edge"
  [^Edge edge]
  (keyword (.getLabel edge)))

(defn to-map
  "Returns a persisten map representing the edge."
  [^Edge edge]
  (->> (keys edge)
       (map #(vector (keyword %) (get edge %)))
       (into {*element-id-key* (id-of edge) *edge-label-key* (label-of edge)})))

(defn find-by-id
  "Retrieves edges by id from the graph."
  [g & ids]
  (if (= 1 (count ids))
                (.getEdge g (first ids))
                (seq (for [id ids] (.getEdge g id)))))

(defn get-all-edges
  "Returns all edges."
  [g]
  (set (.getEdges g)))

(defn ^Vertex get-vertex
  "Get the vertex of the edge in a certain direction."
  [^Edge e direction]
  (.getVertex e (to-edge-direction direction)))

(defn ^Vertex head-vertex
  "Get the head vertex of the edge."
  [^Edge e]
  (.getVertex e Direction/IN))

(defn ^Vertex tail-vertex
  "Get the tail vertex of the edge."
  [^Edge e]
  (.getVertex e Direction/OUT))

(defn endpoints
  "Returns the endpoints of the edge in array with the order [starting-node,ending-node]."
  [^Edge edge]
  [(.getVertex edge Direction/OUT)
   (.getVertex edge Direction/IN)])

(defn edges-between
  "Returns a set of the edges between two vertices, direction considered."
  ([^Vertex v1 ^Vertex v2]
     (edges-between v1 nil v2))
  ([^Vertex v1 label ^Vertex v2]
     ;; Source for these edge queries:
     ;; https://groups.google.com/forum/?fromgroups=#!topic/gremlin-users/R2RJxJc1BHI
     (let [^Edge edges (q/find-edges v1
                                     (q/direction :out)
                                     (q/labels label))
           v2-id (.getId v2)
           edge-set (set (filter #(= v2-id (.getId (.getVertex % (to-edge-direction :in)))) edges))]
       (when (not (empty? edge-set))
         edge-set))))

(defn connected?
  "Returns whether or not two vertices are connected. Optional third
   arguement specifying the label of the edge."
  ([^Vertex v1 ^Vertex v2]
     (connected? v1 nil v2))
  ([^Vertex v1 label ^Vertex v2]
     (not (empty? (edges-between v1 label v2)))))

;;
;; Creation methods
;;

(defn connect!
  "Connects two vertices with the given label, and, optionally, with the given properties."
  ([g ^Vertex v1 label ^Vertex v2]
     (connect! g v1 label v2 {}))
  ([g ^Vertex v1 label ^Vertex v2 data]
     (let [new-edge (.addEdge g nil v1 v2 ^String (name label))]
       (merge! new-edge data))))

(defn connect-with-id!
  "Connects two vertices with the given label, and, optionally, with the given properties."
  ([g id ^Vertex v1 label ^Vertex v2]
     (connect-with-id! g id v1 label v2 {}))
  ([g id ^Vertex v1 label ^Vertex v2 data]
     (let [new-edge (.addEdge g id v1 v2 ^String (name label))]
       (merge! new-edge data))))

(defn upconnect!
  "Upconnect takes all the edges between the given vertices with the
   given label and, if the data is provided, merges the data with the
   current properties of the edge. If no such edge exists, then an
   edge is created with the given data."
  ([g ^Vertex v1 label ^Vertex v2]
     (upconnect! g v1 label v2 {}))
  ([g ^Vertex v1 label ^Vertex v2 data]
     (if-let [^Edge edges (edges-between v1 label v2)]
       (do
         (doseq [^Edge edge edges] (merge! edge data))
         edges)
       #{(connect! g v1 label v2 data)})))

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
  "Upconnect takes all the edges between the given vertices with the
   given label and, if the data is provided, merges the data with the
   current properties of the edge. If no such edge exists, then an
   edge is created with the given data."
  ([g id ^Vertex v1 label ^Vertex v2]
     (upconnect-with-id! g id v1 label v2 {}))
  ([g id ^Vertex v1 label ^Vertex v2 data]
     (if-let [^Edge edges (edges-between v1 label v2)]
       (do
         (doseq [^Edge edge edges] (merge! edge data))
         edges)
       #{(connect-with-id! g id v1 label v2 data)})))

(defn unique-upconnect-with-id!
  "Like upconnect!, but throws an error when more than element is returned."
  [& args]
  (let [upconnected (apply upconnect-with-id! args)]
    (if (= 1 (count upconnected))
      (first upconnected)
      (throw (Throwable.
              (str
               "Don't call unique-upconnect! when there is more than one element returned.\n"
               "There were " (count upconnected) " edges returned.\n"
               "The arguments were: " args "\n"))))))
