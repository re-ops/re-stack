(ns user
  (:refer-clojure :exclude  [update list])
  (:require
   [re-stack.core :refer :all]
   [re-stack.s3 :refer :all]
   [re-stack.cloudformation :refer :all]
   [mount.core :as mount]
   [clojure.repl :refer :all]
   [re-stack.log :refer (setup-logging)]
   [clojure.tools.namespace.repl :refer (refresh refresh-all)]
   [clojure.java.io :as io]
   ; re-share
   [re-share.log :refer (redirect-output debug-on debug-off)])
  (:import java.io.File))

(defn start []
  (mount/start #'aws))

(defn stop []
  (mount/stop))

(defn go
  "starts all states defined by defstate"
  []
  (setup-logging)
  (start)
  (doseq [f (filter (fn [v] (and (.isFile v) (.endsWith (.getName v) ".clj"))) (file-seq (io/file "scripts")))]
    (load-file (.getPath f)))
  :ready)

(defn reset
  "stops all states defined by defstate, reloads modified source files, and restarts the states"
  []
  (stop)
  (refresh :after 'user/go))

(defn clrs
  "clean repl"
  []
  (print (str (char 27) "[2J"))
  (print (str (char 27) "[;H")))

(defn history
  ([]
   (history identity))
  ([f]
   (doseq [line (filter f (clojure.string/split (slurp ".lein-repl-history") #"\n"))]
     (println line))))

