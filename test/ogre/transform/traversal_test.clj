(ns ogre.transform.traversal-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-transform-step
  (g/use-new-tinker-graph!)
  (testing "test_g_V()"
    "Nothing for Ogre to do here")
  
  (testing "test_g_v1_out()"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"})
          (u/get-names-set vs))))
  
  (testing "test_g_v2_in()"
    (let [vs (q/query (g/find-by-id 2)
                      q/<--
                      q/into-vec!)]
      (is (= #{"marko"})
          (u/get-names-set vs))))
  
  (testing "test_g_v4_both()"
    (let [vs (q/query (g/find-by-id 2)
                      q/<->
                      q/into-vec!)]
      (is (= #{"marko" "ripple" "lop"})
          (u/get-names-set vs))))
  
  (testing "test_g_E"
    "Nothign to see here")
  
  (testing "test_g_v1_outE()"
    (let [es (q/query (g/find-by-id 1)
                      q/--E>
                      q/into-vec!)]
      (is (= 3 (count es)))
      (is (= #{"created" "knows"}
             (set (map g/get-label es))))))
  
  (testing "test_g_v2_outE()"
    (let [es (q/query (g/find-by-id 2)
                      q/<E--
                      q/into-vec!)]
      (is (= 1 (count es)))
      (is (= #{"knows"}
             (set (map g/get-label es))))))
  
  (testing "test_g_v4_bothE()"
    (let [es (q/query (g/find-by-id 4)
                      q/<E>
                      q/into-vec!)]
      (is (= 3 (count es)))
      (is (= #{"knows" "created"}
             (set (map g/get-label es))))))

  (testing "test_g_v1_outE_inV"
    (let [vs (q/query (g/find-by-id 1)
                      q/--E>
                      q/in-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"})
          (u/get-names-set vs))))

  (testing "test_g_v2_inE_outV"
    (let [vs (q/query (g/find-by-id 2)
                      q/<E--
                      q/out-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"})
          (u/get-names-set vs))))

  (testing "test_g_v1_out(knows)"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--> [:knows])
                      q/into-vec!)]
      (is (= #{"vadas" "josh"})
          (u/get-names-set vs))))

  (testing "test_g_v1_out(knows created)"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--> [:knows :created])
                      q/into-vec!)]
      (is (= #{"vadas" "josh"})
          (u/get-names-set vs))))
  
  (testing "test_g_v1_outE(knows)_inV"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--E> [:knows])
                      q/in-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh"})
          (u/get-names-set vs))))

  (testing "test_g_v1_outE(knows created)_inE"
    (let [vs (q/query (g/find-by-id 1)
                      (q/--E> [:knows :created])
                      q/in-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh"})
          (u/get-names-set vs))))

  (testing "test_g_v1_out_out"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      q/-->
                      q/into-vec!)]
      (is (= #{"ripple" "lop"})
          (u/get-names-set vs))))

  (testing "test_g_v1_out_out_out"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      q/-->
                      q/-->
                      q/into-vec!)]
      (is (= 0 (count vs))))))