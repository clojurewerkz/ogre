(ns clojurewerkz.ogre.suite.union-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_unionXout__inX_name
  "g.V().union(out(), in()).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/union (q/__ (q/out)) (q/__ (q/in)))
                (q/values :name)))

(defn get_g_VX1X_unionXrepeatXoutX_timesX2X__outX_name
  "g.V(v1Id).union(repeat(out()).times(2), out()).values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/union (q/__ (q/repeat (q/__ (q/out))) (q/times 2)) (q/__ (q/out)))
                (q/values :name)))

(defn get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX
  "g.V().choose(label().is('person'), union(out().values('lang'), out().values('name')), in().label())"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/label) (q/is :person))
                            (q/__ (q/union (q/__ (q/out) (q/values :lang))
                                           (q/__ (q/out) (q/values :name))))
                            (q/__ (q/in) (q/label)))))

(defn get_g_V_chooseXlabel_is_person__unionX__out_lang__out_nameX__in_labelX_groupCount
  "g.V().choose(label().is('person'), union(out().values('lang'), out().values('name')), in().label()).groupCount()"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/label) (q/is :person))
                            (q/__ (q/union (q/__ (q/out) (q/values :lang))
                                           (q/__ (q/out) (q/values :name))))
                            (q/__ (q/in) (q/label)))
                (q/group-count)))

(defn get_g_V_unionXrepeatXunionXoutXcreatedX__inXcreatedXX_timesX2X__repeatXunionXinXcreatedX__outXcreatedXX_timesX2XX_label_groupCount
  "g.V().union(repeat(union(
                       out('created'),
                       in('created'))).times(2),
               repeat(union(
                       in('created'),
                       out('created'))).times(2)).label().groupCount()"
  [g]
  (q/traverse g (q/V)
                (q/union (q/__ (q/repeat (q/__ (q/union (q/__ (q/out :created)) (q/__ (q/in :created))))) (q/times 2))
                         (q/__ (q/repeat (q/__ (q/union (q/__ (q/in :created)) (q/__ (q/out :created))))) (q/times 2)))
                (q/label)
                (q/group-count)))

(defn get_g_VX1_2X_unionXoutE_count__inE_count__outE_weight_sumX
  "g.V(v1Id, v2Id).union(outE().count(), inE().count(), outE().values('weight').sum())"
  [g v1Id v2Id]
  (q/traverse g (q/V v1Id v2Id)
                (q/union (q/__ (q/outE) (q/count)) (q/__ (q/inE) (q/count)) (q/__ (q/outE) (q/values :weight) (q/sum)))))

(defn get_g_VX1_2X_localXunionXoutE_count__inE_count__outE_weight_sumXX
  "g.V(v1Id, v2Id).local(union(outE().count(), inE().count(), outE().values('weight').sum()))"
  [g v1Id v2Id]
  (q/traverse g (q/V v1Id v2Id)
                (q/local
                  (q/__ (q/union (q/__ (q/outE) (q/count)) (q/__ (q/inE) (q/count)) (q/__ (q/outE) (q/values :weight) (q/sum)))))))
