(defproject truskawka "1.0.0.0"
  :description "truskawka: clojure wget"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot pl.tomaszgigiel.truskawka.core

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all :jar-name "truskawka.jar" :uberjar-name "truskawka-uberjar.jar"}})