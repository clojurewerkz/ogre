(ns clojurewerkz.ogre.suite.branch-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser)
           (org.apache.tinkerpop.gremlin.process.traversal.step TraversalOptionParent$Pick)))

(defn get_g_V_branchXlabel_eq_person__a_bX_optionXa__ageX_optionXb__langX_optionXb__nameX
  "g.V().branch(v -> v.get().label().equals('person') ? 'a' : 'b')
                    .option('a', values('age'))
                    .option('b', values('lang'))
                    .option('b', values('name'))"
  [g]
  (traverse g (V)
              (branch (fn [^Vertex v] (if (.equals ^String (.label ^Vertex (.get ^Traverser v)) "person") "a" "b")))
              (option "a" (__ (values :age)))
              (option "b" (__ (values :lang)))
              (option "b" (__ (values :name)))))

(defn get_g_V_branchXlabel_isXpersonX_countX_optionX1__ageX_optionX0__langX_optionX0__nameX
  "g.V().branch(label().is('person').count())
                    .option(1L, values('age'))
                    .option(0L, values('lang'))
                    .option(0L, values('name'))"
  [g]
  (traverse g (V)
              (branch (__ (label) (is :person) (count)))
              (option (long 1) (__ (values :age)))
              (option (long 0) (__ (values :lang)))
              (option (long 0) (__ (values :name)))))

(defn get_g_V_branchXlabel_isXpersonX_countX_optionX1__ageX_optionX0__langX_optionX0__nameX_optionXany__labelX
  "g.V().branch(label().is('person').count())
                    .option(1L, values('age'))
                    .option(0L, values('lang'))
                    .option(0L, values('name'))
                    .option(any, label())"
  [g]
  (traverse g (V)
              (branch (__ (label) (is :person) (count)))
              (option (long 1) (__ (values :age)))
              (option (long 0) (__ (values :lang)))
              (option (long 0) (__ (values :name)))
              (option (TraversalOptionParent$Pick/any) (__ (label)))))
