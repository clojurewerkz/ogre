(ns clojurewerkz.ogre.side-effect.group-by-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-group-count-step
  (testing "test_g_V_groupByXlang_nameX"
    (let [g (g/use-new-tinker-graph!)
          grouped (q/query (g/get-vertices g)
                           (q/get-grouped-by! (partial g/get-property :lang)
                                             (partial g/get-property :name)))]
      (is (= (set (grouped nil))
             (set ["vadas" "marko" "peter" "josh"])))
      (is (= (set (grouped "java"))
             (set ["lop" "ripple"]))))))