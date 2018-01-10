(ns clojurewerkz.ogre.suite.order-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure Column T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Order P Scope Traverser)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_order_byXshuffleX_selectXa_bX
  "g.V().as('a').out('created').as('b').order().by(Order.shuffle).select('a', 'b')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :created) (q/as :b)
                (q/order)
                (q/by (Order/shuffle))
                (q/select :a :b)))

(defn get_g_VX1X_hasXlabel_personX_mapXmapXint_ageXX_orderXlocalX_byXvalues_decrX_byXkeys_incrX
  "g.V(v1Id).map(v -> {
                final Map<Integer, Integer> map = new HashMap<>();
                map.put(1, (int) v.get().value('age'));
                map.put(2, (int) v.get().value('age') * 2);
                map.put(3, (int) v.get().value('age') * 3);
                map.put(4, (int) v.get().value('age'));
                return map;
            }).order(q/scope.local).by(Column.values, Order.decr).by(Column.keys, Order.incr)"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/has-label :person)
                (q/map (fn [^Traverser v] {(int 1) (int (.value ^Vertex (.get v) "age"))
                                (int 2) (int (* (int (.value ^Vertex (.get v) "age")) 2))
                                (int 3) (int (* (int (.value ^Vertex (.get v) "age")) 3))
                                (int 4) (int (.value ^Vertex (.get v) "age")) }))
                (q/order (q/scope :local))
                (q/by (Column/valueOf "values") (Order/decr))
                (q/by (Column/keys) (Order/incr))))

(defn get_g_V_order_byXoutE_count__decrX
  "g.V().order().by(outE().count(), Order.decr)"
  [g]
  (q/traverse g (q/V)
                (q/order)
                (q/by (q/__ (q/outE) (q/count)) (Order/decr))))

(defn get_g_V_group_byXlabelX_byXname_order_byXdecrX_foldX
  "g.V().group().by(T.label).by(__.values('name').order().by(Order.decr).fold())"
  [g]
  (q/traverse g (q/V)
                (q/group)
                (q/by (T/label))
                (q/by (q/__ (q/values :name) (q/order) (q/by (Order/decr)) (q/fold)))))

(defn get_g_V_localXbothE_weight_foldX_order_byXsumXlocalX_decrX
  "g.V().local(__.bothE().values('weight').fold()).order().by(__.sum(q/scope.local), Order.decr)"
  [g]
  (q/traverse g (q/V)
                (q/local (q/__ (q/bothE) (q/values :weight) (q/fold)))
                (q/order)
                (q/by (q/__ (q/sum (q/scope :local))) (Order/decr))))

(defn get_g_V_asXvX_mapXbothE_weight_foldX_sumXlocalX_asXsX_selectXv_sX_order_byXselectXsX_decrX
  "g.V().as('v').map(__.bothE().values('weight').fold()).sum(q/scope.local).as('s').select('v', 's').order().by(__.select('s'), Order.decr)"
  [g]
  (q/traverse g (q/V) (q/as :v)
                (q/map (q/__ (q/bothE) (q/values :weight) (q/fold)))
                (q/sum (q/scope :local)) (q/as :s)
                (q/select :v :s)
                (q/order)
                (q/by (q/__ (q/select :s)) (Order/decr))))

(defn get_g_V_hasLabelXpersonX_order_byXageX
  "g.V().hasLabel('person').order().by('age')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/order)
                (q/by :age)))

(defn get_g_V_hasLabelXpersonX_fold_orderXlocalX_byXageX
  "g.V().hasLabel('person').fold().order(q/scope.local).by('age')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/fold)
                (q/order (q/scope :local))
                (q/by :age)))

(defn get_g_V_hasLabelXpersonX_order_byXvalueXageX__decrX_name
  "g.V().hasLabel('person').order().by(v -> v.value('age'), Order.decr).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/order)
                (q/by (fn [^Vertex v] (.value v "age")) (Order/decr))
                (q/values :name)))

(defn get_g_V_properties_order_byXkey_decrX_key
  "g.V().properties().order().by(T.key, Order.decr).key()"
  [g]
  (q/traverse g (q/V)
                (q/properties)
                (q/order)
                (q/by (T/key) (Order/decr))
                (q/key)))

(defn get_g_V_hasXsong_name_OHBOYX_outXfollowedByX_outXfollowedByX_order_byXperformancesX_byXsongType_incrX
  "g.V().has('song', 'name', 'OH BOY').out('followedBy').out('followedBy').order().by('performances').by('songType', Order.decr)"
  [g]
  (q/traverse g (q/V)
                (q/has :song :name "OH BOY")
                (q/out :followedBy)
                (q/out :followedBy)
                (q/order)
                (q/by :performances)
                (q/by :songType (Order/decr))))

(defn get_g_V_both_hasLabelXpersonX_order_byXage_decrX_limitX5X_name
  "g.V().both().hasLabel('person').order().by('age', Order.decr).limit(5).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/has-label :person)
                (q/order)
                (q/by :age (Order/decr))
                (q/limit 5)
                (q/values :name)))

(defn get_g_V_both_hasLabelXpersonX_order_byXage_decrX_name
  "g.V().both().hasLabel('person').order().by('age', Order.decr).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/both)
                (q/has-label :person)
                (q/order)
                (q/by :age (Order/decr))
                (q/values :name)))

(defn get_g_V_hasLabelXsongX_order_byXperfomances_decrX_byXnameX_rangeX110_120X_name
  "g.V().hasLabel('song').order().by('performances', Order.decr).by('name').range(110, 120).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/has-label :song)
                (q/order)
                (q/by :performances (Order/decr))
                (q/by :name)
                (q/range 110 120)
                (q/values :name)))

(defn get_g_V_name_order_byXa1_b1X_byXb2_a2X
  "g.V().values('name').order().by((a, b) -> a.substring(1, 2).compareTo(b.substring(1, 2)))
                               .by((a, b) -> b.substring(2, 3).compareTo(a.substring(2, 3)))"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/by (fn [^String a ^String b] (.compareTo (.substring a 1 2) (.substring b 1 2))))
                (q/by (fn [^String a ^String b] (.compareTo (.substring b 2 3) (.substring a 2 3))))))

(defn get_g_V_name_order
  "g.V().values('name').order()"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)))

(defn get_g_V_order_byXname_incrX_name
  "g.V().order().by('name', Order.incr).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/order)
                (q/by :name (Order/incr))
                (q/values :name)))

(defn get_g_V_order_byXnameX_name
  "g.V().order().by('name', Order.incr).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/order)
                (q/by :name)
                (q/values :name)))

(defn get_g_V_order_byXname_a1_b1X_byXname_b2_a2X_name
  "g.V().order().by('name', (a, b) -> a.substring(1, 2).compareTo(b.substring(1, 2))).
                 by('name', (a, b) -> b.substring(2, 3).compareTo(a.substring(2, 3))).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/order)
                (q/by :name (fn [^String a ^String b] (.compareTo (.substring a 1 2) (.substring b 1 2))))
                (q/by :name (fn [^String a ^String b] (.compareTo (.substring b 2 3) (.substring a 2 3))))
                (q/values :name)))

(defn get_g_V_outE_order_byXweight_decrX_weight
  "g.V().outE().order().by('weight', Order.decr).values('weight')"
  [g]
  (q/traverse g (q/V)
                (q/outE)
                (q/order)
                (q/by :weight (Order/decr))
                (q/values :weight)))

(defn get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_unfold_order_byXvalues_decrX
  "g.V().hasLabel('person').group().by('name').by(outE().values('weight').sum()).unfold().order().by(Column.values, Order.decr)"
  [g]
  (q/traverse g
              (q/V)
              (q/has-label :person)
              (q/group)
                (q/by :name)
                (q/by (q/__ (q/outE) (q/values :weight) (q/sum)))
              (q/unfold)
              (q/order)
                (q/by (Column/valueOf "values") (Order/decr))))

(defn get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_orderXlocalX_byXvaluesX
  "g.V().hasLabel('person').group().by('name').by(outE().values('weight').sum()).order(Scope.local).by(Column.values)"
  [g]
  (q/traverse g
              (q/V)
              (q/has-label :person)
              (q/group)
                (q/by :name)
                (q/by (q/__ (q/outE) (q/values :weight) (q/sum)))
              (q/order (q/scope :local))
                (q/by (Column/valueOf "values"))))




