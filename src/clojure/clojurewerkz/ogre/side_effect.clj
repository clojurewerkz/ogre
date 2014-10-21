(ns clojurewerkz.ogre.side-effect
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:use clojurewerkz.ogre.util))

(defn side-effect
  [^Traversal t f]
  (.sideEffect t (f-to-consumer f)))

(defn get-grouped-by!
  [^Traversal t key-func value-func]
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
  [^Traversal t key-func]
    (-> (.groupCount t (f-to-function key-func))
        (.cap)
        (.toList)
        seq
        first
        (#(into {} %))))
