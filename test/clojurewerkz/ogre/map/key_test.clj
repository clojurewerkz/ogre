(ns clojurewerkz.ogre.map.key-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-key-step
  (testing "g.V().properties().key()"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      q/properties
                      q/key
                      q/into-set!)]
      (is (= #{"lang" "age" "name"} vs))
      (is (= 3 (count vs))))))
