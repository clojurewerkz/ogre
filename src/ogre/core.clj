(ns ogre.core
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:refer-clojure :exclude [filter and or range count memoize iterate next map loop reverse])
  (:require [potemkin :as po]
            [ogre.util        :as util :refer 
             [keywords-to-strings f-to-pipef fs-to-pipef-array]]
            [ogre.branch      :as branch]
            [ogre.filter      :as filter]
            [ogre.map         :as map]
            [ogre.pipe        :as pipe]
            [ogre.reduce      :as reduce]
            [ogre.side-effect :as side-effect]
            [clojure.string   :as stur]))

;;Define functions for the simple methods.  

;;TODO: Wherever there is a HERE, that function fails to reflect.  It
;;looks like whenever the args aren't all clojure types reflection
;;fails. No idea why. 
(def simple-methods 
  [["exhaustMerge" "TODO: Write doc string"] 
   ["fairMerge" "TODO: Write doc string"] 
   ["_" "TODO: Write doc string"]
   ["id" "TODO: Write doc string"]
   ["label" "TODO: Write doc string"] 
   ["scatter" "TODO: Write doc string"] 
   ["simplePath" "TODO: Write doc string"]
   ["enablePath" "TODO: Write doc string"] 
   ["cap" "TODO: Write doc string"]
   ["path" "TODO:Write doc string"
    clojure.lang.ArraySeq]
   ["range" "TODO: Write doc string" ;;;HERE
    Integer Integer]
   ["sideEffect" "TODO: Write doc string"
    clojure.lang.IFn]
   ["ifThenElse" "TODO: Write doc string"
    clojure.lang.IFn clojure.lang.IFn clojure.lang.IFn]
   ["filter" "TODO: Write doc string" 
    clojure.lang.IFn]
   ["transform" "TODO: Write doc string"
    clojure.lang.IFn]
   ["property" "TODO: Write doc string"
    clojure.lang.Keyword]
   ["except" "TODO: Write doc string" ;;HERE
    java.util.Collection]
   ["random" "TODO: Write doc string" ;;HERE
    Double]
   ["retain" "TODO: Write doc string" ;;HERE
    java.util.Collection]
   ["add" "TODO: Write doc string"
    Object]
   ["as" "TODO: Write doc string" ;;HERE
    String]
   ["back" "TODO: Write doc string" ;;HERE
    Integer]
   ["optimize" "TODO: Write doc string"
    Object]
   ["optional" "TODO: Write doc string" ;;HERE
    Integer]
   ["optional-to" "TODO: Write doc string" ;;HERE
    String]
   ["start" "TODO: Write doc string"
    Object]])

(defn function-template [[f doc & args]]
  (let [method (symbol (str "." f))
        fcall  (symbol (stur/replace f #"[A-Z]" 
                                     #(str "-" (stur/lower-case %1))))
        arguments 
        (map-indexed #(vary-meta (symbol (str "arg" %1)) assoc :tag %2) args)

        pre-args (flatten (clojure.core/map (fn [sym]
                                              (if (= clojure.lang.ArraySeq (:tag (meta sym)))
                                                `(& ~sym)
                                                sym))
                                            arguments))

        transformed-args
        (clojure.core/map (fn [sym]
                            (condp = (:tag (meta sym))
                              clojure.lang.Keyword `(name ~sym)
                              clojure.lang.IFn `(f-to-pipef ~sym)
                              clojure.lang.ArraySeq `(fs-to-pipef-array ~sym)
                              sym))
                          arguments)]
    `(defn ~fcall ~doc 
       ([p# ~@pre-args] (conj p# (fn [parg#] (~method ^GremlinPipeline parg# ~@transformed-args)))))))

(doseq [s simple-methods] 
  (eval (function-template s)))

;;Define the travesal methods
(doseq [[direction short shortE name1] '((both <-> <E> both-vertices)
                                         (in   <-- <E- in-vertex)
                                         (out  --> -E> out-vertex))]
  (let [j1 (symbol (str "." direction))
        f1 (symbol (str direction "-edges"))
        j2 (symbol (str "." direction "E"))
        j3 (symbol (str "." direction "V"))]
    (eval `(do
             (defn ~direction 
               ([p#] (~direction p# []))
               ([p# labels#]
                  (conj p# (fn [parg#] (~j1 ^GremlinPipeline parg# (keywords-to-strings labels#))))))
             (defn ~short
               [& args#]
               (apply ~direction args#))
             (defn ~f1
               ([p#] (~f1 p# []))
               ([p# labels#] 
                  (conj p# (fn [^GremlinPipeline parg#] (~j2 parg# (keywords-to-strings labels#))))))
             (defn ~shortE
               [& args#]
               (apply ~f1 args#))
             (defn ~name1 [p#]
               (conj p# (fn [parg#] (~j3 ^GremlinPipeline parg#))))))))

;; ogre.util
(po/import-macro util/query)
(po/import-macro util/subquery)
(po/import-macro util/bare-pipe)

;; ogre.branch
(po/import-fn branch/copy-split)
(po/import-fn branch/loop)
(po/import-fn branch/loop-to)

;; ogre.filter
(po/import-fn filter/dedup)
(po/import-macro filter/has)
(po/import-macro filter/has-not)
(po/import-fn filter/interval)

;; ogre.map
(po/import-fn map/map)
(po/import-fn map/select)
(po/import-fn map/select-only)

;; ogre.pipe
;; TODO break this into pipe and executors
(po/import-fn pipe/back-to)
(po/import-fn pipe/next!)
(po/import-fn pipe/iterate!)
(po/import-fn pipe/prop)

(po/import-fn pipe/into-lazy-seq!)
(po/import-fn pipe/into-vec!)
(po/import-fn pipe/into-set!)
(po/import-fn pipe/first-of!)
(po/import-fn pipe/first-into-vec!)
(po/import-fn pipe/first-into-set!)
(po/import-fn pipe/first-into-map!)
(po/import-fn pipe/all-into-vecs!)
(po/import-fn pipe/all-into-sets!)
(po/import-fn pipe/all-into-maps!)
(po/import-fn pipe/count!)

;; ogre.reduce
(po/import-fn reduce/gather)
(po/import-fn reduce/order)
(po/import-fn reduce/reverse)

;; ogre.side-effect
(po/import-fn side-effect/get-table!)
(po/import-fn side-effect/get-tree!)
(po/import-fn side-effect/get-grouped-by!)
(po/import-fn side-effect/get-group-count!)

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	idEdge(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdEdgePipe to the end of the Pipeline.

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	idVertex(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdVertexPipe to the end of the Pipeline.
