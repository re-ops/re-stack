(ns re-stack.cloudformation
  "Cloudformation manipulation"
  (:require
   [re-stack.core :refer (aws)]
   [amazonica.aws.cloudformation :as form]))

(defn create-stack [stack template capabilities]
  (form/create-stack aws :stack-name stack :template-url template :capabilities capabilities))

(defn delete-stack [stack]
  (form/delete-stack aws :stack-name stack))

(defn describe-stack [stack]
  (form/describe-stack-resources aws :stack-name stack))
