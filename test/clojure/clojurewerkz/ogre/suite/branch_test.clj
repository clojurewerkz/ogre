(ns clojurewerkz.ogre.suite.branch-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser P)
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

(defn get_g_V_branchXageX_optionXltX30X__youngX_optionXgtX30X__oldX_optionXnone__on_the_edgeX
  "g.V().hasLabel('person').
     branch(values('age')).
            option(lt(30), constant('young')).
            option(gt(30), constant('old')).
            option(none, constant('on the edge'))"
  [g]                                                                                                                                                                                                                                                            7
  (traverse g (V)
            (has-label :person)
            (branch (__ (values :age)))
            (option (P/lt 30) (__ (constant "young")))
            (option (P/gt 30) (__ (constant "old")))
            (option (TraversalOptionParent$Pick/none) (__ (constant "on the edge")))))

(defn get_g_V_branchXidentityX_optionXhasLabelXsoftwareX__inXcreatedX_name_order_foldX_optionXhasXname_vadasX__ageX_optionXneqX123X__bothE_countX
  "g.V().branch(identity()).
                option(hasLabel('software'), __.in('created').values('name').order().fold()).
                option(has('name','vadas'), values('age')).
                option(neq(123), bothE().count())"
  [g]                                                                                                                                                                                                                                                            7
  (traverse g (V)
            (branch (__ (identity)))
            (option (__ (has-label :software)) (__ (in :created) (values :name) (order) (fold)))
            (option (__ (has :name "vadas")) (__ (values :age)))
            (option (P/neq 123) (__ (bothE) (count)))))
