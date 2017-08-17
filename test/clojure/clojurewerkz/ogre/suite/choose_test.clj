(ns clojurewerkz.ogre.suite.choose-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)
           (org.apache.tinkerpop.gremlin.process.traversal.step TraversalOptionParent$Pick)))

(defn get_g_V_chooseXout_countX_optionX2L__nameX_optionX3L__valueMapX
  "g.V().choose(out().count()).option(2L, values('name')).option(3L, valueMap())"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/out) (q/count)))
                (q/option (long 2) (q/__ (q/values :name)))
                (q/option (long 3) (q/__ (q/value-map)))))

(defn get_g_V_chooseXlabel_eqXpersonX__outXknowsX__inXcreatedXX_name
  "g.V().choose(v -> v.label().equals('person'), out('knows'), in('created')).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/choose (fn [^Vertex v] (.equals (.label v) "person")),
                  (q/__ (q/out :knows))
                  (q/__ (q/in :created)))
                (q/values :name)))

(defn get_g_V_chooseXhasLabelXpersonX_and_outXcreatedX__outXknowsX__identityX_name
  "g.V().choose(hasLabel('person').and().out('created'), out('knows'), identity()).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/has-label :person) (q/and) (q/out :created)) (q/__(q/out :knows)) (q/__ (q/identity)))
                (q/values :name)))

(defn get_g_V_chooseXoutXknowsX_count_isXgtX0XX__outXknowsXX_name
  "g.V().choose(out('knows').count().is(P.gt(0)), out('knows')).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/out :knows) (q/count) (q/is (P/gt 0)))
                          (q/__ (q/out :knows)))
                (q/values :name)))

(defn get_g_V_chooseXlabelX_optionXblah__outXknowsXX_optionXbleep__outXcreatedXX_optionXnone__identityX_name
  "g.V().choose(label())
          .option('blah', out('knows'))
          .option('bleep', out('created'))
          .option(TraversalOptionParent.Pick.none, identity()).values('name')"
  [g]
  (q/traverse g (q/V)
                (q/choose (q/__ (q/label)))
                  (q/option "blah" (q/__ (q/out :knows)))
                  (q/option "bleep" (q/__ (q/out :created)))
                  (q/option (TraversalOptionParent$Pick/none) (q/__ (q/identity)))
                (q/values :name)))
