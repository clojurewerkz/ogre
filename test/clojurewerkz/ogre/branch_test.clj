(ns clojurewerkz.ogre.branch-test
  (:use [clojure.test])
  (:import (com.tinkerpop.gremlin.process T))
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-jump
  (testing "g.v(1).as('a').out().jump('a'){it.loops()<2}.values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/as "a")
                      q/out
                      (q/jump "a" (fn [l o] (< l 2)))
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 2))
      (is (every? #{"ripple" "lop"} vs))))

  (testing "g.v(1).as('a').out().jump('a', 2).values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/as "a")
                      q/out
                      (q/jump "a" 2)
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 2))
      (is (every? #{"ripple" "lop"} vs))))

  (testing "g.v(1).jump('a').out().out().out().as('a').values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/jump "a")
                      q/out
                      q/out
                      q/out
                      (q/as "a")
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 1))
      (is (every? #{"marko"} vs))))

  (testing "g.v(1).as('a').out().jump('a'){it.loops()<2}{true}.values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/as "a")
                      q/out
                      (q/jump "a" (fn [l o] (< l 2)) (constantly true))
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 5))
      (is (every? #{"lop" "vadas" "josh" "ripple"} vs)))))

(deftest test-until
  (testing "g.v(1).until('b'){it.loops() > 1}.out().as('b').values('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/until "b" (fn [l o] (> l 1)))
                      q/out
                      (q/as "b")
                      (q/values :name)
                      q/into-vec!)]
      (is (= (count vs) 2))
      (is (every? #{"lop" "ripple"} vs)))))
