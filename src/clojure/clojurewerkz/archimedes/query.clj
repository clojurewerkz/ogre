(ns clojurewerkz.archimedes.query
  (:refer-clojure :exclude [count])
  (:require [clojurewerkz.archimedes.conversion :refer (convert-symbol-to-compare to-edge-direction)])
  (:import  [com.tinkerpop.blueprints Vertex Edge Direction Query]))

;;
;; Implementation
;;

(defn start-at
  [^Vertex starting-point]
  (.query starting-point))

;;
;; API
;;

(defmacro has
  ([q k] `(.has ~q ~(name k)))
  ([q k v] `(.has ~q ~(name k) ~v))
  ([q k c v] `(.has ~q ~(name k) ~v (convert-symbol-to-compare '~c)) ))

(defn interval
  [^Query q key start-val end-val]
  (.interval q (name key) start-val end-val))

(defn direction
  [^Query q dir]
  (.direction q (to-edge-direction dir)))

(defn labels
  [^Query q & coll]
  (.labels q (into-array String (map name (filter identity coll)))))

(defn limit
  [^Query q ^long max]
  (.limit q max))

(defmacro find-vertices
  [^Vertex starting-point & body]
  `(let [^com.tinkerpop.blueprints.Query query# (-> (start-at ~starting-point) ~@body)]
     (into [] (.vertices query#))))

(defmacro find-edges
  [^Vertex starting-point & body]
  `(let [^com.tinkerpop.blueprints.Query query# (-> (start-at ~starting-point) ~@body)]
     (into [] (.edges query#))))

(defmacro count ^long
  [^Vertex starting-point & body]
  `(let [^com.tinkerpop.blueprints.Query query# (-> (start-at ~starting-point) ~@body)]
     (.count query#)))
