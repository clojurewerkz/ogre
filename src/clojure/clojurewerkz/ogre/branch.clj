(ns clojurewerkz.ogre.branch
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.traversal :as t]
            [clojurewerkz.ogre.util :refer (f-to-jump-predicate typed-traversal)]))

;; choose

(defn jump
  ([^Traversal t ^String jump-label]
   (typed-traversal .jump t jump-label))
  ([^Traversal t ^String jump-label jump-predicate]
   (if (integer? jump-predicate)
     (typed-traversal .jump t jump-label ^Integer jump-predicate)
     (typed-traversal .jump t jump-label (f-to-jump-predicate jump-predicate))))
  ([^Traversal t ^String jump-label jump-predicate emit-predicate]
   (if (integer? jump-predicate)
     (typed-traversal .jump t jump-label ^Integer jump-predicate (f-to-jump-predicate emit-predicate))
     (typed-traversal .jump t jump-label (f-to-jump-predicate jump-predicate) (f-to-jump-predicate emit-predicate)))))

(defn until
  ([^Traversal t ^String break-label jump-predicate]
   (until t break-label jump-predicate nil))
  ([^Traversal t ^String break-label jump-predicate emit-predicate]
   (if (integer? jump-predicate)
     (typed-traversal .until t break-label ^Integer jump-predicate (f-to-jump-predicate emit-predicate))
     (typed-traversal .until t break-label (f-to-jump-predicate jump-predicate) (f-to-jump-predicate emit-predicate)))))
