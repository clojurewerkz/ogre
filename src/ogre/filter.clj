(ns ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.gremlin Tokens$T))
  (:require [ogre.util :refer (convert-symbol-to-compare f-to-pipef)]))

(defn filter [^GremlinPipeline p f]
  (.filter p (f-to-pipef f)))

(defn dedup
  ([^GremlinPipeline p] (.dedup p))
  ([^GremlinPipeline p f] (.dedup p (f-to-pipef f))))

(defn except [^GremlinPipeline p ^java.util.Collection xs]
  (.except p xs))

(defmacro has
  ([p k v]
     `(.has ~p ~(name k) ~v))
  ([p k c v]
     `(.has ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defmacro has-not
  ([p k v]
     `(.hasNot ~p ~(name k) ~v))
  ([p k c v]
     `(.hasNot ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defn interval [^GremlinPipeline p key start end]
  (.interval p ^String (name key) ^Float (float start) ^Float (float end)))

(defn random [^GremlinPipeline p ^Double bias]
  (.random p bias))

(defn range [^GremlinPipeline p ^Integer low ^Integer high]
  (.range p low high))

(defn retain [^GremlinPipeline p ^java.util.Collection coll]
  (.retain p coll))

(defn simple-path [^GremlinPipeline p]
  (.simplePath p))
