(ns ogre.side-effect
  (:import (com.tinkerpop.pipes.util.structures Table Tree Row Pair)
           (com.tinkerpop.gremlin.java GremlinPipeline))
  (:require [ogre.pipe :as pipe])

  (:use ogre.util))

(defn convert-table 
  [^Table t]
  (let [ts (seq t)
        names (map keyword (.getColumnNames t))
        converter (fn [^Row row]
                    (into {} (for [i (range (count names))]
                               [(nth names i) (.getColumn row ^Integer i)])))]
    (map converter ts)))

(defn get-table!
  ([^GremlinPipeline p & fs] (->> (.table (compile-query p)
                                          (fs-to-pipef-array fs))
                                  (.cap)
                                  (.toList)
                                  seq
                                  first
                                  convert-table)))

;; (defn table-into
;;   ([^GremlinPipeline p ^Table t] (.table p t))
;;   ([^GremlinPipeline p ^Table t & args]
;;      (if (= clojure.lang.PersistentVector (type (first args)))
;;        (.table p t (first args) (fs-to-pipef-array (rest args)))
;;        (.table p t (fs-to-pipef-array args)))))

(defn- convert-tree-helper 
  [name ^Tree t]
  (let [names (seq (.getObjectsAtDepth t 1))
        children (seq (.getTreesAtDepth t 2))
        nexts (vec (map convert-tree-helper names children))]
    (if (empty? nexts)
      {:value name}
      {:value name :children nexts})))

(defn convert-tree 
  [^Tree t]
  (let [name (first (seq (.getObjectsAtDepth t 1)))
        names (seq (.getObjectsAtDepth t 2))
        children (seq (.getTreesAtDepth t 3))
        nexts (vec (map convert-tree-helper names children))]
    (if (empty? nexts)
      {:value name}
      {:value name :children nexts})))

(defn get-tree! 
  [^GremlinPipeline p & fs]
  (-> (.tree (compile-query p) (fs-to-pipef-array fs))
      (.cap)
      (.toList)
      seq
      first
      convert-tree))

;; (defn tree-into [^GremlinPipeline p t & fs]
;;   (.tree p t (fs-to-pipef-array fs)))

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
;;   ([^GremlinPipeline p] (.store p))
;;   ([^GremlinPipeline p f] (.store p (f-to-pipe f))))

;; (defn store-into
;;   ([^GremlinPipeline p c] (.store p c))
;;   ([^GremlinPipeline p c f] (.store p c (f-to-pipe f))))

;; Collection<E>	fill(Collection<E> collection) 
;; Fill the provided collection with the objects in the pipeline.

;; (defn fill [c]
;;   (.fill c))


(defn get-grouped-by!
  ([p f g] (get-grouped-by! p f g identity))
  ([^GremlinPipeline p f g r]
     (let [results      (-> (.groupBy (compile-query p)
                                      (f-to-pipef f) (f-to-pipef g))
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
  ([^GremlinPipeline p ^clojure.lang.IFn f ^clojure.lang.IFn g]
     (-> (.groupCount (compile-query p)
                      (f-to-pipef f) 
                      (f-to-pipef (fn [^Pair arg] 
                                    (g (.getA arg) (.getB arg)))))
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
