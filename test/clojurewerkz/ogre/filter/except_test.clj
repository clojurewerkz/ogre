(ns clojurewerkz.ogre.filter.except-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-except-step
  (testing "g.v(1).out().except([g.v(2)])"
    (let [g (u/classic-tinkergraph)
          vs (q/query (v/find-by-id g (int 1))
                      q/-->
                      (q/except [(v/find-by-id g (int 2))])
                      (q/into-vec!))]
      (is (= #{"josh" "lop"} (u/get-names-set vs)))))

  (testing "g.v(1).out().aggregate().except([g.v(2)])"
    ;;TODO reapproach this once aggregate has been figured out
    )

  (testing "g.v(1).out('created').in('created').except([g.v(1)]).values('name')"
    (let [g (u/classic-tinkergraph)
          names (q/query (v/find-by-id g (int 1))
                         (q/--> [:created])
                         (q/<-- [:created])
                         (q/except [(v/find-by-id g (int 1))])
                         (q/values :name)
                         (q/into-set!))]
      (is (= #{"peter" "josh"} names)))))
