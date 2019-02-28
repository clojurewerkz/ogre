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

(defn get_g_VX3X_repeatXbothX_createdXX_untilXloops_is_40XXemit_repeatXin_knowsXX_emit_loopsXisX1Xdedup_values
  "g.V(v3Id).repeat(__.both('created')).until(loops().is(40)).emit(__.repeat(__.in('knows')).emit(loops().is(1))).dedup().values('name')"
  [g v3Id]
  (traverse g
            (V v3Id)
            (repeat (__ (both :created)))
              (until (__ (loops) (is 40)))
              (emit (__ (repeat (__ (in :knows))) (emit (__ (loops) (is 1)))))
            (dedup)
            (values :name)))

(defn get_g_VX1X_repeatXrepeatXunionXout_uses_out_traversesXX_whereXloops_isX0X_timesX1X_timeX2X_name
  "g.V(v1Id).repeat(__.repeat(__.union(out('uses'), out('traverses')).where(__.loops().is(0))).times(1)).times(2).values('name')"
  [g v1Id]
  (traverse g
            (V v1Id)
            (repeat (__ (repeat (__ (union (__ (out :uses)) (__ (out :traverses)))
                                    (where (__ (loops) (is 0)))))
                          (times 1)))
              (times 2)
            (values :name)))

(defn get_g_V_repeatXa_outXknows_repeatXb_outXcreatedX_filterXloops_isX0XX_emit_lang
  "g.V().repeat('a', out('knows').repeat('b', out('created').filter(loops('a').is(0))).emit()).emit().values('lang')"
  [g]
  (traverse g
            (V)
            (repeat :a, (__ (out :knows) (repeat :b (__ (out :created) (filter (__ (loops :a) (is 0))))) (emit)))
              (emit)
            (values :lang)))

(defn get_g_V_emit_repeatXa_outXknows_filterXloops_isX0XX_lang
  "g.V().emit().repeat('a', out('knows').filter(loops('a').is(0))).values('lang')"
  [g]
  (traverse g
            (V)
            (emit)
            (repeat :a (__ (out :knows) (filter (__ (loops :a) (is 0)))))
            (values :lang)))

(defn get_g_VX6X_repeatXa_bothXcreatedX_simplePathX_emitXrepeatXb_bothXknowsXX_untilXloopsXbX_asXb_whereXloopsXaX_asXbX_hasXname_vadasXX_dedup_name
  "g.V(v6Id).repeat('a', both('created').simplePath()).
               emit(__.repeat('b', __.both('knows')).
                         until(loops('b').as('b').
                               where(loops('a').as('b'))).
                       has('name', 'vadas')).
             dedup().values('name')"
  [g v6Id]
  (traverse g
            (V v6Id)
            (repeat :a (__ (both :created) (simple-path)))
              (emit (__ (repeat :b (__ (both :knows)))
                          (until (__ (loops :b) (as :b) (where (__ (loops :a) (as :b)))))
                        (has :name "vadas")))
            (dedup)
            (values :name)))

(defn get_g_V_repeatXout_repeatXoutX_timesX1XX_timesX1X_limitX1X_path_by_name
  "g.V().repeat(out().repeat(out()).times(1)).times(1).limit(1).path().by('name')"
  [g]
  (traverse g
            (V)
            (repeat (__ (out) (repeat (__ (out))) (times 1)))
              (times 1)
            (limit 1)
            (path)
              (by :name)))

(defn get_g_V_repeatXoutXknowsXX_untilXrepeatXoutXcreatedXX_emitXhasXname_lopXXX_path_byXnameX
  "g.V().repeat(out('knows')).until(__.repeat(out('created')).emit(__.has('name', 'lop'))).path().by('name')"
  [g]
  (traverse g
            (V)
            (repeat (__ (out :knows)))
              (until (__ (repeat (__ (out :created)))
                           (emit (__ (has :name "lop")))))
            (path)
            (by :name)))

(defn get_g_V_repeatXrepeatXout_createdXX_untilXhasXname_rippleXXXemit_lang
  "g.V().repeat(__.repeat(out('created')).until(__.has('name', 'ripple'))).emit().values('lang')"
  [g]
  (traverse g
            (V)
            (repeat (__ (repeat (__ (out :created)))
                          (until (__ (has :name "ripple")))))
              (emit)
            (values :lang)))

(defn get_g_V_untilXconstantXtrueXX_repeatXrepeatXout_createdXX_untilXhasXname_rippleXXXemit_lang
  "g.V().until(__.constant(true)).repeat(__.repeat(out('created')).until(__.has('name', 'ripple'))).emit().values('lang')"
  [g]
  (traverse g
            (V)
            (until (__ (constant true)))
            (repeat (__ (repeat (__ (out :created)))
                          (until (__ (has :name "ripple")))))
              (emit)
            (values :lang)))

