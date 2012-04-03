;; TFS API Resource: http://msdn.microsoft.com/en-us/library/bb130146(v=vs.90).aspx
(assembly-load-from ".\\libs\\Microsoft.TeamFoundation.dll")
(assembly-load-from ".\\libs\\Microsoft.TeamFoundation.Client.dll")
(assembly-load-from ".\\libs\\Microsoft.TeamFoundation.WorkItemTracking.Client.dll")

(ns tfs-work-items
  (:import [Microsoft.TeamFoundation.Client TeamFoundationServerFactory])
  (:import [Microsoft.TeamFoundation.WorkItemTracking.Client Attachment
	                                      WorkItemStore WorkItem])
  (:import [Microsoft.TeamFoundation.Server ICommonStructureService]))

(defn create-work-item [wi-type title desc area iter]
  (let [wi (WorkItem. wi-type)]
    (set! (.Title wi) title)
    (set! (.Description wi) desc)
    (set! (.AreaPath wi) area)
    (set! (.IterationPath wi) iter)
    wi))

(defn add-attachment [wi path desc]
  (let [attachment (Attachment. path desc)]
    (.Add (.Attachments wi) attachment)))
	
(defn -main [& args]
  (let [tfs (TeamFoundationServerFactory/GetServer "phw-ptrak")
	wi-store (WorkItemStore. tfs)
	project (first (filter #(= (.Name %) "RnD") (.Projects wi-store)))
        bug-type (first (filter #(= (.Name %) "Bug") (.WorkItemTypes project)))
        work-item (create-work-item bug-type
		      (str "clj-clr bug creation (" (.ToString (DateTime/Now)) ")")
		       "This is a bug created from my clj-clr code!!!"
		       "RnD\\Clojure-clr Testing"
		       "RnD\\Testing")]

    (add-attachment work-item
		    "clg-clr-tfs-bug.PNG"
		    "Example image for showing how to add an attachment")
        
    (.Save work-item)

    (println (str "I just created Bug #" (.Id work-item) " with an attachment!"))
    ))
    
    