(ns clojurewerkz.ogre.filter.back-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-transform-step
  (testing "test_g_v1_out_backX1X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/back 1)
                      q/into-vec!)]
      (is (= #{"marko"} (u/get-names-set vs)))
      (is (= 1 (count vs)))))

  (testing "test_g_v1_asXhereX_out_backXhereX()"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/as "here")
                      q/-->
                      (q/back-to "here")
                      q/into-vec!)]
      (is (= #{"marko"} (u/get-names-set vs)))
      (is (= 1 (count vs)))))

  (testing "test_g_v4_out_filterXlang_eq_javaX_backX1X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 4))
                      q/-->
                      (q/filter #(= "java" (v/get % :lang)))
                      (q/back 1)
                      q/into-vec!)]
      (is (= #{"ripple" "lop"} (u/get-names-set vs)))
      (is (= 2 (count vs)))))

  (testing "test_g_v4_out_asXhereX_filterXlang_eq_javaX_backXhereX()"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 4))
                      q/-->
                      (q/as "here")
                      (q/filter #(= "java" (v/get % :lang)))
                      (q/back-to "here")
                      q/into-vec!)]
      (is (= #{"ripple" "lop"} (u/get-names-set vs)))
      (is (= 2 (count vs)))))

  (testing "test_g_v4_out_asXhereX_filterXlang_eq_javaX_backXhereX_propertyXnameX"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/find-by-id g (int 4))
                         q/-->
                         (q/as "here")
                         (q/filter #(= "java" (v/get % :lang)))
                         (q/back-to "here")
                         (q/values :name)
                         q/into-set!)]
      (is (= #{"ripple" "lop"} names))
      (is (= 2 (count names))))))
