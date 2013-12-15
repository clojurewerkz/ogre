(ns ogre.reduce
  (:refer-clojure :exclude [count reverse])
  (:use ogre.util)
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.transform TransformPipe$Order)
           (com.tinkerpop.pipes.util.structures Pair)))

(defn gather
  ([^GremlinPipeline p] (conj p #(.gather %)))
  ([^GremlinPipeline p f]
     (conj p #(.gather % (f-to-pipef f)))))

(defn order
  ([^GremlinPipeline p] (conj p #(.order %)))
  ([^GremlinPipeline p compare]
     (conj p #(.order % (f-to-pipef (fn [^Pair pair]       
                                      (compare (.getA pair)
                                               (.getB pair))))) )))
(defn reverse
  [^GremlinPipeline p] 
  (conj p #(.order % TransformPipe$Order/DECR)))
