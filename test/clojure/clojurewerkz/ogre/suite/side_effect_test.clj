(ns clojurewerkz.ogre.suite.side-effect-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Operator P Traverser)))

(defn get_g_VX1X_sideEffectXstore_aX_name
  "g.withSideEffect('a', ArrayList::new).V(v1Id).sideEffect(traverser -> {
                traverser.<List>sideEffects('a').clear();
                traverser.<List<Vertex>>sideEffects('a').add(traverser.get());
            }).values('name')"
  [g v1Id]
  (q/traverse g (q/with-side-effect :a (fn [] (new java.util.ArrayList)))
                (q/V v1Id)
                (q/side-effect (fn [^Traverser t]
                                 (.clear (.sideEffects t "a"))
                                 (.add (.sideEffects t "a") (.get t))))
                (q/values :name)))

(defn get_g_VX1X_out_sideEffectXincr_cX_name
  "g.withSideEffect('c', () -> {
                final List<Integer> list = new ArrayList<>();
                list.add(0);
                return list;
            }).V(v1Id).out().sideEffect(traverser -> {
                Integer temp = traverser.<List<Integer>>sideEffects('c').get(0);
                traverser.<List<Integer>>sideEffects('c').clear();
                traverser.<List<Integer>>sideEffects('c').add(temp + 1);
            }).values('name')"
  [g v1Id]
  (q/traverse g (q/with-side-effect :c (fn []
                                         (let [l (new java.util.ArrayList)]
                                           (.add l (int 0))
                                           l)))
                (q/V v1Id)
                (q/out)
                (q/side-effect (fn [^Traverser t]
                                 (let [temp (int (+ (.get (.sideEffects t "c") 0) 1))]
                                   (.clear (.sideEffects t "c"))
                                   (.add (.sideEffects t "c") temp))))
                (q/values :name)))

(defn get_g_VX1X_out_sideEffectXX_name
  "g.V(v1Id).out().sideEffect(traverser -> {}).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/side-effect (fn [^Traverser t]))
                (q/values :name)))

(defn get_g_withSideEffectXa__linkedhashmapX_V_out_groupCountXaX_byXlabelX_out_out_capXaX
  "g.withSideEffect('a', new LinkedHashMapSupplier()).V().out().groupCount('a').by(T.label).out().out().cap('a')"
  [g]
  (q/traverse g (q/with-side-effect :a (fn [] (new java.util.LinkedHashMap)))
                (q/V)
                (q/out)
                (q/group-count :a)
                (q/by (T/label))
                (q/out)
                (q/out)
                (q/cap :a)))

(defn get_g_withSideEffectsXa__linkedhashmapX_withSideEffectXb__arraylist__addAllX_withSideEffectXc__arrayList__addAllX_V_groupXaX_byXlabelX_byXcountX_sideEffectXb__1_2_3X_out_out_out_sideEffectXc__bob_danielX_capXaX
  "g.withSideEffect('a', new LinkedHashMapSupplier())
                    .withSideEffect('b', ArrayListSupplier.instance(), Operator.addAll)
                    .withSideEffect('c', ArrayListSupplier.instance(), Operator.addAll)
                    .V().group('a').by(T.label).by(__.count())
                    .sideEffect(t -> t.sideEffects('b', new LinkedList<>(Arrays.asList(1, 2, 3))))
                    .out().out().out()
                    .sideEffect(t -> t.sideEffects('c', new LinkedList<>(Arrays.asList('bob', 'daniel'))))
                    .cap('a')"
  [g]
  (q/traverse g (q/with-side-effect :a (fn [] (new java.util.LinkedHashMap)))
                (q/with-side-effect :b (org.apache.tinkerpop.gremlin.util.function.ArrayListSupplier/instance) (Operator/addAll))
                (q/with-side-effect :c (org.apache.tinkerpop.gremlin.util.function.ArrayListSupplier/instance) (Operator/addAll))
                (q/V)
                (q/group :a) (q/by (T/label)) (q/by (q/__ (q/count)))
                (q/side-effect (fn [^Traverser t] (.sideEffects t "b" (new java.util.LinkedList (java.util.Arrays/asList (to-array [(int 1) (int 2) (int 3)]))))))
                (q/out)
                (q/out)
                (q/out)
                (q/side-effect (fn [^Traverser t] (.sideEffects t "b" (new java.util.LinkedList (java.util.Arrays/asList (to-array ["bob" "daniel"]))))))
                (q/cap :a)))

(defn get_g_withSideEffectXa_0_sumX_V_out_sideEffectXsideEffectsXa_bulkXX_capXaX
  "g.withSideEffect('a', 0, Operator.sum).V().out().sideEffect(t -> t.sideEffects('a', (int) t.bulk())).cap('a')"
  [g]
  (q/traverse g (q/with-side-effect :a (int 0) (Operator/sum))
                (q/V)
                (q/out)
                (q/side-effect (fn [^Traverser t] (.sideEffects t "a" (int (.bulk t)))))
                (q/cap :a)))

(defn get_g_withSideEffectXa_0X_V_out_sideEffectXsideEffectsXa_1XX_capXaX
  "g.withSideEffect('a', 0).V().out().sideEffect(t -> t.sideEffects('a', 1)).cap('a')"
  [g]
  (q/traverse g (q/with-side-effect :a (int 0))
                (q/V)
                (q/out)
                (q/side-effect (fn [^Traverser t] (.sideEffects t "a" (int 1))))
                (q/cap :a)))
