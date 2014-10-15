(ns clojurewerkz.ogre.transform.select-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-select-step
  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select()"
    (let [g (u/classic-tinkergraph)
           selection (q/query (v/find-by-id g (int 1))
                             (q/as "a")
                             (q/--> [:knows])
                             (q/as "b")
                             q/select
                             q/all-into-vecs!)]
      ;;TODO turn these into maps in core
      (is (= #{1} (set (map (comp v/id-of val first) selection))))
      (is (= #{2 4} (set (map (comp v/id-of val second) selection))))
      (is (= 2 (count selection)))
      (is (= 2 (count (first selection))))))

  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select()"
    (let [g (u/classic-tinkergraph)
          selection (q/query (v/find-by-id g (int 1))
                             (q/as "a")
                             (q/--> [:knows])
                             (q/as "b")
                             (q/select [#(v/get % :name)])
                             q/all-into-vecs!)]
      (is (= #{"marko"} (set (map val (map first selection)))))
      (is (= #{"josh" "vadas"} (set (map val (map second selection)))))
      (is (= 2 (count selection)))
      (is (= 2 (count (first selection))))))

  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select([a])"
    (let [g (u/classic-tinkergraph)
          selection (q/query (v/find-by-id g (int 1))
                             (q/as "a")
                             (q/--> [:knows])
                             (q/as "b")
                             (q/select-only ["a"])
                             q/all-into-vecs!)]
      (is (= 2 (count selection)))
      (is (= 1 (count (first selection))))
      (is (= #{1} (set (map (comp v/id-of val first) selection))))))

  (testing "test_g_v1_asXaX_outXknowsX_asXbX_select([a],name)"
    (let [g (u/classic-tinkergraph)
          selection (q/query (v/find-by-id g (int 1))
                             (q/as "a")
                             (q/--> [:knows])
                             (q/as "b")
                             (q/select-only ["a"] #(v/get % :name))
                             q/all-into-vecs!)]
      (is (= 2 (count selection)))
      (is (= 1 (count (first selection))))
      (is (= #{"marko"} (set (map (comp val first) selection)))))))
