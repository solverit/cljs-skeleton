(defproject cljs-skeleton "0.1.0-SNAPSHOT"
  :dependencies [ 
    [org.clojure/clojure        "1.8.0"]
    [org.clojure/clojurescript  "1.9.89"]
    [rum                        "0.10.4"]
    [http-kit                   "2.1.19"]
    [compojure                  "1.5.1" :exclusions [commos-codec]]
    [com.cognitect/transit-clj  "0.8.285"]
    [com.cognitect/transit-cljs "0.8.239"]
  ]
  
  :plugins [
    [lein-cljsbuild "1.1.3"]
    [lein-figwheel  "0.5.4-7"]
  ]
  
  :aliases      { "package" ["do" 
                             "cljsbuild" "once" "advanced,"
                             "uberjar"] }
  :aot          [ skeleton.server ]
  :uberjar-name "skeleton.jar"
  :uberjar-exclusions [#"public/js/out"]

  
  :main         skeleton.server
  :figwheel     { :ring-handler  "skeleton.server/app"
                  :css-dirs     ["resources/public"]
                  :server-port   7080
                  :repl          false }
  
  :cljsbuild {
    :builds [
      { :id           "none" 
        :source-paths ["src"]
        :figwheel     { :on-jsload      "skeleton.app/refresh" }
        :compiler     { :optimizations  :none
                        :main           skeleton.app
                        :asset-path     "/js/out"
                        :output-to      "resources/public/js/main.js"
                        :output-dir     "resources/public/js/out"
                        :source-map     true
                        :compiler-stats true } }
             
      { :id           "advanced" 
        :source-paths ["src"]
        :compiler     { :optimizations  :advanced
                        :main           skeleton.app
                        :output-to      "resources/public/js/main.js"
                        :compiler-stats true
                        :pretty-print   false
                        :pseudo-names   false } }
  ]}
)
