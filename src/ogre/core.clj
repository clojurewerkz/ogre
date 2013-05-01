(ns ogre.core
  (:refer-clojure :exclude [filter and or range count memoize iterate next map loop reverse])
  (:require [potemkin :as po]
            [ogre.util        :as util]
            [ogre.branch      :as branch]
            [ogre.filter      :as filter]
            [ogre.map         :as map]
            [ogre.pipe        :as pipe]
            [ogre.reduce      :as reduce]
            [ogre.traverse    :as traverse]
            [ogre.side-effect :as side-effect]))


;; ogre.util
(po/import-macro util/query)
(po/import-macro util/bare-pipe)

;; ogre.branch
(po/import-fn branch/copy-split)
(po/import-fn branch/exhaust-merge)
(po/import-fn branch/fair-merge)
(po/import-fn branch/if-then-else)
(po/import-fn branch/loop)
(po/import-fn branch/loop-to)

;; ogre.filter
(po/import-fn filter/filter)
(po/import-fn filter/dedup)
(po/import-fn filter/except)
(po/import-macro filter/has)
(po/import-macro filter/has-not)
(po/import-fn filter/interval)
(po/import-fn filter/random)
(po/import-fn filter/range)
(po/import-fn filter/retain)
(po/import-fn filter/simple-path)

;; ogre.map
(po/import-fn map/map)
(po/import-fn map/transform)
(po/import-fn map/_)
(po/import-fn map/id)
(po/import-fn map/property)
(po/import-fn map/label)
(po/import-fn map/select)
(po/import-fn map/select-only)
(po/import-fn map/scatter)
(po/import-fn map/path)

;; ogre.pipe
;; TODO break this into pipe and executors
(po/import-fn pipe/add)
(po/import-fn pipe/as)
(po/import-fn pipe/back)
(po/import-fn pipe/back-to)
(po/import-fn pipe/enable-path)
(po/import-fn pipe/iterate)
(po/import-fn pipe/next)
(po/import-fn pipe/optimize)
(po/import-fn pipe/optional)
(po/import-fn pipe/optional-to)
(po/import-fn pipe/start)
(po/import-fn pipe/prop)

(po/import-fn pipe/to-list!)
(po/import-fn pipe/into-vec!)
(po/import-fn pipe/into-set!)
(po/import-fn pipe/first-of!)
(po/import-fn pipe/first-into-vec!)
(po/import-fn pipe/first-into-set!)
(po/import-fn pipe/first-into-map!)
(po/import-fn pipe/all-into-vecs!)
(po/import-fn pipe/all-into-sets!)
(po/import-fn pipe/all-into-maps!)


;; ogre.reduce
(po/import-fn reduce/gather)
(po/import-fn reduce/order)
(po/import-fn reduce/reverse)
(po/import-fn reduce/count!)

;; ogre.traverse
(po/import-fn traverse/both)
(po/import-fn traverse/<->)
(po/import-fn traverse/both-edges)
(po/import-fn traverse/<E>)
(po/import-fn traverse/both-vertices)

(po/import-fn traverse/in)
(po/import-fn traverse/<--)
(po/import-fn traverse/in-edges)
(po/import-fn traverse/<E-)
(po/import-fn traverse/in-vertex)

(po/import-fn traverse/out)
(po/import-fn traverse/-->)
(po/import-fn traverse/out-edges)
(po/import-fn traverse/-E>)
(po/import-fn traverse/out-vertex)

;; ogre.side-effect
(po/import-fn side-effect/side-effect)
(po/import-fn side-effect/cap)
(po/import-fn side-effect/get-table!)
(po/import-fn side-effect/get-tree!)
(po/import-fn side-effect/get-grouped-by!)
(po/import-fn side-effect/get-group-count!)

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	idEdge(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdEdgePipe to the end of the Pipeline.

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	idVertex(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdVertexPipe to the end of the Pipeline.