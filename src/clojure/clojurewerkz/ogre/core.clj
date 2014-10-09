(ns clojurewerkz.ogre.core
  (:import (com.tinkerpop.gremlin.process.graph GraphTraversal))
  (:refer-clojure :exclude [filter and or range count memoize iterate next map loop reverse])
  (:require [potemkin :as po]
            [clojurewerkz.ogre.util        :as util :refer
             [keywords-to-strings]]
             ;[keywords-to-strings f-to-pipef fs-to-pipef-array]]
            ;[clojurewerkz.ogre.branch      :as branch]
            ;[clojurewerkz.ogre.filter      :as filter]
            ;[clojurewerkz.ogre.map         :as map]
            [clojurewerkz.ogre.pipe        :as pipe]
            ;[clojurewerkz.ogre.reduce      :as reduce]
            ;[clojurewerkz.ogre.side-effect :as side-effect]
            [clojure.string   :as stur]))

;;Define functions for the simple methods.  

;;TODO: Wherever there is a HERE, that function fails to reflect.  It
;;looks like whenever the args aren't all clojure types reflection
;;fails. No idea why. 
(def simple-methods 
  [["exhaustMerge" "TODO: Write doc string"] 
   ["fairMerge" "TODO: Write doc string"] 
   ["_" "TODO: Write doc string"]
   ["id" "Returns the unique identifier of the given element."]
   ["label" "Returns the label of the given edge."] 
   ["scatter" "TODO: Write doc string"] 
   ["simplePath" "TODO: Write doc string"]
   ["enablePath" "TODO: Write doc string"] 
   ["cap" "TODO: Write doc string"]
   ["path" "TODO:Write doc string"
    clojure.lang.ArraySeq]

   ["range" 
    "Returns the objects from within the given range (inclusive) of
    indices for the pipeline."  
    Integer Integer] ;;;HERE

   ["sideEffect" 
    "Maps a function across each element in the pipeline,
    but not necessarily changing the pipeline elements."
    clojure.lang.IFn]

   ["ifThenElse" 
    "Given three functions, if the first function is true, the result
    of the second function is returned, otherwise the result of the
    third function is returned."  
    clojure.lang.IFn clojure.lang.IFn clojure.lang.IFn]

   ["filter" 
    "Filters out elements in the pipeline according to the given
    predicate function." 
    clojure.lang.IFn]

   ["transform" 
    "Maps the given function over the elements in the pipeline and
    returns the results." 
    clojure.lang.IFn]

   ["property"
    "Given a keyword or string, returns the corresponding property for
    each element in the pipeline."  
    clojure.lang.Keyword]

   ["except"
    "Filters out all of elements that are in the given collection." 
    java.util.Collection] ;;Here

   ["random" 
    "Each element is sampled according to the given probability."     
    Double] ;;HERE

   ["retain" 
    "Given a collection, only retains elements from the given
    collection. Given a string corresponding to a named step, retains
    all elements that were present at the named step."
    java.util.Collection] ;; HERE
   
   ["as" 
    "Names the previous step in the pipeline the given string." ;;HERE
    String]

   ["back" 
    "Return to the results of the given step." ;;HERE
    Integer]

   ["optional" 
    "Returns the results of the current step and the given named step." 
    String]])

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
                              ;clojure.lang.IFn `(f-to-pipef ~sym)
                              ;clojure.lang.ArraySeq `(fs-to-pipef-array ~sym)
                              sym))
                          arguments)
        p (gensym "pipeline")]
    `(defn ~fcall ~doc
       ([~p ~@pre-args] (conj ~p (fn [parg#] (~method ^GraphTraversal parg# ~@transformed-args)))))))

(doseq [s simple-methods] 
  (eval (function-template s)))

;;Define the traversal methods
(doseq [[direction short shortE name1] '((both <-> <E> both-vertices)
                                         (in   <-- <E- in-vertex)
                                         (out  --> -E> out-vertex))]
  (let [j1 (symbol (str "." direction))
        f1 (symbol (str direction "-edges"))
        j2 (symbol (str "." direction "E"))
        j3 (symbol (str "." direction "V"))]
    (eval `(do
             (defn ~direction 
               ;; ~(str "Traverses edges along the "
               ;;       direction 
               ;;       " direction and returns the vertices.")
               ([p#] (~direction p# []))
               ([p# labels#]
                  (conj p# (fn [parg#] (~j1 ^GraphTraversal parg# (keywords-to-strings labels#))))))
             (defn ~short
               [& args#]
               (apply ~direction args#))
             (defn ~f1
               ([p#] (~f1 p# []))
               ([p# labels#] 
                  (conj p# (fn [^GraphTraversal parg#] (~j2 parg# (keywords-to-strings labels#))))))
             (defn ~shortE
               [& args#]
               (apply ~f1 args#))
             (defn ~name1 [p#]
               (conj p# (fn [parg#] (~j3 ^GraphTraversal parg#))))))))

;; clojurewerkz.ogre.util
(po/import-macro util/query)
(po/import-macro util/subquery)
;(po/import-macro util/bare-pipe)

;; clojurewerkz.ogre.branch
;(po/import-fn branch/copy-split)
;(po/import-fn branch/loop)
;(po/import-fn branch/loop-to)

;; clojurewerkz.ogre.filter
;(po/import-fn filter/dedup)
;(po/import-macro filter/has)
;(po/import-macro filter/has-not)
;(po/import-fn filter/interval)

;; clojurewerkz.ogre.map
;(po/import-fn map/map)
;(po/import-fn map/select)
;(po/import-fn map/select-only)

;; clojurewerkz.ogre.pipe
;; TODO break this into pipe and executors
(po/import-fn pipe/back-to)
(po/import-fn pipe/next!)
(po/import-fn pipe/iterate!)
(po/import-fn pipe/prop)

(po/import-fn pipe/into-lazy-seq!)
(po/import-fn pipe/into-list!)
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

;; clojurewerkz.ogre.reduce
;(po/import-fn reduce/gather)
;(po/import-fn reduce/order)
;(po/import-fn reduce/reverse)

;; clojurewerkz.ogre.side-effect
;(po/import-fn side-effect/get-table!)
;(po/import-fn side-effect/get-tree!)
;(po/import-fn side-effect/get-grouped-by!)
;(po/import-fn side-effect/get-group-count!)

;; GremlinPipeline<S,com.tinkerpop.blueprints.Edge>	idEdge(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdEdgePipe to the end of the Pipeline.

;; GremlinPipeline<S,com.tinkerpop.blueprints.Vertex>	idVertex(com.tinkerpop.blueprints.Graph graph) 
;; Add an IdVertexPipe to the end of the Pipeline.
