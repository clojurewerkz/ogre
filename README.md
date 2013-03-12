# Ogre

Ogre is a clojure wrapper for
[gremlin](https://github.com/tinkerpop/gremlin/wiki). It was derived
from [Hermes](https://github.com/zmaril/hermes) and inspired by
[Titanium](https://github.com/clojurewerkz/titanium). The goal of this
project is to provide a solid, well tested Clojure wrapper for
gremlin. To that effect, we aim to port most of the tests over from
gremiln itself and eventually provide rigorous documentation and
examples. Currently, Ogre powers Hermes.


## Usage

Leiningen:

```
[zmaril/ogre "0.0.1"]
```

For now, the tests are the documentation.

## Contributing 

The main focus right now is porting over the tests and figuring out
what to do with the more complicated gremlin functions (like loop).
Pull requests are welcome and celebrated.

## License

Copyright © 2013 Zack Maril

Distributed under the Eclipse Public License, the same as Clojure.
