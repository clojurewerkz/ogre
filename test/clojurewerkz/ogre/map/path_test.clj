(ns clojurewerkz.ogre.map.path-test
  (:use [clojure.test])
  (:import (com.tinkerpop.gremlin.process Path))
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-path-step
  (testing "g.v(1).values('name').path().next()"
    (let [g (u/classic-tinkergraph)
          path (q/query (v/find-by-id g (int 1))
                        (q/values :name)
                        q/path
                        q/next!)
          path (.objects ^Path path)]
      (is (= "marko" (v/get (first path) :name)))
      (is (= "marko" (second path))))))
