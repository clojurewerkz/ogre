(ns clojurewerkz.ogre.suite.inject-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traverser Traversal)))

(defn get_g_VX1X_out_name_injectXdanielX_asXaX_mapXlengthX_path
  "g.V(v1Id).out().values('name').inject('daniel').as('a').map(t -> t.get().length()).path()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/values :name)
                (q/inject "daniel") (q/as :a)
                (q/map (fn [^Traverser t] (.length ^String (.get t))))
                (q/path)))

(defn get_g_VX1X_out_injectXv2X_name
  "g.V(v1Id).out().inject(g.V(v2Id).next()).values('name')"
  [g v1Id v2Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/inject (q/traverse g (q/V v2Id) (q/next!)))
                (q/values :name)))
