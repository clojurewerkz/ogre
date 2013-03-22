(ns ogre.reduce
  (:refer-clojure :exclude [count])
  (:use ogre.util)
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)))

(defn gather
  ([^GremlinPipeline p] (.gather p))
  ([^GremlinPipeline p f] (.gather p (f-to-pipef f))))

(defn order
  ([^GremlinPipeline p] (.order p))
  ([^GremlinPipeline p compare]
     (.order #^GremlinPipeline p (f-to-pipef (fn [pair]                                      
                                               (compare (.getA pair)
                                                        (.getB pair)))))))
;; (defn order-decr
;;   ([p] (.order p )))

(defn count! [^GremlinPipeline p]
  (.count p))
