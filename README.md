# Ogre

<img src="gremlin-ogre.png"></img>

Ogre is a Clojure [Gremlin Language Variant](http://tinkerpop.apache.org/docs/current/tutorials/gremlin-language-variants/) of the [Gremlin](http://tinkerpop.apache.org/gremlin.html)
graph traversal language from [Apache Tinkerpop](http://tinkerpop.apache.org/). Like Gremlin, it can be used to query any graphs that are [TinkerPop-enabled](http://tinkerpop.apache.org/providers.html).

## Project Goals

* Provide an API that enhances the expressivity of Gremlin when working in Clojure.
* Expose the features of TinkerPop as it makes sense in Clojure.
* Don't introduce any significant amount of performance overhead.

## Community

Questions related to Ogre can be asked on the [clojure-titanium mailing list](https://groups.google.com/forum/#!forum/clojure-titanium).

To subscribe for announcements of releases, important changes and so on, please follow [@ClojureWerkz](https://twitter.com/#!/clojurewerkz) on Twitter.

## Project Maturity

Despite being first released in 2014, Orge is a relatively young project that regained
active development in 2016. Most of Ogre's features are driven by changes to Apache
TinkerPop (specifically the [Traversal API](http://tinkerpop.apache.org/docs/current/reference/#graph-traversal-steps))
which has largely stabilized itself in over the course of the 3.2.x line of code. As a
result, Ogre tends to be fairly stable with its implementation of that API. Ogre also
implements the TinkerPop Process Test Suite, which helps validate that Ogre is compliant
with Gremlin.

Ogre currently targets TinkerPop 3.4.x.

## Artifacts

Orge artifacts are [released to Clojars](https://clojars.org/clojurewerkz/ogre). Maven users should add the following repository definition to your `pom.xml`:

``` xml
<repository>
  <id>clojars.org</id>
  <url>http://clojars.org/repo</url>
</repository>
```

### The Most Recent Release

With Leiningen:

    [clojurewerkz/ogre "3.4.6.0"]

With Maven:

    <dependency>
      <groupId>clojurewerkz</groupId>
      <artifactId>ogre</artifactId>
      <version>3.4.6.0</version>
    </dependency>

## Documentation & Examples

You'll need to choose a TinkerPop-enabled graph database and add that to your project's dependencies. Here we use the in-memory graph database implementation provided by `org.apache.tinkerpop/tinkergraph-gremlin`, e.g.:

With Leiningen:

    [org.apache.tinkerpop/tinkergraph-gremlin "3.4.6"]

With Maven:

    <dependency>
      <groupId>org.apache.tinkerpop</groupId>
      <artifactId>tinkergraph-gremlin</artifactId>
      <version>3.4.6</version>
    </dependency>

REPL examples:

```text
user=> (load "clojurewerkz/ogre/core")
nil
user=> (in-ns 'clojurewerkz.ogre.core)
#object[clojure.lang.Namespace 0x2bcfe59c "clojurewerkz.ogre.core"]
clojurewerkz.ogre.core=> (def graph (open-graph {(Graph/GRAPH) (.getName org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph)}))
#'clojurewerkz.ogre.core/graph
clojurewerkz.ogre.core=> (def g (traversal graph))
#'clojurewerkz.ogre.core/g
clojurewerkz.ogre.core=> (org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory/generateModern graph)
nil
clojurewerkz.ogre.core=> (traverse g V (match
                    #_=>   (__ (as :a) (out :created) (as :b))
                    #_=>   (__ (as :b) (has :name "lop"))
                    #_=>   (__ (as :b) (in :created) (as :c))
                    #_=>   (__ (as :c) (has :age 29)))
                    #_=>   (select :a :c) (by :name)
                    #_=>   (into-seq!))
({"a" "marko", "c" "marko"} {"a" "josh", "c" "marko"} {"a" "peter", "c" "marko"})
```

Ogre has more complete documentation [here](http://ogre.clojurewerkz.org/).

## Supported Clojure Versions

Orge requires Clojure 1.8+. The most recent stable release is always recommended.

## Continuous Integration

[![Build Status](https://travis-ci.org/clojurewerkz/ogre.svg?branch=master)](https://travis-ci.org/clojurewerkz/ogre)

## Development

Orge uses [Leiningen 2](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md). Once installed and run tests using:

    lein test

## License

Copyright (C) 2014-2017 Zack Maril, and the ClojureWerkz team.
Copyright (C) 2017 Stephen Mallette, Zack Maril, and the ClojureWerkz team.

Licensed under the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html) (the same as Clojure).

## Acknowledgements

[Joe Lee](http://www.jml3designz.com/) illustrated the "Gremlin Ogre" image based on the original Clojurewerkz Ogre logo and
Apache TinkerPop's Gremlin character developed [Ketrina Yim](http://ketrinayim.tumblr.com/).
