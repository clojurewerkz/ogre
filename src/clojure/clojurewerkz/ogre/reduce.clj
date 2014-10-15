(ns clojurewerkz.ogre.reduce
  (:refer-clojure :exclude [fold])
  (:use clojurewerkz.ogre.util)
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal)
           (com.tinkerpop.gremlin.structure Order)))

(defn fold
  ([t] (.fold ^GraphTraversal t)))

(defn order
  ([t] (order t Order/incr))
  ([t c] (.order ^GraphTraversal t c)))

