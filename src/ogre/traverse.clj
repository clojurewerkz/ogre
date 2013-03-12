(ns ogre.traverse
  (:use ogre.util))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	both(String... labels) 
;; Add a BothPipe to the end of the Pipeline.

(defn both [p & labels]
  (.both p (keywords-to-strings labels)))

(defn <-> [& args]
  (apply both args))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	bothE(String... labels) 
;; Add a BothEdgesPipe to the end of the Pipeline.

(defn both-edges [p & labels]
  (.bothE p (keywords-to-strings labels)))

(defn <E> [& args]
  (apply both-edges args))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	bothV() 
;; Add a BothVerticesPipe to the end of the Pipeline.

(defn both-vertices [p]
  (.bothV p))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	in(String... labels) 
;; Add a InPipe to the end of the Pipeline.

(defn in [p & labels]
  (.in p (keywords-to-strings labels)))

(defn <-- [& args]
  (apply in args))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	inE(String... labels) 
;; Add an InEdgesPipe to the end of the Pipeline.

(defn in-edges [p & labels]
  (.inE p (keywords-to-strings labels)))

(defn <E-- [& args]
  (apply in-edges args))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	inV() 
;; Add an InVertexPipe to the end of the Pipeline.

(defn in-vertex [p & labels]
  (.inV p))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	out(String... labels) 
;; Add an OutPipe to the end of the Pipeline.

(defn out [p & labels]
  (.out p (keywords-to-strings labels)))

(defn --> [& args]
  (apply out args))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	outE(String... labels) 
;; Add an OutEdgesPipe to the end of the Pipeline.
(defn out-edges [p & labels]
  (.outE p (keywords-to-strings labels)))

(defn --E> [& args]
  (apply out-edges args))

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	outV() 
;; Add an OutVertexPipe to the end of the Pipeline.

(defn out-vertex [p & labels]
  (.outV p))
