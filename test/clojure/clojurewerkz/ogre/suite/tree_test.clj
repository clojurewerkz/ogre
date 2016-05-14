(ns clojurewerkz.ogre.suite.tree-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_V_out_out_treeXaX_byXidX_capXaX
  "g.V().out().out().tree('a').by(T.id).cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/out)
                (q/tree :a)
                (q/by (T/id))
                (q/cap :a)))

(defn get_g_VX1X_out_out_tree_byXnameX
  "g.V(v1Id).out().out().tree().by('name')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/out)
                (q/tree)
                (q/by :name)))

(defn get_g_V_out_out_tree_byXidX
  "g.V().out().out().tree().by(T.id)"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/out)
                (q/tree)
                (q/by (T/id))))

(defn get_g_V_out_out_tree
  "g.V().out().out().tree()"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/out)
                (q/tree)))

(defn get_g_V_out_out_treeXaX_capXaX
  "g.V().out().out().tree('a').cap('a')"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/out)
                (q/tree :a)
                (q/cap :a)))

(defn get_g_V_out_out_out_tree
  "g.V().out().out().out().tree()"
  [g]
  (q/traverse g (q/V)
                (q/out)
                (q/out)
                (q/out)
                (q/tree)))

(defn get_g_VX1X_out_out_treeXaX_byXnameX_both_both_capXaX
  "g.V(v1Id).out().out().tree('a').by('name').both().both().cap('a')"
  [g v1Id]
  (q/traverse g (q/V v1Id)
                (q/out)
                (q/out)
                (q/tree :a)
                (q/by :name)
                (q/both)
                (q/both)
                (q/cap :a)))
