(ns clojurewerkz.ogre.suite.group-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_repeatXbothXfollowedByXX_timesX2X_group_byXsongTypeX_byXcountX
  "g.V().repeat(both('followedBy')).times(2).group().by('songType').by(count())"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both :followedBy)))
                (q/times 2)
                (q/group)
                (q/by :songType)
                (q/by (q/__ (q/count)))))

(defn get_g_V_repeatXbothXfollowedByXX_timesX2X_groupXaX_byXsongTypeX_byXcountX_capXaX
  "g.V().repeat(both('followedBy')).times(2).group('a').by('songType').by(count()).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both :followedBy)))
                (q/times 2)
                (q/group :a)
                (q/by :songType)
                (q/by (q/__ (q/count)))
                (q/cap :a)))

(defn get_g_V_group_byXname_substring_1X_byXconstantX1XX
  "g.V().group().by(v -> v.value('name').substring(0, 1)).by(constant(1l))"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by (fn [^Vertex v] (.substring ^String (.value v "name") 0 1)) :fn)
                (q/by (q/__ (q/constant (long 1))))))

(defn get_g_V_groupXaX_byXname_substring_1X_byXconstantX1XX_capXaX
  "g.V().group('a').by(v -> v.value('name').substring(0, 1)).by(constant(1l)).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/group :a)
                (q/by (fn [^Vertex v] (.substring ^String (.value v "name") 0 1)) :fn)
                (q/by (q/__ (q/constant (long 1))))
                (q/cap :a)))

(defn get_g_V_out_group_byXlabelX_selectXpersonX_unfold_outXcreatedX_name_limitX2X
  "g.V().out().group().by(T.label).select('person').unfold().out('created').values('name').limit(2)"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/group)
                (q/by (T/label))
                (q/select :person)
                (q/unfold)
                (q/out :created)
                (q/values :name)
                (q/limit 2)))

(defn get_g_V_hasLabelXsongX_group_byXnameX_byXproperties_groupCount_byXlabelXX
  "g.V().hasLabel('song').group().by('name').by(__.properties().groupCount().by(T.label))"
  [g]
  (q/traverse g (q/V)
                (q/has-label :song)
                (q/group)
                (q/by :name)
                (q/by (q/__ (q/properties) (q/group-count) (q/by (T/label))))))

(defn get_g_V_hasLabelXsongX_groupXaX_byXnameX_byXproperties_groupCount_byXlabelXX_out_capXaX
  "g.V().hasLabel('song').group('a').by('name').by(__.properties().groupCount().by(T.label)).out().cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :song)
                (q/group :a)
                (q/by :name)
                (q/by (q/__ (q/properties) (q/group-count) (q/by (T/label))))
                (q/out)
                (q/cap :a)))

(defn get_g_V_repeatXunionXoutXknowsX_groupXaX_byXageX__outXcreatedX_groupXbX_byXnameX_byXcountXX_groupXaX_byXnameXX_timesX2X_capXa_bX
  "g.V().repeat(__.union(__.out('knows').group('a').by('age'),
                         __.out('created').group('b').by('name').by(count()))
                  .group('a').by('name')).times(2).cap('a', 'b')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/union (q/__ (q/out :knows) (q/group :a) (q/by :age))
                                         (q/__ (q/out :created) (q/group :b) (q/by :name) (q/by (q/__ (q/count)))))
                                (q/group :a) (q/by :name)))
                (q/times 2)
                (q/cap :a :b)))

(defn get_g_V_group_byXnameX
  "g.V().group().by('name')"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by :name)))

(defn get_g_V_group_byXnameX_by
  "g.V().group().by('name').by()"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by :name)
                (q/by)))

(defn get_g_V_groupXaX_byXnameX_capXaX
  "g.V().group('a').by('name').cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/group :a)
                (q/by :name)
                (q/by)
                (q/cap :a)))

(defn get_g_V_hasXlangX_groupXaX_byXlangX_byXnameX_out_capXaX
  "g.V().has('lang').group('a').by('lang').by('name').out().cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/has :lang)
                (q/group :a)
                (q/by :lang)
                (q/by :name)
                (q/out)
                (q/cap :a)))

(defn get_g_V_hasXlangX_group_byXlangX_byXcountX
  "g.V().has('lang').group().by('lang').by(count())"
  [g]
  (q/traverse g (q/V)
                (q/has :lang)
                (q/group)
                (q/by :lang)
                (q/by (q/__ (q/count)))))

(defn get_g_V_repeatXout_groupXaX_byXnameX_byXcountX_timesX2X_capXaX
  "g.V().repeat(out().group('a').by('name').by(count())).times(2).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/out) (q/group :a) (q/by :name) (q/by (q/__ (q/count)))))
                (q/times 2)
                (q/cap :a)))

(defn get_g_V_group_byXoutE_countX_byXnameX
  "g.V().group().by(outE().count()).by('name')"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by (q/__ (q/outE) (q/count)))
                (q/by :name)))

(defn get_g_V_groupXaX_byXlabelX_byXoutE_weight_sumX_capXaX
  "g.V().group('a').by(T.label).by(outE().values('weight').sum()).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/group :a)
                (q/by (T/label))
                (q/by (q/__ (q/outE) (q/values :weight) (q/sum)))
                (q/cap :a)))

(defn get_g_V_group_byXbothE_countX_byXgroup_byXlabelXX
  "g.V().<Long, Map<String, List<Vertex>>>group().by(__.bothE().count()).by(__.group().by(T.label))"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by (q/__ (q/bothE) (q/count)))
                (q/by (q/__ (q/group) (q/by (T/label))))))

(defn get_g_V_outXfollowedByX_group_byXsongTypeX_byXbothE_group_byXlabelX_byXweight_sumXX
  "g.V().out('followedBy').<String, Map<String, Number>>group().by('songType').by(__.bothE().group().by(T.label).by(__.values('weight').sum()))"
  [g]
  (q/traverse g (q/V)
                (q/out :followedBy)
                (q/group)
                (q/by :songType)
                (q/by (q/__ (q/bothE) (q/group) (q/by (T/label)) (q/by (q/__ (q/values :weight) (q/sum)))))))
