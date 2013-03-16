(ns ogre.transform.select-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-select-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select()"
    (let [selection (q/query (g/find-by-id 1)
                             (q/as "a")
                             (q/--> :knows)
                             (q/as "b")
                             q/select
                             q/all-into-vecs!)]
      ;;TODO turn these into maps in core
      (is (= #{"1"} (set (map (comp g/get-id first) selection))))
      (is (= #{"2" "4"} (set (map (comp g/get-id second) selection))))
      (is (= 2 (count selection)))
      (is (= 2 (count (first selection))))))
  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select()"
    (let [selection (q/query (g/find-by-id 1)
                             (q/as "a")
                             (q/--> :knows)
                             (q/as "b")
                             (q/select (partial g/get-property :name))
                             q/all-into-vecs!)]
      (is (= #{"marko"} (set (map first selection))))
      (is (= #{"josh" "vadas"} (set (map second selection))))
      (is (= 2 (count selection)))
      (is (= 2 (count (first selection))))))
  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select([a])"
    (let [selection (q/query (g/find-by-id 1)
                             (q/as "a")
                             (q/--> :knows)
                             (q/as "b")
                             (q/select-only ["a"])
                             q/all-into-vecs!)]
      (is (= 2 (count selection)))
      (is (= 1 (count (first selection))))
      (is (= #{"1"} (set (map (comp g/get-id first) selection))))))
  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select([a],name)"
    (let [selection (q/query (g/find-by-id 1)
                             (q/as "a")
                             (q/--> :knows)
                             (q/as "b")
                             (q/select-only ["a"] (partial g/get-property :name))
                             q/all-into-vecs!)]
      (is (= 2 (count selection)))
      (is (= 1 (count (first selection))))
      (is (= #{"marko"} (set (map first selection)))))))