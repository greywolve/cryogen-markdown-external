(ns cryogen-markdown-external.core
  (:require [cryogen-core.markup :refer [rewrite-hrefs markup-registry]]
            [clojure.string :as s]
            [me.raynes.conch.low-level :as sh])
  (:import cryogen_core.markup.Markup))

(defn shell-command [command input-string]
  "Takes a shell command as a vector of strings, and an input string. Returns
   the output of the given shell command as a string"
  (assert (vector? command))
  (let [p (apply sh/proc command)]
    (sh/feed-from-string p input-string)
    (sh/done p)
    (sh/stream-to-string p :out)))

(defn markdown-external
  "Returns an external Markdown program/command implementation of the Markup protocol."
  []
  (reify Markup
    (dir [this] "md")
    (ext [this] ".md")
    (render-fn [this]
      (fn [rdr config]
        (->> (shell-command
              (:markdown-shell-command config)
              (->> (java.io.BufferedReader. rdr)
                   (line-seq)
                   (s/join "\n")))
             (rewrite-hrefs (:blog-prefix config)))))))

(defn init []
  (swap! markup-registry conj (markdown-external)))

