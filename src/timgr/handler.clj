(ns timgr.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :as hiccup]))

(defn layout [title content]
  (hiccup/html
   (hiccup.page/doctype :html5)
   [:html {:lang "ja"}
    [:head
     [:meta {:charset "UTF-8"}]
     [:title title]
     [:link {:rel "stylesheet" :href "//ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/themes/base/jquery-ui.css"}]
     [:link {:rel "stylesheet" :href "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.css"}]
     [:link {:rel "stylesheet" :href "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.print.css" :media "print"}]
     [:link {:rel "stylesheet" :href "//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"}]
     [:link {:rel "stylesheet" :href "//cdn.jsdelivr.net/jqplot/1.0.8/jquery.jqplot.css"}]
     [:link {:rel "stylesheet" :href "/main.css"}]]
    [:body
     [:div.navbar.navbar-inverse.navbar-fixed-top
      [:div.container
       [:div.navbar-header
        [:a.navbar-brand {:href "/"} "TIMGR!"]]
       [:div.collapse.navbar-collapse
        [:ul.nav.navbar-nav
         [:li.active [:a {:href "/"} "time recording"]]
         [:li [:a {:href "/statistics"} "statistics"]]
         ]]]]
     [:div#main-content.container content]
     [:script {:src "//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"}]
     [:script {:src "//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"}]
     [:script {:src "//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"}]
     [:script {:src "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.min.js"}]
     [:script {:src "//cdn.jsdelivr.net/jqplot/1.0.8/jquery.jqplot.js"}]
     [:script {:src "//cdn.jsdelivr.net/jqplot/1.0.8/plugins/jqplot.barRenderer.min.js"}]
     [:script {:src "//cdn.jsdelivr.net/jqplot/1.0.8/plugins/jqplot.categoryAxisRenderer.min.js"}]
     [:script {:src "//cdn.jsdelivr.net/jqplot/1.0.8/plugins/jqplot.pointLabels.min.js"}]
     [:script {:src "//cdn.jsdelivr.net/jqplot/1.0.8//plugins/jqplot.pieRenderer.min.js"}]
     [:script {:src "/main.js"}]]]))

(defn statistics-content []
     [:div#statistics
      [:form.form-inline {:role "form"}
       [:div.form-group
        [:label.sr-only "date from"]
        [:input.form-control.datepicker {:type "text" :placeholder "date from"}]]
       "&nbsp;~&nbsp;"
       [:div.form-group
        [:label.sr-only "date to"]
        [:input.form-control.datepicker {:type "text" :placeholder "date to"}]]
       "&nbsp;"
       [:a#go.btn.btn-default {:href "#"} "Go"]
       ]
      [:div#result.hide
       [:h3 "accumulation"]
       [:div#graph1]
       [:h3 "ratio"]
       [:div#graph2]]])

(defroutes app-routes
  (GET "/" [] (layout "time recording" [:div#calendar]))
  (GET "/statistics" [] (layout "statistics" (statistics-content)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
