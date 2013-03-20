(ns ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:require [ogre.util :refer (convert-symbol-to-compare f-to-pipef)]))

;; GremlinPipeline<S,E>	filter(com.tinkerpop.pipes.PipeFunction<E,Boolean> filterFunction) 
;; Add an FilterFunctionPipe to the end of the Pipeline.

(defn filter [p f]
  (.filter p (f-to-pipef f)))

;; GremlinPipeline<S,E>	dedup() 
;; Add a DuplicateFilterPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	dedup(com.tinkerpop.pipes.PipeFunction<E,?> dedupFunction) 
;; Add a DuplicateFilterPipe to the end of the Pipeline.

(defn dedup
  ([p] (.dedup p))
  ([p f] (.dedup p (f-to-pipef f))))

;; GremlinPipeline<S,E>	except(Collection<E> collection) 
;; Add an ExceptFilterPipe to the end of the Pipeline.

(defn except [p xs]
  (.except p xs))

;; GremlinPipeline<S,? extends com.tinkerpop.blueprints.Element>	has(String key, Object value) 
;; Add an IdFilterPipe, LabelFilterPipe, or PropertyFilterPipe to the end of the Pipeline.
;; GremlinPipeline<S,? extends com.tinkerpop.blueprints.Element>	has(String key, Tokens.T comparison, Object value) 
;; Add an IdFilterPipe, LabelFilterPipe, or PropertyFilterPipe to the end of the Pipeline.

(defmacro has
  ([p k v] `(.has ~p ~(name k) ~v))
  ([p k c v] `(.has ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))


;; GremlinPipeline<S,? extends com.tinkerpop.blueprints.Element>	hasNot(String key, Object value) 
;; Add an IdFilterPipe, LabelFilterPipe, or PropertyFilterPipe to the end of the Pipeline.
;; GremlinPipeline<S,? extends com.tinkerpop.blueprints.Element>
;; hasNot(String key, Tokens.T comparison, Object value) 
;; Add an IdFilterPipe, LabelFilterPipe, or PropertyFilterPipe to the
;; end of the Pipeline.

(defmacro has-not
  ([p k v] `(.hasNot ~p ~(name k) ~v))
  ([p k c v] `(.hasNot ~p ~(name k) (convert-symbol-to-compare '~c) ~v)))

;; GremlinPipeline<S,? extends com.tinkerpop.blueprints.Element>	interval(String key, Object startValue, Object endValue) 
;; Add an IntervalFilterPipe to the end of the Pipeline.

(defn interval [p key start end]
  (.interval p (name key) (float start) (float end)))

;; GremlinPipeline<S,E>	random(Double bias) 
;; Add a RandomFilterPipe to the end of the Pipeline.

(defn random [p bias]
  (.random p bias))

;; GremlinPipeline<S,E>	range(int low, int high) 
;; Add a RageFilterPipe to the end of the Pipeline.

(defn range [p low high]
  (.range p low high))

;; GremlinPipeline<S,E>	retain(Collection<E> collection) 
;; Add a RetainFilterPipe to the end of the Pipeline.

(defn retain [p coll]
  (.retain p coll))

;; GremlinPipeline<S,E>	simplePath() 
;; Add a CyclicPathFilterPipe to the end of the Pipeline.

(defn simple-path [p]
  (.simplePath p))
