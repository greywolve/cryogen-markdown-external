# cryogen-markdown-external

A Clojure library to provide Markdown rendering to the [cryogen](http://cryogenweb.org/)-core compiler by using an external command/program, such as [pandoc](http://pandoc.org/).

## Usage

Add `[cryogen-markdown-external "0.1.0"]` to the `:dependencies` keyword of the `project.clj` of your generated cryogen blog.

Then include your external shell command in `config.edn` under the `:markdown-shell-command` as a vector of strings.

E.g.

```clojure
:markdown-shell-command ["pandoc"]
```

## License

Copyright © 2016 Oliver Powell

Distributed under the MIT License. 
