(ns pl.tomaszgigiel.truskawka.simple
  (:require [clojure.java.io :as io])
  (:require [clojure.tools.logging :as log])
  (:import java.io.File)
  (:import java.net.URL)
  (:gen-class))

(defn copy-uri-to-file [uri file]
  (with-open [in (io/input-stream uri)
              out (io/output-stream file)]
    (io/copy in out)))

(defn -main
  "truskawka: clojure wget"
  [& args]
  (copy-uri-to-file (new URL (first args)) (new File (second args)))
  (log/info "ok (simpple)"))
