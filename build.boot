(set-env!
 :dependencies '[[perun "0.3.0" :scope "test"]
                 [hiccup "1.0.5"]
                 [pandeiro/boot-http "0.7.0"]
                 [deraen/boot-livereload "0.1.2"]
                 [deraen/boot-sass "0.3.0-SNAPSHOT"]]
 :source-paths   #{"src" "content"}
 :resource-paths #{"resources"})

(require
 '[io.perun :refer :all]
 '[pandeiro.boot-http :refer [serve]]
 '[deraen.boot-sass :refer [sass]]
 '[deraen.boot-livereload :refer [livereload]])

(deftask sass-with-config
  []
  (comp
   (sass)
   (sift :move {#"scss/index.css" "public/css/index.css"})))

(deftask build
  []
  (comp
   (sass-with-config)
   (global-metadata :filename "mattgauger.base.edn")
   (base)
   (markdown)
   (render :renderer 'site.core/page)
   (inject-scripts :scripts #{"ga.js"})))

(deftask build-dev
  []
  (comp
   (watch)
   (build)
   (serve :resource-root "public")
   (target)
   (livereload)))

(deftask build-prod
  []
  (comp
   (build)
   ;; some github pages step here
   ))
