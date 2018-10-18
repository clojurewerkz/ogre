(ns clojurewerkz.ogre.suite.union-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_V_unionXout__inX_name
  "g.V().union(out(), in()).values('name')"
  [g]
  (traverse g (V)
              (union (__ (out)) (__ (in)))
              (values :name)))

(defn get_g_VX1X_unionXrepeatXoutX_timesX2X__outX_name
  "g.V(v1Id).union(repeat(out()).times(2), out()).values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (union (__ (repeat (__ (out))) (times 2)) (__ (out)))
              (values :name)))

(defn get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX
  "g.V().choose(label().is('person'), union(out().values('lang'), out().values('name')), in().label())"
  [g]
  (traverse g (V)
              (choose (__ (label) (is :person))
                      (__ (union (__ (out) (values :lang))
                                 (__ (out) (values :name))))
                      (__ (in) (label)))))

(defn get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX_groupCount
  "g.V().choose(label().is('person'), union(out().values('lang'), out().values('name')), in().label()).groupCount()"
  [g]
  (traverse g (V)
              (choose (__ (label) (is :person))
                      (__ (union (__ (out) (values :lang))
                                 (__ (out) (values :name))))
                      (__ (in) (label)))
              (group-count)))

(defn get_g_V_unionXrepeatXunionXoutXcreatedX__inXcreatedXX_timesX2X__repeatXunionXinXcreatedX__outXcreatedXX_timesX2XX_label_groupCount
  "g.V().union(repeat(union(
                       out('created'),
                       in('created'))).times(2),
               repeat(union(
                       in('created'),
                       out('created'))).times(2)).label().groupCount()"
  [g]
  (traverse g (V)
              (union (__ (repeat (__ (union (__ (out :created)) (__ (in :created))))) (times 2))
                     (__ (repeat (__ (union (__ (in :created)) (__ (out :created))))) (times 2)))
              (label)
              (group-count)))

(defn get_g_VX1_2X_unionXoutE_count__inE_count__outE_weight_sumX
  "g.V(v1Id, v2Id).union(outE().count(), inE().count(), outE().values('weight').sum())"
  [g v1Id v2Id]
  (traverse g (V v1Id v2Id)
              (union (__ (outE) (count)) (__ (inE) (count)) (__ (outE) (values :weight) (sum)))))

(defn get_g_VX1_2X_localXunionXoutE_count__inE_count__outE_weight_sumXX
  "g.V(v1Id, v2Id).local(union(outE().count(), inE().count(), outE().values('weight').sum()))"
  [g v1Id v2Id]
  (traverse g (V v1Id v2Id)
              (local (__ (union (__ (outE) (count)) (__ (inE) (count)) (__ (outE) (values :weight) (sum)))))))

(defn get_g_VX1_2X_localXunionXcountXX
  "g.V(v1Id, v2Id).local(union(count()))"
  [g v1Id v2Id]
  (traverse g (V v1Id v2Id) (local (__ (union (__ (count)))))))
