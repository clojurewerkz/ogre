(ns ogre.lazy
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-laziness
  (g/use-new-tinker-graph!)  
  (testing "Laziness!"
    (let [state (atom [])
          vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/side-effect (fn [_] (swap! state conj nil)))
                      (q/property :name)
                      q/into-lazy-seq!)]
      (is (= 1 (count @state)))
      (is (= "vadas" (first vs)))
      (is (= 1 (count @state)))
      (is (= '("vadas" "josh") (take 2 vs)))
      (is (= 2 (count @state)))
      (is (= '("vadas" "josh" "lop" nil) (take 4 vs)))
      (is (= 3 (count @state)))
      (is (= '("vadas" "josh") (take 2 vs)))
      (is (= 3 (count @state)))))

  (testing "Laziness and mutatibility!"
    (let [state (atom #{})
          vs (q/query (g/find-by-id 1)
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
      (is (= #{"vadas"} @state))
      (is (= "vadas" (first v1)))
      (is (= #{"vadas"} @state))
      (is (= "vadas" (first v2))) 
      (is (= #{"vadas"} @state))

      ;;In fact, every thing derived from a half created pipe effects
      ;;every other thing derived from that same pipe. Troubling.
      (is (= ["vadas" "josh" "lop"] (q/into-vec! vs)))))) 
