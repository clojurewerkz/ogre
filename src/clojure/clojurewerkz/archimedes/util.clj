(ns clojurewerkz.archimedes.util
  (:require [clojure.reflect :as r])
  (:use [clojure.pprint :only (pprint)]))

(defn keywords-to-str-array [strs]
  (into-array String (map name strs)))
