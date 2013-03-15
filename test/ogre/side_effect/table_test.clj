(ns ogre.side-effect.table-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-table-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_v1_asXaX_out_properyXnameX_asXbX_table_cap"
    (let [elem (g/find-by-id 1)
          table (q/query elem
                         (q/as "a")
                         q/--> 
                         (q/property :name)
                         (q/as "b")
                         q/get-table)]
      (is (= #{{:a elem :b "josh"}
               {:a elem :b "lop"}
               {:a elem :b "vadas"}}
             (set table)))))

  (testing "test_g_v1_asXaX_out_asXbX_tableXnameX_cap"
    (let [elem (g/find-by-id 1)
          table (q/query elem
                         (q/as "a")
                         q/--> 
                         (q/as "b")
                         (q/get-table (partial g/get-property :name)))]
      (is (= #{{:a "marko" :b "josh"}
               {:a "marko" :b "lop"}
               {:a "marko" :b "vadas"}}
             (set table)))))

  (testing "test_g_v1_asXaX_out_propertyXnameX_asXbX_tableXname_lengthX_cap"
    (let [elem (g/find-by-id 1)
          table (q/query elem
                         (q/as "a")
                         q/-->
                         (q/property :name)
                         (q/as "b")
                         (q/get-table (partial g/get-property :name)
                                      count))]
      (is (= #{{:a "marko" :b 3}
               {:a "marko" :b 4}
               {:a "marko" :b 5}}
             (set table))))))