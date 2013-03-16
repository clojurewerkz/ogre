(ns ogre.filter.dedup-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-dedup-step
  (g/use-new-tinker-graph!)
  (testing "test_g_V_both_dedup_name()"
    (let [names (q/query (g/get-vertices)
                         q/<->
                         q/dedup
                         (q/property :name)
                         (q/into-vec!))]
      (is (= ["marko" "josh" "peter" "vadas""lop" "ripple" ] names))))

  (testing "test_g_V_both_dedup_name()"
    (let [names (q/query (g/get-vertices)
                         q/<->
                         (q/dedup (partial g/get-property :lang))
                         (q/property :name)
                         (q/into-vec!))]
      (is (= 2 (count names)))
      (is (some #{"marko" "josh" "peter" "vadas"} names))
      (is (some #{"lop" "ripple"} names)))))