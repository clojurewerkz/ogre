(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

(defn map
  ([p & keys] 
     (conj p #(.map ^GremlinPipeline % (keywords-to-strings keys)))))

(defn select
  ([p]
     (conj p #(.select ^GremlinPipeline %)))
  ([p & fs] 
     (conj p #(.select ^GremlinPipeline % (fs-to-pipef-array fs)))))

(defn select-only
  ([p cols]
     (select-only p cols identity))
  ([p ^java.util.Collection cols & fs] 
     (conj p #(.select ^GremlinPipeline % cols (fs-to-pipef-array fs)) )))

;; (defn memoize
;;   ([is] (.memoize is))
;;   ([is m] (.memoize is m)))
