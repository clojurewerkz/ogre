(defproject ogre "0.0.1"
  :description "Clojure wrapper around gremlin"
  :url "https://github.com/zmaril/ogre"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["typesafe/snapshots" "http://repo.typesafe.com/typesafe/snapshots/"]
                 ["apache" "http://repository.apache.org/content/repositories/releases/"]
                 ["sonatype" {:url "http://oss.sonatype.org/content/repositories/snapshots"}]
                 ["oracle" "http://download.oracle.com/maven/"]]  
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [com.tinkerpop.gremlin/gremlin-java "2.2.0"]]
  :test-paths ["test" "src/test/ogre"]
  :aliases {"test!" ["do" "clean," "deps," "test"]})
