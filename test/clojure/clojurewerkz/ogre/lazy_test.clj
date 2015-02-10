(ns clojurewerkz.ogre.lazy-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-laziness
  (testing "Laziness!"
    (let [state (atom [])
          g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/side-effect (partial swap! state conj))
                      (q/values :name)
                      q/into-lazy-seq!)]
      (is (= 1 (count @state)))
      (do (first vs))
      (is (= 1 (count @state)))
      (doall (take 2 vs))
      (is (= 2 (count @state)))
      (doall (take 4 vs))
      (is (= 3 (count @state)))
      (doall (take 2 vs))
      (is (= 3 (count @state))))))
