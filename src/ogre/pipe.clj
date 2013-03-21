(ns ogre.pipe
  (:refer-clojure :exclude [iterate next])
  (:use ogre.util))

(defn add [p e]
  (.add p e))

(defn as [p s]
  (.as p s))

(defn back [p i]
  (.back p i))

(defn back-to [p i]
  (.back p i))

(defn enable-path [p]
  (.enablePath p))

(defn iterate [p]
  (.iterate p))

(defn next [p i]
  (.next p i))

(defn optimize [p b]
  (.optimize p b))

(defn optional [p s]
  (.optional p s))

(defn optional-to [p s]
  (.optional p s))

(defn start [p o]
  (.start p o))

(defn step [p e]
  (.step p e))

(defn to-list! [p]
  (.toList p))

(defn into-vec! [p]
  (into [] (to-list! p)))

(defn into-set! [p]
  (into #{} (to-list! p)))

;;Inspiried by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defn convert-to-map [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defn first-of! [p]
  (-> p into-vec! first))

(defn first-into-vec! [p]
  (vec (first-of! p)))

(defn first-into-set! [p]
  (set (first-of! p)))

(defn first-into-map! [p]
  (convert-to-map (first-of! p)))

(defn all-into-vecs! [p]
  (map vec (into-vec! p)))

(defn all-into-sets! [p]
  (map set (into-vec! p)))

(defn all-into-maps! [p]
  (map convert-to-map (into-vec! p)))

;; Reversed property accessors

(defn prop [k]
  (fn [v]
    (.getProperty v (name k))))