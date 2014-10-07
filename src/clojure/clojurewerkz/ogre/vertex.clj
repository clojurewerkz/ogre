(ns clojurewerkz.ogre.vertex
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import (com.tinkerpop.gremlin.structure Vertex Direction Graph)
           (com.tinkerpop.gremlin.tinkergraph.structure TinkerGraph))
  (:require [clojurewerkz.ogre.graph :refer (*element-id-key*)]
            [clojurewerkz.ogre.util :refer (keywords-to-str-array)]
            [clojurewerkz.ogre.conversion :refer (to-edge-direction)]
            [clojurewerkz.ogre.element :as ele]
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
;; Transaction management
;;

(defn refresh
  "Gets a vertex back from the database and refreshes it to be usable again."
  [g vertex]
  (.v g (.id vertex)))

;;
;; Removal methods
;;

(defn remove!
  "Remove a vertex from the graph."
  [vertex]
  (.remove vertex))


;;
;;Information getters
;;
(defn to-map
  "Returns a persistent map representing the vertex."
  [vertex]
  (->> (keys vertex)
       (map #(vector (keyword %) (get vertex %)))
       (into { *element-id-key* (id-of vertex)})))

;;Finders
(defn find-by-id
  "Retrieves nodes by id from the given graph."
  [g & ids]
  (if (= 1 (count ids))
    (.v g (first ids))
    (seq (for [id ids] (.v g id)))))

(defn find-by-kv
  "Given a key and a value, returns the set of all vertices that
   sastify the pair."
  [g k v]
  (set (-> g (.V) (.has (name k) v))))

(defn get-all-vertices
  "Returns all vertices."
  [g]
  (set (.V g)))

(defn edges-of
  "Returns edges that this vertex is part of with direction and with given labels"
  [^Vertex v direction & labels]
  (let [dir (to-edge-direction direction)]
    (case dir
      Direction/IN (incoming-edges-of v labels)
      Direction/OUT (outgoing-edges-of v labels)
      Direction/BOTH (all-edges-of v labels))))

(defn all-edges-of
  "Returns edges that this vertex is part of, with given labels"
  [^Vertex v & labels]
  (.bothE v (keywords-to-str-array labels)))

(defn outgoing-edges-of
  "Returns outgoing (outbound) edges that this vertex is part of, with given labels"
  [^Vertex v & labels]
  (.outE v (keywords-to-str-array labels)))

(defn incoming-edges-of
  "Returns incoming (inbound) edges that this vertex is part of, with given labels"
  [^Vertex v & labels]
  (.inE v (keywords-to-str-array labels)))

(defn connected-vertices-of
  "Returns vertices connected to this vertex with a certain direction by the given labels"
  [^Vertex v direction & labels]
  (let [dir (to-edge-direction direction)]
    (case dir
      Direction/IN (connected-in-vertices v labels)
      Direction/OUT (connected-out-vertices v labels)
      Direction/BOTH (all-connected-vertices v labels))))

(defn connected-out-vertices
  "Returns vertices connected to this vertex by an outbound edge with the given labels"
  [^Vertex v & labels]
  (.out v (keywords-to-str-array labels)))

(defn connected-in-vertices
  "Returns vertices connected to this vertex by an inbound edge with the given labels"
  [^Vertex v & labels]
  (.in v (keywords-to-str-array labels)))

(defn all-connected-vertices
  "Returns vertices connected to this vertex with the given labels"
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
     (let [^Vertex new-vertex (.addVertex g nil)]
       (merge! new-vertex m))))

(defn create-with-id!
  "Create a vertex, optionally with the given property map."
  ([g id]
     (create-with-id! g id {}))
  ([g id m]
     (let [^Vertex new-vertex (.addVertex ^Graph g id)]
       (merge! new-vertex m))))

(defn upsert!
  "Given a key and a property map, upsert! either creates a new node
   with that property map or updates all nodes with the given key
   value pair to have the new properties specifiied by the map. Always
   returns the set of vertices that were just update or created."
  [g k m]
  (let [vertices (find-by-kv g (name k) (k m))]
    (if (empty? vertices)
      (set [(create! g m)])
      (do
        (doseq [vertex vertices] (merge! vertex m))
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
  "Given a key and a property map, upsert! either creates a new node
   with that property map or updates all nodes with the given key
   value pair to have the new properties specifiied by the map. Always
   returns the set of vertices that were just update or created."
  [g id k m]
  (let [vertices (find-by-kv g (name k) (k m))]
    (if (empty? vertices)
      (set [(create-with-id! g id m)])
      (do
        (doseq [vertex vertices] (merge! vertex m))
        vertices))))

(defn unique-upsert-with-id!
  "Like upsert!, but throws an error when more than one element is returned."
  [& args]
  (let [upserted (apply upsert-with-id! args)]
    (if (= 1 (count upserted))
      (first upserted)
      (throw (Throwable.
              (str
               "Don't call unique-upsert! when there is more than one element returned.\n"
               "There were " (count upserted) " vertices returned.\n"
               "The arguments were: " args "\n"))))))
