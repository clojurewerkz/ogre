(ns clojurewerkz.ogre.suite.store-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_withSideEffectXa_setX_V_both_name_storeXaX_capXaX
  "g.withSideEffect('a', HashSetSupplier.instance()).V().both().values('name').store('a').cap('a')"
  [g]
  (q/traverse g (q/with-side-effect :a (org.apache.tinkerpop.gremlin.util.function.HashSetSupplier/instance))
                (q/V)
                (q/both)
                (q/values :name)
                (q/store :a)
                (q/cap :a)))

(defn get_g_V_storeXaX_byXoutEXcreatedX_countX_out_out_storeXaX_byXinEXcreatedX_weight_sumX_capXaX
  "g.V().store('a').by(outE('created').count()).out().out().store('a').by(inE('created').values('weight').sum()).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/store :a)
                (q/by (q/__ (q/outE :created) (q/count)))
                (q/out)
                (q/out)
                (q/store :a)
                (q/by (q/__ (q/inE :created) (q/values :weight) (q/sum)))
                (q/cap :a)))

(defn get_g_V_storeXaX_byXnameX_out_capXaX
  "g.V().store('a').by('name').out().cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/store :a)
                (q/by :name)
                (q/out)
                (q/cap :a)))

(defn get_g_VX1X_storeXaX_byXnameX_out_storeXaX_byXnameX_name_capXaX
  "g.V(v1Id).store('a').by('name').out().store('a').by('name').values('name').cap('a')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/store :a)
                (q/by :name)
                (q/out)
                (q/store :a)
                (q/by :name)
                (q/values :name)
                (q/cap :a)))



