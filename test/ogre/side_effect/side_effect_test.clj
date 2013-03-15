(ns ogre.side-effect.side-effect-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-side-effect-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_v1_sideEffectXstore_aX_propertyXnameX"
    (let [lst (atom [])
          elem (g/find-by-id 1)
          vs (q/query elem
                      (q/side-effect (partial swap! lst conj))
                      (q/property :name)
                      q/into-vec)]
      (is (= elem (first @lst))))))