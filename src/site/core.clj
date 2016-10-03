(ns site.core
  (:require [hiccup.page :as hp]))

(def footer
  [:footer {:class "footer"}
   [:div {:class "container"}
    [:div {:class "content has-text-centered"}
     [:p
      [:strong "mattgauger.com"]
      " by "
      [:a {:href "http://mattgauger.com"} "Matt Gauger"]
      " &copy; 2016"]
     [:p
      "Made with "
      [:a {:href "http://perun.io"} "perun"]
      " and "
      [:a {:href "http://boot-clj.com"} "boot"]
      "."]]]])

(defn page [data]
  (hp/html5
   {:lang "en"}
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv"x-ua-compatible" :content "ie=edge"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
    [:title "mattgauger.com"]
    (hp/include-css "https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css")
    (hp/include-css "/css/index.css")
    (hp/include-js "https://unpkg.com/headroom.js")]
   [:body
    [:header {:class "container"}
     [:nav {:class "row"}
      [:div {:class "nav-brand"}
       [:a {:href "http://mattgauger.com" :class "is-brand"}
        [:h1 "&#955; mattgauger.com"]]]
      [:div {:class ""}
       (repeat 3 [:span])]
      [:div {:class "nav-menu"}
       [:a {:href "http://blog.mattgauger.com" :class ""} "Blog"]
       [:a {:href "mailto:contact@mattgauger.com" :class ""} "Contact"]]]]
    [:div {:class "container"}
     [:div {:class "row"}
      (-> data :entry :content)]]
    footer]))
