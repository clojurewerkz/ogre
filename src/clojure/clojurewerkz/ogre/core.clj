(ns clojurewerkz.ogre.core
  (:refer-clojure :exclude [filter and or range count iterate next map loop reverse])
  (:require [potemkin :as po]
            [clojurewerkz.ogre.util :as util :refer [keywords-to-strings]]
            [clojurewerkz.ogre.filter :as filter]
            [clojurewerkz.ogre.map :as map]
            [clojurewerkz.ogre.traversal :as traversal]
            [clojurewerkz.ogre.side-effect :as side-effect]
            [clojure.string :as string]))

;;Define functions for the simple methods.

;;TODO: Wherever there is a HERE, that function fails to reflect.  It
;;looks like whenever the args aren't all clojure types reflection
;;fails. No idea why.
(def simple-methods
  [["identity"
    "Turns an arbitrary object into a traversal."]

   ["unfold" "Unrolls or flattens contents of the previous step"]

   ["simplePath"
    "Emits the object only if the current path has no repeated elements."]

   ["cap"
    "Gets the side-effect of the prior traversal. In other words, it emits the value of the
     previous step and not the values that flow through it."]

   ["range"
    "Returns the objects from within the given range (inclusive) of
    indices for the traversal."
    Integer Integer] ;;;HERE

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
    "Names the previous step in the traversal the given string." ;;HERE
    String]

   ["back"
    "Return to the results of the given step." ;;HERE
    String]
   ])

(defn function-template [[f doc & args]]
  (let [method (symbol (str "." f))
        fcall  (symbol (string/replace f #"[A-Z]"
                                     #(str "-" (string/lower-case %1))))
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
                              clojure.lang.IFn `(f-to-function ~sym)
                              clojure.lang.ArraySeq `(into-array ~sym)
                              sym))
                          arguments)
        t (gensym "traversal")]
    `(defn ~fcall ~doc
       ([~t ~@pre-args] (~method ^GraphTraversal ~t ~@transformed-args)))))

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
               ([t#] (~direction t# []))
               ([t# labels#]
                 (-> t# (~j1 (keywords-to-strings labels#)))))
             (defn ~short
               [& args#]
               (apply ~direction args#))
             (defn ~f1
               ([t#] (~f1 t# []))
               ([t# labels#]
                  (-> t# (~j2 (keywords-to-strings labels#)))))
             (defn ~shortE
               [& args#]
               (apply ~f1 args#))
             (defn ~name1 [t#]
               (-> t# (~j3)))))))

;; clojurewerkz.ogre.util
(po/import-macro util/query)
(po/import-macro util/subquery)

;; clojurewerkz.ogre.filter
(po/import-fn filter/dedup)
(po/import-macro filter/has)
(po/import-fn filter/has-not)
(po/import-fn filter/interval)
(po/import-fn filter/filter)

;; clojurewerkz.ogre.map
(po/import-fn map/id)
(po/import-fn map/fold)
(po/import-fn map/label)
(po/import-fn map/map)
(po/import-fn map/path)
(po/import-fn map/properties)
(po/import-fn map/order)
(po/import-fn map/select)
(po/import-fn map/select-only)
(po/import-fn map/values)

;; clojurewerkz.ogre.traversal
;; TODO break this into traversal and executors
(po/import-fn traversal/next!)
(po/import-fn traversal/iterate!)

(po/import-fn traversal/into-lazy-seq!)
(po/import-fn traversal/into-list!)
(po/import-fn traversal/into-vec!)
(po/import-fn traversal/into-set!)
(po/import-fn traversal/first-of!)
(po/import-fn traversal/first-into-vec!)
(po/import-fn traversal/first-into-set!)
(po/import-fn traversal/first-into-map!)
(po/import-fn traversal/all-into-vecs!)
(po/import-fn traversal/all-into-sets!)
(po/import-fn traversal/all-into-maps!)
(po/import-fn traversal/count!)

;; clojurewerkz.ogre.side-effect
(po/import-fn side-effect/side-effect)
(po/import-fn side-effect/get-grouped-by!)
(po/import-fn side-effect/get-group-count!)
