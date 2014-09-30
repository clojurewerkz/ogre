(ns clojurewerkz.archimedes.io
  (:require [clojure.java.io :as io]
            [clojurewerkz.archimedes.graph :as g])
  (:import [com.tinkerpop.blueprints.util.io.graphml GraphMLWriter GraphMLReader]
           [com.tinkerpop.blueprints.util.io.gml GMLWriter GMLReader]
           [com.tinkerpop.blueprints.util.io.graphson GraphSONWriter GraphSONReader GraphSONMode]))

(defn- load-graph-with-reader
  [reader g string-or-file]
  (let [in-stream (io/input-stream string-or-file)]
    (reader g in-stream)))

(defn- write-graph-with-writer
  [writer g string-or-file]
  (if (not (g/get-feature g "supportsVertexIteration"))
    (throw (Exception. "Cannot write a graph that does not support vertex iteration.")))
  (let [out-stream (io/output-stream string-or-file)]
    (writer g out-stream)))

;; GML
(def load-graph-gml (partial load-graph-with-reader #(GMLReader/inputGraph %1 %2)))
(def write-graph-gml (partial write-graph-with-writer #(GMLWriter/outputGraph %1 %2)))

;; GraphML
(def load-graph-graphml (partial load-graph-with-reader #(GraphMLReader/inputGraph %1 %2)))
(def write-graph-graphml (partial write-graph-with-writer #(GraphMLWriter/outputGraph %1 %2)))

;; GraphSON
(def load-graph-graphson (partial load-graph-with-reader #(GraphSONReader/inputGraph %1 %2)))

;; write-graph-graphson can take an optional 2nd argument:
;; show-types - determines if types are written explicitly to the JSON
;; Note that for Titan Graphs with types, you will want show-types=true.
;; See https://github.com/tinkerpop/blueprints/wiki/GraphSON-Reader-and-Writer-Library
(defn write-graph-graphson
  [g string-or-file & [ show-types ]]
  (let [graphSON-mode (if show-types GraphSONMode/EXTENDED GraphSONMode/NORMAL)]
    (write-graph-with-writer
     #(GraphSONWriter/outputGraph %1 %2 graphSON-mode)
     g
     string-or-file)))
