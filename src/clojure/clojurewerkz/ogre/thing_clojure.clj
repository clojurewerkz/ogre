(ns clojurewerkz.ogre.thing-clojure
  (:gen-class :name "clojurewerkz.ogre.suite.Dedup"
              :extends com.tinkerpop.gremlin.process.graph.step.filter.DedupTest
              :exposes {g {:get graph}})
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v])
  (:import (com.tinkerpop.gremlin LoadGraphWith LoadGraphWith$GraphData)))




(defn #^{LoadGraphWith LoadGraphWith$GraphData/MODERN} -get_g_V_both_dedup_name [this]
  (let [g (.graph this)
        _ (println g)]
    (q/query (v/get-all-vertices g)
             q/both
             q/dedup
             (q/values :name))))

(defn -get_g_V_both_hasXlabel_softwareX_dedup_byXlangX_name [this]
  (-get_g_V_both_dedup_name this))

(defn -get_g_V_both_propertiesXnameX_orderXa_bX_dedup_value [this]
  (-get_g_V_both_dedup_name this))