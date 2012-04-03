(println (assembly-load-file (str (System.IO.Directory/GetCurrentDirectory) "\\libs\\Newtonsoft.Json.dll")))
(println (assembly-load-file (str (System.IO.Directory/GetCurrentDirectory) "\\libs\\SignalR.Client.dll")))

(ns signalr.client
  (:import [SignalR.Client.Hubs HubConnection])
  (:gen-class))

(defn connect2
  [server-url hub-name]
  (let [hub-conn (HubConnection. server-url)
	hub-proxy (.CreateProxy hub-conn hub-name)
	msg-handler (gen-delegate |System.Action`1[System.Object[]]| [msg]
		    (println msg))
        sub (.Subscribe hub-proxy "receive") ]
    (.Invoke hub-proxy)
    (.add_Data sub msg-handler)
    (.Wait (.Start  hub-conn))
    (println "I'm after wait")))
    

(defn connect
  [server-url hubName]
  (let [conn (HubConnection. server-url)
	hub (.CreateProxy conn hubName)
	sub (.Subscribe hub "receive")]
    ;; using the !preferred method of subscribing to events here
    ;; because of the situation laid out below
    (.add_Data sub
      (gen-delegate |System.Action`1[System.Object[]]| [msg]
		    (println msg)))
    ;; The code below follows the recommended way to subscribe to these events
    ;; according to the SignalR Wiki page
    ;;
    ;; https://github.com/SignalR/SignalR/wiki/SignalR-Client-Hubs
    ;;
    ;; but when I run it I receive an error that states:
    ;;
    ;; NotImplementedException The method or operation is not implemented....
    ;;
    ;;   (.On hub "recieve"
    ;;	 (gen-delegate |System.Action`1[System.Object[]]| [msg]
    ;;		       (println "received: " msg)))

    (let [task (.Start conn)]
      ;(.Start task))     
      (gen-delegate |System.Action`1[System.Threading.Tasks.Task]| [task]
	(if (.IsFaulted task)
	  (println "There was an error connecting to the server")
	  (println "You are connected!"))
       (Console/ReadLine)))))

(defn run
  [& args]
  (connect2 "http://localhost:4055/" "server.Hubs.ChatHub"))