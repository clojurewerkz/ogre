(ns clojurewerkz.ogre.vertex-test
  (:use [clojure.test :only (deftest testing is)])
  (:require [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.edge :as e]
            [clojurewerkz.ogre.pipe :as p]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-create
  (let [graph (u/new-tinkergraph)
        u (v/create! graph)]
    (is (= 1 (count (p/into-set! (v/get-all-vertices graph)))))))

(deftest test-delete
  (let [graph (u/new-tinkergraph)
        u (v/create-with-id! graph 100 {:name "v1"})]
    (v/remove! u)
    (is (=  nil (v/find-by-id graph 100)))
    (is (empty? (p/into-set! (v/find-by-kv graph :name "v1"))))))

(deftest test-simple-property-mutation
  (let [graph (u/new-tinkergraph)
        u (v/create-with-id! graph 100 {:name "v1" :a 1 :b 1})]
    (v/assoc! u {:b 2})
    (v/dissoc! u :a)
    (is (= (list 1 2) (v/mget u :b)))
    (is (= nil (v/get u :a)))
    (is (= 10 (v/get u :a 10)))))

(deftest test-multiple-property-mutation
  (let [graph (u/new-tinkergraph)
        u (v/create-with-id! graph 100 {:name "v1" :a 0 :b 2})]
    (v/assoc! u {:a 1 :b 2 :c 3})
    (is (= (list 0 1) (v/mget u :a)))
    (is (= (list 2 2) (v/mget u :b)))
    (is (= (list 3) (v/mget u :c)))))

(deftest test-to-map
  (let [graph (u/new-tinkergraph)
        v1 (v/create-with-id! graph 100 {:name "v1" :a 1 :b 2 :c 3})
        props (v/to-map v1)]
    (is (= 1 (props :a)))
    (is (= 2 (props :b)))
    (is (= 3 (props :c)))))

(deftest test-to-map-id
  (let [id :ID]
    (try
      (g/set-element-id-key! id)
      (let [graph (u/new-tinkergraph)
            v1 (v/create-with-id! graph 100 {:name "v1" :a 1 :b 2 :c 3})
            props (v/to-map v1)]
        (is (= 100 (props id)))
        (is (= 1 (props :a)))
        (is (= 2 (props :b)))
        (is (= 3 (props :c))))
      (finally
        (g/set-element-id-key! :__id__)))))

(deftest test-find-by-id-single
  (let [graph (u/new-tinkergraph)
        v1 (v/create-with-id! graph 100 {:prop 1})
        v1-maybe (v/find-by-id graph 100)]
    (is (= 1 (v/get v1-maybe :prop)))))

(deftest test-find-by-id-multiple
  (let [graph (u/new-tinkergraph)
        v1 (v/create-with-id! graph 100 {:prop 1})
        v2 (v/create-with-id! graph 101 {:prop 2})
        v3 (v/create-with-id! graph 102 {:prop 3})
        ids (map v/id-of [v1 v2 v3])
        v-maybes (apply v/find-by-id graph ids)]
    (is (= (range 1 4) (map #(v/get % :prop) v-maybes)))))

(deftest test-find-by-kv
  (let [graph (u/new-tinkergraph)
        v1 (v/create-with-id! graph 100 {:age 1 :name "A"})
        v2 (v/create-with-id! graph 101 {:age 2 :name "B"})
        v3 (v/create-with-id! graph 102 {:age 2 :name "C"})]
    (is (= #{"A"}
           (set (map #(v/get % :name) (p/into-set! (v/find-by-kv graph :age 1))))))
    (is (= #{"B" "C"}
           (set (map #(v/get % :name) (p/into-set! (v/find-by-kv graph :age 2))))))))

(deftest test-get-all-vertices
  (let [graph (u/new-tinkergraph)
        v1 (v/create-with-id! graph 100 {:age 1 :name "A"})
        v2 (v/create-with-id! graph 101 {:age 2 :name "B"})
        v3 (v/create-with-id! graph 102 {:age 2 :name "C"})]
    (is (= #{v1 v2 v3} (p/into-set! (v/get-all-vertices graph))))))

(deftest test-adjacent-object-retrieval
  (let [graph (u/new-tinkergraph)
        v1 (v/create-with-id! graph 100 {:age 1 :name "A"})
        v2 (v/create-with-id! graph 101 {:age 2 :name "B"})
        v3 (v/create-with-id! graph 102 {:age 2 :name "C"})
        e1 (e/connect-with-id! 103 v1 :a v2)
        e2 (e/connect-with-id! 104 v2 :b v1)
        e3 (e/connect-with-id! 105 v1 :c v3)]
    ;(is (= (p/into-set! (v/edges-of v1 :in)) #{e2}))
    (is (= (p/into-set! (v/incoming-edges-of v1)) #{e2}))
    ;(is (= (p/into-set! (v/connected-vertices-of v1 :in)) #{v2}))
    (is (= (p/into-set! (v/connected-in-vertices v1)) #{v2}))

    ;(is (= (p/into-set! (v/edges-of v1 :out)) #{e1 e3}))
    (is (= (p/into-set! (v/outgoing-edges-of v1)) #{e1 e3}))
    ;(is (= (p/into-set! (v/connected-vertices-of v1 :out)) #{v2 v3}))
    (is (= (p/into-set! (v/connected-out-vertices v1)) #{v2 v3}))

    ;(is (= (p/into-set! (v/edges-of v1 :both)) #{e1 e2 e3}))
    (is (= (p/into-set! (v/all-edges-of v1)) #{e1 e2 e3}))
    ;(is (= (p/into-set! (v/connected-vertices-of v1 :both)) #{v2 v3}))
    (is (= (p/into-set! (v/all-connected-vertices v1)) #{v2 v3}))

    ;(is (= (p/into-set! (v/edges-of v1 :both :a)) #{e1}))
    (is (= (p/into-set! (v/all-edges-of v1 :a)) #{e1}))
    ;(is (= (p/into-set! (v/connected-vertices-of v1 :both :a)) #{v2}))
    (is (= (p/into-set! (v/all-connected-vertices v1 :a)) #{v2}))

    ;(is (= (p/into-set! (v/edges-of v1 :both :a :b)) #{e1 e2}))
    (is (= (p/into-set! (v/all-edges-of v1 :a :b)) #{e1 e2}))
    ;(is (= (p/into-set! (v/connected-vertices-of v1 :both :a :b)) #{v2}))
    (is (= (p/into-set! (v/all-connected-vertices v1 :a :b)) #{v2}))

    ;(is (= (p/into-set! (v/edges-of v1 :both :a :b :d)) #{e1 e2}))
    (is (= (p/into-set! (v/all-edges-of v1 :a :b :d)) #{e1 e2}))
    ;(is (= (p/into-set! (v/connected-vertices-of v1 :both :a :b :d)) #{v2}))
    (is (= (p/into-set! (v/all-connected-vertices v1 :a :b :d)) #{v2}))))

(deftest test-upsert!
  (testing "upsert! with id"
    (let [graph (u/new-tinkergraph)
          v1-a (v/upsert-with-id! graph 100 :first-name {:first-name "Zack" :last-name "Maril" :age 21})
          v1-b (v/upsert-with-id! graph 101 :first-name {:first-name "Zack" :last-name "Maril" :age 22})
          v2 (v/upsert-with-id! graph 102 :first-name {:first-name "Brooke" :last-name "Maril" :age 19})]
      (is (= 22
            (last (v/mget (first v1-a) :age))
            (last (v/mget (first v1-b) :age))))

      (v/upsert-with-id! graph 103 :last-name {:last-name "Maril"
                                               :heritage "Some German Folks"})

      (is (= "Some German Folks"
            (v/get (first v1-a) :heritage)
            (v/get (first v1-b) :heritage)
            (v/get (first v2) :heritage)))))

  (testing "upsert! without id"
    (let [graph (u/new-tinkergraph)
          v1-a (v/upsert! graph :first-name {:first-name "Zack" :last-name "Maril" :age 21})
          v1-b (v/upsert! graph :first-name {:first-name "Zack" :last-name "Maril" :age 22})
          v2 (v/upsert! graph :first-name {:first-name "Brooke" :last-name "Maril" :age 19})]

      (is (= 22
            (last (v/mget (first v1-a) :age))
            (last (v/mget (first v1-b) :age))))

      (v/upsert! graph :last-name {:last-name "Maril"
                                   :heritage "Some German Folks"})

      (is (= "Some German Folks"
            (v/get (first v1-a) :heritage)
            (v/get (first v1-b) :heritage)
            (v/get (first v2) :heritage))))))

(deftest test-get-false-val
  (let [graph (u/new-tinkergraph)
        v     (v/create-with-id! graph 100 {:foo false})]
    (is (= (v/get v :foo) false))
    (is (= (v/get v :foo 1) false))
    (is (nil? (v/get v :bar)))
    (is (= (v/get v :bar 1) 1))))
