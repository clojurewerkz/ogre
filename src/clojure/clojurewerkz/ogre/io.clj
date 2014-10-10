(ns clojurewerkz.ogre.io
  (:require [clojure.java.io :as io]
            [clojurewerkz.ogre.graph :as g])
  (:import [com.tinkerpop.gremlin.structure.io.graphml GraphMLWriter GraphMLReader]
           [com.tinkerpop.gremlin.structure.io.graphson GraphSONWriter GraphSONReader]))

(defn- read-graph-with-reader
  [reader g string-or-file]
  (let [in-stream (io/input-stream string-or-file)]
    (reader g in-stream)))

(defn- write-graph-with-writer
  [writer g string-or-file]
  (let [out-stream (io/output-stream string-or-file)]
    (writer g out-stream)))

(defn- set-if-present
  [builder arg setter]
  (if-not (nil? arg) (setter builder arg) builder))

;; GraphML Reader
(defn make-graphml-reader [& {:keys [vertex-id-key edge-id-key edge-label-key vertex-label-key batch-size]}]
  (let [builder (GraphMLReader/build)]
    (-> builder
      (set-if-present vertex-id-key (memfn vertexIdKey))
      (set-if-present edge-id-key (memfn edgeIdKey))
      (set-if-present vertex-label-key (memfn vertexLabelKey))
      (set-if-present edge-label-key (memfn edgeLabelKey))
      (set-if-present batch-size (memfn batchSize))
      (.create))))
(def read-graph-graphml (partial read-graph-with-reader #(.readGraph (make-graphml-reader) %1 %2)))

;; GraphML Writer
(defn make-graphml-writer [& {:keys [normalize vertex-key-types edge-key-types edge-label-key vertex-label-key xml-schema-location]}]
  (let [builder (GraphMLWriter/build)]
    (-> builder
      (set-if-present normalize (memfn normalize))
      (set-if-present vertex-key-types (memfn vertexKeyTypes))
      (set-if-present edge-key-types (memfn edgeKeyTypes))
      (set-if-present vertex-label-key (memfn vertexLabelKey))
      (set-if-present edge-label-key (memfn edgeLabelKey))
      (set-if-present xml-schema-location (memfn xmlSchemaLocation))
      (.create))))
(def write-graph-graphml (partial write-graph-with-writer #(.writeGraph (make-graphml-writer) %1 %2)))

;; GraphSON Reader
(defn make-graphson-reader [& {:keys [vertex-id-key edge-id-key custom-module load-custom-modules embed-types batch-size]}]
  (let [builder (GraphSONReader/build)]
    (-> builder
      (set-if-present vertex-id-key (memfn vertexIdKey))
      (set-if-present edge-id-key (memfn edgeIdKey))
      (set-if-present custom-module (memfn customModule))
      (set-if-present load-custom-modules (memfn loadCustomModule))
      (set-if-present embed-types (memfn embedTypes))
      (set-if-present batch-size (memfn batchSize))
      (.create))))
(def read-graph-graphson (partial read-graph-with-reader #(.readGraph (make-graphson-reader) %1 %2)))

;; GraphSON Writer
(defn make-graphson-writer [& {:keys [custom-module load-custom-modules embed-types normalize]}]
  (let [builder (GraphSONWriter/build)]
    (-> builder
      (set-if-present normalize (memfn normalize))
      (set-if-present custom-module (memfn customModule))
      (set-if-present load-custom-modules (memfn loadCustomModule))
      (set-if-present embed-types (memfn embedTypes))
      (.create)))
(def write-graph-graphson (partial write-graph-with-writer #(.writeGraph (make-graphson-writer) %1 %2))))
