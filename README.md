# Orge, a Clojure Library For Querying Blueprints Graphs

<img src="https://raw.github.com/zmaril/ogre/master/ogre.png" height="200"></img>

Ogre is a Clojure "dialect" of [Gremlin](https://github.com/tinkerpop/gremlin/wiki).
It is for querying graphs that conform to the [Tinkerpop Blueprints](http://tinkerpop.com) interface.


## Project Goals

 * Provide an API that makes [Tinkerpop Blueprints](http://tinkerpop.com) really easy to use from Clojure
 * Be reasonably feature complete
 * Don't introduce any significant amount of performance overhead


## Community

[Orge has a mailing list](https://groups.google.com/forum/#!forum/clojure-titanium).
Feel free to join it and ask any questions you may have.


## Project Maturity

Orge is a young project. [Titanium](http://titanium.clojurewerkz.org) is currently being ported to use it.

As the project matures, we will update this section.



## Artifacts

Orge artifacts are [released to Clojars](https://clojars.org/zmaril/ogre). If you are using Maven, add the following repository
definition to your `pom.xml`:

``` xml
<repository>
  <id>clojars.org</id>
  <url>http://clojars.org/repo</url>
</repository>
```

### The Most Recent Release

With Leiningen:

    [zmaril/ogre "2.2.0.0"]


With Maven:

    <dependency>
      <groupId>zmaril</groupId>
      <artifactId>ogre</artifactId>
      <version>2.2.0.0</version>
    </dependency>



## Documentation & Examples

 * [Guides](http://ogredocs.com/)


### Code Examples

Our [test suite](test/orge) has many code examples.


### Mailing List

Don't hesitate to join our [mailing list](https://groups.google.com/forum/#!forum/clojure-titanium) and ask questions!


## Supported Clojure Versions

Orge is built from the ground up for Clojure 1.4 and up. The most recent stable release
is always recommended.


## Continuous Integration

TBD: add it to travis-ci.org.



## Development

Orge uses [Leiningen 2](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md). Make sure you have it installed and then run tests against
supported Clojure versions using

    lein2 all test

Then create a branch and make your changes on it. Once you are done with your changes and all tests pass, submit a pull request
on GitHub.



## License

Copyright (C) 2013 Zack Maril

Licensed under the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html) (the same as Clojure).
