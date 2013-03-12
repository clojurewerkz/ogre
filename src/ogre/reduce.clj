(ns ogre.reduce
  (:refer-clojure :exclude [count])
  (:use ogre.util)
  (:import (com.tinkerpop.pipes.transform TransformPipe)))

;; GremlinPipeline<S,List>	gather() 
;; Add a GatherPipe to the end of the Pipeline.
;; GremlinPipeline<S,?>	gather(com.tinkerpop.pipes.PipeFunction<List,?> function) 
;; Add a GatherPipe to the end of the Pipeline.

(defn gather
  ([p] (.gather p))
  ([p f] (.gather p (f-to-pipe f))))

;; GremlinPipeline<S,E>	order() 
;; Add an OrderPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	order(com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.util.structures.Pair<E,E>,Integer> compareFunction) 
;; Add an OrderPipe to the end of the Pipeline.

(defn order
  ([p] (.order p))
  ([p compare]
     (.order p (f-to-pipe (fn [pair]                                      
                            (compare (.getA pair)
                                     (.getB pair)))))))
;; (defn order-decr
;;   ([p] (.order p )))

;; long	count() 
;; Return the number of objects iterated through the pipeline.

(defn count [p]
  (.count p))
