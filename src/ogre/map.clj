(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

(defn map
  ([^GremlinPipeline p & keys] 
     (conj p #(.map % (keywords-to-strings keys)))))

(defn select
  ([^GremlinPipeline p]
     (conj p #(.select %)))
  ([^GremlinPipeline p & fs] 
     (conj p #(.select % (fs-to-pipef-array fs)))))

(defn select-only
  ([^GremlinPipeline p cols]
     (select-only p cols identity))
  ([^GremlinPipeline p ^java.util.Collection cols & fs] 
     (conj p #(.select % cols (fs-to-pipef-array fs)) )))

;; (defn memoize
;;   ([is] (.memoize is))
;;   ([is m] (.memoize is m)))
