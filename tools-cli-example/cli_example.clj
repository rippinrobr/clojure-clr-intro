(ns cli-example
  (:use cljclr.tools.cli)
  (:gen-class))

(defn -main
  [& args]
   (let [[options args banner]
	 (cli args ["-g" "--greet" "Say Hi to someone"]
		   ["-h" "--help" "Show help" :default false :flag true]
   	           ["-b" "--bye" "Say thanks to someone" :default false ])]
    (cond
     (:help options)(doall (println banner));(Environment/Exit 0))
     (:bye options) (println "Bye" (:bye options))
     (:greet options) (println "Hi " (:greet options))
     :else (println (str "You must have at least one option\n" banner)))))