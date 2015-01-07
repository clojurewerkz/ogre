(ns clojurewerkz.ogre.io
  (:require [clojure.java.io :as io]
            [clojurewerkz.ogre.graph :as g])
  (:import [com.tinkerpop.gremlin.structure.io.graphml GraphMLWriter GraphMLReader]
           [com.tinkerpop.gremlin.structure.io.graphson GraphSONWriter GraphSONReader]
           [com.tinkerpop.gremlin.structure.io.kryo KryoWriter KryoReader]))

(defn- read-graph-with-reader
  [reader g string-or-file]
  (let [in-stream (io/input-stream string-or-file)]
    (reader in-stream g)
    (.close in-stream)))

(defn- write-graph-with-writer
  [writer g string-or-file]
  (let [out-stream (io/output-stream string-or-file)]
    (writer out-stream g)
    (.flush out-stream)
    (.close out-stream)))

(defn- set-if-present
  [builder arg setter]
  (if-not (nil? arg) (setter builder arg) builder))

;; GraphML Reader
(defn ^GraphMLReader make-graphml-reader [& {:keys [vertex-id-key edge-id-key edge-label-key vertex-label-key batch-size]}]
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
(defn ^GraphMLWriter make-graphml-writer [& {:keys [normalize vertex-key-types edge-key-types edge-label-key vertex-label-key xml-schema-location]}]
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
(defn ^GraphSONReader make-graphson-reader [& {:keys [vertex-id-key edge-id-key custom-module load-custom-modules embed-types batch-size]}]
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
(defn ^GraphSONWriter make-graphson-writer [& {:keys [custom-module load-custom-modules embed-types normalize]}]
  (let [builder (GraphSONWriter/build)]
    (-> builder
      (set-if-present normalize (memfn normalize))
      (set-if-present custom-module (memfn customModule))
      (set-if-present load-custom-modules (memfn loadCustomModule))
      (set-if-present embed-types (memfn embedTypes))
      (.create))))
(def write-graph-graphson (partial write-graph-with-writer #(.writeGraph (make-graphson-writer) %1 %2)))

;; Kryo Reader
(defn ^KryoReader make-kryo-reader [& {:keys [vertex-id-key edge-id-key working-directory custom batch-size]}]
  (let [builder (KryoReader/build)]
    (-> builder
      (set-if-present vertex-id-key (memfn vertexIdKey))
      (set-if-present edge-id-key (memfn edgeIdKey))
      (set-if-present custom (memfn custom))
      (set-if-present working-directory (memfn workingDirectory))
      (set-if-present batch-size (memfn batchSize))
      (.create))))
(def read-graph-kryo (partial read-graph-with-reader #(.readGraph (make-kryo-reader) %1 %2)))

;; Kryo Writer
(defn ^KryoWriter make-kryo-writer [& {:keys [custom]}]
  (let [builder (KryoWriter/build)]
    (-> builder
      (set-if-present custom (memfn custom))
      (.create))))
(def write-graph-kryo (partial write-graph-with-writer #(.writeGraph (make-kryo-writer) %1 %2)))
