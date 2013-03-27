(ns ogre.util
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes PipeFunction Pipe)
           (com.tinkerpop.gremlin Tokens$T)))

(defmacro bare-pipe [& body]
  `(-> (GremlinPipeline.)
       ~@body))

(defmacro defpipe [name & body]
  `(def ~name (blank-pipe ~@body)))

(defmacro query [xs & body]  
  `(-> (GremlinPipeline. ~xs)
       ~@body))

(defn ^"com.tinkerpop.gremlin.Tokens$T" convert-symbol-to-compare [s]
  (case s
    =    Tokens$T/eq
    not= Tokens$T/neq  
    >=   Tokens$T/gte
    >    Tokens$T/gt
    <=   Tokens$T/lte
    <    Tokens$T/lt))

(defn ^"[Ljava.lang.String;" str-array [strs]
  (into-array String strs))

(defn ^"[Ljava.lang.String;" keywords-to-strings [labels]
  (->> labels
       (filter identity)
       (map name)
       str-array))

(defn ^PipeFunction f-to-pipef [f]
  (reify PipeFunction
    (compute [this arg] (f arg))))

(defn ^"[Lcom.tinkerpop.pipes.Pipe;" pipe-array
  [ps]
  (into-array Pipe ps))

(defn ^"[Lcom.tinkerpop.pipes.PipeFunction;" fs-to-pipef-array
  [fs]
  (into-array PipeFunction (map f-to-pipef fs)))
