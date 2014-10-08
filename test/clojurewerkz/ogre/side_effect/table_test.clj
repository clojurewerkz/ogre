(ns clojurewerkz.ogre.side-effect.table-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-table-step
  (testing "test_g_v1_asXaX_out_properyXnameX_asXbX_table_cap"
    (let [g (u/classic-tinkergraph)
          elem (v/find-by-id g 1)
          table (q/query elem
                         (q/as "a")
                         q/-->
                         (q/property :name)
                         (q/as "b")
                         q/get-table!)]
      (is (= #{{:a elem :b "josh"}
               {:a elem :b "lop"}
               {:a elem :b "vadas"}}
             (set table)))))

  (testing "test_g_v1_asXaX_out_asXbX_tableXnameX_cap"
    (let [g (u/classic-tinkergraph)
          elem (v/find-by-id g 1)
          table (q/query elem
                         (q/as "a")
                         q/-->
                         (q/as "b")
                         (q/get-table! #(v/get % :name)))]
      (is (= #{{:a "marko" :b "josh"}
               {:a "marko" :b "lop"}
               {:a "marko" :b "vadas"}}
             (set table)))))

  (testing "test_g_v1_asXaX_out_propertyXnameX_asXbX_tableXname_lengthX_cap"
    (let [g (u/classic-tinkergraph)
          elem (v/find-by-id g 1)
          table (q/query elem
                         (q/as "a")
                         q/-->
                         (q/property :name)
                         (q/as "b")
                         (q/get-table! #(v/get % :name)
                                      count))]
      (is (= #{{:a "marko" :b 3}
               {:a "marko" :b 4}
               {:a "marko" :b 5}}
             (set table))))))
