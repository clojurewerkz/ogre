(ns ogre.side-effect
  (:import (com.tinkerpop.pipes.util.structures Table Tree))
  (:require [ogre.pipe :as pipe])
  (:use ogre.util))

(defn side-effect [p f]
  (.sideEffect p (f-to-pipef f)))

(defn cap [p]
  (.cap p))

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
