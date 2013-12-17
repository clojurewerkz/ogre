(ns ogre.branch
  (:refer-clojure :exclude [loop])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.branch LoopPipe$LoopBundle))
  (:use ogre.util))

(defn copy-split 
  [p & es]
  (conj p #(.copySplit ^GremlinPipeline % (pipe-array es))))

(defn- loop-unbundler [f]
  (fn [^LoopPipe$LoopBundle b]
    (f (.getLoops b)
       (.getObject b)
       (.getPath b))))

(defn loop
  ([p ^Integer i while-f]
     (conj p #(.loop ^GremlinPipeline % i (f-to-pipef (loop-unbundler while-f)))))
  ([p ^Integer i while-f emit-f]
     (conj p #(.loop ^GremlinPipeline % i 
                     (f-to-pipef (loop-unbundler while-f))
                     (f-to-pipef emit-f)))))

(defn loop-to
  ([p ^String s while-f]
     (conj p #(.loop ^GremlinPipeline % s (f-to-pipef (loop-unbundler while-f)))))
  ([p ^String s while-f emit-f]
     (conj p #(.loop ^GremlinPipeline % s
                     (f-to-pipef (loop-unbundler while-f))
                     (f-to-pipef emit-f)))))
