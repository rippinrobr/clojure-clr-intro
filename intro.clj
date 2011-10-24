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

