(ns clojurewerkz.ogre.reduce
  (:refer-clojure :exclude [fold])
  (:use clojurewerkz.ogre.util)
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal)))

(defn fold
  ([t] (conj t #(.fold ^GraphTraversal %))))

