(ns $project$.server
  (:require [noir.server :as server]))

(server/load-views "src/$project$/views/")

(defn -main [& m]
  (let [mode (or (first m) :dev)]
    (server/start 8084 {:mode (keyword mode)
                        :ns 'noir})))

