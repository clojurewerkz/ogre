(ns clojurewerkz.ogre.suite.choose-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)
           (org.apache.tinkerpop.gremlin.process.traversal.step TraversalOptionParent$Pick)))

(defn get_g_V_chooseXout_countX_optionX2L__nameX_optionX3L__valueMapX
  "g.V().choose(out().count()).option(2L, values('name')).option(3L, valueMap())"
  [g]
  (traverse g (V)
              (choose (__ (out) (count)))
                (option (long 2) (__ (values :name)))
                (option (long 3) (__ (value-map)))))

(defn get_g_V_chooseXlabel_eqXpersonX__outXknowsX__inXcreatedXX_name
  "g.V().choose(v -> v.label().equals('person'), out('knows'), in('created')).values('name')"
  [g]
  (traverse g (V)
              (choose (fn [^Vertex v] (.equals (.label v) "person")),
                (__ (out :knows))
                (__ (in :created)))
              (values :name)))

(defn get_g_V_chooseXhasLabelXpersonX_and_outXcreatedX__outXknowsX__identityX_name
  "g.V().choose(hasLabel('person').and().out('created'), out('knows'), identity()).values('name')"
  [g]
  (traverse g (V)
              (choose (__ (has-label :person) (and) (out :created)) (__(out :knows)) (__ (identity)))
              (values :name)))

(defn get_g_V_chooseXoutXknowsX_count_isXgtX0XX__outXknowsXX_name
  "g.V().choose(out('knows').count().is(P.gt(0)), out('knows')).values('name')"
  [g]
  (traverse g (V)
              (choose (__ (out :knows) (count) (is (P/gt 0)))
                      (__ (out :knows)))
              (values :name)))

(defn get_g_V_chooseXlabelX_optionXblah__outXknowsXX_optionXbleep__outXcreatedXX_optionXnone__identityX_name
  "g.V().choose(label())
          .option('blah', out('knows'))
          .option('bleep', out('created'))
          .option(TraversalOptionParent.Pick.none, identity()).values('name')"
  [g]
  (traverse g (V)
              (choose (__ (label)))
                (option "blah" (__ (out :knows)))
                (option "bleep" (__ (out :created)))
                (option (TraversalOptionParent$Pick/none) (__ (identity)))
              (values :name)))

(defn get_g_V_hasLabelXpersonX_asXp1X_chooseXoutEXknowsX__outXknowsXX_asXp2X_selectXp1_p2X_byXnameX
  "g.V().hasLabel('person').as('p1').choose(outE('knows'), out('knows')).as('p2').select('p1', 'p2').by('name')"
  [g]
  (traverse g (V)
              (has-label :person) (as :p1)
              (choose (__ (outE :knows))
                        (__ (out :knows))) (as :p2)
              (select :p1 :p2)
                (by :name)))

