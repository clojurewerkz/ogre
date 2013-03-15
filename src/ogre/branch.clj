(ns ogre.branch
  (:refer-clojure :exclude [loop])
  (:use ogre.util))

;; GremlinPipeline<S,?>	copySplit(com.tinkerpop.pipes.Pipe<E,?>... pipes) 
;; Add a CopySplitPipe to the end of the pipeline.

(defn copy-split [p & es]
  (.copySplit p (pipe-array es)))

;; GremlinPipeline<S,?>	exhaustMerge() 
;; Add an ExhaustMergePipe to the end of the pipeline.

(defn exhaust-merge [p]
  (.exhaustMerge p))

;; GremlinPipeline<S,?>	fairMerge() 
;; Add a FairMergePipe to the end of the Pipeline.

(defn fair-merge [p]
  (.fairMerge p))

;; GremlinPipeline<S,?>	ifThenElse(com.tinkerpop.pipes.PipeFunction<E,Boolean> ifFunction, com.tinkerpop.pipes.PipeFunction<E,?> thenFunction, com.tinkerpop.pipes.PipeFunction<E,?> elseFunction) 
;; Add an IfThenElsePipe to the end of the Pipeline.

(defn if-then-else [p pred then else]
  (.ifThenElse p (f-to-pipef pred) (f-to-pipef then) (f-to-pipef else)))


;; GremlinPipeline<S,E>	loop(int numberedStep, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.branch.LoopPipe.LoopBundle<E>,Boolean> whileFunction) 
;; Add a LoopPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	loop(int numberedStep, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.branch.LoopPipe.LoopBundle<E>,Boolean> whileFunction, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.branch.LoopPipe.LoopBundle<E>,Boolean> emitFunction)
;; Add a LoopPipe to the end of the Pipeline.

(defn loop-unbundler [f]
  (fn [b]
    (f (.getLoops b)
       (.getObject b)
       (.getPath b))))

(defn loop
  ([p i while-f]
     (.loop p i (f-to-pipef (loop-unbundler while-f))))
  ([p i while-f emit-f]
     (.loop p i (f-to-pipef (loop-unbundler while-f)) (f-to-pipef emit-f))))

;; GremlinPipeline<S,E>	loop(String namedStep, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.branch.LoopPipe.LoopBundle<E>,Boolean> whileFunction) 
;; Add a LoopPipe to the end of the Pipeline.
;; GremlinPipeline<S,E>	loop(String namedStep, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.branch.LoopPipe.LoopBundle<E>,Boolean> whileFunction, com.tinkerpop.pipes.PipeFunction<com.tinkerpop.pipes.branch.LoopPipe.LoopBundle<E>,Boolean> emitFunction) 
;; Add a LoopPipe to the end of the Pipeline.

(defn loop-to
  ([p s while-f]
     (.loop p s (f-to-pipef (loop-unbundler while-f))))
  ([p s while-f emit-f]
     (.loop p s
            (f-to-pipef (loop-unbundler while-f))
            (f-to-pipef emit-f))))