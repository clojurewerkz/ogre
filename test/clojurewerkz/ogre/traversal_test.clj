(ns clojurewerkz.ogre.traversal-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-traversal
  (testing "g.V()"
    (let [vs (q/query (v/get-all-vertices (u/classic-tinkergraph))
                      q/into-vec!)]
      (is (= 6 (count vs)))))

  (testing "g.v(1).out()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      q/-->
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"}
             (u/get-names-set vs)))))

  (testing "g.v(2).in()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 2))
                      q/<--
                      q/into-vec!)]
      (is (= #{"marko"}
             (u/get-names-set vs)))))

  (testing "g.v(4).both()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 4))
                      q/<->
                      q/into-vec!)]
      (is (= #{"marko" "ripple" "lop"}
             (u/get-names-set vs)))))

  (testing "g.E()"
    (let [vs (q/query (e/get-all-edges (u/classic-tinkergraph))
               q/into-vec!)]
      (is (= 6 (count vs)))))

  (testing "g.v(1).outE()"
    (let [es (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      q/-E>
                      q/into-vec!)]
      (is (= 3 (count es)))
      (is (= #{:created :knows}
             (set (map e/label-of es))))))

  (testing "g.v(2).inE()"
    (let [es (q/query (v/find-by-id (u/classic-tinkergraph) (int 2))
                      q/<E-
                      q/into-vec!)]
      (is (= 1 (count es)))
      (is (= #{:knows}
             (set (map e/label-of es))))))

  (testing "g.v(4).bothE()"
    (let [es (q/query (v/find-by-id (u/classic-tinkergraph) (int 4))
                      q/<E>
                      q/into-vec!)]
      (is (= 3 (count es)))
      (is (= #{:knows :created}
             (set (map e/label-of es))))))

  (testing "g.v(1).outE().inV()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      q/-E>
                      q/in-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"}
             (u/get-names-set vs)))))

  (testing "g.v(2).inE().outV()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 2))
                      q/<E-
                      q/out-vertex
                      q/into-vec!)]
      (is (= #{"marko"}
             (u/get-names-set vs)))))

  (testing "g.v(1).out('knows')"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      (q/--> [:knows])
                      q/into-vec!)]
      (is (= #{"vadas" "josh"}
             (u/get-names-set vs)))))

  (testing "g.v(1).out('knows','created')"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      (q/--> [:knows :created])
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"}
             (u/get-names-set vs)))))

  (testing "g.v(1).outE('knows').inV()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      (q/-E> [:knows])
                      q/in-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh"}
             (u/get-names-set vs)))))

  (testing "g.v(1).outE('knows','created').inE()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      (q/-E> [:knows :created])
                      q/in-vertex
                      q/into-vec!)]
      (is (= #{"vadas" "josh" "lop"}
             (u/get-names-set vs)))))

  (testing "g.v(1).out().out()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      q/-->
                      q/-->
                      q/into-vec!)]
      (is (= #{"ripple" "lop"}
             (u/get-names-set vs)))))

  (testing "g.v(1).out().out().out()"
    (let [vs (q/query (v/find-by-id (u/classic-tinkergraph) (int 1))
                      q/-->
                      q/-->
                      q/-->
                      q/into-vec!)]
      (is (= 0 (count vs))))))

(deftest test-basic-vertices-query
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b)
        _     (e/connect-with-id! 104 a :friend c)
        vs    (q/query a
                 (q/--> [:friend])
                 q/into-set!)]
    (is (= 2 (count vs)))
    (is (= #{b c} vs))))

(deftest test-edge-count
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b)
        _     (e/connect-with-id! 104 a :friend c)
        _     (e/connect-with-id! 105 a :remembers c)
        _     (e/connect-with-id! 106 c :remembers a)
        n     (q/query a
                 (q/--> [:friend :remembers])
                 q/count!)]
    (is (= 3 n))))

(deftest test-edge-count-with-default-comparator
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b {:age 28})
        _     (e/connect-with-id! 104 a :friend c {:age 30})
        n1    (q/query a
                 (q/--> [:friend])
                 (q/has :age 38)
                 q/count!)
        n2    (q/query a
                 (q/-->  [:friend])
                 (q/has :age 29)
                 q/count!)
        n3    (q/query a
                 (q/--> [:hates])
                 (q/has :age 28)
                 q/count!)]
    (is (= n1 1))
    (is (= n2 0))
    (is (= n3 0))))

(deftest test-edge-count-with-gte-comparator
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b {:age 28})
        _     (e/connect-with-id! 104 a :friend c {:age 30})
        n1    (q/query a
                 (q/--> [:friend])
                 (q/has :age >= 28)
                 q/count!)
        n2    (q/query a
                 (q/--> [:friend])
                 (q/has :age >= 33)
                 q/count!)
        n3    (q/query a
                 (q/--> [:hates])
                 (q/has :age >= 28)
                 q/count!)]
    (is (= n1 2))
    (is (= n2 1))
    (is (= n3 0))))

(deftest test-edge-count-with-lte-comparator
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        _     (e/connect-with-id! 103 a :friend b {:age 28})
        _     (e/connect-with-id! 104 a :friend c {:age 30})
        n1    (q/query a
                 (q/--> [:friend])
                 (q/has :age <= 40)
                 q/count!)
        n2    (q/query a
                 (q/--> [:friend])
                 (q/has :age <= 37)
                 q/count!)
        n3    (q/query a
                 (q/--> [:hates])
                 (q/has :age <= 28)
                 q/count!)]
    (is (= n1 2))
    (is (= n2 1))
    (is (= n3 0))))

(deftest test-has-property-key
  (let [graph (u/new-tinkergraph)
        a     (v/create-with-id!  graph 100 {:name "Steven" :age 30})
        b     (v/create-with-id!  graph 101 {:name "Alonso" :age 32})
        c     (v/create-with-id!  graph 102 {:name "Thomas" :age 38})
        d     (v/create-with-id!  graph 103 {:name "Claire"})
        _     (e/connect-with-id! 104 a :friend b {:age 28})
        _     (e/connect-with-id! 105 a :friend c {:age 30})
        _     (e/connect-with-id! 106 a :friend d)
        n1    (q/query a
                 (q/--> [:friend])
                 q/count!)
        n2    (q/query a
                 (q/--> [:friend])
                 (q/has :age)
                 q/count!)]
    (is (= n1 3))
    (is (= n2 2))))

