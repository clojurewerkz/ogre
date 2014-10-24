(ns clojurewerkz.ogre.filter.range-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-range-step
  (testing "g.v(1).out().range(0,1)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/-->)
                      (q/range 0 1)
                      (q/into-vec!))]
      (is (= 2 (count vs)))))
  (testing "g.v(1).out('knows').outE('created').range(0,0).inV()"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/--> [:knows])
                      (q/-E> [:created])
                      (q/range 0 0)
                      (q/in-vertex)
                      (q/into-vec!))]
      (is (some #{"ripple" "lop"} (u/get-names vs)))
      (is (= 1 (count vs)))))
  (testing "g.v(1).out('knows').out('created').range(0,0)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/--> [:knows])
                      (q/--> [:created])
                      (q/range 0 0)
                      (q/into-vec!))]
      (is (some #{"ripple" "lop"} (u/get-names vs)))
      (is (= 1 (count vs)))))
  (testing "g.v(1).out('created').in('created').range(1,2)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/--> [:created])
                      (q/<-- [:created])
                      (q/range 1 2)
                      (q/into-vec!))]
      (is (some #{"josh" "peter" "marko"} (u/get-names vs)))
      (is (= 2 (count vs)))))
  (testing "g.v(1).out('created').inE('created').range(1,2).outV()"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/--> [:created])
                      (q/<E- [:created])
                      (q/range 1 2)
                      q/out-vertex
                      (q/into-vec!))]
      (is (some #{"josh" "peter" "marko"} (u/get-names vs)))
      (is (= 2 (count vs))))))
