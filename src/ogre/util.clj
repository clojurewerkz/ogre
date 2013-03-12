(ns ogre.util
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes PipeFunction Pipe)
           (com.tinkerpop.gremlin Tokens$T)))

(defmacro blank-pipe [& body]
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
    !=   Tokens$T/neq  
    >=   Tokens$T/gte
    >    Tokens$T/gt
    <=   Tokens$T/lte
    <    Tokens$T/lt))

(defn keywords-to-labels [labels]
  (into-array String (map name (clojure.core/filter identity labels))))

(defn f-to-pipe [f]
  (reify PipeFunction
    (compute [_ arg] (f arg))))


(defn pipe-array [ps]
  (into-array Pipe ps))

(defn pipef-array [pfs]
  (into-array PipeFunction pfs))

(defn str-array [pfs]
  (into-array PipeFunction pfs))