(ns clojurewerkz.ogre.map.label-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-label-step
  (testing "g.v(1).outE().label()"
    (let [names (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         q/-E>
                         q/label
                         q/into-vec!)]
      (is (= ["created" "knows" "knows"] names)))))