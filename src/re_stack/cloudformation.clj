(ns re-stack.cloudformation
  "Cloudformation manipulation"
  (:require
   [re-stack.core :refer (aws)]
   [amazonica.aws.cloudformation :as form]))

(defn create-stack [stack template capabilities]
  (form/create-stack aws :stack-name stack :template-url template :capabilities capabilities))

(defn update-stack [stack template capabilities]
  (form/update-stack aws :stack-name stack :template-url template :capabilities capabilities))

(defn delete-stack [stack]
  (form/delete-stack aws :stack-name stack))

(defn describe-stack [stack]
  (form/describe-stack-resources aws :stack-name stack))

(defn list-stasks []
  (:stack-summaries (form/list-stacks aws)))

(defn stack-status
  "Tasks with name prefix status"
  [prefix]
  (map (juxt :stack-name :stack-status)
       (filter (fn [{:keys [stack-name]}] (.startsWith stack-name prefix)) (list-stasks))))
