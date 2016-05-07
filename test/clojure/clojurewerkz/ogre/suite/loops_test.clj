(ns clojurewerkz.ogre.suite.loops-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX3XX_hasXname_peterX_path_byXnameX
  "g.V(v1Id).repeat(__.both().simplePath()).until(__.has('name', 'peter').or().loops().is(3)).has('name', 'peter').path().by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/both) (q/simple-path)))
                (q/until (q/__ (q/has :name "peter") (q/or) (q/loops) (q/is 3)))
                (q/has :name "peter")
                (q/path)
                (q/by :name)))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX2XX_hasXname_peterX_path_byXnameX
  "g.V(v1Id).repeat(__.both().simplePath()).until(__.has('name', 'peter').or().loops().is(2)).has('name', 'peter').path().by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/both) (q/simple-path)))
                (q/until (q/__ (q/has :name "peter") (q/or) (q/loops) (q/is 2)))
                (q/has :name "peter")
                (q/path)
                (q/by :name)))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_and_loops_isX3XX_hasXname_peterX_path_byXnameX
  "g.V(v1Id).repeat(__.both().simplePath()).until(__.has('name', 'peter').and().loops().is(3)).has('name', 'peter').path().by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/both) (q/simple-path)))
                (q/until (q/__ (q/has :name "peter") (q/and) (q/loops) (q/is 3)))
                (q/has :name "peter")
                (q/path)
                (q/by :name)))

(defn get_g_V_emitXhasXname_markoX_or_loops_isX2XX_repeatXoutX_valuesXnameX
  "g.V().emit(__.has('name', 'marko').or().loops().is(2)).repeat(__.out()).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/emit (q/__ (q/has :name "marko")
                              (q/or)
                              (q/loops)
                              (q/is 2)))
                (q/repeat (q/__ (q/out)))
                (q/values :name)))

