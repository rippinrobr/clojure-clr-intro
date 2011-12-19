;; In the :gen-class call I am indicating which functions should be
;; visible to the C# code.  Since I didn't use :name to give the a
;; different name to the generated class, :gen-class will create a
;; one class. 
;;
;; The methods signatures follow this pattern:
;;
;; [method-name [vector of params] return-val]
;;
;; the meta data is used to indicate that the method should be static
(ns one
  (:require [clojure.core])
  (:gen-class
   :methods [#^{:static true}
		  [standings [System.String System.Object] System.String]
		#^{:static true} [ba [int int int] double]]))

(defn ba
  "calculates the batting average for the hits and at-bats provided.
   If ab is zero then zero is returned"
  [h ab]
  (if (= ab 0) 0
	  (double (/ h ab))))

(defn standings
  "Given a team id keyword a string is returned that states which place
  the team finished in 2011."
  [team] 
  (condp = (keyword team)
	:ari "Arizona came in first" 
	:sfg "San Francisco came in second. Still finished ahead of the Dodgers!"
	:lad "Los Angeles came in third"
	:col "Colorado came in fourth"
	:sdp "San Diego came in last"
	))

(defn -ba
  [h ab dummy]
  (println "-ba params => dummy = " dummy " h = " h " ab = " ab)
  (double (ba h ab)))

(defn  -standings
  [team dummy]
  (println "-standings params => dummy = " dummy " team = " team)
  (standings team))

(defn -main
  []
  (println "This is one.clj...using a more C# way of calling Clojure code. ")
  (println)
  (println "(-ba 3 14 0) => " (str (-ba 3 14 0)))
  (println)
  (println "(-standings \"sfg\") => " (-standings "sfg" 0))
 )
 