(ns clojurewerkz.ogre.pipe
  (:refer-clojure :exclude [iterate])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.blueprints Vertex)
           (com.tinkerpop.pipes.util.structures Row))
  (:use clojurewerkz.ogre.util))

(defn back-to 
  [p ^String s]
  (conj p #(.back ^GremlinPipeline % s)))

(defn iterate!
  [p]
  (.iterate ^GremlinPipeline (compile-query p)))

(defn next!
  [p i]
  (.next ^GremlinPipeline (compile-query p) i))

;; (defn step [^GremlinPipeline p e]
;;   (.step p e))

(defmacro ^{:private true}
  to-java-list! 
  [p]
  `(.toList ^GremlinPipeline (compile-query ~p)))

(defn into-vec! 
  [p]
  (into [] (to-java-list! p)))

(defn into-set! 
  [p]
  (into #{} (to-java-list! p)))

(defn into-list! 
  [p]
  (into '() (to-java-list! p)))

(defn into-lazy-seq!
  [p]
  (let [pipe (compile-query p)
        f (fn [_] (first (.next pipe 1)))]
    (clojure.core/iterate f (f nil))))

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
  [p]
  (-> p (next! 1) first))

(defn first-into-vec! 
  [p]
  (vec (first-of! p)))

(defn first-into-set! 
  [p]
  (set (first-of! p)))

(defn first-into-map! 
  [p]
  (convert-to-map (first-of! p)))

(defn all-into-vecs! 
  [p]
  (map vec (into-vec! p)))

(defn all-into-sets! 
  [p]
  (map set (into-vec! p)))

(defn all-into-maps! 
  [p]
  (map convert-to-map (into-vec! p)))

(defn count! 
  [p]
  (.count ^GremlinPipeline (compile-query p)))
;; Reversed property accessors

(defn prop 
  [k]
  (fn [^Vertex v]
    (.getProperty v (name k))))
