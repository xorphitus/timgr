(ns timgr.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :as hiccup]))

(defn layout [title content]
  (html
   (hiccup.page/doctype :html5)
   [:html {:lang "ja"}
    [:head
     [:meta {:charset "UTF-8"}]
     [:title title]
     [:link {:rel "stylesheet" :href "//ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/themes/base/jquery-ui.css"}]
     [:link {:rel "stylesheet" :href "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.css"}]
     [:link {:rel "stylesheet" :href "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.print.css"}]]
    [:body content
     [:script {:src "//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"}]
     [:script {:src "//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"}]
     [:script {:src "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.min.js"}]
     [:script {:src "/main.js"}]]]))

(defroutes app-routes
  (GET "/" [] (layout "main page" [:div#calendar]))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
