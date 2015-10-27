(ns clojurewerkz.ogre.filter.where-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest where-test
  (testing "g.V().match('a',
            g.of().as('a').out('created').as('b'),
            g.of().as('b').in('created').as('c')).
              where('a', neq, 'c').
               select(['a','c']).by('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/match :a
                        :a (-> (q/out [:created]) (q/as :b))
                        :b (-> (q/in [:created]) (q/as :c)))
                      (q/where not= :a :c)
                      (q/select-only [:a :c])
                      (q/by "name")
                      q/all-into-maps!)]
      (is (= (count vs) 6))
      (is (every? (comp (fn [vs]
                          (= (count vs) (count (distinct vs))))
                        vals) vs))))

  (testing "g.V().match('a',
            g.of().as('a').out('created').as('b'),
            g.of().as('b').in('created').as('c')).
              where(g.of().as('a').out('knows').as('c')).
               select(['a','c']).by('name')"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/match :a
                        :a (-> (q/out [:created]) (q/as :b))
                        :b (-> (q/in [:created]) (q/as :c)))
                      (q/where (-> (q/as :a) (q/out [:knows]) (q/as :c)))
                      (q/select-only [:a :c])
                      (q/by "name")
                      q/first-into-map!
                      vals)]
      (is (every? #{"marko" "josh"} vs)))))
