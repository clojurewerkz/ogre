(ns clojurewerkz.ogre.side-effect.side-effect-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-side-effect-step
  (testing "test_g_v1_sideEffectXstore_aX_propertyXnameX"
    (let [lst (atom [])
          g (u/classic-tinkergraph)
          elem (v/find-by-id g 1)
          names (q/query elem
                      (q/side-effect (partial swap! lst conj))
                      (q/values :name)
                      q/into-vec!)]
      (is (= elem (first @lst)))
      (is (= "marko" (first names)))))

  (testing "test_g_v1_out_sideEffectXincr_cX_propertyXnameX"
    (let [lst (atom [])
          g (u/classic-tinkergraph)
          elem (v/find-by-id g 1)
          names (q/query elem
                      q/-->
                      (q/side-effect (partial swap! lst conj))
                      (q/values :name)
                      q/into-vec!)]
      (is (= 3 (count @lst)))
      (is (= #{"josh" "lop" "vadas"} (set names))))))






