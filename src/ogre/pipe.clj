(ns ogre.pipe
  (:refer-clojure :exclude [iterate next])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.blueprints Vertex))
  (:use ogre.util))

(defn add [^GremlinPipeline p e]
  (.add p e))

(defn as [^GremlinPipeline p ^String s]
  (.as p s))

(defn back [^GremlinPipeline p ^Integer i]
  (.back p i))

(defn back-to [^GremlinPipeline p ^String i]
  (.back p i))

(defn enable-path [^GremlinPipeline p]
  (.enablePath p))

(defn iterate [^GremlinPipeline p]
  (.iterate p))

(defn next [^GremlinPipeline p i]
  (.next p i))

(defn optimize [^GremlinPipeline p b]
  (.optimize p b))

(defn optional [^GremlinPipeline p ^Integer s]
  (.optional p s))

(defn optional-to [^GremlinPipeline p ^String s]
  (.optional p s))

(defn start [^GremlinPipeline p o]
  (.start p o))

;; (defn step [^GremlinPipeline p e]
;;   (.step p e))

(defn to-list! [^GremlinPipeline p]
  (.toList p))

(defn into-vec! [p]
  (into [] (to-list! p)))

(defn into-set! [p]
  (into #{} (to-list! p)))

;;Inspiried by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defn convert-to-map [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defn first-of! [p]
  (-> p into-vec! first))

(defn first-into-vec! [p]
  (vec (first-of! p)))

(defn first-into-set! [p]
  (set (first-of! p)))

(defn first-into-map! [p]
  (convert-to-map (first-of! p)))

(defn all-into-vecs! [p]
  (map vec (into-vec! p)))

(defn all-into-sets! [p]
  (map set (into-vec! p)))

(defn all-into-maps! [p]
  (map convert-to-map (into-vec! p)))

;; Reversed property accessors

(defn prop [k]
  (fn [^Vertex v]
    (.getProperty v (name k))))