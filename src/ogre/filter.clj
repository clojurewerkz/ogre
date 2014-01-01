(ns ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.gremlin Tokens$T))
  (:require [ogre.util :refer (convert-symbol-to-compare f-to-pipef)]))

(defn dedup
  ([p]   
     (conj p #(.dedup ^GremlinPipeline %)))
  ([p f] 
     (conj p #(.dedup ^GremlinPipeline % (f-to-pipef f)))))

(defmacro has
  ([p k v]
     `(conj ~p (fn [parg#] (.has parg# ~(name k) ~v))))
  ([p k c v]
     `(conj ~p (fn [parg#] (.has parg# ~(name k) 
                                 (convert-symbol-to-compare '~c)
                                 ~v)))))

(defmacro has-not
  [p k v] 
  `(conj ~p (fn [parg#] (.hasNot parg# ~(name k) ~v))))

(defn interval 
  [p key start end]
  (conj p #(.interval ^GremlinPipeline % 
                      ^String (name key) 
                      ^Float  (float start) 
                      ^Float  (float end))))
