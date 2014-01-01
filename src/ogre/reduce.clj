(ns ogre.reduce
  (:refer-clojure :exclude [count reverse])
  (:use ogre.util)
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.transform TransformPipe$Order)
           (com.tinkerpop.pipes.util.structures Pair)))

(defn gather
  ([p] (conj p #(.gather ^GremlinPipeline %)))
  ([p f]
     (conj p #(.gather ^GremlinPipeline % (f-to-pipef f)))))

(defn order
  ([p] (conj p #(.order ^GremlinPipeline %)))
  ([p compare]
     (conj p #(.order ^GremlinPipeline % (f-to-pipef (fn [^Pair pair]       
                                                       (compare (.getA pair)
                                                                (.getB pair))))) )))
(defn reverse
  [p] 
  (conj p #(.order ^GremlinPipeline % TransformPipe$Order/DECR)))
