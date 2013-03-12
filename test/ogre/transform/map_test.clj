(ns ogre.transform.map-test
  (:use [clojure.test])
  (:import (com.tinkerpop.blueprints.impls.tg TinkerGraphFactory))
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-map-step
  (g/use-new-tinker-graph!)
  (testing "g(v1).map"
    (let [m (q/query (g/find-by-id 1)
                     q/map
                     q/first-into-map)]
      (is (= "marko" (:name m)))
      (is (= 29 (:age m)))
      (is (= 2 (count m)))))
  ;;Gremlin 0.2.3?
  ;; (testing "g(v1).map('name' 'id')"
  ;;   (let [m (q/query (g/find-by-id 1)
  ;;                    (q/map :name :id)
  ;;                   q/first-into-map)]
  ;;     (println m)
  ;;     (is (= "marko" (:name m)))
  ;;     (is (= 29 (:age m)))
  ;;     (is (= 2 (count m)))))
  (testing "g(v1).out('knows').map()"
    (let [ms (q/query (g/find-by-id 1)
                     (q/--> :knows)
                     q/map
                     q/all-into-maps)
          vadas (first (filter #(= "vadas" (:name %)) ms))
          josh  (first (filter #(= "josh" (:name %)) ms))
          ]
      (is (= 27 (:age vadas)))
      (is (= 32 (:age josh)))
      (is (= 2 (count ms))))))
