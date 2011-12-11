# lein-noir

A [leiningen](https://github.com/technomancy/leiningen) plugin to help create and manage noir projects.

## Usage

```bash
lein plugin install lein-noir 1.2.0
lein noir new my-website
```

Currently the only supported operation is new, but more will be coming to help deploy and manage projects.

## Hacking lein-noir

```bash
git clone <your-repo>/lein-noir.git
cd lein-noir
... some hacking here ...
lein install
lein plugin install lein-noir x.y.z
```

## License

Copyright (C) 2011 Chris Granger

Distributed under the Eclipse Public License, the same as Clojure.
