(defproject clojurewerkz/ogre "3.3.0.0"
  :description "Clojure library for working with TinkerPop graphs and a dialect of the Gremlin graph processing language"
  :url "https://github.com/clojurewerkz/ogre"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :aot :all
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [potemkin "0.3.12"]
                 [org.apache.tinkerpop/gremlin-core "3.3.0"]
                 [org.apache.tinkerpop/gremlin-test "3.3.0"  :scope "test"]
                 [org.apache.tinkerpop/tinkergraph-gremlin "3.3.0" :scope "test"]
                 [org.slf4j/slf4j-log4j12 "1.7.21" :scope "test"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["test/java"]
  :resource-paths ["test/resources"]
  :profiles {:dev    { :global-vars {*assert* true}
                       :dependencies [[clojurewerkz/support "1.1.0" :exclusions [org.clojure/clojure]]
                                      [commons-io/commons-io "2.4"]]}
             :master { :dependencies [[org.clojure/clojure "1.9.0-master-SNAPSHOT"]]}}
  :aliases {"all" ["with-profile" "dev,dev:master"]}
  :repositories {"sonatype" {:url "https://oss.sonatype.org/content/repositories/releases"
                             :snapshots false
                             :releases {:checksum :fail :update :always}}
                 "sonatype-snapshots" {:url "https://oss.sonatype.org/content/repositories/snapshots"
                                       :snapshots true
                                       :releases {:checksum :fail :update :always}}}
  :plugins [[lein-junit "1.1.8"]]
  :junit ["test/java"]
  :test-paths ["test/clojure"]
  :global-vars {*warn-on-reflection* true
                *assert* false})
