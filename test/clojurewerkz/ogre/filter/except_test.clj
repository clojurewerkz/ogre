(ns clojurewerkz.ogre.filter.except-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]            
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-except-step
  (g/use-new-tinker-graph!)
  (testing "test_g_v1_out_exceptXg_v2X"
    (let [vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/except [(g/find-by-id 2)])
                      (q/into-vec!))]
      (is (= #{"josh" "lop"} (u/get-names-set vs)))))
  (testing "test_g_v1_out_aggregateXxX_out_exceptXxX"
    ;;TODO reapproach this once aggregate has been figured out
    )
  (testing "test_g_v1_outXcreatedX_inXcreatedX_exceptXg_v1X_propertyXnameX"
    (let [names (q/query (g/find-by-id 1)
                         (q/--> [:created])
                         (q/<-- [:created])
                         (q/except [(g/find-by-id 1)])
                         (q/property :name)
                         (q/into-set!))]
      (is (= #{"peter" "josh"} names)))))