(ns clojurewerkz.ogre.suite.loops-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX3XX_hasXname_peterX_path_byXnameX
  "g.V(v1Id).repeat(__.both().simplePath()).until(__.has('name', 'peter').or().loops().is(3)).has('name', 'peter').path().by('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (repeat (__ (both) (simple-path)))
                (until (__ (has :name "peter") (or) (loops) (is 3)))
              (has :name "peter")
              (path)
                (by :name)))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_or_loops_isX2XX_hasXname_peterX_path_byXnameX
  "g.V(v1Id).repeat(__.both().simplePath()).until(__.has('name', 'peter').or().loops().is(2)).has('name', 'peter').path().by('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (repeat (__ (both) (simple-path)))
                (until (__ (has :name "peter") (or) (loops) (is 2)))
              (has :name "peter")
              (path)
                (by :name)))

(defn get_g_VX1X_repeatXboth_simplePathX_untilXhasXname_peterX_and_loops_isX3XX_hasXname_peterX_path_byXnameX
  "g.V(v1Id).repeat(__.both().simplePath()).until(__.has('name', 'peter').and().loops().is(3)).has('name', 'peter').path().by('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (repeat (__ (both) (simple-path)))
                (until (__ (has :name "peter") (and) (loops) (is 3)))
              (has :name "peter")
              (path)
                (by :name)))

(defn get_g_V_emitXhasXname_markoX_or_loops_isX2XX_repeatXoutX_valuesXnameX
  "g.V().emit(__.has('name', 'marko').or().loops().is(2)).repeat(__.out()).values('name')"
  [g]
  (traverse g (V)
              (emit (__ (has :name "marko")
                        (or)
                        (loops)
                        (is 2)))
              (repeat (__ (out)))
              (values :name)))

