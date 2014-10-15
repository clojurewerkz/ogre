(ns clojurewerkz.ogre.filter.dedup-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-dedup-step
  (testing "test_g_V_both_dedup_name()"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         q/<->
                         q/dedup
                         (q/values :name)
                         (q/into-vec!))]
      (is (= (sort ["marko" "josh" "peter" "vadas" "lop" "ripple"]) (sort names)))))

  (testing "test_g_V_both_dedup_name()"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/get-all-vertices g)
                         q/<->
                         (q/dedup #(v/get (.get %) :lang))
                         (q/values :name)
                         (q/into-vec!))]
      (is (= 2 (count names)))
      (is (some #{"marko" "josh" "peter" "vadas"} names))
      (is (some #{"lop" "ripple"} names)))))
