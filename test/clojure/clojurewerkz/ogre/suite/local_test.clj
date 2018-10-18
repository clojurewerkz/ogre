(ns clojurewerkz.ogre.suite.local-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_V_localXoutE_countX
  "g.V().local(outE().count())"
  [g]
  (traverse g (V)
              (local (__ (outE) (count)))))

(defn get_g_VX4X_localXbothE_limitX2XX_otherV_name
  "g.V(v4Id).local(bothE().limit(2)).otherV().values('name')"
  [g v4Id]
  (traverse g (V v4Id)
              (local (__ (bothE) (limit 2)))
              (otherV)
              (values :name)))

(defn get_g_V_localXpropertiesXlocationX_order_byXvalueX_limitX2XX_value
  "g.V().local(properties('location').order().by(T.value, Order.incr).range(0, 2)).value()"
  [g]
  (traverse g (V)
              (local (__ (properties :location) (order) (by (T/value) (sort :incr)) (range 0 2)))
              (value)))

(defn get_g_V_hasXlabel_personX_asXaX_localXoutXcreatedX_asXbXX_selectXa_bX_byXnameX_byXidX
  "g.V().has(T.label, 'person').as('a').local(out('created').as('b')).select('a', 'b').by('name').by(T.id)"
  [g]
  (traverse g (V)
              (has T/label :person) (as :a)
              (local (__ (out :created) (as :b)))
              (select :a :b)
                (by :name)
                (by T/id)))

(defn get_g_VX1X_localXoutEXknowsX_limitX1XX_inV_name
  "g.V(v1Id).local(outE('knows').limit(1)).inV().values('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (local (__ (outE :knows) (limit 1)))
              (inV)
              (values :name)))

(defn get_g_V_localXbothEXcreatedX_limitX1XX_otherV_name
  "g.V().local(bothE('created').limit(1)).otherV().values('name')"
  [g]
  (traverse g (V)
              (local (__ (bothE :created) (limit 1)))
              (otherV)
              (values :name)))

(defn get_g_VX4X_localXbothEX1_createdX_limitX1XX
  "g.V(v4Id).local(bothE('created').limit(1))"
  [g v4Id]
  (traverse g (V v4Id)
              (local (__ (bothE :created) (limit 1)))))

(defn get_g_VX4X_localXbothEXknows_createdX_limitX1XX
  "g.V(v4Id).local(bothE('knows', 'created').limit(1))"
  [g v4Id]
  (traverse g (V v4Id)
              (local (__ (bothE :knows :created) (limit 1)))))

(defn get_g_VX4X_localXbothE_limitX1XX_otherV_name
  "g.V(v4Id).local(bothE().limit(1)).otherV().values('name')"
  [g v4Id]
  (traverse g (V v4Id)
              (local (__ (bothE) (limit 1)))
              (otherV)
              (values :name)))

(defn get_g_V_localXinEXknowsX_limitX2XX_outV_name
  "g.V().local(inE('knows').limit(2)).outV().values('name')"
  [g]
  (traverse g (V)
              (local (__ (inE :knows) (limit 2)))
              (outV)
              (values :name)))

(defn get_g_V_localXmatchXproject__created_person__person_name_nameX_selectXname_projectX_by_byXnameX
  "g.V().local(__.match(as('project').in('created').as('person'),
                        as('person').values('name').as('name')))
                        .<String>select('name', 'project').by().by('name');"
  [g]
  (traverse g (V)
              (local (__ (match (__ (as :project) (in :created) (as :person))
                                (__ (as :person) (values :name) (as :name)))))
              (select :name :project)
                (by) (by :name)))

