(ns clojurewerkz.ogre.suite.optional-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_VX2X_optionalXoutXknowsXX
  "g.V(v2Id).optional(out('knows'))"
  [g v2Id]
  (traverse g (V v2Id)
              (optional (__ (out :knows)))))

(defn get_g_VX2X_optionalXinXknowsXX
  "1g.V(v2Id).optional(in('knows'))"
  [g v2Id]
  (traverse g (V v2Id)
              (optional (__ (in :knows)))))

(defn get_g_V_hasLabelXpersonX_optionalXoutXknowsX_optionalXoutXcreatedXXX_path
  "g.V().hasLabel('person').optional(out('knows').optional(out('created'))).path()"
  [g]
  (traverse g (V)
              (has-label :person)
              (optional (__ (out :knows) (optional (__ (out :created)))))
              (path)))

(defn get_g_V_optionalXout_optionalXoutXX_path
  "g.V().optional(out().optional(out())).path()"
  [g]
  (traverse g (V)
              (optional (__ (out) (optional (__ (out)))))
              (path)))

(defn get_g_VX1X_optionalXaddVXdogXX_label
  "g.V(v1Id).optional(addV('dog')).label()"
  [g v1Id]
  (traverse g (V v1Id)
              (optional (__ (add-V "dog")))
              (label)))
