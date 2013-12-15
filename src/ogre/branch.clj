(ns ogre.branch
  (:refer-clojure :exclude [loop])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.branch LoopPipe$LoopBundle))
  (:use ogre.util))

(defn copy-split 
  [p & es]
  (conj p #(.copySplit % (pipe-array es))))

(defn- loop-unbundler [f]
  (fn [^LoopPipe$LoopBundle b]
    (f (.getLoops b)
       (.getObject b)
       (.getPath b))))

(defn loop
  ([^GremlinPipeline p ^Integer i while-f]
     (conj p #(.loop % i (f-to-pipef (loop-unbundler while-f)))))
  ([^GremlinPipeline p ^Integer i while-f emit-f]
     (conj p #(.loop % i 
                     (f-to-pipef (loop-unbundler while-f))
                     (f-to-pipef emit-f)))))

(defn loop-to
  ([^GremlinPipeline p ^String s while-f]
     (conj p #(.loop % s (f-to-pipef (loop-unbundler while-f)))))
  ([^GremlinPipeline p ^String s while-f emit-f]
     (conj p #(.loop % s
                     (f-to-pipef (loop-unbundler while-f))
                     (f-to-pipef emit-f)))))
