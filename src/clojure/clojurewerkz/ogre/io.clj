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
      (set-if-present vertex-id-key (.vertexIdKey %1 %2))
      (set-if-present edge-id-key (.edgeIdKey %1 %2))
      (set-if-present vertex-label-key (.vertexLabelKey %1 %2))
      (set-if-present edge-label-key (.edgeLabelKey %1 %2))
      (set-if-present batch-size (.batchSize %1 %2))
      (.create))))
(def read-graph-graphml
  ([] (read-graph-graphml (make-graphml-reader)))
  ([reader](partial read-graph-with-reader #(.readGraph reader %1 %2))))

;; GraphML Writer
(defn make-graphml-writer [& {:keys [normalize vertex-key-types edge-key-types edge-label-key vertex-label-key xml-schema-location]}]
  (let [builder (GraphMLWriter/build)]
    (-> builder
      (set-if-present normalize (.normalize %1 %2))
      (set-if-present vertex-key (.vertexKeyTypes %1 %2))
      (set-if-present edge-id-key (.edgeKeyTypes %1 %2))
      (set-if-present vertex-label-key (.vertexLabelKey %1 %2))
      (set-if-present edge-label-key (.edgeLabelKey %1 %2))
      (set-if-present batch-size (.xmlSchemaLocation %1 %2)))
      (.create)))
(def write-graph-graphml
  ([] (write-graph-graphml (make-graphml-writer)))
  ([writer] (partial write-graph-with-writer #(.writeGraph writer %1 %2))))

;; GraphSON Reader
(defn make-graphson-reader [& {:keys [vertex-id-key edge-id-key custom-module load-custom-modules embed-types batch-size]}]
  (let [builder (GraphSONReader/build)]
    (-> builder
      (set-if-present vertex-id-key (.vertexIdKey %1 %2))
      (set-if-present edge-id-key (.edgeIdKey %1 %2))
      (set-if-present custom-module (.customModule %1 %2))
      (set-if-present load-custom-modules (.loadCustomModule %1 %2))
      (set-if-present embed-types (.embedTypes %1 %2))
      (set-if-present batch-size (.batchSize %1 %2))
      (.create))))
(def read-graph-graphson
  ([] (read-graph-graphson (make-graphml-reader)))
  ([reader](partial read-graph-with-reader #(.readGraph reader %1 %2))))

;; GraphSON Writer
(defn make-graphson-writer [& {:keys [custom-module load-custom-modules embed-types normalize]}]
  (let [builder (GraphSONWriter/build)]
    (-> builder
      (set-if-present normalize (.normalize %1 %2))
      (set-if-present custom-module (.customModule %1 %2))
      (set-if-present load-custom-modules (.loadCustomModule %1 %2))
      (set-if-present embed-types (.embedTypes %1 %2))
      (.create)))
(def write-graph-graphson
  ([] (write-graph-graphson (make-graphml-writer)))
  ([writer] (partial write-graph-with-writer #(.writeGraph writer %1 %2))))
