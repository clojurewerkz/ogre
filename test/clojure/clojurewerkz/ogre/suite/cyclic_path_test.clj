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

(defn get_g_VX1X_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_cyclicPath_fromXaX_toXbX_path
  "g.V(v1Id).as('a').out('created').as('b').in('created').as('c').cyclicPath().from('a').to('b').path()"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created) (q/as :b)
                (q/in :created) (q/as :c)
                (q/cyclic-path)
                  (q/from :a)
                  (q/to :b)
                (q/path)))

