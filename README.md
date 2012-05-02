# lein-noir

A [leiningen](https://github.com/technomancy/leiningen) plugin to help create and manage noir projects. It only has a lein-newnew template for creating noir projects at the moment, but it'll eventually have tasks for managing and deploying noir websites.

## Usage

lein-noir includes a template for lein-newnew. To use it, you'll need to install that. `lein install plugin lein-newnew 0.1.1`.

```bash
lein plugin install lein-noir 1.3.0
lein new noir my-website
```

## License

Copyright (C) 2011 Chris Granger

Distributed under the Eclipse Public License, the same as Clojure.
