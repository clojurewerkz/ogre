(ns clojurewerkz.ogre.util
  (:require [clojure.reflect :as r])
  (:use [clojure.pprint :only (pprint)])
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal)
           (com.tinkerpop.gremlin.structure Compare)))

;;TODO bring over the one test from the lazy branch
(defmacro bare-pipe
  [& body]
  `(reduce #(%2 %1) (GraphTraversal.) (-> [] ~@body)))


;(defmacro defpipe [name & body]
;  `(def ~name (blank-pipe ~@body)))

(defmacro query [xs & body]
  `(-> ~xs ~@body))

(defmacro subquery
  ""
  [& body]
  `(fn [p#]
     (-> p#
       ~@body)))

(defn ^Compare convert-symbol-to-compare [s]
  (case s
    =    Compare/eq
    not= Compare/neq
    >=   Compare/gte
    >    Compare/gt
    <=   Compare/lte
    <    Compare/lt))

(defn ^"[Ljava.lang.String;" str-array [strs]
  (into-array String strs))

(defn ^"[Ljava.lang.String;" keywords-to-strings [labels]
  (->> labels
    (filter identity)
    (map name)
    str-array))

;(defn ^PipeFunction f-to-pipef [f]
;  (reify PipeFunction
;    (compute [this arg] (f arg))))

;(defn ^"[Lcom.tinkerpop.pipes.Pipe;" pipe-array
;  [ps]
;  (into-array Pipe ps))

;(defn ^"[Lcom.tinkerpop.pipes.PipeFunction;" fs-to-pipef-array
;  [fs]
;  (into-array PipeFunction (map f-to-pipef fs)))

(defn compile-query
  ^GraphTraversal
  [[xs & fs]]
  (reduce #(%2 %1) xs fs))

(defn keywords-to-str-array [strs]
  (into-array String (map name strs)))

(defn prop-map-to-array [m]
  (into-array Object
    (into []
      (flatten
        (map #(let [key (first %)
                    value (second %)]
            (vector (if (keyword? key) (name key) key) value)) m)))))