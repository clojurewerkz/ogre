(ns clojurewerkz.ogre.suite.side-effect-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_VX1X_sideEffectXstore_aX_name
  "g.withSideEffect('a', ArrayList::new).V(v1Id).sideEffect(traverser -> {
                traverser.<List>sideEffects('a').clear();
                traverser.<List<Vertex>>sideEffects('a').add(traverser.get());
            }).values('name')"
  [g v1Id]
  (traverse g (with-side-effect :a (fn [] (new java.util.ArrayList)))
              (V v1Id)
              (side-effect (fn [^Traverser t]
                               (.clear ^java.util.ArrayList (.sideEffects t "a"))
                               (.add ^ java.util.ArrayList (.sideEffects t "a") (.get t))))
              (values :name)))

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
  (traverse g (with-side-effect :c (fn []
                                       (let [l (new java.util.ArrayList)]
                                         (.add l (int 0))
                                         l)))
              (V v1Id)
              (out)
              (side-effect (fn [^Traverser t]
                               (let [temp (int (+ (.get ^java.util.ArrayList (.sideEffects t "c") 0) 1))]
                                 (.clear ^java.util.ArrayList (.sideEffects t "c"))
                                 (.add ^java.util.ArrayList (.sideEffects t "c") temp))))
              (values :name)))

(defn get_g_VX1X_out_sideEffectXX_name
  "g.V(v1Id).out().sideEffect(traverser -> {}).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (side-effect (fn [^Traverser t]))
              (values :name)))

(defn get_g_withSideEffectXa__linkedhashmapX_V_out_groupCountXaX_byXlabelX_out_out_capXaX
  "g.withSideEffect('a', new LinkedHashMapSupplier()).V().out().groupCount('a').by(T.label).out().out().cap('a')"
  [g]
  (traverse g (with-side-effect :a (fn [] (new java.util.LinkedHashMap)))
              (V)
              (out)
              (group-count :a)
                (by (T/label))
              (out)
              (out)
              (cap :a)))

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
  (traverse g (with-side-effect :a (fn [] (new java.util.LinkedHashMap)))
              (with-side-effect :b (org.apache.tinkerpop.gremlin.util.function.ArrayListSupplier/instance) (operator :addAll))
              (with-side-effect :c (org.apache.tinkerpop.gremlin.util.function.ArrayListSupplier/instance) (operator :addAll))
              (V)
              (group :a) (by (T/label)) (by (__ (count)))
              (side-effect (fn [^Traverser t] (.sideEffects t "b" (new java.util.LinkedList (java.util.Arrays/asList (to-array [(int 1) (int 2) (int 3)]))))))
              (out)
              (out)
              (out)
              (side-effect (fn [^Traverser t] (.sideEffects t "b" (new java.util.LinkedList (java.util.Arrays/asList (to-array ["bob" "daniel"]))))))
              (cap :a)))

(defn get_g_withSideEffectXa_0_sumX_V_out_sideEffectXsideEffectsXa_bulkXX_capXaX
  "g.withSideEffect('a', 0, Operator.sum).V().out().sideEffect(t -> t.sideEffects('a', (int) t.bulk())).cap('a')"
  [g]
  (traverse g (with-side-effect :a (int 0) (operator :sum))
              (V)
              (out)
              (side-effect (fn [^Traverser t] (.sideEffects t "a" (int (.bulk t)))))
              (cap :a)))

(defn get_g_withSideEffectXa_0X_V_out_sideEffectXsideEffectsXa_1XX_capXaX
  "g.withSideEffect('a', 0).V().out().sideEffect(t -> t.sideEffects('a', 1)).cap('a')"
  [g]
  (traverse g (with-side-effect :a (int 0))
              (V)
              (out)
              (side-effect (fn [^Traverser t] (.sideEffects t "a" (int 1))))
              (cap :a)))

(defn get_g_withSideEffectXk_nameX_V_order_byXvalueMap_selectXkX_unfoldX_name
  "g.withSideEffect('key','name').V().order().by(valueMap().select(select('key')).unfold()).values('name')"
  [g]
  (traverse g (with-side-effect :key "name")
            (V)
            (order)
              (by (__ (value-map) (select (__ (select :key))) (unfold)))
            (values :name)))