(ns clojurewerkz.ogre.suite.branch-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser)))

(defn get_g_V_branchXlabel_eq_person__a_bX_optionXa__ageX_optionXb__langX_optionXb__nameX
  "g.V().branch(v -> v.get().label().equals('person') ? 'a' : 'b')
                    .option('a', values('age'))
                    .option('b', values('lang'))
                    .option('b', values('name'))"
  [g]
  (q/traverse g (q/V)
                (q/branch (fn [^Vertex v] (if (.equals ^String (.label ^Vertex (.get ^Traverser v)) "person") "a" "b")))
                (q/option "a" (q/__ (values :age)))
                (q/option "b" (q/__ (values :lang)))
                (q/option "b" (q/__ (values :name)))))

(defn get_g_V_branchXlabelX_optionXperson__ageX_optionXsoftware__langX_optionXsoftware__nameX
  "g.V().branch(label().is('person').count())
                    .option(1L, values('age'))
                    .option(0L, values('lang'))
                    .option(0L, values('name'))"
  [g]
  (q/traverse g (q/V)
                (q/branch (q/__ (q/label) (q/is :person) (q/count)))
                (q/option (long 1) (q/__ (values :age)))
                (q/option (long 0) (q/__ (values :lang)))
                (q/option (long 0) (q/__ (values :name)))))
