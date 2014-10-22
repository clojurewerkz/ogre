(ns clojurewerkz.ogre.map.path-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

  (deftest test-path-step
  (testing "g.getVertex(1).property('name').path"
    (let [g (u/classic-tinkergraph)
          path (.getObjects (q/query (v/find-by-id g (int 1))
                        (q/values :name)
                        q/path
                        q/next!))]
      (is (= "marko" (v/get (first path) :name)))
      (is (= "marko" (second path))))))
