(ns clojurewerkz.ogre.filter.has-not-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-has-not-step
  (testing "g.V().hasNot('age')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has-not :age)
                      q/into-vec!)]
      (is (= 2 (count vs)))
      (is (= (#{"lop" "ripple"} (u/get-names vs)))))))
