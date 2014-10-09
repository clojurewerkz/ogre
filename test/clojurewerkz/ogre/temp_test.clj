(ns clojurewerkz.ogre.temp-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.util :as ut]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-temp-step

  (testing "test_g_v1_out()"
    (let [g (u/classic-tinkergraph)
          vs1 (q/query (v/find-by-id g (int 1)) v/connected-out-vertices q/into-vec!)
          vs2 (q/query (v/find-by-id g (int 1)) (v/connected-out-vertices "knows") q/into-vec! first ((q/prop :name)))
          ]
      (println vs1)
      (println vs2)
      (is true))))
