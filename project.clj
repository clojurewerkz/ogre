(defproject clojurewerkz/ogre "3.0.0.0-SNAPSHOT"
  :description "Clojure dialect of the Gremlin graph processing language"
  :url "https://github.com/clojurewerkz/ogre"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [potemkin "0.3.3"]
                 [com.tinkerpop/gremlin-core "3.0.0.M7"]
                 [com.tinkerpop/gremlin-test "3.0.0.M7"  :scope "test" :exclusions [org.slf4j/slf4j-api]]
                 [com.tinkerpop/tinkergraph-gremlin "3.0.0.M7"  :scope "test" :exclusions [org.slf4j/slf4j-api]]
                 [com.tinkerpop/neo4j-gremlin "3.0.0.M7" :scope "test" :exclusions [org.slf4j/slf4j-api ch.qos.logback/logback-classic]]]
  :source-paths ["src/clojure"]
  :profiles {:dev    { :global-vars {*assert* true}
                       :dependencies [[clojurewerkz/support "1.1.0" :exclusions [org.clojure/clojure]]
                                      [commons-io/commons-io "2.4"]]}
             :1.5    {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.7    {:dependencies [[org.clojure/clojure "1.7.0-alpha5"]]}
             :master {:dependencies [[org.clojure/clojure "1.7.0-master-SNAPSHOT"]]}}
  :aliases {"all" ["with-profile" "dev:dev,1.5:dev,1.7"]}
  :repositories {"sonatype" {:url "http://oss.sonatype.org/content/repositories/releases"
                             :snapshots false
                             :releases {:checksum :fail :update :always}}
                 "sonatype-snapshots" {:url "http://oss.sonatype.org/content/repositories/snapshots"
                                       :snapshots true
                                       :releases {:checksum :fail :update :always}}}

  :test-paths ["test/clojure"]
  :java-source-paths ["test/java"]
  :junit ["test/java"]
  :plugins [[lein-junit "1.1.8"]]
  :global-vars {*warn-on-reflection* true
                *assert* false})
