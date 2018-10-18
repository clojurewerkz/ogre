(ns clojurewerkz.ogre.suite.repeat-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_V_repeatXoutX_timesX2X
  "g.V().repeat(out()).times(2)"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 2)))

(defn get_g_V_repeatXoutX_timesX2X_emit_path
  "g.V().repeat(out()).times(2).emit().path()"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 2)
                (emit)
              (path)))

(defn get_g_V_repeatXoutX_timesX2X_emit
  "g.V().repeat(out()).times(2).emit()"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 2)
                (emit)))

(defn get_g_V_repeatXoutX_timesX2X_repeatXinX_timesX2X_name
  "g.V().repeat(out()).times(2).repeat(in()).times(2).values('name')"
  [g]
  (traverse g (V)
              (repeat (__ (out)))
                (times 2)
              (repeat (__ (in)))
                (times 2)
              (values :name)))

(defn get_g_VX1X_timesX2X_repeatXoutX_name
  "g.V(v1Id).times(2).repeat(out()).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (times 2)
              (repeat (__ (out)))
              (values :name)))

(defn get_g_V_emit_repeatXoutX_timesX2X_path
  "g.V().emit().repeat(out()).times(2).path()"
  [g]
  (traverse g (V)
              (emit)
              (repeat (__ (out)))
                (times 2)
              (path)))

(defn get_g_V_emit_timesX2X_repeatXoutX_path
  "g.V().emit().times(2).repeat(out()).path()"
  [g]
  (traverse g (V)
              (emit)
              (times 2)
              (repeat (__ (out)))
              (path)))

(defn get_g_V_repeatXgroupCountXmX_byXnameX_outX_timesX2X_capXmX
  "g.V().repeat(groupCount('m').by('name').out()).times(2).cap('m')"
  [g]
  (traverse g (V)
              (repeat (__ (group-count :m) (by :name) (out)))
                (times 2)
              (cap :m)))

(defn get_g_V_repeatXbothX_timesX10X_asXaX_out_asXbX_selectXa_bX
  "g.V().repeat(both()).times(10).as('a').out().as('b').select('a', 'b')"
  [g]
  (traverse g (V)
              (repeat (__ (both)))
                (times 10) (as :a)
              (out) (as :b)
              (select :a :b)))

(defn get_g_VX1X_repeatXoutX_untilXoutE_count_isX0XX_name
  "g.V(v1Id).repeat(out()).until(outE().count().is(0)).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (repeat (__ (out)))
                (until (__ (outE) (count) (is 0)))
              (values :name)))

(defn get_g_VX1X_emitXhasXlabel_personXX_repeatXoutX_name
  "g.V(v1Id).emit(has(T.label, 'person')).repeat(out()).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (emit (__ (has T/label :person)))
              (repeat (__ (out)))
              (values :name)))

(defn get_g_V_repeatXbothX_untilXname_eq_marko_or_loops_gt_1X_groupCount_byXnameX
  "g.V().repeat(both()).until(t -> t.get().value('name').equals('lop') || t.loops() > 1).groupCount().by('name')"
  [g]
  (traverse g (V)
              (repeat (__ (both)))
                (until (fn [^Traverser t] (clojure.core/or (= (.value ^Vertex (.get t) "name") "lop") (> (.loops t) 1))))
              (group-count)
                (by :name)))

(defn get_g_VX1X_repeatXgroupCountXmX_byXloopsX_outX_timesX3X_capXmX
  "g.V(v1Id).repeat(groupCount('m').by(loops()).out()).times(3).cap('m')"
  [g v1Id]
  (traverse g (V v1Id)
              (repeat (__ (group-count :m) (by (__ (loops))) (out)))
                (times 3)
              (cap :m)))

(defn get_g_V_hasXname_markoX_repeatXoutE_inV_simplePathX_untilXhasXname_rippleXX_path_byXnameX_byXlabelX
  "g.V().has('name', 'marko').repeat(outE().inV().simplePath()).until(has('name', 'ripple')).path().by('name').by(T.label)"
  [g]
  (traverse g (V)
              (has :name "marko")
              (repeat (__ (outE) (inV) (simple-path)))
                (until (__ (has :name "ripple")))
              (path)
                (by :name)
                (by T/label)))

(defn get_g_V_hasXloop_name_loopX_repeatXinX_timesX5X_path_by_name
  "g.V().has('loops','name','loop').repeat(__.in()).times(5).path().by('name')"
  [g]
  (traverse g (V)
              (has "loops" :name "loop")
              (repeat (__ (in)))
                (times 5)
              (path)
                (by :name)))