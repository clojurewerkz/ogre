(ns clojurewerkz.ogre.suite.aggregate-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_name_aggregateXxX_capXxX
  "g.V().values('name').aggregate('x').cap('x')"
  [g]
  (traverse g (V)
              (values :name)
              (aggregate :x)
              (cap :x)))

(defn get_g_V_out_aggregateXaX_path
  "g.V().out().aggregate('a').path()"
  [g]
  (traverse g (V)
              (out)
              (aggregate :a)
              (path)))

(defn get_g_V_aggregateXxX_byXnameX_capXxX
  "g.V().aggregate('x').by('name').cap('x')"
  [g]
  (traverse g (V)
              (aggregate :x)
                (by :name)
              (cap :x)))

(defn get_g_V_hasLabelXpersonX_aggregateXxX_byXageX_capXxX_asXyX_selectXyX
  "g.V().hasLabel('person').aggregate('x').by('age').cap('x').as('y').select('y')"
  [g]
  (traverse g (V)
              (has-label :person)
              (aggregate :x)
                (by :age)
              (cap :x)
              (as :y)
              (select :y)))

(defn get_g_V_name_aggregateXglobal_xX_capXxX
  "g.V().values('name').aggregate(global,'x').cap('x')"
  [g]
  (traverse g (V)
            (values :name)
            (aggregate (scope :global) :x)
            (cap :x)))

(defn get_g_V_aggregateXlocal_aX_byXnameX_out_capXaX
  "g.V().aggregate(Scope.local, \"a\").by(\"name\").out().cap(\"a\")"
  [g]
  (traverse g (V)
            (aggregate (scope :local) :a) (by :name)
            (out)
            (cap :a)))

(defn get_g_VX1X_aggregateXlocal_aX_byXnameX_out_aggregateXlocal_aX_byXnameX_name_capXaX
  "g.V(v1Id).aggregate(Scope.local, \"a\").by(\"name\").out().aggregate(Scope.local, \"a\").by(\"name\").values(\"name\").cap(\"a\")"
  [g v1Id]
  (traverse g (V v1Id)
            (aggregate (scope :local) :a) (by :name)
            (out)
            (aggregate (scope :local) :a) (by :name)
            (values :name)
            (cap :a)))

(defn get_g_withSideEffectXa_setX_V_both_name_aggregateXlocal_aX_capXaX
  "g.withSideEffect(\"a\", new HashSet()).V().both().values(\"name\").aggregate(Scope.local, \"a\").cap(\"a\")"
  [g]
  (traverse g (with-side-effect :a (new java.util.HashSet))
            (V)
            (both)
            (values :name)
            (aggregate (scope :local) :a)
            (cap :a)))

(defn get_g_V_aggregateXlocal_aX_byXoutEXcreatedX_countX_out_out_aggregateXlocal_aX_byXinEXcreatedX_weight_sumX_capXaX
  "g.V().aggregate(Scope.local, \"a\").by(outE(\"created\").count()).out().out().aggregate(Scope.local, \"a\").by(inE(\"created\").values(\"weight\").sum()).cap(\"a\")"
  [g]
  (traverse g (V)
            (aggregate (scope :local) :a) (by (__ (outE :created) (count)))
            (out)
            (out)
            (aggregate (scope :local) :a) (by (__ (inE :created) (values :weight) (sum)))
            (cap :a)))
