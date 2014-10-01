(ns clojurewerkz.ogre.transform.transform-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]))

(deftest test-transform-step

  (testing "test_g_v1_transformXnameX()"
    (let [name (q/query (g/find-by-id (g/use-new-tinker-graph!) 1)
                        (q/transform (partial g/get-property :name))
                        q/first-of!)]
      (is (= "marko" name))))
  (testing "test_g_v1_outE_label_transformXlengthX()"
    (let [names (q/query (g/find-by-id (g/use-new-tinker-graph!) 1)
                         q/-E>
                         q/label
                         (q/transform count)
                         q/into-vec!)]
      (is (= (set (map count ["knows" "created"]))
             (set names)))
      (is (= 3 (count names)))))

    (testing "test_g_v1_outE_label_transformXlengthX()"
    (let [names (q/query (g/find-by-id (g/use-new-tinker-graph!) 1)
                         q/-->
                         (q/transform (partial g/get-property :name))
                         (q/transform count)
                         q/into-vec!)]
      (is (= (set (map count ["josh" "vadas" "lop"]))
             (set names)))
      (is (= 3 (count names))))))