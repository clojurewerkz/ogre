(ns ogre.core
  (:refer-clojure :exclude [filter and or range count memoize iterate next]))

;;https://groups.google.com/forum/?fromgroups=#!topic/clojure/GAGF38uI1-o
(defn- merge-meta! 
  "Destructively merge metadata from a source object into a target." 
  [source target] 
  (.setMeta target 
    (merge (meta source) 
           (select-keys (meta target) [:name :ns])))) 

(defn immigrate 
  "Add all the public vars in a list of namespaces to the current 
namespace." 
  [& namespaces] 
  (doseq [ns namespaces] 
    (require ns) 
    (doseq [[sym v] (ns-publics (find-ns ns))] 
      (merge-meta! v 
        (if (.isBound v) 
          (intern *ns* sym (var-get v)) 
          (intern *ns* sym))))))

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