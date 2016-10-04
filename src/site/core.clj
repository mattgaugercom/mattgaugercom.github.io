(ns site.core
  (:require [hiccup.page :as hp]
            [hiccup.util :as util]))

(def header
  [:header {:class "container"}
   [:nav {:class "row"}
    [:div {:class "col-6 nav-brand"}
     [:a {:href "http://mattgauger.com" :class "is-brand"}
      [:h1 "&#955; mattgauger.com"]]]
    [:div {:class "hamburger"}
     (repeat 3 [:span])]
    [:div {:class "col-6 nav-menu"}
     [:a {:href "http://blog.mattgauger.com" :class ""} "Blog"]
     [:a {:href "mailto:contact@mattgauger.com" :class ""} "Contact"]]]])

(def footer
  [:footer {:class "footer container"}
   [:div {:class "row"}
    [:div {:class "col-12 has-text-centered"}
     [:p
      [:strong "mattgauger.com"]
      " by "
      [:a {:href "http://mattgauger.com"} "Matt Gauger"]
      " &copy; 2016"]
     [:p
      "Made with "
      [:a {:href "http://clojure.org/"} "Clojure"]
      ", "
      [:a {:href "http://perun.io"} "perun"]
      ", and "
      [:a {:href "http://boot-clj.com"} "Boot"]
      ". "
      [:a {:href "https://github.com/mattgaugercom/mattgaugercom.github.io"} "Repo on Github"]
      "."]]]])

(defn page [data]
  (hp/html5
   {:lang "en"}
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv"x-ua-compatible" :content "ie=edge"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
    [:title "mattgauger.com"]
    (hp/include-css "https://unpkg.com/reeeset@0.0.7")
    (hp/include-css "/css/index.css")
    (hp/include-js "https://unpkg.com/headroom.js")]
   [:body
    header
    [:div {:class "container"}
     [:div {:class "row"}
      [:div {:class "col-12"}
       (-> data :entry :content)]]]
    footer
    (util/as-str "
<!--[if lt IE 9]>
  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>
  <script>console.log(window.jQuery)</script>
  <script src=\"js/polyfill/ie-love.js\"></script>
<![endif]-->
<!--[if gt IE 8]><!-->
  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js\"></script>
  <script>console.log(window.jQuery)</script>
<!--<![endif]-->")]))
