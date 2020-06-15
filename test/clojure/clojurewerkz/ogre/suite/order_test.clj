(ns clojurewerkz.ogre.suite.order-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_V_asXaX_outXcreatedX_asXbX_order_byXshuffleX_selectXa_bX
  "g.V().as('a').out('created').as('b').order().by(Order.shuffle).select('a', 'b')"
  [g]
  (traverse g (V) (as :a)
              (out :created) (as :b)
              (order)
                (by (sort :shuffle))
              (select :a :b)))

(defn get_g_VX1X_hasXlabel_personX_mapXmapXint_ageXX_orderXlocalX_byXvalues_descX_byXkeys_ascX
  "g.V(v1Id).map(v -> {
                final Map<Integer, Integer> map = new HashMap<>();
                map.put(1, (int) v.get().value('age'));
                map.put(2, (int) v.get().value('age') * 2);
                map.put(3, (int) v.get().value('age') * 3);
                map.put(4, (int) v.get().value('age'));
                return map;
            }).order(scope.local).by(Column.values, Order.desc).by(Column.keys, Order.asc)"
  [g v1Id]
  (traverse g (V v1Id)
              (has-label :person)
              (map (fn [^Traverser v] { (int 1) (int (.value ^Vertex (.get v) "age"))
                                        (int 2) (int (* (int (.value ^Vertex (.get v) "age")) 2))
                                        (int 3) (int (* (int (.value ^Vertex (.get v) "age")) 3))
                                        (int 4) (int (.value ^Vertex (.get v) "age")) }))
              (order (scope :local))
                (by (column :values) (sort :desc))
                (by (column :keys) (sort :asc))))

(defn get_g_V_order_byXoutE_count_descX
  "g.V().order().by(outE().count(), Order.desc)"
  [g]
  (traverse g (V)
              (order)
                (by (__ (outE) (count)) (sort :desc))))

(defn get_g_V_group_byXlabelX_byXname_order_byXdescX_foldX
  "g.V().group().by(T.label).by(__.values('name').order().by(Order.desc).fold())"
  [g]
  (traverse g (V)
              (group)
                (by (T/label))
                (by (__ (values :name) (order) (by (sort :desc)) (fold)))))

(defn get_g_V_asXvX_mapXbothE_weight_foldX_sumXlocalX_asXsX_selectXv_sX_order_byXselectXsX_descX
  "g.V().as('v').map(__.bothE().values('weight').fold()).sum(scope.local).as('s').select('v', 's').order().by(__.select('s'), Order.desc)"
  [g]
  (traverse g (V) (as :v)
              (map (__ (bothE) (values :weight) (fold)))
              (sum (scope :local)) (as :s)
              (select :v :s)
              (order)
                (by (__ (select :s)) (sort :desc))))

(defn get_g_V_hasLabelXpersonX_order_byXageX
  "g.V().hasLabel('person').order().by('age')"
  [g]
  (traverse g (V)
              (has-label :person)
              (order)
                (by :age)))

(defn get_g_V_hasLabelXpersonX_fold_orderXlocalX_byXageX
  "g.V().hasLabel('person').fold().order(scope.local).by('age')"
  [g]
  (traverse g (V)
              (has-label :person)
              (fold)
              (order (scope :local))
                (by :age)))

(defn get_g_V_hasLabelXpersonX_order_byXvalueXageX__descX_name
  "g.V().hasLabel('person').order().by(v -> v.value('age'), Order.desc).values('name')"
  [g]
  (traverse g (V)
              (has-label :person)
              (order)
                (by (fn [^Vertex v] (.value v "age")) (sort :desc))
              (values :name)))

(defn get_g_V_properties_order_byXkey_descX_key
  "g.V().properties().order().by(T.key, Order.desc).key()"
  [g]
  (traverse g (V)
              (properties)
              (order)
                (by (T/key) (sort :desc))
              (key)))

(defn get_g_V_hasXsong_name_OHBOYX_outXfollowedByX_outXfollowedByX_order_byXperformancesX_byXsongType_descX
  "g.V().has('song', 'name', 'OH BOY').out('followedBy').out('followedBy').order().by('performances').by('songType', Order.desc)"
  [g]
  (traverse g (V)
              (has :song :name "OH BOY")
              (out :followedBy)
              (out :followedBy)
              (order)
                (by :performances)
                (by :songType (sort :desc))))

(defn get_g_V_both_hasLabelXpersonX_order_byXage_descX_limitX5X_name
  "g.V().both().hasLabel('person').order().by('age', Order.desc).limit(5).values('name')"
  [g]
  (traverse g (V)
              (both)
              (has-label :person)
              (order)
                (by :age (sort :desc))
              (limit 5)
              (values :name)))

(defn get_g_V_both_hasLabelXpersonX_order_byXage_descX_name
  "g.V().both().hasLabel('person').order().by('age', Order.desc).values('name')"
  [g]
  (traverse g (V)
              (both)
              (has-label :person)
              (order)
                (by :age (sort :desc))
              (values :name)))

(defn get_g_V_hasLabelXpersonX_order_byXvalueXageX_descX_name
  "g.V().hasLabel('person').order().by(v -> v.value('age'), Order.desc).values('name')"
  [g]
  (traverse g (V)
            (has-label :person)
            (order)
              (by (fn [^Vertex x] (.value x "age")) (sort :desc))
            (values :name)))

(defn get_g_V_hasLabelXsongX_order_byXperformances_descX_byXnameX_rangeX110_120X_name
  "g.V().hasLabel('song').order().by('performances', Order.desc).by('name').range(110, 120).values('name')"
  [g]
  (traverse g (V)
              (has-label :song)
              (order)
                (by :performances (sort :desc))
                (by :name)
              (range 110 120)
              (values :name)))

(defn get_g_V_name_order_byXa1_b1X_byXb2_a2X
  "g.V().values('name').order().by((a, b) -> a.substring(1, 2).compareTo(b.substring(1, 2)))
                               .by((a, b) -> b.substring(2, 3).compareTo(a.substring(2, 3)))"
  [g]
  (traverse g (V)
              (values :name)
              (order)
                (by (fn [^String a ^String b] (.compareTo (.substring a 1 2) (.substring b 1 2))))
                (by (fn [^String a ^String b] (.compareTo (.substring b 2 3) (.substring a 2 3))))))

(defn get_g_V_name_order
  "g.V().values('name').order()"
  [g]
  (traverse g (V)
              (values :name)
              (order)))

(defn get_g_V_order_byXname_ascX_name
  "g.V().order().by('name', Order.asc).values('name')"
  [g]
  (traverse g (V)
              (order)
                (by :name (sort :asc))
              (values :name)))

(defn get_g_V_order_byXname_incrX_name
  "g.V().order().by('name', Order.incr).values('name')"
  [g]
  (traverse g (V)
            (order)
              (by :name (sort :incr))
            (values :name)))

(defn get_g_V_order_byXnameX_name
  "g.V().order().by('name').values('name')"
  [g]
  (traverse g (V)
              (order)
                (by :name)
              (values :name)))

(defn get_g_V_order_byXname_a1_b1X_byXname_b2_a2X_name
  "g.V().order().by('name', (a, b) -> a.substring(1, 2).compareTo(b.substring(1, 2))).
                 by('name', (a, b) -> b.substring(2, 3).compareTo(a.substring(2, 3))).values('name')"
  [g]
  (traverse g (V)
              (order)
                (by :name (fn [^String a ^String b] (.compareTo (.substring a 1 2) (.substring b 1 2))))
                (by :name (fn [^String a ^String b] (.compareTo (.substring b 2 3) (.substring a 2 3))))
              (values :name)))

(defn get_g_V_outE_order_byXweight_descX_weight
  "g.V().outE().order().by('weight', Order.desc).values('weight')"
  [g]
  (traverse g (V)
              (outE)
              (order)
                (by :weight (sort :desc))
              (values :weight)))

(defn get_g_V_outE_order_byXweight_decrX_weight
  "g.V().outE().order().by('weight', Order.decr).values('weight')"
  [g]
  (traverse g (V)
            (outE)
            (order)
              (by :weight (sort :decr))
            (values :weight)))

(defn get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_unfold_order_byXvalues_descX
  "g.V().hasLabel('person').group().by('name').by(outE().values('weight').sum()).unfold().order().by(Column.values, Order.desc)"
  [g]
  (traverse g (V)
              (has-label :person)
              (group)
                (by :name)
                (by (__ (outE) (values :weight) (sum)))
              (unfold)
              (order)
                (by (column :values) (sort :desc))))

(defn get_g_V_hasLabelXpersonX_group_byXnameX_byXoutE_weight_sumX_orderXlocalX_byXvaluesX
  "g.V().hasLabel('person').group().by('name').by(outE().values('weight').sum()).order(Scope.local).by(Column.values)"
  [g]
  (traverse g (V)
              (has-label :person)
              (group)
                (by :name)
                (by (__ (outE) (values :weight) (sum)))
              (order (scope :local))
                (by (column :values))))

(defn get_g_V_mapXbothE_weight_foldX_order_byXsumXlocalX_descX
  "g.V().map(__.bothE().values('weight').fold()).order().by(__.sum(Scope.local), Order.desc)"
  [g]
  (traverse g 
            (V)
            (map (__ (bothE) (values :weight) (fold)))
            (order)
              (by (__ (sum (scope :local))) (sort :desc))))

(defn g_VX1X_elementMap_orderXlocalX_byXkeys_descXunfold
  "g.V(v1Id).elementMap().order(Scope.local).by(keys, desc).unfold()"
  [g v1Id]
  (traverse g
            (V v1Id)
            (element-map)
            (order (scope :local))
              (by (column :keys) (sort :desc))
            (unfold)))