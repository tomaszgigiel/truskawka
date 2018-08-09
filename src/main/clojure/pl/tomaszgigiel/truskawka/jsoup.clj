(ns pl.tomaszgigiel.truskawka.jsoup
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as string])
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:require [clojure.tools.logging :as log])
  (:import java.io.File)
  (:import java.net.URL)
  (:import org.jsoup.Jsoup)
  (:gen-class))

(def cli-options
  [["-o" "--output-file LOGFILE" "log all messages to logfile"]
   ["-p" "--port PORT" "proxy port number"
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ["-H" "--host HOST" "proxy host"]
   ["-v" "--validate-tls-certificates VALIDATE" "if should validate TLS (SSL) certificates, true by default"
    :default true
    :parse-fn #(Boolean/parseBoolean %)]
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["truskawka: clojure wget"
        ""
        "Usage: truskawka [options] uri"
        ""
        "Options:"
        options-summary
        ""
        "Please refer to the manual page for more information."]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn validate-args
  "Validate command line arguments. Either return a map indicating the program
  should exit (with a error message, and optional ok status), or a map
  indicating the action the program should take and the options provided."
  [args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) ; help => exit OK with usage summary
      {:exit-message (usage summary) :ok? true}
      errors ; errors => exit with description of errors
      {:exit-message (error-msg errors)}
      ;; custom validation on arguments
      (= 1 (count arguments))
      {:uri (first arguments) :options options}
      :else ; failed custom validation => exit with usage summary
      {:exit-message (usage summary)})))

(defn exit [status msg]
  (log/info msg)
  (System/exit status))

(defn copy-uri-to-file [uri file v]
  (with-open [out (io/output-stream file)]
    (io/copy (.. Jsoup (connect uri) (validateTLSCertificates v) get outerHtml) out)))

(defn -main [& args]
  "truskawka: clojure wget by jsoup"
  (let [{:keys [uri options exit-message ok?]} (validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (do (if (contains? options :host) (.. System (setProperty "https.proxyHost" (:host options))))
        (if (contains? options :port) (.. System (setProperty "https.proxyPort" (str (:port options)))))
        (copy-uri-to-file uri (new File (:output-file options)) (:validate-tls-certificates options)))))
  (log/info "ok (jsoup)"))
