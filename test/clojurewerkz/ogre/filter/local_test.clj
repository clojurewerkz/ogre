(ns clojurewerkz.ogre.filter.local-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.util :as util]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-local-step
  (testing "g.V().local(__.outE().count())"
    (let [g (u/modern-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/local (-> (util/anon-traversal)
                                   q/-E>
                                   q/count))
                      q/into-vec!)]
      (is (= 6 (count vs)))
      (is (= (frequencies [3 0 0 0 1 2])  (frequencies vs))))))
