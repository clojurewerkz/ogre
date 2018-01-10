(ns clojurewerkz.ogre.suite.math-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Operator Order)))

(defn get_g_V_asXaX_outXknowsX_asXbX_mathXa_plus_bX_byXageX
  "g.V().as('a').out('knows').as('b').math('a + b').by('age')"
  [g]
  (q/traverse g
              (q/V) (q/as :a)
              (q/out :knows) (q/as :b)
              (q/math "a + b")
                (q/by :age)))

(defn get_g_withSideEffectXx_100X_V_age_mathX__plus_xX
  "g.withSideEffect('x', 100).V().values('age').math('_ + x')"
  [g]
  (q/traverse g
              (q/with-side-effect "x" 100)
              (q/V)
              (q/values :age)
              (q/math "_ + x")))


(defn get_g_V_asXaX_outXcreatedX_asXbX_mathXb_plus_aX_byXinXcreatedX_countX_byXageX
  "g.V().as('a').out('created').as('b').math('b + a').by(in('created').count()).by('age')"
  [g]
  (q/traverse g
              (q/V) (q/as :a)
              (q/out :created) (q/as :b)
              (q/math "b + a")
                (q/by (q/__ (q/in :created) (q/count)))
                (q/by :age)))

(defn get_g_withSackX1X_injectX1X_repeatXsackXsumX_byXconstantX1XXX_timesX5X_emit_mathXsin__X_byXsackX
  "g.withSack(1).inject(1).repeat(__.sack(sum).by(__.constant(1))).times(10).emit().math('sin _').by(sack())"
  [g]
  (q/traverse g
              (q/with-sack 1)
              (q/injects 1)
              (q/repeat (q/__ (q/sack Operator/sum)
                                (q/by (q/__ (q/constant 1)))))
                (q/times 10)
                (q/emit)
              (q/math "sin _")
                (q/by (q/__ (q/sack)))))


(defn get_g_V_projectXa_b_cX_byXbothE_weight_sumX_byXbothE_countX_byXnameX_order_byXmathXa_div_bX_decrX_selectXcX
  "g.V().project('a', 'b', 'c').by(bothE().values('weight').sum()).by(bothE().count()).by('name').order().by(math('a / b'), decr).select('c')"
  [g]
  (q/traverse g
              (q/V)
              (q/project :a :b :c)
                (q/by (q/__ (q/bothE) (q/values :weight) (q/sum)))
                (q/by (q/__ (q/bothE) (q/count)))
                (q/by :name)
              (q/order)
                (q/by (q/__ (q/math "a /b")) (Order/decr))
              (q/select :c)))
