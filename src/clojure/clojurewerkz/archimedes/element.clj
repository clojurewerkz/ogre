(ns clojurewerkz.archimedes.element
  (:refer-clojure :exclude [keys vals assoc! dissoc! get])
  (:import com.tinkerpop.blueprints.Element))

(defn get
  ([^Element elem key]
     (get elem key nil))
  ([^Element elem key not-found]
     (let [value (.getProperty elem (name key))]
       (if (nil? value) not-found value))))

(defn keys
  [^Element elem]
  (set (map keyword (.getPropertyKeys elem))))

(defn vals
  [^Element elem]
  (set (map #(.getProperty elem %) (.getPropertyKeys elem))))

(defn id-of
  [^Element elem]
  (.getId elem))

(defn assoc!
  [^Element elem & kvs]
  ;;Avoids changing keys that shouldn't be changed.
  ;;Important when using types. You aren't ever going to change a
  ;;user's id for example.
  (doseq [[key value] (partition 2 kvs)]
      (.setProperty elem (name key) value))
  elem)

(defn merge!
  [^Element elem & maps]
  (doseq [d maps]
    (apply assoc! (cons elem (flatten (into [] d)))))
  elem)

(defn dissoc!
  [^Element elem & keys]
  (doseq [key keys] (.removeProperty elem (name key)))
  elem)

(defn update!
  [^Element elem key f & args]
  (let [curr-val (get elem key)
        new-val  (apply f (cons curr-val args))]
    (assoc! elem key new-val)))

(defn clear!
  [^Element elem]
  (apply dissoc! (cons elem (keys elem))))
