# Ogre

<img src="https://raw.github.com/clojurewerkz/ogre/master/ogre.png" height="200"></img>

Ogre is a Clojure "flavor" of the [Gremlin](http://gremlin.tinkerpop.com) graph query language. Like Gremlin, it can be used to query graphs that conform to the [Tinkerpop Blueprints](http://blueprints.tinkerpop.com) interface.

## Project Goals

* Provide and API that enhances the expressivity of Gremlin when working in Clojure.
* Expose the all the features of Gremlin and Blueprints as it makes sense in Clojure.
* Don't introduce any significant amount of performance overhead.

## Community

Questions related to Ogre can be asked on the [clojure-titanium mailing list](https://groups.google.com/forum/#!forum/clojure-titanium). 

To subscribe for announcements of releases, important changes and so on, please follow [@ClojureWerkz](https://twitter.com/#!/clojurewerkz) on Twitter.

## Project Maturity

Orge is a young project that regained active development in 2014.

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

    [clojurewerkz/ogre "2.5.0.0"]

With Maven:

    <dependency>
      <groupId>clojurewerkz</groupId>
      <artifactId>ogre</artifactId>
      <version>2.5.0.0</version>
    </dependency>

## Documentation & Examples

 * [Guides](http://ogre.clojurewerkz.org/)


## Supported Clojure Versions

Orge requires Clojure 1.6+. The most recent stable release is always recommended.

## Continuous Integration

[![Build Status](https://travis-ci.org/clojurewerkz/ogre.svg?branch=master)](https://travis-ci.org/clojurewerkz/ogre)

## Development

Orge uses [Leiningen 2](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md). Once installed and run tests against supported Clojure versions using:

    lein all test

## License

Copyright (C) 2014 Zack Maril

Licensed under the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html) (the same as Clojure).
