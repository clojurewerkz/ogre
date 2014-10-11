(ns clojurewerkz.ogre.filter.has-test
  (:use [clojure.test])
  (:import (com.tinkerpop.gremlin.process T))
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.test-util :as u]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]))

(deftest test-has-step
  (testing "test_g_V_hasXname_markoX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :name "marko")
                      (q/into-vec!))]
      (is (= 1 (count vs)))
      (is (= #{"marko"} (u/get-names-set vs)))))

  (testing "test_g_V_hasXname_blahX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :name "blah")
                      (q/into-vec!))]
      (is (= 0 (count vs)))))

  (testing "test_g_V_hasXnameX"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :name)
                      (q/into-vec!))]
     (is (= 6 (count vs)))
      (is (not (#{"blah"} (u/get-names vs))))))

;  (testing "test_g_v1_out_hasXid_2X"
;    (let [g (u/classic-tinkergraph)
;          vs (q/query (v/get-all-vertices g)
;                      (q/has T/id 2)
;                      (q/into-vec!))]
;      (is (= 1 (count vs)))
;      (is (every? (partial >= 32) (u/get-ages vs)))))

  (testing "test_g_V_hasXage_gt_32X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :age > (int 30))
                      (q/into-vec!))]
      (is (= 2 (count vs)))
      (is (every? (partial < 30) (u/get-ages vs))))))
