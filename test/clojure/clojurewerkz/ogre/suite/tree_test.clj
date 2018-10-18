(ns clojurewerkz.ogre.suite.tree-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle sort])
  (:require [clojurewerkz.ogre.core :refer :all])
  (:import (org.apache.tinkerpop.gremlin.structure T)))

(defn get_g_V_out_out_treeXaX_byXidX_capXaX
  "g.V().out().out().tree('a').by(T.id).cap('a')"
  [g]
  (traverse g (V)
              (out)
              (out)
              (tree :a)
                (by (T/id))
              (cap :a)))

(defn get_g_VX1X_out_out_tree_byXnameX
  "g.V(v1Id).out().out().tree().by('name')"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (out)
              (tree)
                (by :name)))

(defn get_g_V_out_out_tree_byXidX
  "g.V().out().out().tree().by(T.id)"
  [g]
  (traverse g (V)
              (out)
              (out)
              (tree)
                (by (T/id))))

(defn get_g_V_out_out_tree
  "g.V().out().out().tree()"
  [g]
  (traverse g (V)
              (out)
              (out)
              (tree)))

(defn get_g_V_out_out_treeXaX_capXaX
  "g.V().out().out().tree('a').cap('a')"
  [g]
  (traverse g (V)
              (out)
              (out)
              (tree :a)
              (cap :a)))

(defn get_g_V_out_out_out_tree
  "g.V().out().out().out().tree()"
  [g]
  (traverse g (V)
              (out)
              (out)
              (out)
              (tree)))

(defn get_g_VX1X_out_out_treeXaX_byXnameX_both_both_capXaX
  "g.V(v1Id).out().out().tree('a').by('name').both().both().cap('a')"
  [g v1Id]
  (traverse g (V v1Id)
              (out)
              (out)
              (tree :a)
                (by :name)
              (both)
              (both)
              (cap :a)))
