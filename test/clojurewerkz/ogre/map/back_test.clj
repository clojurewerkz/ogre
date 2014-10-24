(ns clojurewerkz.ogre.map.back-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-back-step
  (testing "g.v(1).as('here').out().back('here')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      (q/as "here")
                      q/-->
                      (q/back "here")
                      q/into-vec!)]
      (is (= #{"marko"} (u/get-names-set vs)))
      (is (= 3 (count vs)))))

  (testing "g.v(4).out().as('here').filter{it.get().value('lang','') == 'java'}.back('here')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 4))
                      q/-->
                      (q/as "here")
                      (q/filter #(= "java" (v/get (.get %) :lang)))
                      (q/back "here")
                      q/into-vec!)]
      (is (= #{"ripple" "lop"} (u/get-names-set vs)))
      (is (= 2 (count vs)))))

  (testing "g.v(4).out().as('here').filter{it.get().value('lang','') == 'java'}.back('here').values('name')"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/find-by-id g (int 4))
                         q/-->
                         (q/as "here")
                         (q/filter #(= "java" (v/get (.get %) :lang)))
                         (q/back "here")
                         (q/values :name)
                         q/into-set!)]
      (is (= #{"ripple" "lop"} names))
      (is (= 2 (count names))))))
