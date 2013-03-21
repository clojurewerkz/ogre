(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:use ogre.util))

(defn map
  ([p & keys] (.map p (keywords-to-strings keys))))

(defn transform [p f]
  (.transform p (f-to-pipef f)))


(defn _ [p]
  (._ p))

(defn id [p] (.id p))


(defn property [p prop]
  (.property p (name prop)))

(defn label [p]
  (.label p))

(defn select
  ([p] (.select p))
  ([p & fs] (.select p (fs-to-pipef-array fs))))

(defn select-only
  ([p cols] (select-only p cols identity))
  ([p cols & fs] (.select p cols (fs-to-pipef-array fs))))

(defn memoize
  ([is] (.memoize is))
  ([is m] (.memoize is m)))

(defn scatter [p]
  (.scatter p))

(defn path [p & args]
  (.path p (fs-to-pipef-array args)))