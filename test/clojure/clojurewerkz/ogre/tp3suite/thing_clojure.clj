(ns clojurewerkz.ogre.tp3suite.thing-clojure
  (:gen-class)
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]))


(defn get-g-v-both-dedup-name [g]
  (q/query (v/get-all-vertices g)
           q/both
           q/dedup
           (q/values :name)))
