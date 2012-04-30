(ns leiningen.noir
  (:require [leiningen.noir.new :as nnew])
  (:use [leiningen.help :only (help-for)]))

(defn new [& [proj-name]]
  (if-not proj-name
    (println "No project name given:\r\n~    lein noir new my-website")
    (nnew/create proj-name)))

(defn ^:no-project-needed noir
  "Create and manage noir projects."
  {:help-arglists '([new])
   :subtasks [#'new]}
  ([project]
     (println (help-for "noir")))
  ([project subtask & args]
     (case subtask
       "new"     (apply leiningen.noir/new args)
       (println (help-for "noir")))))
