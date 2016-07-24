(set-env!
 :dependencies '[[perun "0.3.0" :scope "test"]
                 [hiccup "1.0.5"]
                 [pandeiro/boot-http "0.7.0"]]
  :source-paths   #{"src" "content"}
  :resource-paths #{"assets"})

(require
 '[io.perun :refer :all]
 '[pandeiro.boot-http :refer [serve]])

(deftask build
  []
  (comp
   (watch)
   (base)
   (markdown)
   (render :renderer 'site.core/page)))

(deftask dev
  []
  (comp
   (build)
   (serve :resource-root "public")))
