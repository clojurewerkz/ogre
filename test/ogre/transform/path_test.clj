(ns ogre.transform.path-test
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]))

(deftest test-path-step
  (g/use-new-tinker-graph!)
  (testing "g.getVertex(1).property('name').path"
    (let [path (q/query (g/find-by-id 1)
                        (q/property :name)
                        q/path
                        q/first-into-vec)]
      (is (= "marko" (g/get-property :name (first path))))
      (is (= "marko" (second path)))))
  (testing "g.getVertex(1).out.path{it.age}{it.name}"
    (let [path (q/query (g/find-by-id 1)
                        q/-->
                        (q/path
                         (partial g/get-property :age)
                         (partial g/get-property :name))
                        q/all-into-vecs)
          age  (map first path)
          names  (map second path)]
      (is (= [29 29 29] age))
      (is (= ["vadas" "josh" "lop"] names))))

  ;;TODO write this test once loop has been finished
  ;; GremlinPipeline(g.getVertices()).out().loop(1, new PipeFunction<LoopPipe.LoopBundle, Boolean>() {
;;             public Boolean compute(LoopPipe.LoopBundle bundle) {
;;                 return bundle.getLoops() < 3;
;;             }
;;         }).path(new PipeFunction<Vertex, Vertex>() {
;;                     public Vertex compute(Vertex vertex) {
;;                         return vertex;
;;                     }
;;                 }, new PipeFunction<Vertex, String>() {
;;                     public String compute(Vertex vertex) {
;;                         return (String) vertex.getProperty("name");
;;                     }
;;                 }, new PipeFunction<Vertex, String>() {
;;                     public String compute(Vertex vertex) {
;;                         return (String) vertex.getProperty("lang");
;;                     }
;;                 }
;;         ));

  )
