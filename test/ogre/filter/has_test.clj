(ns ogre.filter.has-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-has-step
  (g/use-new-tinker-graph!)
  (testing "test_g_V_hasXname_markoX"
    (let [vs (q/query (g/get-vertices)
                      (q/has :name "marko")                    
                      (q/into-vec))]
      (is (= 1 (count vs)))
      (is (= #{"marko"} (g/get-names-set vs)))))

  (testing "test_g_V_hasXname_blahX"
    (let [vs (q/query (g/get-vertices)
                      (q/has :name "blah")                    
                      (q/into-vec))]
      (is (= 0 (count vs)))))
  
  (testing "test_g_V_hasXblah_nullX"
    (let [vs (q/query (g/get-vertices)
                      (q/has :blah nil)                    
                      (q/into-vec))]
      (is (= 6 (count vs)))
      (is (not (#{"blah"} (g/get-names vs))))))
  
  (testing "test_g_v1_out_hasXid_2X"
    (let [vs (q/query (g/get-vertices)
                      (q/has :id "2")                    
                      (q/into-vec))]
      (is (= 1 (count vs)))
      (is (every? (partial >= 32) (g/get-ages vs)))))
  
  (testing "test_g_V_hasXage_gt_32X"
    (let [vs (q/query (g/get-vertices)
                      (q/has :age > (int 30))                    
                      (q/into-vec))]
      (is (= 2 (count vs)))
      (is (every? (partial < 30) (g/get-ages vs))))))