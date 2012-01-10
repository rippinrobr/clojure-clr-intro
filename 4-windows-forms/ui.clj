(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns ui
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form
	    MessageBox PaintEventHandler Label TextBox])
  (:import [System.Drawing Size Font FontStyle GraphicsUnit Point])
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
      (.set_Font (Font. "Microsoft Sans Serif"
			12.0 FontStyle/Bold GraphicsUnit/Point 0)))
    
    ; GroupBox
    (doto grp-box
      (.set_Text "Database Tables")
      (.set_Location (Point. 12 42))
      (.set_Size (Size. 360 333)))

    ; CheckedListBox
    (doto chkd-list
      (.set_Location (Point. 4 20))
      (.set_Name "tableCheckedList")
      (.set_Size (Size. 345 304)))

    ; Button
    (doto load-btn
      (.set_Name "loadButton")
      (.set_Location (Point. 12 378))
      (.set_Text "Load Table Names"))

    ; DB Label
    (doto db-lbl
      (.set_Text "New DB Name")
      (.set_Width 80)
      (.set_Location (Point. 92 382)))

    ; DB TextField
    (doto db-txt
      (.set_Name "newDbName")
      (.set_Width 86)
      (.set_Location (Point. 172 380)))

    ; Migrate button
    (doto migrate-btn
      (.set_Location (Point. 266 378))
      (.set_Text "Migrate Tables...")
      (.set_Size (Size. 105 23)))

    ;Putting things together
    (doto (.Controls form)
      (.Add title-lbl)
      (.Add db-lbl)
      (.Add db-txt)
      (.Add load-btn)
      (.Add migrate-btn)
      (.Add grp-box))
    
    (.Add (.Controls grp-box) chkd-list)

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
	        db-name (.Text db-txt)
		ms-db (sql/get-database ".\\SQLExpress"  db-name)]
	    (doseq [c (.CheckedItems chkd-list)]
	      (sql/create-table ms-db c (mysql/get-columns mysql-con c))
	      (.Close mysql-con))
	     (MessageBox/Show "Table migration complete!"))))      
    
    (doto form
      (.set_Text title-str)
      (.set_Size (Size. 400 460))
      .ShowDialog)))