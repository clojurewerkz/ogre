(ns ogre.side-effect
  (:use ogre.util))

;; GremlinPipeline<S,E>	sideEffect(com.tinkerpop.pipes.PipeFunction<E,?> sideEffectFunction) 
;; Add a SideEffectFunctionPipe to the end of the Pipeline.

(defn side-effect [p f]
  (.sideEffect p (f-to-pipe f)))

;; GremlinPipeline<S,?>	cap() 
;; Add a SideEffectCapPipe to the end of the Pipeline.

(defn cap [p]
  (.cap p))

;; GremlinPipeline<S,E>	table() 
;; Add a TablePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	table(com.tinkerpop.pipes.PipeFunction... columnFunctions) 
;; Add a TablePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	table(com.tinkerpop.pipes.util.structures.Table table) 
;; Add a TablePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	table(com.tinkerpop.pipes.util.structures.Table table, Collection<String> stepNames, com.tinkerpop.pipes.PipeFunction... columnFunctions) 
;; Add a TablePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	table(com.tinkerpop.pipes.util.structures.Table table, com.tinkerpop.pipes.PipeFunction... columnFunctions) 
;; Add a TablePipe to the end of the Pipeline.
;;TODO: What does a table entail? Implement this in full
(defn table
  ([p] (.table p))
  ([p f] (.table (f-to-pipe f))))

;; GremlinPipeline<S,E>	tree(com.tinkerpop.pipes.PipeFunction... branchFunctions) 
;; Add a TreePipe to the end of the Pipeline This step maintains an internal tree representation of the paths that have flowed through the step.
;; GremlinPipeline<S,E>	tree(com.tinkerpop.pipes.util.structures.Tree tree, com.tinkerpop.pipes.PipeFunction... branchFunctions) 
;; Add a TreePipe to the end of the Pipeline This step maintains an internal tree representation of the paths that have flowed through the step.

;;TODO: tree structure?
(defn tree [p & fs]
  (.tree p (pipef-array (map f-to-pipe fs))))

;; GremlinPipeline<S,E>	store() 
;; Add an StorePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	store(Collection<E> storage) 
;; Add a StorePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	store(Collection storage, com.tinkerpop.pipes.PipeFunction<E,?> storageFunction) 
;; Add a StorePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	store(com.tinkerpop.pipes.PipeFunction<E,?> storageFunction) 
;; Add a StorePipe to the end of the Pipeline.
;;TODO: figure out what this is supposed to do within clojure

(defn store [p]
  (.store p))

;; Collection<E>	fill(Collection<E> collection) 
;; Fill the provided collection with the objects in the pipeline.

;;TODO: does this make sense from within clojure? 


;; GremlinPipeline<S,E>	groupBy(Map<?,List<?>> map, com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction) 
;; Add a GroupByPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupBy(Map reduceMap, com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction, com.tinkerpop.pipes.PipeFunction reduceFunction) 
;; Add a GroupByReducePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupBy(com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction) 
;; Add a GroupByPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupBy(com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction, com.tinkerpop.pipes.PipeFunction reduceFunction) 
;; Add a GroupByReducePipe to the end of the Pipeline.

;; GremlinPipeline<S,E>	groupCount() 
;; Add a GroupCountPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupCount(Map<?,Number> map) 
;; Add a GroupCountPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupCount(Map<?,Number> map, com.tinkerpop.pipes.PipeFunction keyFunction) 
;; Add a GroupCountPipe or GroupCountFunctionPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupCount(Map<?,Number> map, com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.util.structures.Pair<?,Number>,Number> valueFunction) 
;; Add a GroupCountPipe or GroupCountFunctionPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupCount(com.tinkerpop.pipes.PipeFunction keyFunction) 
;; Add a GroupCountPipe or GroupCountFunctionPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupCount(com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.util.structures.Pair<?,Number>,Number> valueFunction) 
;; Add a GroupCountPipe or GroupCountFunctionPipe to the end of the Pipeline.

;;TODO: extend groupCount further 
(defn group-count [p]
  (.group-count p))

;; GremlinPipeline<S,E>	aggregate() 
;; Add an AggregatePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	aggregate(Collection<E> aggregate)
;; Add an AggregatePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	aggregate(Collection aggregate, com.tinkerpop.pipes.PipeFunction<E,?> aggregateFunction) 
;; Add an AggregatePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	aggregate(com.tinkerpop.pipes.PipeFunction<E,?> aggregateFunction) 
;; Add an AggregatePipe to the end of the Pipeline.

;;TODO: What should this do? What's it's purpose?
