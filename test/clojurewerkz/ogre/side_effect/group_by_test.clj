(ns clojurewerkz.ogre.side-effect.group-by-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-group-count-step
  (testing "test_g_V_groupByXlang_nameX"
    (let [g (g/new-tinkergraph)
          grouped (q/query (v/get-all-vertices g)
                           (q/get-grouped-by! #(v/get % :lang)
                                             #(v/get % :name)))]
      (is (= (set (grouped nil))
             (set ["vadas" "marko" "peter" "josh"])))
      (is (= (set (grouped "java"))
             (set ["lop" "ripple"]))))))