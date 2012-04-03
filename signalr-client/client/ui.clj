(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns ui
  (:import [System.Windows.Forms Button GroupBox ListBox Form
	    MessageBox PaintEventHandler Label TextBox])
  (:import [System.Drawing Size Font FontStyle GraphicsUnit Point])
  (:require [db.mysql :as mysql])
  (:require [db.sqlserver :as sql])
  (:gen-class))

(defn -main
  [& args]
  (let [form (Form.)
	title-lbl (Label.)
	name-lbl (Label.)
	name-txt (TextBox.)
	migrate-btn (Button.)
	grp-box (GroupBox.)
	msg-grp-box (GroupBox.)
	msg-txt (TextBox.)
	convo-list (ListBox.)
	title-str "Rob's SignalR Client ClojureCLR style!"]
    
    ; Title Label
    (doto title-lbl
      (.set_Text title-str)
      (.set_Location (Point. 12 12))
      (.set_Size (Size. 360 22))
      (.set_Font (Font. "Microsoft Sans Serif"
			12.0 FontStyle/Bold GraphicsUnit/Point 0)))
    
    ; GroupBox
    (doto grp-box
      (.set_Text "Conversation")
      (.set_Location (Point. 12 42))
      (.set_Size (Size. 360 213)))

    ; Your Message  GroupBox
    (doto msg-grp-box
      (.set_Text "Your Message")
      (.set_Location (Point. 12 264))
      (.set_Size (Size. 360 100)))
    
    ; Conversation ListBox
    (doto convo-list
      (.set_Location (Point. 4 20))
      (.set_Name "conversationCheckedList")
      (.set_Size (Size. 345 194)))

    ; Message Text Box
    (doto msg-txt
      (.set_Location (Point. 4 16))
      (.set_Multiline true)
      (.set_Name "msgTextBox")
      (.set_Size (Size. 345 74)))
    
    ; Name Label
    (doto name-lbl
      (.set_Text "Your Name")
      (.set_Width 80)
      (.set_Location (Point. 12 382)))

    ; Your Name TextField
    (doto name-txt
      (.set_Name "yourNameTextField")
      (.set_Text "cljclr client")
      (.set_Width 86)
      (.set_Location (Point. 92 380)))

    ; Migrate button
    (doto migrate-btn
      (.set_Location (Point. 266 378))
      (.set_Text "Send...")
      (.set_Size (Size. 105 23)))

    ;Putting things together
    (doto (.Controls form)
      (.Add title-lbl)
      (.Add name-lbl)
      (.Add name-txt)
      (.Add migrate-btn)
      (.Add grp-box)
      (.Add msg-grp-box))
    
    (.Add (.Controls grp-box) convo-list)
    (.Add (.Controls msg-grp-box) msg-txt)
    
    (.add_Click migrate-btn
      (gen-delegate EventHandler [sender args]
		    (.set_Text msg-txt "")))
    
    (doto form
      (.set_Text title-str)
      (.set_Size (Size. 400 460))
      .ShowDialog)))