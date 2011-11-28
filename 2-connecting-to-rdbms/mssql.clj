(ns mssql)
;; Brings in the ADO.NET classes I use to connect to the database
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

;; 
;; Pretty straitforward:
;; 1. Create the SqlConnection by passing in the connection string
;; 2. Create the SqlCommand object passing in the CommandText and
;;    the SqlConnection object that you want to use when running the
;;    command.
;; 3. Execute the command.  Since I'm doing a select I use the ExecuteReader
;;    method.  This allows me to read the results of my statement.
;; 4. While I have rows to read, read them and write out the player's id
;; 5. Clean up after myself by closing the reading and the connection
(defn ms-run-it []
  (let [ conn-str (str "Data Source=.\\Sqlexpress;Initial Catalog=sample_baseball_databank;user id=sample_user;password=sample_user;")
         conn (System.Data.SqlClient.SqlConnection. conn-str)
         _ (.Open conn)
         cmd (System.Data.SqlClient.SqlCommand. 
                "select * from battingpost where yearId = 2009" conn)
         reader (.ExecuteReader cmd)]
       (while (.Read reader)
        (println (str "playerId: " (.GetString reader 2))))
     (.Close reader) 
     (.Close conn)))
     
