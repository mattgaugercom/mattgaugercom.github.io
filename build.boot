#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
 :project 'mattgaugerdotcom
 :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                 [tailrecursion/hoplon      "5.10.17"]
                 [hum                       "0.3.0"]]
 :out-path     "out"
 :src-paths    #{"src/"})

;; Static assets
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.boot.task.ring :refer [dev-server]])

(deftask development
  "Build site for development."
  []
  (comp (watch)
        (hoplon {:prerender false :pretty-print true})
        (dev-server)))

(deftask production
  "Build site for production."
  []
  (hoplon {:optimizations :advanced}))
