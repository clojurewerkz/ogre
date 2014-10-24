(ns clojurewerkz.ogre.map.label-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-label-step
  (testing "test_g_v1_outE_labelX()"
    (let [names (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         q/-E>
                         q/label
                         q/into-vec!)]
      (is (= ["created" "knows" "knows"] names)))))