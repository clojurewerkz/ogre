(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

(defn map
  ([^GremlinPipeline p & keys] 
     (.map p (keywords-to-strings keys))))

(defn select
  ([^GremlinPipeline p]
     (.select p))
  ([^GremlinPipeline p & fs] 
     (.select p (fs-to-pipef-array fs))))

(defn select-only
  ([^GremlinPipeline p cols]
     (select-only p cols identity))
  ([^GremlinPipeline p ^java.util.Collection cols & fs] 
     (.select p cols (fs-to-pipef-array fs))))

;; (defn memoize
;;   ([is] (.memoize is))
;;   ([is m] (.memoize is m)))
