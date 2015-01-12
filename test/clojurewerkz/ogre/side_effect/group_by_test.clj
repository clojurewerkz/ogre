(ns clojurewerkz.ogre.side-effect.group-by-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-get-grouped-by
  (testing "g.V().groupBy{it.get().value('lang','')}{it.get().value('name','')}"
    (let [g (u/classic-tinkergraph)
          grouped (q/query (v/get-all-vertices g)
                           (q/get-grouped-by! #(v/get % :lang) #(v/get % :name)))]
      (is (= (set (grouped nil))
             #{"vadas" "marko" "peter" "josh"}))
      (is (= (set (grouped "java"))
             #{"lop" "ripple"})))))

(deftest test-group-by
  (testing "g.V().groupBy{it.get().value('name')[1]}"
    (let [g (u/classic-tinkergraph)
          grouped (q/query (v/get-all-vertices g)
                           (q/group-by #(get (v/get % :name) 1))
                           q/into-vec!
                           first
                           (->> (into {})))]
      grouped))

  (testing "g.V().groupBy{it.get().value('name')[1]}{it.get().value('name')}"
    (let [g (u/classic-tinkergraph)
          grouped (q/query (v/get-all-vertices g)
                           (q/group-by #(get (v/get % :name) 1) #(v/get % :name))
                           q/into-vec!
                           first
                           (->> (into {})))]
      (is (= (grouped \a) #{"marko" "vadas"}))))

  (testing "g.V().groupBy{it.get().value('name')[1]}{it.get().value('name')}{it.size()}"
    (let [g (u/classic-tinkergraph)
          grouped (q/query (v/get-all-vertices g)
                           (q/group-by #(get (v/get % :name) 1) #(v/get % :name) count)
                           q/into-vec!
                           first
                           (->> (into {})))]
      (is (= grouped {\a 2 \e 1 \i 1 \o 2})))))
