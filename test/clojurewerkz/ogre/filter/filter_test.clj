(ns clojurewerkz.ogre.filter.filter-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-filter-step
  (testing "g.V().filter{false}"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter (constantly false))
                      q/into-vec!)]
      (is (= 0 (count vs)))))

  (testing "g.V().filter{true}"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter (constantly true))
                      q/into-vec!)]
      (is (= 6 (count vs)))))

  (testing "g.V().filter{it.get().value('lang','') == 'java'}"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter (partial v/prop-pred :lang = "java"))
                      q/into-vec!)]
      (is (= 2 (count vs)))
      (is (= #{"lop" "ripple"} (u/get-names-set vs)))))

  (testing "g.v(1).out().filter{it.get().value('age',0) > 30}"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/filter (fn [v]
                                  (let [age (v/get v :age)]
                                    (and (not (nil? age)) (> age 30)))))
                      q/into-vec!)]
      (is (= 1 (count vs)))
      (is (= #{32} (set (map #(v/get % :age) vs))))))

  (testing "g.V().filter{it.get().value('name').matches('^(m|p).*')}"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter #(->> %
                                    (v/get % :name)
                                    first
                                    #{\m \p}
                                    boolean))
                      q/into-vec!)]
      (is (= 2 (count vs)))
      (is (= #{"marko" "peter"} (u/get-names-set vs))))))
