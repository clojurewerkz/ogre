(ns clojurewerkz.ogre.suite.group-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)))

(defn get_g_V_repeatXbothXfollowedByXX_timesX2X_group_byXsongTypeX_byXcountX
  "g.V().repeat(both('followedBy')).times(2).group().by('songType').by(count())"
  [g]
  (traverse g (V)
              (repeat (__ (both :followedBy)))
                (times 2)
              (group)
                (by :songType)
                (by (__ (count)))))

(defn get_g_V_repeatXbothXfollowedByXX_timesX2X_groupXaX_byXsongTypeX_byXcountX_capXaX
  "g.V().repeat(both('followedBy')).times(2).group('a').by('songType').by(count()).cap('a')"
  [g]
  (traverse g (V)
              (repeat (__ (both :followedBy)))
                (times 2)
              (group :a)
                (by :songType)
                (by (__ (count)))
              (cap :a)))

(defn get_g_V_group_byXname_substring_1X_byXconstantX1XX
  "g.V().group().by(v -> v.value('name').substring(0, 1)).by(constant(1l))"
  [g]
  (traverse g (V)
              (group)
                (by (fn [^Vertex v] (.substring ^String (.value v "name") 0 1)) :fn)
                (by (__ (constant (long 1))))))

(defn get_g_V_groupXaX_byXname_substring_1X_byXconstantX1XX_capXaX
  "g.V().group('a').by(v -> v.value('name').substring(0, 1)).by(constant(1l)).cap('a')"
  [g]
  (traverse g (V)
              (group :a)
                (by (fn [^Vertex v] (.substring ^String (.value v "name") 0 1)) :fn)
                (by (__ (constant (long 1))))
              (cap :a)))

(defn get_g_V_out_group_byXlabelX_selectXpersonX_unfold_outXcreatedX_name_limitX2X
  "g.V().out().group().by(T.label).select('person').unfold().out('created').values('name').limit(2)"
  [g]
  (traverse g (V)
              (out)
              (group)
                (by (T/label))
              (select :person)
              (unfold)
              (out :created)
              (values :name)
              (limit 2)))

(defn get_g_V_hasLabelXsongX_group_byXnameX_byXproperties_groupCount_byXlabelXX
  "g.V().hasLabel('song').group().by('name').by(__.properties().groupCount().by(T.label))"
  [g]
  (traverse g (V)
              (has-label :song)
              (group)
                (by :name)
                (by (__ (properties) (group-count) (by (T/label))))))

(defn get_g_V_hasLabelXsongX_groupXaX_byXnameX_byXproperties_groupCount_byXlabelXX_out_capXaX
  "g.V().hasLabel('song').group('a').by('name').by(__.properties().groupCount().by(T.label)).out().cap('a')"
  [g]
  (traverse g (V)
              (has-label :song)
              (group :a)
                (by :name)
                (by (__ (properties) (group-count) (by (T/label))))
              (out)
              (cap :a)))

(defn get_g_V_repeatXunionXoutXknowsX_groupXaX_byXageX__outXcreatedX_groupXbX_byXnameX_byXcountXX_groupXaX_byXnameXX_timesX2X_capXa_bX
  "g.V().repeat(__.union(__.out('knows').group('a').by('age'),
                         __.out('created').group('b').by('name').by(count()))
                  .group('a').by('name')).times(2).cap('a', 'b')"
  [g]
  (traverse g (V)
              (repeat (__ (union (__ (out :knows) (group :a) (by :age))
                                 (__ (out :created) (group :b) (by :name) (by (__ (count)))))
                          (group :a) (by :name)))
                (times 2)
              (cap :a :b)))

(defn get_g_V_group_byXnameX
  "g.V().group().by('name')"
  [g]
  (traverse g (V)
              (group)
                (by :name)))

(defn get_g_V_group_byXnameX_by
  "g.V().group().by('name').by()"
  [g]
  (traverse g (V)
              (group)
                (by :name)
                (by)))

(defn get_g_V_groupXaX_byXnameX_capXaX
  "g.V().group('a').by('name').cap('a')"
  [g]
  (traverse g (V)
              (group :a)
                (by :name)
                (by)
              (cap :a)))

(defn get_g_V_hasXlangX_groupXaX_byXlangX_byXnameX_out_capXaX
  "g.V().has('lang').group('a').by('lang').by('name').out().cap('a')"
  [g]
  (traverse g (V)
              (has :lang)
              (group :a)
                (by :lang)
                (by :name)
              (out)
              (cap :a)))

(defn get_g_V_hasXlangX_group_byXlangX_byXcountX
  "g.V().has('lang').group().by('lang').by(count())"
  [g]
  (traverse g (V)
              (has :lang)
              (group)
                (by :lang)
                (by (__ (count)))))

(defn get_g_V_repeatXout_groupXaX_byXnameX_byXcountX_timesX2X_capXaX
  "g.V().repeat(out().group('a').by('name').by(count())).times(2).cap('a')"
  [g]
  (traverse g (V)
              (repeat (__ (out) (group :a) (by :name) (by (__ (count)))))
                (times 2)
              (cap :a)))

(defn get_g_V_group_byXoutE_countX_byXnameX
  "g.V().group().by(outE().count()).by('name')"
  [g]
  (traverse g (V)
              (group)
                (by (__ (outE) (count)))
                (by :name)))

(defn get_g_V_groupXaX_byXlabelX_byXoutE_weight_sumX_capXaX
  "g.V().group('a').by(T.label).by(outE().values('weight').sum()).cap('a')"
  [g]
  (traverse g (V)
              (group :a)
                (by (T/label))
                (by (__ (outE) (values :weight) (sum)))
              (cap :a)))

(defn get_g_V_group_byXbothE_countX_byXgroup_byXlabelXX
  "g.V().<Long, Map<String, List<Vertex>>>group().by(__.bothE().count()).by(__.group().by(T.label))"
  [g]
  (traverse g (V)
              (group)
                (by (__ (bothE) (count)))
                (by (__ (group) (by (T/label))))))

(defn get_g_V_outXfollowedByX_group_byXsongTypeX_byXbothE_group_byXlabelX_byXweight_sumXX
  "g.V().out('followedBy').<String, Map<String, Number>>group().by('songType').by(__.bothE().group().by(T.label).by(__.values('weight').sum()))"
  [g]
  (traverse g (V)
              (out :followedBy)
              (group)
                (by :songType)
                (by (__ (bothE) (group) (by (T/label)) (by (__ (values :weight) (sum)))))))

(defn get_g_withSideEffectXa__marko_666_noone_blahX_V_groupXaX_byXnameX_byXoutE_label_foldX_capXaX
  "g.withSideEffect('a', m).V().group('a').by('name').by(outE().label().fold()).cap('a')"
  [g m]
  (traverse g (with-side-effect :a m)
              (V)
              (group :a)
                (by :name)
                (by (__ (outE) (label) (fold)))
              (cap :a)))

(defn get_g_V_group_byXlabelX_byXbothE_groupXaX_byXlabelX_byXweight_sumX_weight_sumX
  "g.V().group().by(T.label).by(bothE().group('a').by(T.label).by(values('weight').sum()).values('weight').sum())"
  [g]
  (traverse g (V)
              (group)
                (by (T/label))
                (by (__ (bothE) (group :a)
                                  (by (T/label))
                                  (by (__ (values :weight) (sum)))
                        (values :weight) (sum)))))

(defn get_g_V_groupXmX_byXnameX_byXinXknowsX_nameX_capXmX
  "g.V().group('m').by('name').by(__.in('knows').values('name')).cap('m')"
  [g]
  (traverse g (V)
              (group :m)
                (by :name)
                (by (__ (in :knows) (values :name)))
              (cap :m)))