(ns clojurewerkz.ogre.lazy
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.tinkergraph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-laziness
  (testing "Laziness!"
    (let [state (atom [])
          g (g/use-new-tinker-graph!)
          vs (q/query (g/find-by-id g 1)
                      q/-->
                      (q/side-effect (fn [_] (swap! state conj nil)))
                      (q/property :name)
                      q/into-lazy-seq!)]
      (is (= 1 (count @state)))
      (do (first vs))
      (is (= 1 (count @state)))
      (doall (take 2 vs))
      (is (= 2 (count @state)))
      (doall (take 4 vs))
      (is (= 3 (count @state)))
      (doall (take 2 vs))
      (is (= 3 (count @state)))))

  (testing "Laziness and mutatibility!"
    (let [state (atom #{})
          g (g/use-new-tinker-graph!)
          vs (q/query (g/find-by-id g 1)
                      q/-->
                      (q/side-effect (fn [v] 
                                       (swap! state conj
                                              (.getProperty v "name"))))
                      (q/property :name))
          v1 (q/into-lazy-seq! vs)
          v2 (q/into-lazy-seq! vs)]
      ;;The following tests show that somehow the lazy lists are
      ;;interacting. Whenever a lazy list is created from a query, it
      ;;doesn't effect any other query (anymore)!
      (is (= #{(first v1)} @state))
      (is (= #{(first v2)} @state))

      ;;In fact, every thing derived from a half created pipe effects
      ;;every other thing derived from that same pipe. Troubling.
      (is (= (sort ["vadas" "josh" "lop"]) (sort (q/into-vec! vs)))))))
