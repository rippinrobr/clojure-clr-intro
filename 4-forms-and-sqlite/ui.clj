(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns ui
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form TableLayoutPanel MessageBox PaintEventHandler Label]); FontStyle])
  (:import [System.Drawing Size Font FontStyle])
  (:use db.mysql)
  (:gen-class))

(defn -main
  [& args]
  (let [form (Form.)
	load-btn (Button.)
	title-lbl (Label.)
	migrate-btn (Button.)
	grp-box (GroupBox.)
	chkd-list (CheckedListBox.)
	]

    (.set_Text title-lbl "Rob's Table Migrator Tool")
    (.set_Location title-lbl (System.Drawing.Point. 12 12))
    (.set_Size title-lbl (System.Drawing.Size. 360 12))
    
    
    (.set_Text grp-box "Database Tables")
    (.set_Location grp-box (System.Drawing.Point. 12 48))
    (.set_Size grp-box (System.Drawing.Size. 360 333))

    (.set_Name load-btn "loadButton")
    (.set_Location load-btn (System.Drawing.Point. 12 384))
    (.set_Text load-btn "Load Table Names")
    
    (.set_Location migrate-btn (System.Drawing.Point. 266 384))
    (.set_Text migrate-btn "Migrate Tables...")
    (.set_Size migrate-btn (System.Drawing.Size. 105 23))

    (.Add (.Controls form) title-lbl)
    (.Add (.Controls form) load-btn)
    (.Add (.Controls form) migrate-btn)
    (.Add (.Controls form) grp-box)

    (.add_Click load-btn
      (gen-delegate EventHandler [sender args]
	(MessageBox/Show "Thanks for clickin'!")))		      

    (.set_Text form "Rob's Table Migration Tool ClojureCLR style!")
    (doto form
      (.set_Size (Size. 400 520))
      .ShowDialog)
    
    (println "load-btn.Name " (.Name load-btn))
    (println "created buttons!")))