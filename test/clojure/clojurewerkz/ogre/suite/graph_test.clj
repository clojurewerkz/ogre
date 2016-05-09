(ns clojurewerkz.ogre.suite.graph-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traversal)))

(defn get_g_V_hasXname_GarciaX_inXsungByX_asXsongX_V_hasXname_Willie_DixonX_inXwrittenByX_whereXeqXsongXX_name
  "g.V().has('artist', 'name', 'Garcia').in('sungBy').as('song')
                    .V().has('artist', 'name', 'Willie_Dixon').in('writtenBy').where(P.eq('song')).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/has :artist "name" "Garcia")
                (q/in :sungBy) (q/as :song)
                (q/midV)
                (q/has :artist "name" "Willie_Dixon")
                (q/in :writtenBy)
                (q/where (P/eq "song"))
                (q/values :name)))

(defn get_g_V_hasLabelXpersonX_asXpX_VXsoftwareX_addInEXuses_pX
  "g.V().hasLabel('person').as('p').V(software).addE('uses').from('p')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person) (q/as :p)
                (q/midV ^List (.toList ^Traversal (q/traverse g (q/V) (q/has-label :software))))
                (q/addE :uses)
                (q/from :p)))

(defn get_g_VX1X_V_valuesXnameX
  "g.V(v1Id).V().values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/midV)
                (q/values :name)))

(defn get_g_V_outXknowsX_V_name
  "g.V().out('knows').V().values('name')"
  [g]
  (q/traverse g (q/V)
                (q/out :knows)
                (q/midV)
                (q/values :name)))
