(ns clojurewerkz.ogre.filter.has-not-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-has-not-step
  (testing "test_g_V_hasNotXname_markoX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has-not :name "marko")
                      (q/into-vec!))]
      (is (= 5 (count vs)))
      (is (not (#{"marko"} (u/get-names vs))))))

  (testing "test_g_V_hasNotXname_blahX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has-not :name "blah")
                      (q/into-vec!))]
      (is (= 6 (count vs)))
      (is (not (#{"blah"} (u/get-names vs))))))

  (testing "test_g_V_hasNotXblah_nullX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has-not :blah nil)
                      (q/into-vec!))]
      (is (= 0 (count vs))))))
