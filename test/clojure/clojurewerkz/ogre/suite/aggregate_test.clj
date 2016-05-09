(ns clojurewerkz.ogre.suite.aggregate-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_name_aggregateXxX_capXxX
  "g.V().values('name').aggregate('x').cap('x')"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/aggregate :x)
                (q/cap :x)))

(defn get_g_V_out_aggregateXaX_path
  "g.V().out().aggregate('a').path()"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/aggregate :a)
                (q/path)))

(defn get_g_V_aggregateXxX_byXnameX_capXxX
  "g.V().aggregate('x').by('name').cap('x')"
  [g]
  (q/traverse g (q/V)
                (q/aggregate :x)
                (q/by :name)
                (q/cap :x)))
