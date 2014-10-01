(ns clojurewerkz.ogre.side-effect.side-effect-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-side-effect-step
  (testing "test_g_v1_sideEffectXstore_aX_propertyXnameX"
    (let [lst (atom [])
          g (g/use-new-tinker-graph!)
          elem (g/find-by-id g 1)
          names (q/query elem
                      (q/side-effect (partial swap! lst conj))
                      (q/property :name)
                      q/into-vec!)]
      (is (= elem (first @lst)))
      (is (= "marko" (first names)))))

  (testing "test_g_v1_out_sideEffectXincr_cX_propertyXnameX"
    (let [lst (atom [])
          g (g/use-new-tinker-graph!)
          elem (g/find-by-id g 1)
          names (q/query elem
                      q/-->
                      (q/side-effect (partial swap! lst conj))
                      (q/property :name)
                      q/into-vec!)]
      (is (= 3 (count @lst)))
      (is (= #{"josh" "lop" "vadas"} (set names))))))






