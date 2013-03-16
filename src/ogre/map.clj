(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:use ogre.util))


;; GremlinPipeline<S,Map<String,Object>>	map() 
;; Add a PropertyMapPipe to the end of the Pipeline.

(defn map
  ([p] (.map p)))

;; <T> GremlinPipeline<S,T>
;; transform(com.tinkerpop.pipes.PipeFunction<E,T> function) 
;; Add a TransformFunctionPipe to the end of the Pipeline.

(defn transform [p f]
  (.transform p (f-to-pipef f)))

;; GremlinPipeline<S,E>	_() 
;; Add an IdentityPipe to the end of the Pipeline.

(defn _ [p]
  (._ p))

;; GremlinPipeline<S,Object>	id() 
;; Add an IdPipe to the end of the Pipeline.

(defn id [p] (.id p))


;; GremlinPipeline<S,Object>	property(String key) 
;; Add a PropertyPipe to the end of the Pipeline.

(defn property [p prop]
  (.property p (name prop)))

;; GremlinPipeline<S,String>	label() 
;; Add an LabelPipe to the end of the Pipeline.

(defn label [p]
  (.label p))

;; GremlinPipeline<S,com.tinkerpop.pipes.util.structures.Row>	select() 
;; Add a SelectPipe to the end of the Pipeline.
;; GremlinPipeline<S,com.tinkerpop.pipes.util.structures.Row>	select(Collection<String> stepNames, com.tinkerpop.pipes.PipeFunction... columnFunctions) 
;; Add a SelectPipe to the end of the Pipeline.
;; GremlinPipeline<S,com.tinkerpop.pipes.util.structures.Row>	select(com.tinkerpop.pipes.PipeFunction... columnFunctions) 
;; Add a SelectPipe to the end of the Pipeline.

(defn select
  ([p] (.select p))
  ([p & fs] (.select p (fs-to-pipef-array fs))))

(defn select-only
  ([p cols] (select-only p cols identity))
  ([p cols & fs] (.select p cols (fs-to-pipef-array fs))))

;; GremlinPipeline<S,E>	memoize(int numberedStep) 
;; Add a MemoizePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	memoize(int numberedStep, Map map) 
;; Add a MemoizePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	memoize(String namedStep) 
;; Add a MemoizePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	memoize(String namedStep, Map map) 
;; Add a MemoizePipe to the end of the Pipeline.

(defn memoize
  ([is] (.memoize is))
  ([is m] (.memoize is m)))

;; GremlinPipeline<S,?>	scatter() 
;; Add a ScatterPipe to the end of the Pipeline.

(defn scatter [p]
  (.scatter p))

;; GremlinPipeline<S,List>	path(com.tinkerpop.pipes.PipeFunction... pathFunctions) 
;; Add a PathPipe or PathPipe to the end of the Pipeline.

(defn path [p & args]
  (.path p (fs-to-pipef-array args)))