;;------------------------------------------------------------------
;; 0.) Getting Started with Clojure-CLR
;;     Ensures that your clojure-clr environment is setup correctly
;;     and shows you how to call static .NET methods.
;;
;;     The code is a compilation of REPL entries I used in my
;;     "Getting Started with Clojure-CLR" blogpost 

;;     http://rob-rowe.blogspot.com/2011/10/getting-started-with-clojure-clr.html     
;;-------------------------------------------------------------------

(ns intro
   (:gen-class))

 ;;  I'm using the LoadWithPartialName to save me typing.  Remember you
 ;;  can also use Load method like so:
 ;;   
 ;;  (System.Reflection.Assembly/Load "System.Windows.Forms,
 ;;      Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089")

(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")
(import (System.Windows.Forms MessageBox))

(defn -main [& args]
   (println "yep, it worked! (called println)")
   (System.Console/WriteLine 
     "I just called a .NET method! (called System.Console/WriteLine)")
   (println "Remeber to dismiss the dialog so the program will end.")
   (println "The message box doesn't always show up on top!")
   (MessageBox/Show "Hi from clojure-clr!" "Clojure-CLR Dialog"))

