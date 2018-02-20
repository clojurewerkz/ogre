(defproject clojurewerkz/ogre "3.3.1.0"
  :description "Clojure library for traversing Apache TinkerPop enabled graphs and a dialect of the Gremlin graph processing language"
  :url "https://github.com/clojurewerkz/ogre"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [potemkin "0.4.4"]
                 [org.apache.tinkerpop/gremlin-core "3.3.1"]]
  :source-paths ["src/clojure"]
  :profiles {:dev {:global-vars {*assert* true}
                   :dependencies [[clojurewerkz/support "1.1.0" :exclusions [org.clojure/clojure]]
                                  [commons-io/commons-io "2.6"]
                                  [org.apache.tinkerpop/gremlin-test "3.3.1"  :scope "test"]
                                  [org.apache.tinkerpop/tinkergraph-gremlin "3.3.1" :scope "test"]
                                  [org.slf4j/slf4j-log4j12 "1.7.25" :scope "test"]]
                   :resource-paths ["test/resources"]
                   :java-source-paths ["test/java"]
                   :junit ["test/java"]}
             :uberjar {:aot :all}}
  :aliases {"all" ["with-profile" "dev,dev:master"]}
  :repositories {"sonatype" {:url "https://oss.sonatype.org/content/repositories/releases"
                             :snapshots false
                             :releases {:checksum :fail :update :always}}
                 "sonatype-snapshots" {:url "https://oss.sonatype.org/content/repositories/snapshots"
                                       :snapshots true
                                       :releases {:checksum :fail :update :always}}}
  :plugins [[lein-junit "1.1.8"]]
  :test-paths ["test/clojure" "test/java"]
  :global-vars {*warn-on-reflection* true
                *assert* false})
