(ns ogre.branch
  (:refer-clojure :exclude [loop])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.branch LoopPipe$LoopBundle))
  (:use ogre.util))

(defn copy-split [^GremlinPipeline p & es]
  (.copySplit p ^java.util.List (pipe-array es)))

(defn exhaust-merge [^GremlinPipeline p]
  (.exhaustMerge p))


(defn fair-merge [^GremlinPipeline p]
  (.fairMerge p))

(defn if-then-else [^GremlinPipeline p pred then else]
  (.ifThenElse p (f-to-pipef pred) (f-to-pipef then) (f-to-pipef else)))


(defn loop-unbundler [f]
  (fn [^LoopPipe$LoopBundle b]
    (f (.getLoops b)
       (.getObject b)
       (.getPath b))))

(defn loop
  ([^GremlinPipeline p i while-f]
     (.loop p i (f-to-pipef (loop-unbundler while-f))))
  ([^GremlinPipeline p i while-f emit-f]
     (.loop p i (f-to-pipef (loop-unbundler while-f)) (f-to-pipef emit-f))))

(defn loop-to
  ([^GremlinPipeline p s while-f]
     (.loop p s (f-to-pipef (loop-unbundler while-f))))
  ([^GremlinPipeline p s while-f emit-f]
     (.loop p s
            (f-to-pipef (loop-unbundler while-f))
            (f-to-pipef emit-f))))