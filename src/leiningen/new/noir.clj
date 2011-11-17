(ns leiningen.new.noir
  (:use leiningen.new.templates))

(def render (renderer "noir"))

(defn noir
  "A skeleton Noir project."
  [name]
  (let [data {:name name
              :sanitized (sanitize name)}]
    (->files name data
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)]
             ["README.md" (render "README.md" data)]
             ["src/{{sanitized}}/server.clj" (render "server.clj" data)]
             ["src/{{sanitized}}/views/welcome.clj" (render "welcome.clj" data)]
             ["src/{{sanitized}}/views/common.clj" (render "common.clj" data)]
             ["resources/public/css/reset.css" (render "reset.css" data)]
             "resources/public/js"
             "resources/public/img"
             "src/{{sanitized}}/models"
             "test/{{sanitized}}")))

