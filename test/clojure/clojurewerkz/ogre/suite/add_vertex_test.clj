(ns clojurewerkz.ogre.suite.add-vertex-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal.step.util WithOptions)))

(defn get_g_VX1X_addVXanimalX_propertyXage_selectXaX_byXageXX_propertyXname_puppyX
  "g.V(v1Id).as('a').addV('animal').property('age', __.select('a').by('age')).property('name', 'puppy')"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (add-V :animal)
              (property :age (__ (select :a) (by :age)))
              (property :name "puppy")))

(defn get_g_addVXpersonX_propertyXname_stephenX
  "g.addV('person').property('name', 'stephen')"
  [g]
  (traverse g (add-V :person)
              (property :name "stephen")))

(defn get_g_addVXpersonX_propertyXname_stephenX_propertyXname_stephenmX
  "g.addV('person').property('name', 'stephen').property('name', 'stephenm')"
  [g]
  (traverse g (add-V :person)
              (property :name "stephen")
              (property :name "stephenm")))

(defn get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenmX
  "g.addV('person').property(VertexProperty.Cardinality.single, 'name', 'stephen').property(VertexProperty.Cardinality.single, 'name', 'stephenm')"
  [g]
  (traverse g (add-V :person)
              (property (cardinality :single) :name "stephen")
              (property (cardinality :single) :name "stephenm")))

(defn get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenm_since_2010X
  "g.addV('person').property(VertexProperty.Cardinality.single, 'name', 'stephen').
     property(VertexProperty.Cardinality.single, 'name', 'stephenm', 'since', 2010)"
  [g]
  (traverse g (add-V :person)
              (property (cardinality :single) :name "stephen")
              (property (cardinality :single) :name "stephenm" :since (int 2010))))

(defn get_g_V_hasXname_markoX_propertyXfriendWeight_outEXknowsX_weight_sum__acl_privateX
  "g.V().has('name', 'marko').property('friendWeight', __.outE('knows').values('weight').sum(), 'acl', 'private')"
  [g]
  (traverse g (V)
              (has :name "marko")
              (property :friendWeight (__ (outE :knows) (values :weight) (sum)) :acl "private")))

(defn get_g_addVXanimalX_propertyXname_mateoX_propertyXname_gateoX_propertyXname_cateoX_propertyXage_5X
  "g.addV('animal').property('name', 'mateo').property('name', 'gateo').property('name', 'cateo').property('age', 5)"
  [g]
  (traverse g (add-V :animal)
              (property :name "mateo")
              (property :name "gateo")
              (property :name "cateo")
              (property :age (int 5))))

(defn get_g_V_addVXanimalX_propertyXname_valuesXnameXX_propertyXname_an_animalX_propertyXvaluesXnameX_labelX
  "g.V().addV('animal').property('name', __.values('name')).property('name', 'an animal').property(__.values('name'), __.label())"
  [g]
  (traverse g (V)
              (add-V :animal)
              (property :name (__ (values :name)))
              (property :name "an animal")
              (property (__ (values :name)) (__ (label)))))

(defn get_g_V_addVXanimalX_propertyXage_0X
  "g.V().addV('animal').property('age', 0)"
  [g]
  (traverse g (V)
              (add-V :animal)
              (property :age (int 0))))

(defn get_g_withSideEffectXa_markoX_addV_propertyXname_selectXaXX_name
  "g.withSideEffect('a', 'marko').addV().property('name', select('a')).values('name')"
  [g]
  (traverse g (with-side-effect :a "marko")
              (add-V)
              (property :name (__ (select :a)))
              (values :name)))

(defn get_g_withSideEffectXa_testX_V_hasLabelXsoftwareX_propertyXtemp_selectXaXX_valueMapXname_tempX
  "g.withSideEffect('a', 'test').V().hasLabel('software').property('temp', select('a')).valueMap('name', 'temp')"
  [g]
  (traverse g (with-side-effect :a "test")
              (V)
              (has-label :software)
              (property :temp (__ (select :a)))
              (value-map :name :temp)))

(defn get_g_V_asXaX_hasXname_markoX_outXcreatedX_asXbX_addVXselectXaX_labelX_propertyXtest_selectXbX_labelX_valueMap_withXtokensX
  "g.V().as('a').has('name', 'marko').out('created').as('b').addV(select('a').label()).property('test', select('b').label()).valueMap().with(WithOptions.tokens)"
  [g]
  (traverse g
            (V) (as :a)
            (has :name "marko")
            (out :created) (as :b)
            (add-V (__ (select :a) (label)))
            (property :test (__ (select :b) (label)))
            (value-map)
            (with WithOptions/tokens)))

(defn get_g_addVXV_hasXname_markoX_propertiesXnameX_keyX_label
  "g.addV(V().has('name', 'marko').properties('name').key()).label()"
  [g]
  (traverse g
              (add-V (__ (V) (has :name "marko") (properties :name) (key)))
              (label)))

(defn get_g_withSideEffectXa_nameX_addV_propertyXselectXaX_markoX_name
  "g.withSideEffect('a', 'name').addV().property(select('a'),'marko').values('name')"
  [g]
  (traverse g
              (with-side-effect :a "name")
              (add-V)
              (property (__ (select :a)) "marko")
              (values :name)))

