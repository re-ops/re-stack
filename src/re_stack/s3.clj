(ns re-stack.s3
  "Deploying templates into S3"
  (:require
   [re-stack.core :refer (aws)]
   [amazonica.aws.s3 :refer (put-object)]
   [amazonica.aws.s3transfer]
   [clojure.java.io :as io]))

(defn name-of
  "file name"
  [file]
  (.getName file))

(defn parent-file
  "file folder parent"
  [file]
  (name-of (.getParentFile file)))

(defn with-dest [parent file]
  (if (= (parent-file file) (name-of (io/file parent)))
    {:dest (name-of file) :file file}
    {:dest (str (parent-file file) "/" (name-of file)) :file file}))

(defn upload-file
  "Upload a single file"
  [{:keys [dest file]} bucket]
  (put-object aws
              :bucket-name bucket
              :key dest
              :metadata {:server-side-encryption "AES256"}
              :file file))

(defn upload
  "Upload cloudformation yml files into an s3 bucket"
  [parent bucket]
  (let [files (file-seq (io/file parent))
        ys (filter (fn [v] (and (.isFile v) (.endsWith (.getName v) ".yaml"))) files)
        ups (map (partial with-dest parent) ys)]
    (doseq [f ups]
      (upload-file f bucket))))

