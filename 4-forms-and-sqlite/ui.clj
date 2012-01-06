(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns ui
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form
	    TableLayoutPanel MessageBox PaintEventHandler Label])
  (:import [System.Drawing Size Font FontStyle Point])
  (:use db.mysql)
  (:require [db.sqlite :as sql])
  (:gen-class))

(def conn-str "SERVER=localhost;DATABASE=bdb_post_2010;UID=rob-clr;PASSWORD=rob-clr;")

(defn -main
  [& args]
  (let [form (Form.)
	load-btn (Button.)
	title-lbl (Label.)
	migrate-btn (Button.)
	grp-box (GroupBox.)
	chkd-list (CheckedListBox.)
	title-str "Rob's Table Migration Tool ClojureCLR style!"
	]

    (.set_Text form title-str)
    
    ; Title Label
    (.set_Text title-lbl title-str)
    (.set_Location title-lbl (Point. 12 12))
    (.set_Size title-lbl (Size. 360 22))
    (.set_Font title-lbl (Font. "Microsoft Sans Serif" 12.0 System.Drawing.FontStyle/Bold System.Drawing.GraphicsUnit/Point 0))
    
    ; GroupBox
    (.set_Text grp-box "Database Tables")
    (.set_Location grp-box (Point. 12 48))
    (.set_Size grp-box (Size. 360 333))

    ; CheckedListBox
    (.set_Location chkd-list (Point. 4 20))
    (.set_Name chkd-list "tableCheckedList")
    (.set_Size chkd-list (Size. 345 304))

    ; Load Button
    (.set_Name load-btn "loadButton")
    (.set_Location load-btn (Point. 12 384))
    (.set_Text load-btn "Load Table Names")

    ; Migrate button
    (.set_Location migrate-btn (Point. 266 384))
    (.set_Text migrate-btn "Migrate Tables...")
    (.set_Size migrate-btn (Size. 105 23))

    ; Put things together
    (.Add (.Controls form) title-lbl)
    (.Add (.Controls form) load-btn)
    (.Add (.Controls form) migrate-btn)
    
    (.Add (.Controls grp-box) chkd-list)
    (.Add (.Controls form) grp-box)

    ;; Adding the button click event handlers
    (.add_Click load-btn
      (gen-delegate EventHandler [sender args]
	(let [con (get-connection conn-str)
	      tables (map #(:Tables_in_bdb_post_2010 %) (get-tables con))]
	  (doseq [t tables] (.Add (.Items chkd-list) t))
	  (.Close con))
      ))		      

    (.add_Click migrate-btn
      (gen-delegate EventHandler [sender args]
		    (let [mysql-con (get-connection conn-str)
			  sqlite-con (sql/get-connection "")]
	  
	  (doseq [c (.CheckedItems chkd-list)]
	    (sql/create-table c (get-columns mysql-con c)))
	  (.Close mysql-con))))      
    
    (doto form
      (.set_Size (Size. 400 520))
      .ShowDialog)))