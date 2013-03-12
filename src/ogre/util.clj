(ns ogre.util
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes PipeFunction Pipe)
           (com.tinkerpop.gremlin Tokens$T)))

(defmacro headless-pipe [& body]
  `(-> (GremlinPipeline.)
       ~@body))

(defmacro defpipe [name & body]
  `(def ~name (blank-pipe ~@body)))

(defmacro query [xs & body]  
  `(-> (GremlinPipeline. ~xs)
       ~@body))

(defn convert-symbol-to-token [s]
  (case s
    ==   Tokens$T/eq
    not=   Tokens$T/neq  
    >=   Tokens$T/gte
    >    Tokens$T/gt
    <=   Tokens$T/lte
    <    Tokens$T/lt))

(defn str-array [strs]
  (into-array String strs))

(defn keywords-to-strings [labels]
  (->> labels
       (filter identity)
       (map name)
       str-array))

(defn f-to-pipe [f]
  (reify PipeFunction
    (compute [this arg] (f arg))))

(defn pipe-array [ps]
  (into-array Pipe ps))

(defn fs-to-pipef-array [fs]
  (into-array PipeFunction (map f-to-pipe fs)))
