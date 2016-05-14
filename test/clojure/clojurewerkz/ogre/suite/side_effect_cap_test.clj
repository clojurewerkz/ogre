(ns clojurewerkz.ogre.suite.side-effect-cap-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_hasXageX_groupCountXaX_byXnameX_out_capXaX
  "g.V().has('age').groupCount('a').by('name').out().cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/has :age)
                (q/group-count :a)
                (q/by :name)
                (q/out)
                (q/cap :a)))

(defn get_g_V_chooseXlabel_person__age_groupCountXaX__name_groupCountXbXX_capXa_bX
  "g.V().choose(has(T.label, 'person'),
                    values('age').groupCount('a'),
                    values('name').groupCount('b')).cap('a', 'b')"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/has (T/label) "person"))
                          (q/__ (q/values :age) (q/group-count :a))
                          (q/__ (q/values :name) (q/group-count :b)))
                (q/cap :a :b)))
