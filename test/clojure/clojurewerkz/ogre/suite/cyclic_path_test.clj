(ns clojurewerkz.ogre.suite.cyclic-path-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse sort shuffle])
  (:require [clojurewerkz.ogre.core :refer :all]))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath
  "g.V(v1Id).out('created').in('created').cyclicPath()"
  [g v1Id]
  (traverse g (V v1Id)
              (out :created)
              (in :created)
              (cyclic-path)))

(defn get_g_VX1X_outXcreatedX_inXcreatedX_cyclicPath_path
  "g.V(v1Id).out('created').in('created').cyclicPath().path()"
  [g v1Id]
  (traverse g (V v1Id)
              (out :created)
              (in :created)
              (cyclic-path)
              (path)))

(defn get_g_VX1X_asXaX_outXcreatedX_asXbX_inXcreatedX_asXcX_cyclicPath_fromXaX_toXbX_path
  "g.V(v1Id).as('a').out('created').as('b').in('created').as('c').cyclicPath().from('a').to('b').path()"
  [g v1Id]
  (traverse g (V v1Id) (as :a)
              (out :created) (as :b)
              (in :created) (as :c)
              (cyclic-path)
                (from :a)
                (to :b)
              (path)))

