(ns ogre.side-effect.side-effect-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-side-effect-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_v1_sideEffectXstore_aX_propertyXnameX"
    (let [lst (atom [])
          elem (g/find-by-id 1)
          names (q/query elem
                      (q/side-effect (partial swap! lst conj))
                      (q/property :name)
                      q/into-vec!)]
      (is (= elem (first @lst)))
      (is (= "marko" (first names)))))
  
  (testing "test_g_v1_out_sideEffectXincr_cX_propertyXnameX"
    (let [lst (atom [])
          elem (g/find-by-id 1)
          names (q/query elem
                      q/-->
                      (q/side-effect (partial swap! lst conj))
                      (q/property :name)
                      q/into-vec!)]
      (= 3 (count @lst))
      (= #{"josh" "lop" "vadas"} (set names))))

  (testing "test_g_v1_out_sideEffectXfalseX_propertyXnameX"
    (let [lst (atom [])
          elem (g/find-by-id 1)
          names (q/query elem
                      q/-->
                      (q/side-effect (constantly false))
                      (q/property :name)
                      q/into-vec!)]
      (= 3 (count @lst))
      (= #{"josh" "lop" "vadas"} (set names)))))





