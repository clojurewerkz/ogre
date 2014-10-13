(ns clojurewerkz.ogre.filter.except-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-except-step
  (testing "test_g_v1_out_exceptXg_v2X"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/except [(v/find-by-id g (int 2))])
                      (q/into-vec!))]
      (is (= #{"josh" "lop"} (u/get-names-set vs)))))
  (testing "test_g_v1_out_aggregateXxX_out_exceptXxX"
    ;;TODO reapproach this once aggregate has been figured out
    )
  (testing "test_g_v1_outXcreatedX_inXcreatedX_exceptXg_v1X_propertyXnameX"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/find-by-id g (int 1))
                         (q/--> [:created])
                         (q/<-- [:created])
                         (q/except [(v/find-by-id g (int 1))])
                         (q/property :name)
                         (q/into-set!))]
      (is (= #{"peter" "josh"} names)))))
