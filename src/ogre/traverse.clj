(ns ogre.traverse
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

(defn both [^GremlinPipeline p & labels]
  (.both p (keywords-to-strings labels)))

(defn <-> [& args]
  (apply both args))

(defn both-edges [^GremlinPipeline p & labels]
  (.bothE p (keywords-to-strings labels)))

(defn <E> [& args]
  (apply both-edges args))

(defn both-vertices [^GremlinPipeline p]
  (.bothV p))

(defn in [^GremlinPipeline p & labels]
  (.in p (keywords-to-strings labels)))

(defn <-- [& args]
  (apply in args))

(defn in-edges [^GremlinPipeline p & labels]
  (.inE p (keywords-to-strings labels)))

(defn <E-- [& args]
  (apply in-edges args))

(defn in-vertex [^GremlinPipeline p & labels]
  (.inV p))

(defn out [^GremlinPipeline p & labels]
  (.out p (keywords-to-strings labels)))

(defn --> [& args]
  (apply out args))

(defn out-edges [^GremlinPipeline p & labels]
  (.outE p (keywords-to-strings labels)))

(defn --E> [& args]
  (apply out-edges args))

(defn out-vertex [^GremlinPipeline p & labels]
  (.outV p))
