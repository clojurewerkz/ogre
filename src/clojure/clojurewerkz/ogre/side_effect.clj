(ns clojurewerkz.ogre.side-effect
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:use clojurewerkz.ogre.util))

(defn side-effect
  [t f]
  (.sideEffect t (f-to-consumer f)))

(defn get-grouped-by!
  [t key-func value-func]
    (let [results  (-> (.groupBy t (f-to-function key-func) (f-to-function value-func))
                       (.cap)
                       (.toList)
                       seq
                       first)]
      (->> results
        (into {})
        (map (fn [[a b]] [a (vec b)]))
        (into {}))))

(defn get-group-count!
  [t key-func]
    (-> (.groupCount t (f-to-function key-func))
        (.cap)
        (.toList)
        seq
        first
        (#(into {} %))))
