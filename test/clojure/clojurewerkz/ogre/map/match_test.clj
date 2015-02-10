(ns clojurewerkz.ogre.map.match-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-match
  (testing "g.V().match('a',
            g.of().as('a').out('created').as('b'),
            g.of().as('b').has('name', 'lop'),
            g.of().as('b').in('created').as('c'),
            g.of().as('c').has('age', 29)).
          select(['a', 'c']){it.value('name')}"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/match :a
                        :a (-> (q/out [:created]) (q/as :b))
                        :b (q/has :name "lop")
                        :b (-> (q/in [:created]) (q/as :c))
                        :c (q/has :age (int 29)))
                      (q/select-only [:a :c] #(v/get % :name))
                      q/all-into-maps!)]
      (is (= (count vs) 3))
      (is (= (first vs) {:a "marko" :c "marko"}))
      (is (= (second vs) {:a "josh" :c "marko"})))))
