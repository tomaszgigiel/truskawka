(defproject truskawka "1.0.0.0-SNAPSHOT"
  :description "truskawka: clojure wget"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.7"]
                 [org.clojure/tools.logging "0.4.1"]
                 ; otherwise log4j.properties has no effect
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jmdk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [org.jsoup/jsoup "1.11.3"]]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all :jar-name "truskawka.jar" :uberjar-name "truskawka-uberjar.jar"}
             :main-simple {:main ^:skip-aot pl.tomaszgigiel.truskawka.simple}
             :main-jsoup {:main ^:skip-aot pl.tomaszgigiel.truskawka.jsoup}
             :dev {:resource-paths ["src/test/resources"]}}
  :aliases {"run-main-simple-dev" ["with-profile" "main-simple,dev" "run"]
            "run-main-jsoup-dev" ["with-profile" "main-jsoup,dev" "run"]})
