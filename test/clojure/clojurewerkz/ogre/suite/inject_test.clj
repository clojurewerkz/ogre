(ns clojurewerkz.ogre.suite.inject-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.process.traversal Traverser)))

(defn get_g_VX1X_out_name_injectXdanielX_asXaX_mapXlengthX_path
  "g.V(v1Id).out().values('name').inject('daniel').as('a').map(t -> t.get().length()).path()"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (values :name)
              (inject "daniel") (as :a)
              (map (fn [^Traverser t] (.length ^String (.get t))))
              (path)))

(defn get_g_VX1X_out_injectXv2X_name
  "g.V(v1Id).out().inject(g.V(v2Id).next()).values('name')"
  [g v1Id v2Id]
  (traverse g (V v1Id)
              (out)
              (inject (traverse g (V v2Id) (next!)))
              (values :name)))

(defn get_g_VX1X_injectXg_VX4XX_out_name
  "g.V(v1Id).inject(g.V(v4Id).next()).out().values('name')"
  [g v1Id v4Id]
  (traverse g (V v1Id)
              (inject (traverse g (V v4Id) (next!)))
              (out)
              (values :name)))

