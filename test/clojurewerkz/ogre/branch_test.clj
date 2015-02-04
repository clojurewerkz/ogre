(ns clojurewerkz.ogre.branch-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.util :refer (anon-traversal)]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-choose
  (testing "g.V().has('age').choose({it.get().value('name').length()}, [5:g.of().in(), 4:g.of().out()]).values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :age)
                      (q/choose #(count (v/get % :name))
                                {5 (q/in)
                                 4 q/out})
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 3))
      (is (every? #{"lop" "marko" "ripple"} vs))))

  (testing "precomputed map as destination"
    (let [g (u/classic-tinkergraph)
          m {5 (-> (anon-traversal) (q/in))
             4 (-> (anon-traversal) q/out)}
          vs (q/query (v/get-all-vertices g)
                      (q/has :age)
                      (q/choose #(count (v/get % :name)) m)
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 3))
      (is (every? #{"lop" "marko" "ripple"} vs))))

  (testing "g.V().has('age').choose({it.get().value('name').length() == 5}, g.of().in(), g.of().out()).values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :age)
                      (q/choose #(= 5 (count (v/get % :name)))
                                (q/in)
                                q/out)
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 3))
      (is (every? #{"lop" "marko" "ripple"} vs)))))
