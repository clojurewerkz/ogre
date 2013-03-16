(ns ogre.side-effect
  (:import (com.tinkerpop.pipes.util.structures Table Tree))
  (:require [ogre.pipe :as pipe])
  (:use ogre.util))

;; GremlinPipeline<S,E>	sideEffect(com.tinkerpop.pipes.PipeFunction<E,?> sideEffectFunction) 
;; Add a SideEffectFunctionPipe to the end of the Pipeline.

(defn side-effect [p f]
  (.sideEffect p (f-to-pipef f)))

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


(defn convert-table [t]
  (let [ts (seq t)
        names (map keyword (.getColumnNames t))
        converter (fn [row]
                    (into {} (for [i (range (count names))]
                               [(nth names i) (.getColumn row i)])))]
    (map converter ts)))

(defn get-table!
  ([p & fs] (->> (.table p  (fs-to-pipef-array fs))
                 (.cap)
                 (.toList)
                 seq
                 first
                 convert-table)))

(defn table-into
  ([p t] (.table p t))
  ([p t & args]
     (if (= clojure.lang.PersistentVector (type (first args)))
       (.table p t (first args) (fs-to-pipef-array (rest args)))
       (.table p t (fs-to-pipef-array args)))))

;; GremlinPipeline<S,E>	tree(com.tinkerpop.pipes.PipeFunction... branchFunctions) 
;; Add a TreePipe to the end of the Pipeline This step maintains an internal tree representation of the paths that have flowed through the step.
;; GremlinPipeline<S,E>	tree(com.tinkerpop.pipes.util.structures.Tree tree, com.tinkerpop.pipes.PipeFunction... branchFunctions) 
;; Add a TreePipe to the end of the Pipeline This step maintains an internal tree representation of the paths that have flowed through the step.


(defn- convert-tree-helper [name t]
  (let [names (seq (.getObjectsAtDepth t 1))
        children (seq (.getTreesAtDepth t 2))
        nexts (vec (map convert-tree-helper names children))]
    (if (empty? nexts)
      {:value name}
      {:value name :children nexts})))

(defn convert-tree [t]
  (let [name (first (seq (.getObjectsAtDepth t 1)))
        names (seq (.getObjectsAtDepth t 2))
        children (seq (.getTreesAtDepth t 3))
        nexts (vec (map convert-tree-helper names children))]
    (if (empty? nexts)
      {:value name}
      {:value name :children nexts})))

(defn get-tree! [p & fs]
  (-> (.tree p (fs-to-pipef-array fs))
      (.cap)
      (.toList)
      seq
      first
      convert-tree))

(defn tree-into [p t & fs]
  (.tree p t (fs-to-pipef-array fs)))

;; GremlinPipeline<S,E>	store() 
;; Add an StorePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	store(Collection<E> storage) 
;; Add a StorePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	store(Collection storage, com.tinkerpop.pipes.PipeFunction<E,?> storageFunction) 
;; Add a StorePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	store(com.tinkerpop.pipes.PipeFunction<E,?> storageFunction) 
;; Add a StorePipe to the end of the Pipeline.
;;TODO: figure out what this is supposed to do within clojure

;; (defn store
;;   ([p] (.store p))
;;   ([p f] (.store p (f-to-pipe f))))

;; (defn store-into
;;   ([p c] (.store p c))
;;   ([p c f] (.store p c (f-to-pipe f))))

;; Collection<E>	fill(Collection<E> collection) 
;; Fill the provided collection with the objects in the pipeline.

;; (defn fill [c]
;;   (.fill c))


;; GremlinPipeline<S,E>	groupBy(Map<?,List<?>> map, com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction) 
;; Add a GroupByPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupBy(Map reduceMap, com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction, com.tinkerpop.pipes.PipeFunction reduceFunction) 
;; Add a GroupByReducePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupBy(com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction) 
;; Add a GroupByPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	groupBy(com.tinkerpop.pipes.PipeFunction keyFunction, com.tinkerpop.pipes.PipeFunction valueFunction, com.tinkerpop.pipes.PipeFunction reduceFunction) 
;; Add a GroupByReducePipe to the end of the Pipeline.


(defn get-grouped-by!
  ([p f g] (get-grouped-by! p f g identity))
  ([p f g r]
     (let [results      (-> (.groupBy p (f-to-pipef f) (f-to-pipef g))
                            (.cap)
                            (.toList)
                            seq
                            first)]
       (->> results
            (into {})
            (map (fn [[a b]] [a (vec b)]))
            (into {})))))

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

(defn get-group-count!
  ([p] (get-group-count! p identity))
  ([p f] (get-group-count! p f (fn [a b] (inc b))))
  ([p f g]
     (-> (.groupCount p (f-to-pipef f) (f-to-pipef (fn [arg] (g (.getA arg) (.getB arg)))))
         (.cap)
         (.toList)
         seq
         first
         (#(into {} %)))))

;; GremlinPipeline<S,E>	aggregate() 
;; Add an AggregatePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	aggregate(Collection<E> aggregate)
;; Add an AggregatePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	aggregate(Collection aggregate, com.tinkerpop.pipes.PipeFunction<E,?> aggregateFunction) 
;; Add an AggregatePipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	aggregate(com.tinkerpop.pipes.PipeFunction<E,?> aggregateFunction) 
;; Add an AggregatePipe to the end of the Pipeline.

;;TODO: What should this do? What's it's purpose?
