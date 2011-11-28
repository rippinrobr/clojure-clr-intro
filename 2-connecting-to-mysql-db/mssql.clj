(ns mssql)
(System.Reflection.Assembly/LoadWithPartialName "System.Configuration")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

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
     
