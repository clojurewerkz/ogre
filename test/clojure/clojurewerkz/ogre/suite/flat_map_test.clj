(ns clojurewerkz.ogre.suite.flat-map-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_asXaX_flatMapXselectXaXX
  "g.V().as('a').flatMap(select('a'))"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/flat-map (q/__ (q/select :a)))))
