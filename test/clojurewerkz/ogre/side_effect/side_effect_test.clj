(ns clojurewerkz.ogre.side-effect.side-effect-test
  (:use [clojure.test])
  (:import (com.tinkerpop.gremlin.process Traverser))
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-side-effect-step
  (testing "g.v(1).sideEffect{lst.add(it)}.values('name')"
    (let [lst (atom [])
          g (u/classic-tinkergraph)
          elem (v/find-by-id g (int 1))
          names (q/query elem
                      (q/side-effect (partial swap! lst conj))
                      (q/values :name)
                      q/into-vec!)]
      (is (= elem (.get ^Traverser (first @lst))))
      (is (= "marko" (first names)))))

  (testing "g.v(1).out().sideEffect{lst.add(it)}.values('name')"
    (let [lst (atom [])
          g (u/classic-tinkergraph)
          elem (v/find-by-id g (int 1))
          names (q/query elem
                      q/-->
                      (q/side-effect (partial swap! lst conj))
                      (q/values :name)
                      q/into-vec!)]
      (is (= 3 (count @lst)))
      (is (= #{"josh" "lop" "vadas"} (set names))))))
