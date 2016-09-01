(ns clojurewerkz.ogre.suite.local-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P Order)))

(defn get_g_V_localXoutE_countX
  "g.V().local(outE().count())"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/outE) (q/count)))))

(defn get_g_VX4X_localXbothE_limitX2XX_otherV_name
  "g.V(v4Id).local(bothE().limit(2)).otherV().values('name')"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/local (q/__ (q/bothE) (q/limit 2)))
                (q/otherV)
                (q/values :name)))

(defn get_g_V_localXpropertiesXlocationX_order_byXvalueX_limitX2XX_value
  "g.V().local(properties('location').order().by(T.value, Order.incr).range(0, 2)).value()"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/properties :location) (q/order) (q/by (T/value) (Order/incr)) (q/range 0 2)))
                (q/value)))

(defn get_g_V_hasXlabel_personX_asXaX_localXoutXcreatedX_asXbXX_selectXa_bX_byXnameX_byXidX
  "g.V().has(T.label, 'person').as('a').local(out('created').as('b')).select('a', 'b').by('name').by(T.id)"
  [g]
  (q/traverse g (q/V)
                (q/has T/label :person)
                (q/as :a)
                (q/local (q/__ (q/out :created) (q/as :b)))
                (q/select :a :b)
                (q/by :name)
                (q/by T/id)))

(defn get_g_VX1X_localXoutEXknowsX_limitX1XX_inV_name
  "g.V(v1Id).local(outE('knows').limit(1)).inV().values('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/local (q/__ (q/outE :knows) (q/limit 1)))
                (q/inV)
                (q/values :name)))

(defn get_g_V_localXbothEXcreatedX_limitX1XX_otherV_name
  "g.V().local(bothE('created').limit(1)).otherV().values('name')"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/bothE :created) (q/limit 1)))
                (q/otherV)
                (q/values :name)))

(defn get_g_VX4X_localXbothEX1_createdX_limitX1XX
  "g.V(v4Id).local(bothE('created').limit(1))"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/local (q/__ (q/bothE :created) (q/limit 1)))))

(defn get_g_VX4X_localXbothEXknows_createdX_limitX1XX
  "g.V(v4Id).local(bothE('knows', 'created').limit(1))"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/local (q/__ (q/bothE :knows :created) (q/limit 1)))))

(defn get_g_VX4X_localXbothE_limitX1XX_otherV_name
  "g.V(v4Id).local(bothE().limit(1)).otherV().values('name')"
  [g v4Id]
  (q/traverse g (q/V v4Id)
                (q/local (q/__ (q/bothE) (q/limit 1)))
                (q/otherV)
                (q/values :name)))

(defn get_g_V_localXinEXknowsX_limitX2XX_outV_name
  "g.V().local(inE('knows').limit(2)).outV().values('name')"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/inE :knows) (q/limit 2)))
                (q/outV)
                (q/values :name)))

(defn get_g_V_localXmatchXproject__created_person__person_name_nameX_selectXname_projectX_by_byXnameX
  "g.V().local(__.match(as('project').in('created').as('person'),
                        as('person').values('name').as('name')))
                        .<String>select('name', 'project').by().by('name');"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/match (q/__ (q/as :project) (q/in :created) (q/as :person))
                                        (q/__ (q/as :person) (q/values :name) (q/as :name)))))
                (q/select :name :project)
                (q/by) (q/by :name)))

