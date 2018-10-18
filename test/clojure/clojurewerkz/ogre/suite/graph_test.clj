(ns clojurewerkz.ogre.suite.graph-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXname_GarciaX_inXsungByX_asXsongX_V_hasXname_Willie_DixonX_inXwrittenByX_whereXeqXsongXX_name
  "g.V().has('artist', 'name', 'Garcia').in('sungBy').as('song')
                    .V().has('artist', 'name', 'Willie_Dixon').in('writtenBy').where(P.eq('song')).values('name')"
  [g]
  (traverse g (V)
              (has :artist "name" "Garcia")
              (in :sungBy) (as :song)
              (V)
              (has :artist "name" "Willie_Dixon")
              (in :writtenBy)
              (where (P/eq "song"))
              (values :name)))

(defn get_g_V_hasLabelXpersonX_asXpX_VXsoftwareX_addInEXuses_pX
  "g.V().hasLabel('person').as('p').V(software).addE('uses').from('p')"
  [g]
  (traverse g (V)
              (has-label :person) (as :p)
              (V ^List  (traverse g (V) (has-label :software) (into-list!)))
              (add-E :uses)
                (from :p)))

(defn get_g_VX1X_V_valuesXnameX
  "g.V(v1Id).V().values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (V)
              (values :name)))

(defn get_g_V_outXknowsX_V_name
  "g.V().out('knows').V().values('name')"
  [g]
  (traverse g (V)
              (out :knows)
              (V)
              (values :name)))
