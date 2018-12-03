(ns re-stack.log
  "Log setup"
  (:require
   [timbre-ns-pattern-level :as level]
   [clojure.string :refer (join upper-case)]
   [taoensso.timbre.appenders.3rd-party.rolling :refer (rolling-appender)]
   [taoensso.timbre.appenders.core :refer (println-appender)]
   [clansi.core :refer (style)]
   [taoensso.timbre :refer (refer-timbre set-level! merge-config!)]
   [clojure.core.strint :refer (<<)]
   [clj-time.core :as t]
   [clj-time.coerce :refer [to-long]]
   [clojure.java.io :refer (reader)]
   [re-share.log :as log]
   [re-share.schedule :refer (watch seconds)]))

(defn setup-logging
  "Sets up logging configuration:
    - stale logs removale interval
    - steam collect logs
    - log level
  "
  [& {:keys [interval level] :or {interval 10 level :info}}]
  (log/setup "re-stack" [] ["re-stack"])
  (set-level! level))
