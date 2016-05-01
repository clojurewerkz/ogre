(ns clojurewerkz.ogre.anon
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [potemkin :as po]
            [clojurewerkz.ogre.util :as util])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Traversal Compare P Order)
           (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal.dsl.graph __)
           (java.util Iterator)
           (org.apache.tinkerpop.gremlin.process.traversal.dsl.graph GraphTraversal GraphTraversalSource)))

; __ (anonymous GraphTraversal)

(defn __bothE
  [& labels]
    (__/bothE (util/keywords-to-str-array labels)))

(defn __is
  [val-or-pred]
  (if (instance? P val-or-pred)
    (__/is ^P val-or-pred)
    (__/is ^Object val-or-pred)))

(defn __map
  [f-or-t]
  (if (instance? Traversal f-or-t)
    (__/map ^Traversal f-or-t)
    (__/map (util/f-to-function f-or-t))))
