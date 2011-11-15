(ns fauxlein
  (:gen-class))

(defn mk-project-dirs [proj-name]
   (println (str "Creating the directory " proj-name))
   (System.IO.Directory/CreateDirectory proj-name)
   (println (str "Creating the directory " proj-name "/src"))
   (System.IO.Directory/CreateDirectory (str proj-name "/src"))
   (println (str "Creating the directory " proj-name "/bin"))
   (System.IO.Directory/CreateDirectory (str proj-name "/bin")))

(defn display-help
   [] (println "pseudo help message"))

(defn -main [& args]
   (if (= (count args) 0) (display-help)
     (if (= (.ToLower (first args)) "new")
       (if (> (count (rest args)) 0)
         (mk-project-dirs (second args))
         (println "No project name given"))            
       (println "I don't know what to do"))))