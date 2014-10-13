(ns clojurewerkz.ogre.transform.path-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-path-step
  (testing "g.getVertex(1).property('name').path"
    (let [g (u/classic-tinkergraph)
          path (q/query (v/find-by-id g 1)
                        (q/values :name)
                        q/path
                        q/first-into-vec!)]
      (is (= "marko" (v/get (first path) :name)))
      (is (= "marko" (second path)))))

  (testing "g.getVertex(1).out.path{it.age}{it.name}"
    (let [g (u/classic-tinkergraph)
          path (q/query (v/find-by-id g 1)
                        q/-->
                        (q/path
                         #(v/get % :age)
                         #(v/get % :name))
                        q/all-into-vecs!)]
      (is (= (sort path) (sort [[29 "vadas"] [29 "josh"] [29 "lop"]])))))

  (testing "g.V.out.loop(1,loops_lt_3)X_path{it.name,it.lang}"
    (let [g (u/classic-tinkergraph)
          path (q/query (v/get-all-vertices g)
                        q/-->
                        (q/loop 1 (fn [i o p] (< i 3)))
                        (q/path identity
                                #(v/get % :name)
                                #(v/get % :lang))
                        q/all-into-vecs!)
          age  (map first path)
          names  (map second path)]
      (is (= 3 (count (first path))))
      (is (= (rest (first path)) '("josh" "java"))))))
