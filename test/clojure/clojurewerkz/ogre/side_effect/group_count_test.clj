(ns clojurewerkz.ogre.side-effect.group-count-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-get-group-count-step
  (testing "g.V().out('created').groupCount{it.get().value('name')}"
    (let [g (u/classic-tinkergraph)
          group (q/query (v/get-all-vertices g)
                         (q/--> [:created])
                         (q/get-group-count! #(v/get % :name)))]
      (is (= group {"lop" 3 "ripple" 1})))))

(deftest test-group-count
  (testing "g.V().has('age').values('age').groupCount()"
    (let [g (u/classic-tinkergraph)
          group (q/query (v/get-all-vertices g)
                         (q/has :age)
                         (q/values :age)
                         q/group-count
                         q/into-vec!
                         first
                         (->> (into {})))]
      (is (= group {32 1 35 1 27 1 29 1}))))

  (testing "g.V().has('age').groupCount{it.get().value('age')}"
    (let [g (u/classic-tinkergraph)
          group (q/query (v/get-all-vertices g)
                         (q/has :age)
                         (q/group-count #(v/get % :age))
                         q/into-vec!
                         first
                         (->> (into {})))]
      (is (= group {32 1 35 1 27 1 29 1})))))
