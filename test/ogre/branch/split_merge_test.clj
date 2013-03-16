(ns ogre.branch.split-merge-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-split-merge-test-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_copySplitXpropertyXnameX__propertyXageXX_fairMerge"
    (let [props (q/query (g/find-by-id 1)
                         q/-->
                         (q/copy-split
                          (q/bare-pipe (q/property :name))
                          (q/bare-pipe (q/property :age)))
                         q/fair-merge
                         (q/into-vec!)
                         ((partial partition-all 2)))]
      (is (every? #{'("vadas" 27) '("josh" 32) '("lop" nil)}
                  props))))

  (testing "test_g_v1_outXknowsX_copySplitXpropertyXnameX__propertyXageXX_exhaustMerge"
    (let [props (q/query (g/find-by-id 1)
                         (q/--> :knows)
                         (q/copy-split
                          (q/bare-pipe (q/property :name))
                          (q/bare-pipe (q/property :age)))
                         q/exhaust-merge
                         (q/into-vec!))]
      (is (= #{"vadas" "josh"} (set (take 2 props))))
      (is (= #{27 32} (set (drop 2 props))))))

    (testing "test_g_v1_outXknowsX_copySplitXpropertyXnameX__propertyXageXX_exhaustMerge_path"
    (let [props (q/query (g/find-by-id 1)
                         (q/--> :knows)
                         (q/copy-split
                          (q/bare-pipe (q/property :name))
                          (q/bare-pipe (q/property :age)))
                         q/exhaust-merge
                         q/path
                         (q/all-into-vecs!))
          names (take 2 props)
          ages  (drop 2 props)]
      (is (= 2 (count names)))
      (is (= 3 (count (first names))))
      (is (= 2 (count ages)))
      (is (= 3 (count (first ages))))      

      (is (= "1" (g/get-id (nth (nth names 0) 0))))
      (is (= "1" (g/get-id (nth (nth ages  0) 0))))
      (is (= "1" (g/get-id (nth (nth names 1) 0))))
      (is (= "1" (g/get-id (nth (nth ages  1) 0))))

      (is (= "2" (g/get-id (nth (nth names 0) 1))))
      (is (= "2" (g/get-id (nth (nth ages  0) 1))))
      (is (= "4" (g/get-id (nth (nth names 1) 1))))
      (is (= "4" (g/get-id (nth (nth ages  1) 1))))

      (is (= "vadas" (nth (nth names 0) 2)))
      (is (= 27      (nth (nth ages  0) 2)))
      (is (= "josh"  (nth (nth names 1) 2)))
      (is (= 32      (nth (nth ages  1) 2))))))                        