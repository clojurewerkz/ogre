(ns ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.gremlin Tokens$T))
  (:require [ogre.util :refer (convert-symbol-to-compare f-to-pipef)]))

(defn dedup
  ([^GremlinPipeline p] (.dedup p))
  ([^GremlinPipeline p f] (.dedup p (f-to-pipef f))))

(defmacro has
  ([p k v]
     `(.has ~(with-meta p {:tag GremlinPipeline}) 
            ~(name k)
            ~v))
  ([p k c v]
     `(.has ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defmacro has-not
  [p k v] 
  `(.hasNot ~p ~(name k) ~v))

(defn interval 
  [^GremlinPipeline p key start end]
  (.interval p ^String (name key) 
             ^Float (float start) ^Float (float end)))
