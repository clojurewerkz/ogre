(ns clojurewerkz.ogre.test.gen
  (:import (org.apache.tinkerpop.gremlin.process.traversal.step.branch BranchTest)
           (org.apache.tinkerpop.gremlin.process.traversal.dsl.graph __ GraphTraversalSource)
           (org.apache.tinkerpop.gremlin AbstractGremlinTest)
           (java.util.function Function)))

(gen-class
  :name org.clojurewerkz.Branch$Traversals
  :extends org.apache.tinkerpop.gremlin.process.traversal.step.branch.BranchTest
  :exposes {g {:get g}}
  :prefix "branch-")

(defn branchfn [v]
  (let [label (.label (.get v))]
    (println label)
    (if (= "person" label)
      "a"
      "b")))
(defn branch-get_g_V_branchXlabel_eq_person__a_bX_optionXa__ageX_optionXb__langX_optionXb__nameX [^BranchTest this]
;  return g.V().branch(v -> v.get().label().equals("person") ? "a" : "b")
;.option("a", values("age"))
;.option("b", values("lang"))
;.option("b", values("name"));

  (let [g ^GraphTraversalSource (.g this)
        g-v (. g (V (into-array [])))
        thing (. g-v (branch ^Function branchfn) (option "a" (__/values "name")) (option "b" (__/values "lang")) (option "b" (__/values "age")))]
    thing))

