(defproject truskawka "1.0.0.0-SNAPSHOT"
  :description "truskawka: clojure wget"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.7"]
                 [org.jsoup/jsoup "1.11.3"]]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all :jar-name "truskawka.jar" :uberjar-name "truskawka-uberjar.jar"}
             :main-simple {:main ^:skip-aot pl.tomaszgigiel.truskawka.simple}
             :main-jsoup {:main ^:skip-aot pl.tomaszgigiel.truskawka.jsoup}}
  :aliases {"run-main-simple" ["with-profile" "main-simple" "run"]
            "run-main-jsoup" ["with-profile" "main-jsoup" "run"]})
