(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

(defn map
  ([^GremlinPipeline p & keys] (.map p (keywords-to-strings keys))))

(defn transform 
  [^GremlinPipeline p f]
  (.transform p (f-to-pipef f)))

(defn _ 
  [^GremlinPipeline p]
  (._ p))

(defn id 
  [^GremlinPipeline p] 
  (.id p))

(defn property 
  [^GremlinPipeline p prop]
  (.property p (name prop)))

(defn label 
  [^GremlinPipeline p]
  (.label p))

(defn select
  ([^GremlinPipeline p] (.select p))
  ([^GremlinPipeline p & fs] (.select p (fs-to-pipef-array fs))))

(defn select-only
  ([^GremlinPipeline p cols] (select-only p cols identity))
  ([^GremlinPipeline p ^java.util.Collection cols & fs] (.select p cols (fs-to-pipef-array fs))))

;; (defn memoize
;;   ([is] (.memoize is))
;;   ([is m] (.memoize is m)))

(defn scatter 
  [^GremlinPipeline p]
  (.scatter p))

(defn path 
  [^GremlinPipeline p & args]
  (.path p (fs-to-pipef-array args)))