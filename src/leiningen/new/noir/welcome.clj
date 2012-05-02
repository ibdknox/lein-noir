(ns {{name}}.views.welcome
  (:require [{{name}}.views.common :as common]
            [noir.content.pages :as pages])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to {{name}}"]))
