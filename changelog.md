## Changes in Ogre 3.3.3.0

* Bump to Apache TinkerPop 3.3.3
* Improved syntax for specifying `VertexProperty$Cardinality` by adding `(cardinality :single)`.

## Changes in Ogre 3.3.2.0

* Bump to Apache TinkerPop 3.3.2

## Changes in Ogre 3.3.1.0

* Bump to Apache TinkerPop 3.3.1
* The API for `addV`, `addE`, and `midV` have been made more consistent to simply be `add-V`, `add-E` and `V`
respectively. The old methods are still supported for now.
* The `injects` start step is now simply `inject`, though `injects` is still supported.
* Support the new `math` step
* Dependency updates

## Changes in Ogre 3.3.0.0

* Bump to Apache TinkerPop 3.3.0
* Support newly added `skip` step
* Support revised `addE` functionality that allows `Vertex` instances to be passed to `from` and `to`

## Changes in Ogre 3.2.6.0

* Bump to TinkerPop 3.2.6.

## Changes in Ogre 3.2.5.0

* Bump to TinkerPop 3.2.5. 
* Added support for `hasNot()` and `hasValue()` steps which were missed in previous versions

## Changes in Ogre 3.2.4.0

Ogre now targets Apache TinkerPop 3.2.4 and is the first non-beta release of
Ogre for TinkerPop 3.x. This release of Ogre involves major breaking changes
to the API since 2.5.0.0. Please see the most recent documentation for the
revised API.

## Changes in Ogre 2.5.0.0

 * Bump to support of TinkerPop 2.5.0.
 * Arity 2 `has` and `has-not` filters.
 * Add `into-list!` function.

## Changes in Ogre 2.3.0.2

* `--E>`\`<E--` have become `-E>`\`<E-`. This enforces all shortened
  traversal methods to only be three characters long.
* `reverse` has been added. This function reverses the order of the
  elements in the pipeline.

## Changes in Ogre 2.3.0.1

* `convert-to-map` is now polymorphic and converts Rows as well.
* Ogre is now a Clojurewerkz project.
* Traversals now take in a list of labels.

## Changes in Ogre 2.3.0.0

Update Gremlin version to 2.3.0. `count` is now `count!` because it
executes the pipeline and returns all the objects. Type hinting has mostly
been added in.

## Changes in Ogre 2.2.0.0

First release. Covers most of the library.
