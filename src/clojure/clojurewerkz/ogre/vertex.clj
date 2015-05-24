(ns clojurewerkz.ogre.vertex
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import (org.apache.tinkerpop.gremlin.structure Vertex Graph T))
  (:require [clojurewerkz.ogre.util :refer (to-edge-direction keywords-to-str-array prop-map-to-array)]
            [clojurewerkz.ogre.element :as el]
            [clojurewerkz.ogre.traversal :as t]
            [potemkin :as po]))

(po/import-fn el/get)
(po/import-fn el/prop-pred)
(po/import-fn el/keys)
(po/import-fn el/label-of)
(po/import-fn el/vals)
(po/import-fn el/id-of)
(po/import-fn el/assoc!)
(po/import-fn el/dissoc!)
(po/import-fn el/clear!)

;;
;;Transaction management
;;

(defn refresh
  "Gets a vertex back from the database and refreshes it to be usable again."
  [^Graph g ^Vertex vertex]
  (.V g (.id vertex)))

;;
;; Removal methods
;;

(defn remove!
  "Removes a vertex from the graph."
  [^Vertex vertex]
  (.remove vertex))

;;
;; Information getters
;;

(defn to-map
  "Returns a persistent map representing the vertex."
  [^Vertex vertex]
  (->> (keys vertex)
       (map #(vector (keyword %) (get vertex %)))
       (into {T/id (id-of vertex)})))

;; todo: consider what should be done with find-by-id and get-all-edges now that .v is gone from M7
(defn find-by-id
  "Retrieves nodes by id from the given graph."
  [^Graph g & ids]
  (if (= 1 (count ids))
    (try (.next (.V g (into-array ids))) (catch Exception e nil))
    (t/into-vec! (.V g (into-array ids)))))

(defn find-by-kv
  "Given a key and a value, returns the set of all vertices that satisfy the pair."
  [^Graph g k ^Vertex v]
  (-> g (.V (into-array [])) (.has (name k) v)))

(defn get-all-vertices
  "Returns all vertices."
  [^Graph g]
  (.V g (into-array [])))

(defn edges-of
  "Returns edges that this vertex is part of with direction and with given labels."
  [^Vertex v direction & labels]
  (.toE v (to-edge-direction direction) (keywords-to-str-array labels)))

(defn all-edges-of
  "Returns edges that this vertex is part of, with given labels."
  [^Vertex v & labels]
  (.bothE v (keywords-to-str-array labels)))

(defn outgoing-edges-of
  "Returns outgoing (outbound) edges that this vertex is part of, with given labels."
  [^Vertex v & labels]
  (.outE v (keywords-to-str-array labels)))

(defn incoming-edges-of
  "Returns incoming (inbound) edges that this vertex is part of, with given labels."
  [^Vertex v & labels]
  (.inE v (keywords-to-str-array labels)))

(defn connected-vertices-of
  "Returns vertices connected to this vertex with a certain direction by the given labels."
  [^Vertex v direction & labels]
  (.to v (to-edge-direction direction) (keywords-to-str-array labels)))

(defn connected-out-vertices
  "Returns vertices connected to this vertex by an outbound edge with the given labels."
  [^Vertex v & labels]
  (.out v (keywords-to-str-array labels)))

(defn connected-in-vertices
  "Returns vertices connected to this vertex by an inbound edge with the given labels."
  [^Vertex v & labels]
  (.in v (keywords-to-str-array labels)))

(defn all-connected-vertices
  "Returns vertices connected to this vertex with the given labels."
  [^Vertex v & labels]
  (.both v (keywords-to-str-array labels)))

;;
;; Creation methods
;;

(defn create!
  "Create a vertex, optionally with the given property map."
  ([g]
    (create! g {}))
  ([g m]
    (.addVertex ^Graph g ^"[Ljava.lang.Object;" (prop-map-to-array m))))

(defn create-with-id!
  "Create a vertex with id, optionally with the given property map."
  ([g id]
    (create-with-id! g id {}))
  ([g id m]
    (.addVertex ^Graph g ^"[Ljava.lang.Object;" (prop-map-to-array (assoc m T/id id)))))

(defn upsert!
  "Given a key and a property map, either creates a new node
   with that property map or updates all nodes with the given key
   value pair to have the new properties specifiied by the map. Always
   returns the set of vertices that were just updated or created."
  [g k m]
  (let [vertices (t/into-set! (find-by-kv g (name k) (k m)))]
    (if (empty? vertices)
      (set [(create! g m)])
      (do
        (doseq [vertex vertices] (assoc! vertex m))
        vertices))))

(defn unique-upsert!
  "Like upsert!, but throws an error when more than one element is returned."
  [& args]
  (let [upserted (apply upsert! args)]
    (if (= 1 (count upserted))
      (first upserted)
      (throw (Throwable.
              (str
               "Don't call unique-upsert! when there is more than one element returned.\n"
               "There were " (count upserted) " vertices returned.\n"
               "The arguments were: " args "\n"))))))

(defn upsert-with-id!
  "Given an id, key and a property map, either creates a new node
   with that id and property map or updates all nodes with the given key
   value pair to have the new properties specifiied by the map. Always
   returns the set of vertices that were just updated or created."
  [g id k m]
  (let [vertices (t/into-set! (find-by-kv g (name k) (k m)))]
    (if (empty? vertices)
      (set [(create-with-id! g id m)])
      (do
        (doseq [vertex vertices] (assoc! vertex m))
        vertices))))

(defn unique-upsert-with-id!
  "Like upsert-with-id!, but throws an error when more than one element is returned."
  [& args]
  (let [upserted (apply upsert-with-id! args)]
    (if (= 1 (count upserted))
      (first upserted)
      (throw (Throwable.
              (str
               "Don't call unique-upsert! when there is more than one element returned.\n"
               "There were " (count upserted) " vertices returned.\n"
               "The arguments were: " args "\n"))))))
