(ns clojurewerkz.ogre.filter.dedup-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

(deftest test-dedup-step
  (testing "test_g_V_both_dedup_name()"
    (let [g (g/use-new-tinker-graph!)
          names (q/query (g/get-vertices g)
                         q/<->
                         q/dedup
                         (q/property :name)
                         (q/into-vec!))]
      (is (= (sort ["marko" "josh" "peter" "vadas" "lop" "ripple"]) (sort names)))))

  (testing "test_g_V_both_dedup_name()"
    (let [g (g/use-new-tinker-graph!)
          names (q/query (g/get-vertices g)
                         q/<->
                         (q/dedup (partial g/get-property :lang))
                         (q/property :name)
                         (q/into-vec!))]
      (is (= 2 (count names)))
      (is (some #{"marko" "josh" "peter" "vadas"} names))
      (is (some #{"lop" "ripple"} names)))))