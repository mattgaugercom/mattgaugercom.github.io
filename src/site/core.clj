(ns site.core
  (:require [hiccup.page :as hp]))

(defn page [data]
  (hp/html5
   [:head
    (hp/include-css "/css/index.css")]
   [:body
    [:nav {:class "nav container"}
      [:div {:class "nav-left"}
       [:a {:href "http://mattgauger.com" :class "nav-item is-brand"}
        [:h1 "&#955; mattgauger.com"]]]
      [:div {:class "nav-center"}]
     [:div {:class "nav-toggle"}
      (repeat 3 [:span])]
      [:div {:class "nav-right nav-menu"}
       [:a {:href "http://blog.mattgauger.com" :class "nav-item"} "Blog"]
       [:a {:href "mailto:contact@mattgauger.com" :class "nav-item"} "Contact"]]]
    [:div {:class "container"}
     [:div {:class "content is-medium"}
      (-> data :entry :content)]]
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
        "."]]]]]))
