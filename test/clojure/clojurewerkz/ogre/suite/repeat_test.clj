(ns clojurewerkz.ogre.suite.repeat-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser)))

(defn get_g_V_repeatXoutX_timesX2X
  "g.V().repeat(out()).times(2)"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 2)))

(defn get_g_V_repeatXoutX_timesX2X_emit_path
  "g.V().repeat(out()).times(2).emit().path()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 2)
                (q/emit)
                (q/path)))

(defn get_g_V_repeatXoutX_timesX2X_emit
  "g.V().repeat(out()).times(2).emit()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 2)
                (q/emit)))

(defn get_g_V_repeatXoutX_timesX2X_repeatXinX_timesX2X_name
  "g.V().repeat(out()).times(2).repeat(in()).times(2).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out)))
                (q/times 2)
                (q/repeat (q/__ (q/in)))
                (q/times 2)
                (q/values :name)))

(defn get_g_VX1X_timesX2X_repeatXoutX_name
  "g.V(v1Id).times(2).repeat(out()).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/times 2)
                (q/repeat (q/__ (q/out)))
                (q/values :name)))

(defn get_g_V_emit_repeatXoutX_timesX2X_path
  "g.V().emit().repeat(out()).times(2).path()"
  [g]
  (q/traverse g (q/V)
                (q/emit)
                (q/repeat (q/__ (q/out)))
                (q/times 2)
                (q/path)))

(defn get_g_V_emit_timesX2X_repeatXoutX_path
  "g.V().emit().times(2).repeat(out()).path()"
  [g]
  (q/traverse g (q/V)
                (q/emit)
                (q/times 2)
                (q/repeat (q/__ (q/out)))
                (q/path)))

(defn get_g_V_repeatXgroupCountXmX_byXnameX_outX_timesX2X_capXmX
  "g.V().repeat(groupCount('m').by('name').out()).times(2).cap('m')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/group-count :m) (q/by :name) (q/out)))
                (q/times 2)
                (q/cap :m)))

(defn get_g_V_repeatXbothX_timesX10X_asXaX_out_asXbX_selectXa_bX
  "g.V().repeat(both()).times(10).as('a').out().as('b').select('a', 'b')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both)))
                (q/times 10)
                (q/as :a)
                (q/out)
                (q/as :b)
                (q/select :a :b)))

(defn get_g_VX1X_repeatXoutX_untilXoutE_count_isX0XX_name
  "g.V(v1Id).repeat(out()).until(outE().count().is(0)).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/out)))
                (q/until (q/__ (q/outE) (q/count) (q/is 0)))
                (q/values :name)))

(defn get_g_VX1X_emitXhasXlabel_personXX_repeatXoutX_name
  "g.V(v1Id).emit(has(T.label, 'person')).repeat(out()).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/emit (q/__ (q/has T/label :person)))
                (q/repeat (q/__ (q/out)))
                (q/values :name)))

(defn get_g_V_repeatXbothX_untilXname_eq_marko_or_loops_gt_1X_groupCount_byXnameX
  "g.V().repeat(both()).until(t -> t.get().value('name').equals('lop') || t.loops() > 1).groupCount().by('name')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both)))
                  (q/until (fn [^Traverser t] (clojure.core/or (= (.value ^Vertex (.get t) "name") "lop") (> (.loops t) 1))))
                (q/group-count)
                  (q/by :name)))

(defn get_g_VX1X_repeatXgroupCountXmX_byXloopsX_outX_timesX3X_capXmX
  "g.V(v1Id).repeat(groupCount('m').by(loops()).out()).times(3).cap('m')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/repeat (q/__ (q/group-count :m) (q/by (q/__ (q/loops))) (q/out)))
                  (q/times 3)
                (q/cap :m)))

(defn get_g_V_hasXname_markoX_repeatXoutE_inV_simplePathX_untilXhasXname_rippleXX_path_byXnameX_byXlabelX
  "g.V().has('name', 'marko').repeat(outE().inV().simplePath()).until(has('name', 'ripple')).path().by('name').by(T.label)"
  [g]
  (q/traverse g (q/V)
                (q/has :name "marko")
                (q/repeat (q/__ (q/outE) (q/inV) (q/simple-path)))
                  (q/until (q/__ (q/has :name "ripple")))
                (q/path)
                  (q/by :name)
                  (q/by T/label)))

(defn get_g_V_hasXloop_name_loopX_repeatXinX_timesX5X_path_by_name
  "g.V().has('loops','name','loop').repeat(__.in()).times(5).path().by('name')"
  [g]
  (q/traverse g (q/V)
              (q/has "loops" :name "loop")
              (q/repeat (q/__ (q/in)))
              (q/times 5)
              (q/path)
              (q/by :name)))