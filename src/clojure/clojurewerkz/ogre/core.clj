(ns clojurewerkz.ogre.core
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [potemkin :as po]
            [clojurewerkz.ogre.util :as util]
            [clojurewerkz.ogre.anon :as anon])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Compare Operator Order P Pop SackFunctions$Barrier Scope Traversal)
           (org.apache.tinkerpop.gremlin.structure Graph T Column VertexProperty$Cardinality)
           (org.apache.tinkerpop.gremlin.structure.util GraphFactory)
           (java.util Iterator)
           (org.apache.tinkerpop.gremlin.process.traversal.dsl.graph GraphTraversal GraphTraversalSource)))

(po/import-macro util/traverse)
(po/import-macro anon/__)

(po/import-fn util/into-seq!)
(po/import-fn util/into-list!)
(po/import-fn util/into-vec!)
(po/import-fn util/into-set!)
(po/import-fn util/iterate!)
(po/import-fn util/next!)

; GraphFactory
(defn open-graph
  "Opens a new TinkerGraph with default configuration or open a new Graph instance with the specified
   configuration. The configuration may be a path to a file or a Map of configuration options."
  ([] (open-graph {(Graph/GRAPH) (.getName org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph)}))
  ([conf]
   (cond
     (map? conf)
     (GraphFactory/open ^java.util.Map conf)
     (string? conf)
     (GraphFactory/open ^String conf))))

; Graph
(defn traversal
  [^Graph graph]
  (.traversal graph))

; GraphTraversalSource
(defn add-V
  ([^GraphTraversalSource g]
    (.addV g))
  ([^GraphTraversalSource g label]
    (.addV g ^String (util/cast-param label))))

(defn E
  "Returns all edges matching the supplied ids. If no ids are supplied, returns all edges."
  [^GraphTraversalSource g & ids]
  (.E g (into-array ids)))

(defn inject
  [^GraphTraversalSource g & starts]
  (.inject g (into-array starts)))

(defn V
  "Returns all vertices matching the supplied ids. If no ids are supplied, returns all vertices."
  [^GraphTraversalSource g & ids]
  (.V g (into-array ids)))

(defn with-bulk
  [^GraphTraversalSource g use-bulk]
  (.withBulk g use-bulk))

(defn with-path
  [^GraphTraversalSource g]
  (.withPath g))

(defn with-sack
  ([^GraphTraversalSource g arg]
   (if (instance? clojure.lang.IFn arg)
     (.withSack g (util/f-to-supplier arg))
     (.withSack g arg)))
  ([^GraphTraversalSource g arg m]
    (let [^java.util.function.BinaryOperator merge-operator (if (contains? m ::merge)
                                                              (if (instance? Operator (::merge m)) (::merge m) (util/f-to-binaryoperator (::merge m)))
                                                              nil)
          ^java.util.function.UnaryOperator split-operator (if (contains? m ::split)
                                                             (if (instance? Operator (::split m)) (::split m) (util/f-to-unaryoperator (::split m)))
                                                             nil)]
      (if (instance? clojure.lang.IFn arg)
        (cond
          (clojure.core/and (nil? merge-operator) (nil? split-operator))
          (.withSack g (util/f-to-supplier arg))
          (nil? merge-operator)
          (.withSack g (util/f-to-supplier arg) split-operator)
          (nil? split-operator)
          (.withSack g (util/f-to-supplier arg) merge-operator)
          :else
          (.withSack g (util/f-to-supplier arg) split-operator merge-operator))
        (cond
          (clojure.core/and (nil? merge-operator) (nil? split-operator))
          (.withSack g arg)
          (nil? merge-operator)
          (.withSack g arg split-operator)
          (nil? split-operator)
          (.withSack g arg merge-operator)
          :else
          (.withSack g arg split-operator merge-operator))))))

(defn with-side-effect
  ([^GraphTraversalSource g ^String k v]
    (if (instance? clojure.lang.IFn v)
      (.withSideEffect g ^String (util/cast-param k) (util/f-to-supplier v))
      (.withSideEffect g ^String (util/cast-param k) v)))
  ([^GraphTraversalSource g ^String k v r]
   (if (instance? Operator r)
     (if (instance? clojure.lang.IFn v)
        (.withSideEffect g ^String (util/cast-param k) (util/f-to-supplier v) ^Operator r)
        (.withSideEffect g ^String (util/cast-param k) v ^Operator r))
     (if (instance? clojure.lang.IFn v)
        (.withSideEffect g ^String (util/cast-param k) (util/f-to-supplier v) (util/f-to-binaryoperator r))
        (.withSideEffect g ^String (util/cast-param k) v (util/f-to-binaryoperator r))))))

; GraphTraversal

(defn addE
  [^GraphTraversal t label]
  (.addE t ^String (util/cast-param label)))

(defn addV
  ([^GraphTraversal t]
   (.addV t))
  ([^GraphTraversal t label]
   (.addV t ^String (util/cast-param label))))

(defn aggregate
  [^GraphTraversal t k]
  (.aggregate t (util/cast-param k)))

(defn and
  [^GraphTraversal t & traversals]
  (.and t (into-array Traversal traversals)))

(defn as
  [^GraphTraversal t step-label & step-labels]
  (if (empty? step-labels)
    (.as t (util/cast-param step-label) (util/str-array []))
    (.as t (util/cast-param step-label) (util/keywords-to-str-array step-labels))))

(defn barrier
  ([^GraphTraversal t]
   (.barrier t))
  ([^GraphTraversal t max-or-consumer]
   (cond
     (identical? max-or-consumer ::norm-sack)
     (.barrier t (SackFunctions$Barrier/normSack))
     (instance? clojure.lang.IFn max-or-consumer)
     (.barrier t (util/f-to-consumer max-or-consumer))
     :else
     (.barrier t (int max-or-consumer)))))

(defn both
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.both t label-array)))

(defn bothE
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.bothE t label-array)))

(defn bothV
  [^GraphTraversal t]
  (.bothV t))

(defn branch
  [^GraphTraversal t f-or-t]
  (if (instance? Traversal f-or-t)
    (.branch t ^Traversal f-or-t)
    (.branch t (util/f-to-function f-or-t))))

(defn by
  ([^GraphTraversal t]
   (.by t))
  ([^GraphTraversal t arg1]
   (cond
     (keyword? arg1)
     (.by t ^String (util/cast-param arg1))
     (instance? String arg1)
     (.by t ^String arg1)
     (instance? Order arg1)
     (.by t ^Order arg1)
     (instance? java.util.Comparator arg1)
     (.by t ^java.util.Comparator arg1)
     (instance? T arg1)
     (.by t ^T arg1)
     (instance? Traversal arg1)
     (.by t ^Traversal arg1)))
  ([^GraphTraversal t arg1 compar]
   (if (identical? :fn compar)
     (.by t (util/f-to-function arg1))
     (cond
       (keyword? arg1)
       (.by t ^String (util/cast-param arg1) ^java.util.Comparator compar)
       (instance? Column arg1)
       (.by t ^Column arg1 ^java.util.Comparator compar)
       (instance? clojure.lang.IFn arg1)
       (.by t (util/f-to-function arg1) ^java.util.Comparator compar)
       (instance? T arg1)
       (.by t ^T arg1 ^java.util.Comparator compar)
       (instance? String arg1)
       (.by t ^String arg1 ^java.util.Comparator compar)
       (instance? Traversal arg1)
       (.by t ^Traversal arg1 ^java.util.Comparator compar)))))

(defn cap
  [^GraphTraversal t k & ks]
  (.cap t (util/cast-param k) (util/keywords-to-str-array ks)))

(defn choose
  ([^GraphTraversal t f-or-t]
   (if (instance? Traversal f-or-t)
     (.choose t ^Traversal f-or-t)
     (.choose t (util/f-to-function f-or-t))))
  ([^GraphTraversal t p-or-t true-choice false-choice]
   (if (instance? Traversal p-or-t)
     (.choose t ^Traversal p-or-t ^Traversal true-choice ^Traversal false-choice)
     (.choose t (util/f-to-predicate p-or-t) ^Traversal true-choice ^Traversal false-choice))))

(defn coalesce
  [^GraphTraversal t & traversals]
  (.coalesce t (into-array Traversal traversals)))

(defn coin
  [^GraphTraversal t prob]
  (.coin t prob))

(defn constant
  [^GraphTraversal t c]
  (.constant t c))

(defn count
  ([^GraphTraversal t]
   (.count t))
  ([^GraphTraversal t scope]
   (.count t scope)))

(defn cyclic-path
  [^GraphTraversal t]
  (.cyclicPath t))

(defn dedup
  ([^GraphTraversal t]
   (.dedup t (into-array String [])))
  ([^GraphTraversal t & args]
   (if (instance? Scope (first args))
     (.dedup t ^Scope (first args) (util/keywords-to-str-array (rest args)))
     (.dedup t (util/keywords-to-str-array args)))))

(defn drop
  [^GraphTraversal t]
  (.drop t))

(defn emit
  ([^GraphTraversal t]
   (.emit t))
  ([^GraphTraversal t pred-or-t]
   (if (instance? Traversal pred-or-t)
     (.emit t ^Traversal pred-or-t)
     (.emit t (util/f-to-predicate pred-or-t)))))

(defn explain
  ([^GraphTraversal t]
   (.explain t)))

(defn filter
  [^GraphTraversal t pred-or-t]
  (if (instance? Traversal pred-or-t)
    (.filter t ^Traversal pred-or-t)
    (.filter t (util/f-to-predicate pred-or-t))))

(defn flat-map
  [^GraphTraversal t f-or-t]
  (if (instance? Traversal f-or-t)
    (.flatMap t ^Traversal f-or-t)
    (.flatMap t (util/f-to-function f-or-t))))

(defn fold
  ([^GraphTraversal t]
   (.fold t))
  ([^GraphTraversal t seed fold-function]
   (if (instance? Operator fold-function)
     (.fold t seed fold-function)
     (.fold t seed (util/f-to-bifunction fold-function)))))

(defn from
  ([^GraphTraversal t t-or-label]
   (if (instance? Traversal t-or-label)
     (.from t ^Traversal t-or-label)
     (.from t ^String (util/cast-param t-or-label)))))

(defn group
  ([^GraphTraversal t]
   (.group t))
  ([^GraphTraversal t k]
   (.group t (util/cast-param k))))

(defn group-count
  ([^GraphTraversal t]
   (.groupCount t))
  ([^GraphTraversal t k]
   (.groupCount t (util/cast-param k))))

(defn has
  "Allows an element if it has the given property or it satisfies given predicate."
  ([^GraphTraversal t k]
   (.has t (util/cast-param k)))
  ([^GraphTraversal t k val-or-pred-or-t]
   (let [arg1 (util/cast-param k)]
     (cond
       (instance? String arg1)
       (cond
         (instance? P val-or-pred-or-t)
         (.has t ^String arg1 ^P val-or-pred-or-t)
         (instance? Traversal val-or-pred-or-t)
         (.has t ^String arg1 ^Traversal val-or-pred-or-t)
         :else (.has t ^String arg1 ^Object (util/cast-param val-or-pred-or-t)))
       (instance? T arg1)
       (cond
         (instance? P val-or-pred-or-t)
         (.has t ^T arg1 ^P val-or-pred-or-t)
         (instance? Traversal val-or-pred-or-t)
         (.has t ^T arg1 ^Traversal val-or-pred-or-t)
         :else (.has t ^T arg1 ^Object (util/cast-param val-or-pred-or-t))))))
  ([^GraphTraversal t label k val-or-pred]
   (let [arg2 (util/cast-param k)
         arg1 (util/cast-param label)]
     (if (instance? P val-or-pred)
       (.has t ^String arg1 ^String arg2 ^P val-or-pred)
       (.has t ^String arg1 ^String arg2 ^Object (util/cast-param val-or-pred))))))

(defn has-id
  [^GraphTraversal t & ids]
  (if (seq? ids)
    (.hasId t (into-array Object ids))
    (.hasId t (into-array Object [ids]))))

(defn has-key
  [^GraphTraversal t & ks]
  (let [k-array (util/keywords-to-str-array ks)]
    (.hasKey t k-array)))

(defn has-label
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.hasLabel t label-array)))

(defn id
  [^GraphTraversal t]
  (.id t))

(defn identity
  [^GraphTraversal t]
  (.identity t))

(defn in
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.in t label-array)))

(defn inE
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.inE t label-array)))

(defn inject
  [^GraphTraversal t & args]
  (.inject t (into-array args)))

(defn inV
  [^GraphTraversal t]
  (.inV t))

(defn is
  [^GraphTraversal t val-or-pred]
  (if (instance? P val-or-pred)
    (.is t ^P val-or-pred)
    (.is t ^Object (util/cast-param val-or-pred))))

(defn key
  [^GraphTraversal t]
  (.key t))

(defn label
  [^GraphTraversal t]
  (.label t))

(defn limit
  ([^GraphTraversal t lim]
   (.limit t lim))
  ([^GraphTraversal t scope lim]
   (.limit t scope lim)))

(defn local
  [^GraphTraversal t local-traversal]
  (.local t local-traversal))

(defn loops
  [^GraphTraversal t]
  (.loops t))

(defn map
  [^GraphTraversal t f-or-t]
  (if (instance? Traversal f-or-t)
    (.map t ^Traversal f-or-t)
    (.map t (util/f-to-function f-or-t))))

(defn match
  [^GraphTraversal t & traversals]
  (.match t (into-array traversals)))

(defn max
  ([^GraphTraversal t]
   (.max t))
  ([^GraphTraversal t scope]
   (.max t scope)))

(defn mean
  ([^GraphTraversal t]
   (.mean t))
  ([^GraphTraversal t scope]
   (.mean t scope)))

(defn min
  ([^GraphTraversal t]
   (.min t))
  ([^GraphTraversal t scope]
   (.min t scope)))

(defn not
  ([^GraphTraversal t not-traversal]
   (.not t not-traversal)))

(defn option
  ([^GraphTraversal t opt-traversal]
   (.option t opt-traversal))
  ([^GraphTraversal t pick-token opt-traversal]
   (.option t pick-token opt-traversal)))

(defn optional
  ([^GraphTraversal t opt-traversal]
   (.optional t opt-traversal)))

(defn or
  [^GraphTraversal t & traversals]
   (.or t (into-array Traversal traversals)))

(defn order
  ([^GraphTraversal t]
   (.order t))
  ([^GraphTraversal t scope]
   (.order t scope)))

(defn otherV
  [^GraphTraversal t]
  (.otherV t))

(defn out
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.out t label-array)))

(defn outE
  [^GraphTraversal t & labels]
  (let [label-array (util/keywords-to-str-array labels)]
    (.outE t label-array)))

(defn outV
  [^GraphTraversal t]
  (.outV t))

(defn page-rank
  ([^GraphTraversal t]
   (.pageRank t))
  ([^GraphTraversal t alpha]
   (.pageRank t alpha)))

(defn path
  [^GraphTraversal t]
  (.path t))

(defn peer-pressure
  [^GraphTraversal t]
  (.peerPressure t))

(defn profile
  ([^GraphTraversal t k]
   (.profile t k)))

;; excluded program()

(defn project
  [^GraphTraversal t k & ks]
  (.project t (util/cast-param k) (util/keywords-to-str-array ks)))

(defn properties
  [^GraphTraversal t & ks]
  (.properties t (util/keywords-to-str-array ks)))

(defn property
  [^GraphTraversal t & args]
  (if (instance? VertexProperty$Cardinality (first args))
    (if (= (clojure.core/count args) 3)
      (.property t ^VertexProperty$Cardinality (first args) (util/cast-param (second args)) (nth args 2) (into-array []))
      (.property t ^VertexProperty$Cardinality (first args) (util/cast-param (second args)) (nth args 2) (util/cast-every-other-param (take-last (- (clojure.core/count args) 3) args))))
    (if (= (clojure.core/count args) 2)
      (.property t ^Object (util/cast-param (first args)) (second args) (into-array []))
      (.property t ^Object (util/cast-param (first args)) (second args) (util/cast-every-other-param (take-last (- (clojure.core/count args) 2) args))))))

(defn property-map
  [^GraphTraversal t & ks]
  (.propertyMap t (util/keywords-to-str-array ks)))

(defn range
  ([^GraphTraversal t low high]
   (.range t low high))
  ([^GraphTraversal t scope low high]
   (.range t scope low high)))

(defn repeat
  [^GraphTraversal t repeat-traversal]
  (.repeat t repeat-traversal))

(defn sack
  ([^GraphTraversal t]
   (.sack t))
  ([^GraphTraversal t sack-op]
   (if (instance? Operator sack-op)
     (.sack t sack-op)
     (.sack t (util/f-to-bifunction sack-op)))))

(defn sample
  ([^GraphTraversal t amount]
   (.sample t amount))
  ([^GraphTraversal t scope amount]
   (.sample t scope amount)))

(defn select
  ([^GraphTraversal t arg1]
   (if (instance? Column arg1)
     (.select t ^Column arg1)
     (.select t ^String (util/cast-param arg1))))
  ([^GraphTraversal t arg1 & args]
   (if (instance? Pop arg1)
     (if (= (clojure.core/count args) 1)
       (.select t ^Pop arg1 (util/cast-param (first args)))
       (.select t ^Pop arg1 (util/cast-param (first args)) (util/cast-param (second args)) (util/keywords-to-str-array (take-last 2 args))))
     (.select t ^String (util/cast-param arg1) (util/cast-param (first args)) (util/keywords-to-str-array (rest args))))))

(defn side-effect
  [^GraphTraversal t c-or-t]
  (if (instance? Traversal c-or-t)
    (.sideEffect t ^Traversal c-or-t)
    (.sideEffect t (util/f-to-consumer c-or-t))))

(defn simple-path
  [^GraphTraversal t]
  (.simplePath t))

(defn store
  [^GraphTraversal t k]
  (.store t (util/cast-param k)))

(defn subgraph
  [^GraphTraversal t k]
  (.subgraph t (util/cast-param k)))

(defn sum
  ([^GraphTraversal t]
   (.sum t))
  ([^GraphTraversal t scope]
   (.sum t scope)))

(defn tail
  ([^GraphTraversal t]
   (.tail t))
  ([^GraphTraversal t arg1]
   (if (instance? Scope arg1)
     (.tail t ^Scope arg1)
     (.tail t (long arg1))))
  ([^GraphTraversal t ^Scope scope ^Long lim]
   (.tail t scope lim)))

(defn time-limit
  [^GraphTraversal t lim]
  (.timeLimit t lim))

(defn times
  [^GraphTraversal t loops]
  (.times t loops))

(defn to
  ([^GraphTraversal t arg1]
   (if (instance? Traversal arg1)
     (.to t ^Traversal arg1)
     (.to t ^String (util/cast-param arg1))))
  ([^GraphTraversal t direction & labels]
   (.to t direction (util/keywords-to-str-array labels))))

(defn to-E
  ([^GraphTraversal t direction & labels]
   (.toE t direction (util/keywords-to-str-array labels))))

(defn to-V
  ([^GraphTraversal t direction]
   (.toV t direction)))

(defn tree
  ([^GraphTraversal t]
   (.tree t))
  ([^GraphTraversal t k]
   (.tree t (util/cast-param k))))

(defn unfold
  [^GraphTraversal t]
  (.unfold t))

(defn union
  [^GraphTraversal t & traversals]
  (.union t (into-array traversals)))

(defn until
  [^GraphTraversal t pred-or-t]
  (if (instance? Traversal pred-or-t)
    (.until t ^Traversal pred-or-t)
    (.until t (util/f-to-predicate pred-or-t))))

(defn midV
  "A mid-traversal V known in Gremlin-Java as just V()"
  [^GraphTraversal t & ids]
  (.V t (into-array ids)))

(defn value
  [^GraphTraversal t]
  (.value t))

(defn value-map
  [^GraphTraversal t & args]
  (if (clojure.core/and (clojure.core/not (empty? args)) (instance? Boolean (first args)))
    (.valueMap t (first args) (util/keywords-to-str-array (rest args))))
    (.valueMap t (util/keywords-to-str-array args)))

(defn values
  [^GraphTraversal t & ks]
  (let [k-array (util/keywords-to-str-array ks)]
    (.values t k-array)))

(defn where
  ([^GraphTraversal t p-or-t]
   (if (instance? Traversal p-or-t)
     (.where t ^Traversal p-or-t)
     (.where t ^P p-or-t)))
  ([^GraphTraversal t k p]
   (.where t (util/cast-param k) p)))

;; helpers

(defn scope
  [s]
  (if (clojure.core/and (keyword? s) (contains? #{:local :global} s))
    (if (= s :local) (Scope/local) (Scope/global))
    (throw (IllegalArgumentException. "Must be :local or :global"))))
