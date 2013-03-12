(ns ogre.transform.order-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.test-util :as g]))

(deftest test-order-step
  (g/use-new-tinker-graph!)
  (testing "g(g.getVertices).property('name').order"
    (let [names (q/query (g/get-vertices)
                         (q/property :name)
                         q/order
                         q/into-vec)]
      (is ["josh" "lop" "marko""peter" "ripple" "vadas"] names)))
  (testing "g(g.getVertices).property('name').order(ab)"
    (let [names (q/query (g/get-vertices)
                         (q/property :name)
                         (q/order #(compare %2 %1))
                         q/into-vec)]
      (is ["vadas""ripple""peter" "marko" "lop" "josh"] names)))

  (testing "g(g.getVertices).order(a.name>b.name).property('name')"
    (let [names (q/query (g/get-vertices)
                         (q/order (fn [a b]
                                    (compare (g/get-property a :name)
                                             (g/get-property b :name))))
                         (q/property :name)
                         q/into-vec)]
      (is ["vadas""ripple""peter" "marko" "lop" "josh"] names)))

  ;;Gremlin 0.2.3 ?
  ;; (testing "g(g.getVertices).property('name').order(decr)"
  ;;   (let [names (q/query (g/get-vertices)
  ;;                        (q/property :name)
  ;;                        q/order-decr
  ;;                        q/into-vec)]
  ;;     (is ["vadas""ripple""peter" "marko" "lop" "josh"] names))) 
  )
