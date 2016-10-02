(set-env!
 :dependencies '[[perun "0.3.0" :scope "test"]
                 [hiccup "1.0.5"]
                 [pandeiro/boot-http "0.7.0"]]
  :source-paths   #{"src" "content"}
  :resource-paths #{"resources"})

(require
 '[io.perun :refer :all]
 '[pandeiro.boot-http :refer [serve]])

(deftask build
  []
  (comp
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
   (serve :resource-root "public")))

(deftask build-prod
  []
  (comp
   (build)
   (inject-scripts :scripts #{"ga.js"})))
