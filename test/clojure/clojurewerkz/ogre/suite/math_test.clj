(ns clojurewerkz.ogre.suite.math-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Operator)))

(defn get_g_V_asXaX_outXknowsX_asXbX_mathXa_plus_bX_byXageX
  "g.V().as('a').out('knows').as('b').math('a + b').by('age')"
  [g]
  (traverse g (V) (as :a)
              (out :knows) (as :b)
              (math "a + b")
                (by :age)))

(defn get_g_withSideEffectXx_100X_V_age_mathX__plus_xX
  "g.withSideEffect('x', 100).V().values('age').math('_ + x')"
  [g]
  (traverse g (with-side-effect "x" 100)
              (V)
              (values :age)
              (math "_ + x")))


(defn get_g_V_asXaX_outXcreatedX_asXbX_mathXb_plus_aX_byXinXcreatedX_countX_byXageX
  "g.V().as('a').out('created').as('b').math('b + a').by(in('created').count()).by('age')"
  [g]
  (traverse g (V) (as :a)
              (out :created) (as :b)
              (math "b + a")
                (by (__ (in :created) (count)))
                (by :age)))

(defn get_g_withSackX1X_injectX1X_repeatXsackXsumX_byXconstantX1XXX_timesX5X_emit_mathXsin__X_byXsackX
  "g.withSack(1).inject(1).repeat(__.sack(sum).by(__.constant(1))).times(10).emit().math('sin _').by(sack())"
  [g]
  (traverse g (with-sack 1)
              (inject 1)
              (repeat (__ (sack (operator :sum))
                            (by (__ (constant 1)))))
                (times 10)
                (emit)
              (math "sin _")
                (by (__ (sack)))))


(defn get_g_V_projectXa_b_cX_byXbothE_weight_sumX_byXbothE_countX_byXnameX_order_byXmathXa_div_bX_decrX_selectXcX
  "g.V().project('a', 'b', 'c').by(bothE().values('weight').sum()).by(bothE().count()).by('name').order().by(math('a / b'), decr).select('c')"
  [g]
  (traverse g (V)
              (project :a :b :c)
                (by (__ (bothE) (values :weight) (sum)))
                (by (__ (bothE) (count)))
                (by :name)
              (order)
                (by (__ (math "a /b")) (sort :decr))
              (select :c)))
