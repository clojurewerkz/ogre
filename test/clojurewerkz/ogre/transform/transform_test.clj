(ns clojurewerkz.ogre.transform.transform-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]))

(deftest test-transform-step

  (testing "test_g_v1_transformXnameX()"
    (let [name (q/query (v/find-by-id (g/new-tinkergraph) 1)
                        (q/transform #(v/get % :name))
                        q/first-of!)]
      (is (= "marko" name))))
  (testing "test_g_v1_outE_label_transformXlengthX()"
    (let [names (q/query (v/find-by-id (g/new-tinkergraph) 1)
                         q/-E>
                         q/label
                         (q/transform count)
                         q/into-vec!)]
      (is (= (set (map count ["knows" "created"]))
             (set names)))
      (is (= 3 (count names)))))

    (testing "test_g_v1_outE_label_transformXlengthX()"
    (let [names (q/query (v/find-by-id (g/new-tinkergraph) 1)
                         q/-->
                         (q/transform #(v/get % :name))
                         (q/transform count)
                         q/into-vec!)]
      (is (= (set (map count ["josh" "vadas" "lop"]))
             (set names)))
      (is (= 3 (count names))))))