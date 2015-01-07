(ns clojurewerkz.ogre.filter.has-test
  (:use [clojure.test])
  (:import (com.tinkerpop.gremlin.process T))
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-has-step
  (testing "g.V().has('name','marko')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :name "marko")
                      q/into-vec!)]
      (is (= 1 (count vs)))
      (is (= #{"marko"} (u/get-names-set vs)))))

  (testing "g.V().has('name','blah')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :name "blah")
                      q/into-vec!)]
      (is (= 0 (count vs)))))

  (testing "g.V().has('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :name)
                      q/into-vec!)]
     (is (= 6 (count vs)))
      (is (not (#{"blah"} (u/get-names vs))))))

; Not able to use has with T/id, probably because of type issue
;  (testing "g.V().has(T.id,2)"
;    (let [g (u/classic-tinkergraph)
;          vs (q/query (v/get-all-vertices g)
;                      (q/has T/id 2)
;                      q/into-vec!)]
;      (is (= 1 (count vs)))
;      (is (every? (partial >= 32) (u/get-ages vs)))))

  (testing "g.V().has('age',Compare.gte,30)"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :age > (int 30))
                      q/into-vec!)]
      (is (= 2 (count vs)))
      (is (every? (partial < 30) (u/get-ages vs)))))

  (testing "g.V().has('location',Contains.within,['aachen', 'san diego', 'brussels'])"
    (let [g (u/crew-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/has :location "aachen")
                      q/into-vec!)]
      (is (= 1 (count vs)))
      (is (every? (partial some #{"aachen"}) (u/get-locations vs))))))
