(ns {{name}}.views.welcome
  (:require [{{name}}.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to {{name}}"]))
