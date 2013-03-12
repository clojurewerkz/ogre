(ns ogre.pipe
  (:refer-clojure :exclude [iterate next])
  (:use ogre.util))

;; <T> GremlinPipeline<S,T>
;; add(com.tinkerpop.pipes.Pipe<?,T> pipe) 

;;(add (blank-pipe (q/-->) (q/-->)))
(defn add [p e]
  (.add p e))

;; GremlinPipeline<S,E>	as(String name) 
;; Wrap the previous step in an AsPipe.

(defn as [p s]
  (.as p s))

;; GremlinPipeline<S,?>	back(int numberedStep) 
;; Add a BackFilterPipe to the end of the Pipeline.
;; GremlinPipeline<S,?>	back(String namedStep) 
;; Add a BackFilterPipe to the end of the Pipeline.

(defn back [p i]
  (.back p i))

;; GremlinPipeline<S,E>	enablePath() 

(defn enable-path [p]
  (.enablePath p))

;; void	iterate() 
;; Completely drain the pipeline of its objects.

(defn iterate [p]
  (.iterate p))

;; List<E>	next(int number) 
;; Return the next X objects in the pipeline as a list.

(defn next [p i]
  (.next p i))

;; GremlinPipeline<S,E>	optimize(boolean optimize) 
;; When possible, Gremlin takes advantage of certain sequences of pipes in order to make a more concise, and generally more efficient expression.

(defn optimize [p b]
  (.optimize p b))

;; GremlinPipeline<S,?>	optional(int numberedStep) 
;; Add an OptionalPipe to the end of the Pipeline.
;; GremlinPipeline<S,?>	optional(String namedStep) 
;; Add an OptionalPipe to the end of the Pipeline.
(defn optional [p s]
  (.optional p s))

;; GremlinPipeline<S,S>	start(S object) 
;; Add a StartPipe to the end of the pipeline.

(defn start [p o]
  (.start p o))

;; <T> GremlinPipeline<S,T>
;; step(com.tinkerpop.pipes.Pipe<E,T> pipe) 
;; Add an arbitrary Pipe to the end of the pipeline.
;; GremlinPipeline<S,?>	step(com.tinkerpop.pipes.PipeFunction function) 
;; Add a FunctionPipe to the end of the pipeline.

(defn step [p e]
  (.step p e))

;; List<E>	toList() 
;; Return a list of all the objects in the pipeline.

(defn to-list [p]
  (.toList p))

(defn into-vec [p]
  (into [] (to-list p)))

(defn into-set [p]
  (into #{} (to-list p)))


;;Inspiried by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defn convert-to-map [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defn first-of [p]
  (-> p into-vec first))

(defn first-into-vec [p]
  (vec (first-of p)))

(defn first-into-set [p]
  (set (first-of p)))

(defn first-into-map [p]
  (convert-to-map (first-of p)))

(defn all-into-vecs [p]
  (map vec (into-vec p)))

(defn all-into-sets [p]
  (map set (into-vec p)))

(defn all-into-maps [p]
  (map convert-to-map (into-vec p)))
