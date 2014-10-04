(ns clojurewerkz.ogre.conversion
  (:import [com.tinkerpop.gremlin.structure Direction Compare]))

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
    =    Compare/EQUAL
    not= Compare/NOT_EQUAL
    >=   Compare/GREATER_THAN_EQUAL
    >    Compare/GREATER_THAN
    <=   Compare/LESS_THAN_EQUAL
    <    Compare/LESS_THAN))
