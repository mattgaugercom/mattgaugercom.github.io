#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
 :project 'mattgaugerdotcom
 :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                 [tailrecursion/hoplon      "5.10.17"]]
 :out-path     "gh-pages"
 :src-paths    #{"src/"})

;; Static assets
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.boot.task.ring :refer [dev-server]])

(def bgs
  "List of tasks running in other threads that will need to be cleaned up before
  boot can exit."
  (atom ()))
 
;; cleanup background tasks on shutdown
(->
  (Runtime/getRuntime)
  (.addShutdownHook (Thread. #(doseq [job @bgs] (future-cancel job)))))
 
(defn shell [& args]
  ((apply #'tailrecursion.boot.core.task/sh args)))

(deftask once
  "Evaluate the `task` only once. Subsequent evaluations will pass through."
  [task]
  (let [ran? (atom false)
        run? (partial compare-and-set! ran? @ran?)]
    (fn [continue]
      (let [task (task continue)]
        #(continue ((if (run? true) task identity) %)))))) 

(deftask bg
  "Run the given `task` once, in a separate thread."
  [task]
  (once
    (with-pre-wrap
      (swap! bgs conj (future ((task identity) *event*))))))

(deftask watch-sass []
  (shell "grunt" "watch"))

(deftask development
  "Build site for development."
  []
  (comp (watch)
        (hoplon {:prerender false :pretty-print true})
        (dev-server)
;;        (bg (watch-sass))
        ))

(deftask production
  "Build site for production."
  []
  (hoplon {:optimizations :advanced}))
