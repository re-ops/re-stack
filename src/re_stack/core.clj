(ns re-stack.core
  (:require
   [mount.core :as mount :refer (defstate)]
   [re-share.config :as conf :refer (get!)]))

(def env :default)

(defn hypervisor
  "obtains current environment hypervisor"
  [& ks]
  (apply get! :re-core :hypervisor env ks))

(defn creds []
  (conf/load (fn [_]))
  (dissoc (hypervisor :aws) :ostemplates))

(def endpoint "ap-southeast-2")

(defstate aws
  :start (assoc (creds) :endpoint endpoint))
