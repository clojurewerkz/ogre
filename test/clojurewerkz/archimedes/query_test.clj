(ns clojurewerkz.archimedes.query-test
  (:require [clojurewerkz.archimedes.graph  :as g]
            [clojurewerkz.archimedes.vertex :as v]
            [clojurewerkz.archimedes.edge   :as e]
            [clojurewerkz.archimedes.query  :as q])
  (:use [clojure.test :only (deftest is)]))


(deftest test-basic-vertices-query
  (let [graph (g/clean-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! graph 103 a :friend b)
        _     (e/connect-with-id! graph 104 a :friend c)
        vs    (q/find-vertices a
                 (q/direction :out)
                 (q/labels :friend))]
    (is (= 2 (count vs)))
    (is (= #{b c} (set vs)))))

(deftest test-edge-count
  (let [graph (g/clean-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! graph 103 a :friend b)
        _     (e/connect-with-id! graph 104 a :friend c)
        _     (e/connect-with-id! graph 105 a :remembers c)
        _     (e/connect-with-id! graph 106 c :remembers a)
        n     (q/count a
                 (q/direction :out)
                 (q/labels :friend :remembers))]
    (is (= 3 n))))

(deftest test-edge-count-with-default-comparator
  (let [graph (g/clean-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! graph 103 a :friend b {:age 28})
        _     (e/connect-with-id! graph 104 a :friend c {:age 30})
        n1    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age 28))
        n2    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age 29))
        n3    (q/count a
                 (q/direction :out)
                 (q/labels :hates)
                 (q/has :age 28))]
    (is (= n1 1))
    (is (= n2 0))
    (is (= n3 0))))

(deftest test-edge-count-with-gte-comparator
  (let [graph (g/clean-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! graph 103 a :friend b {:age 28})
        _     (e/connect-with-id! graph 104 a :friend c {:age 30})
        n1    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age >= 28))
        n2    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age >= 29))
        n3    (q/count a
                 (q/direction :out)
                 (q/labels :hates)
                 (q/has :age >= 28))]
    (is (= n1 2))
    (is (= n2 1))
    (is (= n3 0))))

(deftest test-edge-count-with-lte-comparator
  (let [graph (g/clean-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! graph 103 a :friend b {:age 28})
        _     (e/connect-with-id! graph 104 a :friend c {:age 30})
        n1    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age <= 28))
        n2    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age <= 29))
        n3    (q/count a
                 (q/direction :out)
                 (q/labels :hates)
                 (q/has :age <= 28))]
    (is (= n1 1))
    (is (= n2 1))
    (is (= n3 0))))

(deftest test-has-propetry-key
  (let [graph (g/clean-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        d     (v/create-with-id!  graph 103 {:name "Claire" :age 26})
        _     (e/connect-with-id! graph 104 a :friend b {:age 28})
        _     (e/connect-with-id! graph 105 a :friend c {:age 30})
        _     (e/connect-with-id! graph 106 a :friend d)
        n1    (q/count a
                 (q/direction :out)
                 (q/labels :friend))
        n2    (q/count a
                 (q/direction :out)
                 (q/labels :friend)
                 (q/has :age))]
    (is (= n1 3))
    (is (= n2 2)))
)
