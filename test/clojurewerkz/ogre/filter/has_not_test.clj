(ns clojurewerkz.ogre.filter.has-not-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-has-not-step
  (testing "test_g_V_hasNotXname_markoX"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/has-not :name "marko")                    
                      (q/into-vec!))]
      (is (= 5 (count vs)))
      (is (not (#{"marko"} (u/get-names vs))))))

  (testing "test_g_V_hasNotXname_blahX"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/has-not :name "blah")                    
                      (q/into-vec!))]
      (is (= 6 (count vs)))
      (is (not (#{"blah"} (u/get-names vs))))))
  
  (testing "test_g_V_hasNotXblah_nullX"
    (let [g (g/use-new-tinker-graph!)
          vs (q/query (g/get-vertices g)
                      (q/has-not :blah nil)                    
                      (q/into-vec!))]
      (is (= 0 (count vs))))))
