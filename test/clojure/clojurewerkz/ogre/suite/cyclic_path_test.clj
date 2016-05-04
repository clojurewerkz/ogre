(ns clojurewerkz.ogre.suite.cyclic-path-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath
  "g.V(v1Id).out('created').in('created').cyclicPath()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :created)
                (q/in :created)
                (q/cyclic-path)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath_path
  "g.V(v1Id).out('created').in('created').cyclicPath().path()"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out :created)
                (q/in :created)
                (q/cyclic-path)
                (q/path)))
