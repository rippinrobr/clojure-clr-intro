(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns ui
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form
	    TableLayoutPanel MessageBox PaintEventHandler Label TextBox])
  (:import [System.Drawing Size Font FontStyle Point])
  (:require [db.mysql :as mysql])
  (:require [db.sqlserver :as sql])
  (:gen-class))

(def conn-str "SERVER=localhost;DATABASE=bdb_post_2010;UID=rob-clr;PASSWORD=rob-clr;")
(def sqlserver-conn-str "Data Source=.\\Sqlexpress;Initial Catalog=sample_baseball_databank;Integrated Security=true;")

(defn -main
  [& args]
  (let [form (Form.)
	load-btn (Button.)
	title-lbl (Label.)
	db-lbl (Label.)
	db-txt (TextBox.)
	migrate-btn (Button.)
	grp-box (GroupBox.)
	chkd-list (CheckedListBox.)
	title-str "Rob's Table Migration Tool ClojureCLR style!"
	]
    
    ; Title Label
    (doto title-lbl
      (.set_Text title-str)
      (.set_Location (Point. 12 12))
      (.set_Size (Size. 360 22))
      (.set_Font (Font. "Microsoft Sans Serif" 12.0 System.Drawing.FontStyle/Bold System.Drawing.GraphicsUnit/Point 0)))

    ; DB Label
    (doto db-lbl
      (.set_Text "New DB Name")
      (.set_Width 80)
      (.set_Location (Point. 12 48)))

    (doto db-txt
      (.set_Name "newDbName")
      (.set_Location (Point. 120 44)))
    
    ; GroupBox
    (doto grp-box
      (.set_Text "Database Tables")
      (.set_Location (Point. 12 72))
      (.set_Size (Size. 360 333)))

    ; CheckedListBox
    (doto chkd-list
      (.set_Location (Point. 4 20))
      (.set_Name "tableCheckedList")
      (.set_Size (Size. 345 304)))

    ; Button
    (doto load-btn
      (.set_Name "loadButton")
      (.set_Location (Point. 12 384))
      (.set_Text "Load Table Names"))

    ; Migrate button
    (.set_Location migrate-btn (Point. 266 384))
    (.set_Text migrate-btn "Migrate Tables...")
    (.set_Size migrate-btn (Size. 105 23))

    ; Put things together
    (.Add (.Controls form) title-lbl)
    (.Add (.Controls form) db-lbl)
    (.Add (.Controls form) db-txt)
    (.Add (.Controls form) load-btn)
    (.Add (.Controls form) migrate-btn)
    
    (.Add (.Controls grp-box) chkd-list)
    (.Add (.Controls form) grp-box)

    ;; Adding the button click event handlers
    (.add_Click load-btn
      (gen-delegate EventHandler [sender args]
	(let [con (mysql/get-connection conn-str)
	      tables (map #(:Tables_in_bdb_post_2010 %) (mysql/get-tables con))]
	  (doseq [t tables] (.Add (.Items chkd-list) t))
	  (.Close con))
      ))		      

    (.add_Click migrate-btn
      (gen-delegate EventHandler [sender args]
		    (let [mysql-con (mysql/get-connection conn-str)
			  ms-db (sql/get-database ".\\SQLExpress" "clr_intro_4")]
	  (println "class of ms-db: " (class ms-db))
	  (println "state: " (.State ms-db))
	  (doseq [c (.CheckedItems chkd-list)]
	    (println c)
	    (sql/create-table ms-db c (mysql/get-columns mysql-con c))
	  (.Close mysql-con)
	  ))))      
    
    (doto form
      (.set_Text title-str)
      (.set_Size (Size. 400 520))
      .ShowDialog)))