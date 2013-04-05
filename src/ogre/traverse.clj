(ns ogre.traverse
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

;;; Both traversals 
(defn both 
  ([^GremlinPipeline p] (both p []))
  ([^GremlinPipeline p labels] (.both p (keywords-to-strings labels))))

(defn <-> 
  [& args]
  (apply both args))

(defn both-edges 
  ([^GremlinPipeline p] (both-edges p []))
  ([^GremlinPipeline p labels] (.bothE p (keywords-to-strings labels))))

(defn <E> 
  [& args]
  (apply both-edges args))

(defn both-vertices [^GremlinPipeline p]
  (.bothV p))

;; In traversals
(defn in 
  ([^GremlinPipeline p] (in p []))
  ([^GremlinPipeline p labels] (.in p (keywords-to-strings labels))))

(defn <-- 
  [& args]
  (apply in args))

(defn in-edges 
  ([^GremlinPipeline p] (in-edges p []))
  ([^GremlinPipeline p labels] (.inE p (keywords-to-strings labels))))

(defn <E-- 
  [& args]
  (apply in-edges args))

(defn in-vertex 
  [^GremlinPipeline p]
  (.inV p))

;; Out traversals 

(defn out 
  ([^GremlinPipeline p] (out p []))
  ([^GremlinPipeline p labels] (.out p (keywords-to-strings labels))))


(defn --> 
  [& args]
  (apply out args))

(defn out-edges 
  ([^GremlinPipeline p] (out-edges p []))
  ([^GremlinPipeline p labels] (.outE p (keywords-to-strings labels))))


(defn --E> 
  [& args]
  (apply out-edges args))

(defn out-vertex 
  [^GremlinPipeline p]
  (.outV p))
