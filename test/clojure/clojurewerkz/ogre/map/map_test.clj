(ns clojurewerkz.ogre.map.map-test
  (:import (com.tinkerpop.gremlin.process Traverser))
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-map-step
  (testing "g.v(1).map{it.get().value('name')}.next()"
    (let [name (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                        (q/map #(v/get % :name))
                        q/first-of!)]
      (is (= "marko" name))))

  (testing "g.v(1).outE().label().map{it.get().length()}"
    (let [names (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         q/-E>
                         q/label
                         (q/map #(count (name (.get ^Traverser %))))
                         q/into-vec!)]
      (is (= (set (map count ["knows" "created"]))
             (set names)))
      (is (= 3 (count names)))))

  (testing "g.v(1).out().map{it.get().value('name')}.map{it.get().length()}"
    (let [names (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                         q/-->
                         (q/map #(v/get % :name))
                         (q/map #(count (.get ^Traverser %)))
                         q/into-vec!)]
      (is (= (set (map count ["josh" "vadas" "lop"]))
             (set names)))
      (is (= 3 (count names))))))
