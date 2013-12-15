(ns ogre.pipe
  (:refer-clojure :exclude [iterate])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.blueprints Vertex)
           (com.tinkerpop.pipes.util.structures Row))
  (:use ogre.util))

(defn back-to 
  [^GremlinPipeline p ^String s]
  (conj p #(.back % s)))

(defn iterate!
  [^GremlinPipeline p]
  (.iterate (compile-query p)))

(defn next!
  [^GremlinPipeline p i]
  (.next (compile-query p) i))

;; (defn step [^GremlinPipeline p e]
;;   (.step p e))

(defmacro ^{:private true}
  to-java-list! 
  [^GremlinPipeline p]
  `(.toList (compile-query ~p)))

(defn into-vec! 
  [^GremlinPipeline p]
  (into [] (to-java-list! p)))

(defn into-set! 
  [^GremlinPipeline p]
  (into #{} (to-java-list! p)))

(defn into-list! 
  [^GremlinPipeline p]
  (into '() (to-java-list! p)))

;;Inspiried by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defmulti convert-to-map class)

(defmethod convert-to-map java.util.HashMap
  [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defmethod convert-to-map Row
  [^Row m]
  (into {} (for [^String k (.getColumnNames m)] 
             [(keyword k) (.getColumn m k)])))

(defn first-of! 
  [^GremlinPipeline p]
  (-> p (next! 1) first))

(defn first-into-vec! 
  [^GremlinPipeline p]
  (vec (first-of! p)))

(defn first-into-set! 
  [^GremlinPipeline p]
  (set (first-of! p)))

(defn first-into-map! 
  [^GremlinPipeline p]
  (convert-to-map (first-of! p)))

(defn all-into-vecs! 
  [^GremlinPipeline p]
  (map vec (into-vec! p)))

(defn all-into-sets! 
  [^GremlinPipeline p]
  (map set (into-vec! p)))

(defn all-into-maps! 
  [^GremlinPipeline p]
  (map convert-to-map (into-vec! p)))

(defn count! 
  [^GremlinPipeline p]
  (.count (compile-query p)))
;; Reversed property accessors

(defn prop 
  [k]
  (fn [^Vertex v]
    (.getProperty v (name k))))
