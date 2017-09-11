(ns clojurewerkz.ogre.suite.optional-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX2X_optionalXoutXknowsXX
  "g.V(v2Id).optional(out('knows'))"
  [g v2Id]
  (q/traverse g (q/V v2Id)
                (q/optional (q/__ (q/out :knows)))))

(defn get_g_VX2X_optionalXinXknowsXX
  "1g.V(v2Id).optional(in('knows'))"
  [g v2Id]
  (q/traverse g (q/V v2Id)
                (q/optional (q/__ (q/in :knows)))))

(defn get_g_V_hasLabelXpersonX_optionalXoutXknowsX_optionalXoutXcreatedXXX_path
  "g.V().hasLabel('person').optional(out('knows').optional(out('created'))).path()"
  [g]
  (q/traverse g (q/V)
                (q/has-label :person)
                (q/optional (q/__ (q/out :knows) (q/optional (q/__ (q/out :created)))))
                (q/path)))

(defn get_g_V_optionalXout_optionalXoutXX_path
  "g.V().optional(out().optional(out())).path()"
  [g]
  (q/traverse g (q/V)
                (q/optional (q/__ (q/out) (q/optional (q/__ (q/out)))))
                (q/path)))

(defn get_g_VX1X_optionalXaddVXdogXX_label
  "g.V(v1Id).optional(addV('dog')).label()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/optional (q/__ (q/addV "dog")))
              (q/label)))
