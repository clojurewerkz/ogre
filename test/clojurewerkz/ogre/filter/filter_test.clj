(ns clojurewerkz.ogre.filter.filter-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-filter-step
  (testing "test_g_V_filterXfalseX"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/filter (constantly false))                    
                      (q/into-vec!))]
      (is (= 0 (count vs)))))

  (testing "test_g_V_filterXtrueX"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/filter (constantly true))                    
                      (q/into-vec!))]
      (is (= 6 (count vs)))))
  
  (testing "test_g_V_filterXlang_eq_javaX()"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/filter (partial u/prop-pred :lang = "java"))                    
                      (q/into-vec!))]
      (is (= 2 (count vs)))
      (is (= #{"lop" "ripple"} (u/get-names-set vs)))))

  (testing "test_g_v1_out_filterXage_gt_30X"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/find-by-id g 1)
                      (q/-->)
                      (q/filter (fn [v]
                                  (let [age (g/get-property :age v)]
                                    (and (not (nil? age)) (> age 30)))))                    
                      (q/into-vec!))]
      (is (= 1 (count vs)))
      (is (= #{32} (set (map (partial g/get-property :age) vs))))))

  (testing "test_g_V_filterXname_startsWith_m_OR_name_startsWith_pX()"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/filter #(->> %
                                    (g/get-property :name)
                                    first
                                    #{\m \p}
                                    boolean))                    
                      (q/into-vec!))]
      (is (= 2 (count vs)))
      (is (= #{"marko" "peter"} (u/get-names-set vs))))))