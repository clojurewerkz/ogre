(ns clojurewerkz.ogre.side-effect.group-count-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-group-count-step
  (testing "g.V().out('created').groupCount{it.get().value('name')}"
    (let [g (u/classic-tinkergraph)
          group (q/query (v/get-all-vertices g)
                         (q/--> [:created])
                         (q/get-group-count! #(v/get (.get %) :name)))]
      (is (= group {"lop" 3 "ripple" 1})))))
