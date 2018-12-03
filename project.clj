(defproject re-stack "0.9.0"
  :description "Fast Cloudformation"

  :dependencies [[org.clojure/clojure "1.9.0"]

                 [amazonica "0.3.94" :exclusions [com.taoensso/nippy]]
                 [me.raynes/fs "1.4.6"]
                 [re-share "0.9.11"]


                 ; common utilities and shared functions
                 [org.clojure/core.incubator "0.1.4"]

                 ; repl
                 [org.clojure/tools.namespace "0.2.11"]

                 ; wiring
                 [mount "0.1.13"]]

  :plugins  [[jonase/eastwood "0.2.4"]
             [mvxcvi/whidbey "1.3.1"]
             [lein-cljfmt "0.5.6"]
             [lein-kibit "0.1.5"]
             [lein-ancient "0.6.15" :exclusions [org.clojure/clojure]]
             [lein-tar "2.0.0" ]
             [self-build "0.0.9"]
             [lein-tag "0.1.0"]
             [venantius/ultra "0.5.2"]
             [lein-set-version "0.3.0"]]

  :profiles {
     :dev {
        :source-paths  ["dev"]
        :set-version {
           :updates [
             {:path "project.clj" :search-regex #"\"target\/re-core-\d+\.\d+\.\d+\.jar"}
             {:path "src/re-core/common.clj" :search-regex #"\"\d+\.\d+\.\d+\""}]}

      }
    }


  :repl-options {
    :init-ns user
    :prompt (fn [ns] (str "\u001B[35m[\u001B[34m" "re-stack" "\u001B[35m]\u001B[33mλ:\u001B[m " ))
    :welcome (println "Welcome to re-stack!" )
  }

)
