## Changes in Ogre 3.4.10.0

* Bump to Apache TinkerPop 3.4.10 (fully compatible with 3.4.9)

## Changes in Ogre 3.4.8.0

* Bump to Apache TinkerPop 3.4.8
* Allowed the `traversal` function to take a `RemoteConnection` directly which is more in line with TinkerPop `AnonymousTraversalSource` method of construction - the `with-remote`standalone function is no longer recommended. 

## Changes in Ogre 3.4.7.0

* Bump to Apache TinkerPop 3.4.7

## Changes in Ogre 3.4.6.0

* Bump to Apache TinkerPop 3.4.6 (note that TinkerPop 3.4.5 contained a bug of significance and users of that version were asked to immediately upgrade to 3.4.6)

## Changes in Ogre 3.4.4.0

* Bump to Apache TinkerPop 3.4.4
* Added support for `elementMap()` step.

## Changes in Ogre 3.4.3.0

* Bump to Apache TinkerPop 3.4.3
* Supported new form of the `aggregate()` step that takes a `Scope`.

## Changes in Ogre 3.4.2.0

* Bump to Apache TinkerPop 3.4.2

## Changes in Ogre 3.4.1.0

* Bump to Apache TinkerPop 3.4.1

## Changes in Ogre 3.4.0.0

* Bump to Apache TinkerPop 3.4.0

## Changes in Ogre 3.3.4.0

* Bump to Apache TinkerPop 3.3.4

## Changes in Ogre 3.3.3.0

* Bump to Apache TinkerPop 3.3.3
* Improved syntax for specifying `VertexProperty$Cardinality` with `(cardinality :single)`.
* Improved syntax for specifying `Column` with `(column :values)`.
* Improved syntax for specifying `Order` with `(sort :decr)`.
* `Barrier` is no longer specified by `::norm-sack` and instead follows the common pattern of `(sack-barrier :normSack)`.
* `with-sack` no longer takes `::split` and `::merge` and rather just takes `:split` and `:merge` as its map key arguments
* Improved syntax for specifying `Operator` with `(operator :sum)`

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
