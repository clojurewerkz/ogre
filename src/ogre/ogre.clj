(ns ogre.core
  (:use [ogre.util :only (immigrate)]))

(immigrate 'ogre.util)
(immigrate 'ogre.branch)
(immigrate 'ogre.filter)
(immigrate 'ogre.map)
(immigrate 'ogre.pipe)
(immigrate 'ogre.reduce)
(immigrate 'ogre.traverse)
(immigrate 'ogre.side-effect)

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	idEdge(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdEdgePipe to the end of the Pipeline.

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	idVertex(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdVertexPipe to the end of the Pipeline.