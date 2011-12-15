(ns two
  (:gen-class))

(defn ba 
  [h ab]
  (if (= ab 0) 0
      (float (/ h ab))))

(defn standings
  [team] 
  (condp = (keyword team)
    :ari "Arizona came in first" 
    :sfg "San Francisco came in second. Still finished ahead of the Dodgers!"
    :lad "Los Angeles came in third"
    :col "Colorado came in fourth"
    :sdp "San Diego came in last"
    ))

(defn total-bases
  [h dbl tpl hr]
  (+ (+ h dbl) (* 2 tpl) (* 3 hr)))

(defn -ba
  [h ab]
  (ba h ab))

(defn -standings
  [team]
  (standings team))

(defn -total-bases
  [h dbl tpl hr]
  (total-bases h dbl tpl hr))

(defn -main
  []
 (println "This is two.clj. It shows floats, ints and strings 'breaking the barrier'")
 (println "(-ba 3 14) => " (str (-ba 3 14)))
 (println "(-total-bases 20 4 1 3) => " (str (-total-bases 20 4 1 3)))
 (println "(-standings \"SFN\") => " (-standings "sfg"))
 )
 