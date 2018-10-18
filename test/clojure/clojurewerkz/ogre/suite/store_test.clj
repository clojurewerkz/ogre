(ns clojurewerkz.ogre.suite.store-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_withSideEffectXa_setX_V_both_name_storeXaX_capXaX
  "g.withSideEffect('a', HashSetSupplier.instance()).V().both().values('name').store('a').cap('a')"
  [g]
  (traverse g (with-side-effect :a (org.apache.tinkerpop.gremlin.util.function.HashSetSupplier/instance))
              (V)
              (both)
              (values :name)
              (store :a)
              (cap :a)))

(defn get_g_V_storeXaX_byXoutEXcreatedX_countX_out_out_storeXaX_byXinEXcreatedX_weight_sumX_capXaX
  "g.V().store('a').by(outE('created').count()).out().out().store('a').by(inE('created').values('weight').sum()).cap('a')"
  [g]
  (traverse g (V)
              (store :a)
                (by (__ (outE :created) (count)))
              (out)
              (out)
              (store :a)
                (by (__ (inE :created) (values :weight) (sum)))
              (cap :a)))

(defn get_g_V_storeXaX_byXnameX_out_capXaX
  "g.V().store('a').by('name').out().cap('a')"
  [g]
  (traverse g (V)
              (store :a)
                (by :name)
              (out)
              (cap :a)))

(defn get_g_VX1X_storeXaX_byXnameX_out_storeXaX_byXnameX_name_capXaX
  "g.V(v1Id).store('a').by('name').out().store('a').by('name').values('name').cap('a')"
  [g v1Id]
  (traverse g (V v1Id)
              (store :a)
                (by :name)
              (out)
              (store :a)
                (by :name)
              (values :name)
              (cap :a)))



