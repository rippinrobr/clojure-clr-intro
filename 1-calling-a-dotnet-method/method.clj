;;;------------------------------------------------------------------
;;; 1.) Calling a .NET instance method from ClojureCLR
;;;     A *VERY* brief intro how-to instantiate a .NET object
;;;     and interact with instance methods and properties.
;;;
;;;     The code retrieves the home page from http://clojure.org,
;;;     parses the links from the first paragraph to the paragraph
;;;     just above the copyright message.  After the parsing has been
;;;     completed the text and the href of the links are written to
;;;     a file named links.txt.
;;;
;;;     The parsing is done by using an open source .NET library 
;;;     called HtmlAgilityPack.  It can be found here:
;;;
;;;     http://htmlagilitypack.codeplex.com/
;;;
;;;     This code ran against ClojureCLR 1.3 Debug version for 4.0
;;;
;;;-------------------------------------------------------------------

;; I ran gacutil -i HtmlAgilityPack in the libs dir as administrator
;; to load the dll into the gac for simplicity.
(System.Reflection.Assembly/LoadWithPartialName "HtmlAgilityPack")

(ns method
  (:gen-class))

(defn -main [& args]
  ;; below is where I use the HtmlAgilityPack to retrieve a web page and convert it into
  ;; an object that can be used to retrieve the text and links I am looking for.  I create
  ;; a HtmlAgilityPack object with the (HtmlAgilityPack.HtmlWeb.) call, call its load method
  ;; and pass the URL to the clojure.org site and store the resulting DocumentObject for use
  ;; in the parsing section.
  (let [doc-node (.DocumentNode (.Load (HtmlAgilityPack.HtmlWeb.) "http://clojure.org"))
        ;; I then call SelectNoes on the doc-node object and tell it to return all links
        ;; that match the given XPATH.
        links (.SelectNodes doc-node "//div[@id='content_view']/a[@class='wiki_link_ext']")
        ;; And finally I create the StreamWriter object that will create the links.txt file.
        f-stream (System.IO.StreamWriter. "links.txt")]
    ;; using doseq to print out the link's text and href
    (doseq [l links]
      (let [text (str (.InnerHtml l) "\n" (.Value (second (.Attributes l))) "\n")]
        (.WriteLine f-stream text)))
      (.Close f-stream)))