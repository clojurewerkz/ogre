(ns clojurewerkz.ogre.map.map-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-map-step
  (testing "test_g_v1_mapXnameX()"
    (let [name (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                        (q/map #(v/get (.get %) :name))
                        q/first-of!)]
      (is (= "marko" name))))

  (testing "test_g_v1_outE_label_mapXlengthX()"
    (let [names (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         q/-E>
                         q/label
                         (q/map #(count (.get %)))
                         q/into-vec!)]
      (is (= (set (map count ["knows" "created"]))
             (set names)))
      (is (= 3 (count names)))))

  (testing "test_g_v1_outE_label_mapXlengthX()"
    (let [names (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         q/-->
                         (q/map #(v/get (.get %) :name))
                         (q/map #(count (.get %)))
                         q/into-vec!)]
      (is (= (set (map count ["josh" "vadas" "lop"]))
             (set names)))
      (is (= 3 (count names))))))