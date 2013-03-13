(ns ogre.filter.back-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-transform-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_v1_out_backX1X"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/back 1)
                      q/into-vec)]
      (is (= #{"marko"} (set (map (partial g/get-property :name) vs))))
      (is (= 1 (count vs)))))
  
  (testing "test_g_v1_asXhereX_out_backXhereX()"
    (let [vs (q/query (g/find-by-id 1)
                      (q/as "here")
                      q/-->
                      (q/back-to "here")
                      q/into-vec)]
      (is (= #{"marko"} (set (map (partial g/get-property :name) vs))))
      (is (= 1 (count vs)))))

  (testing "test_g_v4_out_filterXlang_eq_javaX_backX1X"
    (let [vs (q/query (g/find-by-id 4)
                      q/-->
                      (q/filter #(= "java" (g/get-property :lang % )))
                      (q/back 1)
                      q/into-vec)]
      (is (= #{"ripple" "lop"} (set (map (partial g/get-property :name) vs))))
      (is (= 2 (count vs)))))

  (testing "test_g_v4_out_asXhereX_filterXlang_eq_javaX_backXhereX()"
    (let [vs (q/query (g/find-by-id 4)
                      q/-->
                      (q/as "here")
                      (q/filter #(= "java" (g/get-property :lang % )))
                      (q/back-to "here")
                      q/into-vec)]
      (is (= #{"ripple" "lop"} (set (map (partial g/get-property :name) vs))))
      (is (= 2 (count vs)))))

  (testing "test_g_v4_out_asXhereX_filterXlang_eq_javaX_backXhereX_propertyXnameX"
    (let [names (q/query (g/find-by-id 4)
                      q/-->
                      (q/as "here")
                      (q/filter #(= "java" (g/get-property :lang % )))
                      (q/back-to "here")
                      (q/property :name)
                      q/into-set)]
      (is (= #{"ripple" "lop"} names))
      (is (= 2 (count names))))))