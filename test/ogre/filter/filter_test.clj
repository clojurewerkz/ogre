(ns ogre.filter.filter-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-filter-step
  (g/use-new-tinker-graph!)
  (testing "test_g_V_filterXfalseX"
    (let [vs (q/query (g/get-vertices)
                      (q/filter (constantly false))                    
                      (q/into-vec))]
      (is (= 0 (count vs)))))

  (testing "test_g_V_filterXtrueX"
    (let [vs (q/query (g/get-vertices)
                      (q/filter (constantly true))                    
                      (q/into-vec))]
      (is (= 6 (count vs)))))
  
  (testing "test_g_V_filterXlang_eq_javaX()"
    (let [vs (q/query (g/get-vertices)
                      (q/filter (partial g/prop-pred :lang = "java"))                    
                      (q/into-vec))]
      (is (= 2 (count vs)))
      (is (= #{"lop" "ripple"} (g/get-names-set vs)))))

  (testing "test_g_v1_out_filterXage_gt_30X"
    (let [vs (q/query (g/find-by-id 1)
                      (q/-->)
                      (q/filter (fn [v]
                                  (let [age (g/get-property :age v)]
                                    (and (not (nil? age)) (> age 30)))))                    
                      (q/into-vec))]
      (is (= 1 (count vs)))
      (is (= #{32} (set (map (partial g/get-property :age) vs))))))

  (testing "test_g_V_filterXname_startsWith_m_OR_name_startsWith_pX()"
    (let [vs (q/query (g/get-vertices)
                      (q/filter #(->> %
                                    (g/get-property :name)
                                    first
                                    #{\m \p}
                                    boolean))                    
                      (q/into-vec))]
      (is (= 2 (count vs)))
      (is (= #{"marko" "peter"} (g/get-names-set vs))))))