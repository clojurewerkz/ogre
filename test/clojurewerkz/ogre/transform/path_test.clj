(ns clojurewerkz.ogre.transform.path-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

(deftest test-path-step
  (g/use-new-tinker-graph!)
  (testing "g.getVertex(1).property('name').path"
    (let [path (q/query (g/find-by-id 1)
                        (q/property :name)
                        q/path
                        q/first-into-vec!)]
      (is (= "marko" (g/get-property :name (first path))))
      (is (= "marko" (second path)))))
  
  (testing "g.getVertex(1).out.path{it.age}{it.name}"
    (let [path (q/query (g/find-by-id 1)
                        q/-->
                        (q/path
                         (partial g/get-property :age)
                         (partial g/get-property :name))
                        q/all-into-vecs!)]
      (is (= (sort path) (sort [[29 "vadas"] [29 "josh"] [29 "lop"]])))))

  (testing "g.V.out.loop(1,loops_lt_3)X_path{it.name,it.lang}"
    (let [path (q/query (g/get-vertices)
                        q/-->
                        (q/loop 1 (fn [i o p] (< i 3)))
                        (q/path identity
                                (partial g/get-property :name)
                                (partial g/get-property :lang))
                        q/all-into-vecs!)
          age  (map first path)
          names  (map second path)]
      (is (= 3 (count (first path))))
      (is (= (rest (first path)) '("josh" "java"))))))