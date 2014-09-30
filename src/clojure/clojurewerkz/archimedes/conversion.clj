(ns clojurewerkz.archimedes.conversion
  (:import [com.tinkerpop.blueprints Direction Query$Compare]))

(defprotocol EdgeDirectionConversion
  (to-edge-direction [input] "Converts input to a Blueprints edge direction"))

(extend-protocol EdgeDirectionConversion
  clojure.lang.Named
  (to-edge-direction [input]
    (to-edge-direction (name input)))

  String
  (to-edge-direction [input]
    (case (.toLowerCase input)
      "in"    Direction/IN
      "out"   Direction/OUT
      "both"  Direction/BOTH))

  Direction
  (to-edge-direction [input]
    input))

(defn convert-symbol-to-compare [s]
  (case s
    =    Query$Compare/EQUAL
    not= Query$Compare/NOT_EQUAL
    >=   Query$Compare/GREATER_THAN_EQUAL
    >    Query$Compare/GREATER_THAN
    <=   Query$Compare/LESS_THAN_EQUAL
    <    Query$Compare/LESS_THAN))
