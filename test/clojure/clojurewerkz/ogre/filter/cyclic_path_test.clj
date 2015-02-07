(ns clojurewerkz.ogre.filter.cyclic-path-test
  (:import (com.tinkerpop.gremlin.process T))
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-cyclic-path-step
  (testing "g_v1_outXcreatedX_inXcreatedX_cyclicPath"
    (let [g (u/modern-tinkergraph)
          paths (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         (q/--> [:created])
                         (q/<-- [:created])
                         (q/cyclic-path)
                         q/into-vec!)]
      (is (= 1 (count paths)))
      (is (= #{"marko"} (u/get-names-set paths))))))
