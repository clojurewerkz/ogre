(ns clojurewerkz.ogre.branch
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.util :refer (typed-traversal f-to-function f-to-predicate fresh-traversal)]))

(defmacro choose
  "Select which branch to take based on predicate or jump map."
  ([^Traversal t k m]
   `(typed-traversal .choose ~t (f-to-function ~k)
                     ~(if (map? m)
                        (into {} (for [[k v] m]
                                   [k `(fresh-traversal ~v)]))
                        m)))
  ([^Traversal t pred true-choice false-choice]
   `(typed-traversal .choose ~t (f-to-predicate ~pred)
                     (fresh-traversal ~true-choice)
                     (fresh-traversal ~false-choice))))

;; jump
;; until
