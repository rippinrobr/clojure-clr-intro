;;------------------------------------------------------------------
;; 1.) Calling a .NET instance method from ClojureCLR
;;     A *VERY* brief intro how-to instantiate a .NET object
;;     and interact with instance methods and properties.
;;
;;     The code retrieves the home page from http://clojure.org,
;;     parses the links from the first paragraph to the paragraph
;;     just above the copyright message.  After the parsing has been
;;     completed the text and the href of the links are written to
;;     a file named links.txt.
;;
;;     The parsing is done by using an open source .NET library 
;;     called HtmlAgilityPack.  It can be found here:
;;
;;     http://htmlagilitypack.codeplex.com/
;;
;;     This code ran against ClojureCLR 1.3 Debug version for 4.0
;;
;;-------------------------------------------------------------------

;; I ran gacutil -i HtmlAgilityPack in the libs dir as administrator
;; to load the dll into the gac for simplicity.
(System.Reflection.Assembly/LoadWithPartialName "HtmlAgilityPack")

(ns method
  (:gen-class))

(defn -main [& args]
  (let [doc-node (.DocumentNode (.Load (HtmlAgilityPack.HtmlWeb.) "http://clojure.org"))
        links (.SelectNodes doc-node "//div[@id='content_view']/a[@class='wiki_link_ext']")
        f-stream (System.IO.StreamWriter. "links.txt")]
    (doseq [l links]
      (let [text (str (.InnerHtml l) "\n" (.Value (second (.Attributes l))) "\n")]
        (.WriteLine f-stream text)))
      (.Close f-stream)))