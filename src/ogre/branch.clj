(ns ogre.branch
  (:refer-clojure :exclude [loop])
  (:use ogre.util))

(defn copy-split [p & es]
  (.copySplit p (pipe-array es)))

(defn exhaust-merge [p]
  (.exhaustMerge p))


(defn fair-merge [p]
  (.fairMerge p))

(defn if-then-else [p pred then else]
  (.ifThenElse p (f-to-pipef pred) (f-to-pipef then) (f-to-pipef else)))


(defn loop-unbundler [f]
  (fn [b]
    (f (.getLoops b)
       (.getObject b)
       (.getPath b))))

(defn loop
  ([p i while-f]
     (.loop p i (f-to-pipef (loop-unbundler while-f))))
  ([p i while-f emit-f]
     (.loop p i (f-to-pipef (loop-unbundler while-f)) (f-to-pipef emit-f))))

(defn loop-to
  ([p s while-f]
     (.loop p s (f-to-pipef (loop-unbundler while-f))))
  ([p s while-f emit-f]
     (.loop p s
            (f-to-pipef (loop-unbundler while-f))
            (f-to-pipef emit-f))))