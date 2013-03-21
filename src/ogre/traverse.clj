(ns ogre.traverse
  (:use ogre.util))

(defn both [p & labels]
  (.both p (keywords-to-strings labels)))

(defn <-> [& args]
  (apply both args))

(defn both-edges [p & labels]
  (.bothE p (keywords-to-strings labels)))

(defn <E> [& args]
  (apply both-edges args))

(defn both-vertices [p]
  (.bothV p))

(defn in [p & labels]
  (.in p (keywords-to-strings labels)))

(defn <-- [& args]
  (apply in args))

(defn in-edges [p & labels]
  (.inE p (keywords-to-strings labels)))

(defn <E-- [& args]
  (apply in-edges args))

(defn in-vertex [p & labels]
  (.inV p))

(defn out [p & labels]
  (.out p (keywords-to-strings labels)))

(defn --> [& args]
  (apply out args))

(defn out-edges [p & labels]
  (.outE p (keywords-to-strings labels)))

(defn --E> [& args]
  (apply out-edges args))

(defn out-vertex [p & labels]
  (.outV p))
