(ns clojurewerkz.ogre.suite.side-effect-cap-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_V_hasXageX_groupCountXaX_byXnameX_out_capXaX
  "g.V().has('age').groupCount('a').by('name').out().cap('a')"
  [g]
  (traverse g (V)
              (has :age)
              (group-count :a)
                (by :name)
              (out)
              (cap :a)))

(defn get_g_V_chooseXlabel_person__age_groupCountXaX__name_groupCountXbXX_capXa_bX
  "g.V().choose(has(T.label, 'person'),
                    values('age').groupCount('a'),
                    values('name').groupCount('b')).cap('a', 'b')"
  [g]
  (traverse g (V)
              (choose (__ (has (T/label) "person"))
                      (__ (values :age) (group-count :a))
                      (__ (values :name) (group-count :b)))
              (cap :a :b)))
