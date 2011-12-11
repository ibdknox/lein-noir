(ns leiningen.noir.new
  (:require [clojure.string :as string])
  (:use clojure.java.io))

(declare *project-dir* *project* *dirs*)
(def dir-keys- [:css :js :img :views :models :test])

;;From the maginalia source: http://fogus.me/fun/marginalia/
(defn slurp-resource-
  [resource-name]
  (try
    (-> (.getContextClassLoader (Thread/currentThread))
        (.getResourceAsStream resource-name)
        (java.io.InputStreamReader.)
        (slurp))
    (catch java.lang.NullPointerException npe
      (println (str "Could not locate resources at " resource-name))
      (println "    ... attempting to fix.")
      (let [resource-name (str "./resources/" resource-name)]
        (try
          (-> (.getContextClassLoader (Thread/currentThread))
              (.getResourceAsStream resource-name)
              (java.io.InputStreamReader.)
              (slurp))
          (catch java.lang.NullPointerException npe
            (println (str "    STILL could not locate resources at " resource-name ". Giving up!"))))))))

(defn get-dir- [n]
  (get *dirs* n))

(defn clean-proj-name- [n]
  (string/replace n #"-" "_"))

(defn get-template- [n]
  (let [tmpl (slurp-resource- (str "templates/" n))]
    (-> tmpl
      (string/replace #"\$project\$" *project*)
      (string/replace #"\$safeproject\$" (clean-proj-name- *project*)))))

(defn mkdir- [args]
  (.mkdirs (apply file *project-dir* args)))

(defn ->file- [path file-name content]
  (spit (apply file *project-dir* (conj path file-name)) content))

(defn create-dirs- []
  (doseq [k dir-keys-]
    (mkdir- (get-dir- k))))

(defn populate-dirs- []
  (->file- [] "README.md" (get-template- "README.md"))
  (->file- [] "project.clj" (get-template- "project.clj"))
  (->file- [] ".gitignore" (get-template- "gitignore"))
  (->file- (get-dir- :css) "reset.css" (get-template- "reset.css"))
  (->file- (get-dir- :src) "server.clj" (get-template- "server.clj"))
  (->file- (get-dir- :views) "common.clj" (get-template- "common.clj"))
  (->file- (get-dir- :views) "welcome.clj" (get-template- "welcome.clj")))

(defn create [proj-name]
  (let [clean-name (clean-proj-name- proj-name)]
    (binding [*project* proj-name
              *project-dir* (-> (System/getProperty "leiningen.original.pwd")
                              (file proj-name)
                              (.getAbsolutePath))
              *dirs* {:src ["src" clean-name]
                      :views ["src" clean-name "views"]
                      :models ["src" clean-name "models"]
                      :test ["test" clean-name]
                      :css ["resources" "public" "css"]
                      :js ["resources" "public" "js"]
                      :img ["resources" "public" "img"]}]
      (println "Creating noir project: " *project*)
      (println "Creating new dirs at: " *project-dir*)
      (create-dirs-)
      (println "Adding files...")
      (populate-dirs-)
      (println "Project created!"))))
