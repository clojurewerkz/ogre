(ns ogre.reduce
  (:refer-clojure :exclude [count])
  (:use ogre.util)
  (:import (com.tinkerpop.pipes.transform TransformPipe)))

(defn gather
  ([p] (.gather p))
  ([p f] (.gather p (f-to-pipef f))))

(defn order
  ([p] (.order p))
  ([p compare]
     (.order p (f-to-pipef (fn [pair]                                      
                            (compare (.getA pair)
                                     (.getB pair)))))))
;; (defn order-decr
;;   ([p] (.order p )))

(defn count! [p]
  (.count p))
