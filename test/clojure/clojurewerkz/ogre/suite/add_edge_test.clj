(ns clojurewerkz.ogre.suite.add-edge-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traversal)))

(defn get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X
  "g.V(v1Id).as('a').out('created').addE('createdBy').to('a').property('weight', 2.0d)"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/addE :createdBy)
                (q/to :a)
                (q/property :weight 2.0)))

(defn get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX
  "g.V(v1Id).as('a').out('created').addE('createdBy').to('a')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/addE :createdBy)
                (q/to :a)))

(defn get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX
  "g.V().aggregate('x').as('a').select('x').unfold().addE('existsWith').to('a').property('time', 'now')"
  [g]
  (q/traverse g (q/V)
                (q/aggregate :x) (q/as :a)
                (q/select :x)
                (q/unfold)
                (q/addE :existsWith)
                (q/to :a)
                (q/property :time "now")))

(defn get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X
  "g.V().as('a').out('created').in('created').where(P.neq('a')).as('b').addE('co-developer').from('a').to('b').property('year', 2009)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :created)
                (q/in :created)
                (q/where (P/neq "a")) (q/as :b)
                (q/addE :co-developer)
                (q/from :a)
                (q/to :b)
                (q/property :year (int 2009))))

(defn get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX
  "g.V().as('a').in('created').addE('createdBy').from('a').property('year', 2009).property('acl', 'public')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/in :created)
                (q/addE :createdBy)
                (q/from :a)
                (q/property :year (int 2009))
                (q/property :acl "public")))

;; addOutE was deprecated in TinkerPop 3.1.0 - not supported by Ogre directly
(defn get_g_VX1X_asXaX_outXcreatedX_addOutEXcreatedBy_aX
  "g.V(v1Id).as('a').out('created').addOutE('createdBy', 'a')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/addE :createdBy)
                (q/from :a)))

;; addOutE was deprecated in TinkerPop 3.1.0 - not supported by Ogre directly
(defn get_g_VX1X_asXaX_outXcreatedX_addOutEXcreatedBy_a_weight_2X
  "g.V(v1Id).as('a').out('created').addOutE('createdBy', 'a', 'weight', 2.0d)"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/addE :createdBy)
                (q/from :a)
                (q/property :weight 2.0)))

;; addOutE was deprecated in TinkerPop 3.1.0 - not supported by Ogre directly
(defn get_g_withSideEffectXx__g_V_toListX_addOutEXexistsWith_x_time_nowX
  "g.withSideEffect('x', g.V().toList()).V().addOutE('existsWith', 'x', 'time', 'now')"
  [g]
  (q/traverse g (q/with-side-effect :x ^ArrayList (q/traverse g (q/V) (q/into-list!)))
                (q/V)
                (q/addE :existsWith)
                (q/from :x)
                (q/property :time "now")))

;; addInE was deprecated in TinkerPop 3.1.0 - not supported by Ogre directly
(defn get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_selectXa_bX_addInEXa_codeveloper_b_year_2009X
  "g.V().as('a').out('created').in('created').where(P.neq('a')).as('b').select('a', 'b').addInE('a', 'co-developer', 'b', 'year', 2009)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :created)
                (q/in :created)
                (q/where (P/neq "a")) (q/as :b)
                (q/addE :co-developer)
                (q/from :a)
                (q/to :b)
                (q/property :year (int 2009))))

;; addInE was deprecated in TinkerPop 3.1.0 - not supported by Ogre directly
(defn get_g_V_asXaX_inXcreatedX_addInEXcreatedBy_a_year_2009_acl_publicX
  "g.V().as('a').in('created').addInE('createdBy', 'a', 'year', 2009, 'acl', 'public')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/in :created)
                (q/addE :createdBy)
                (q/from :a)
                (q/property :year (int 2009))
                (q/property :acl "public")))
