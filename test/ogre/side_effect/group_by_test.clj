(ns ogre.side-effect.group-by-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-group-count-step
  (g/use-new-tinker-graph!)  
  (testing "test_g_V_groupByXlang_nameX"
    (let [grouped (q/query (g/get-vertices)
                           (q/get-grouped-by! (partial g/get-property :lang)
                                             (partial g/get-property :name)))]
      (is (= (set (grouped nil))
             (set ["vadas" "marko" "peter" "josh"])))
      (is (= (set (grouped "java"))
             (set ["lop" "ripple"]))))))