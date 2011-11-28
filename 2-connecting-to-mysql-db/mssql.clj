(ns monitors.core)
(System.Reflection.Assembly/LoadWithPartialName "System.Configuration")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

(def conn-str "")

(defn -main [& args]
  (let [ conn (System.Data.SqlClient.SqlConnection. (.ConnectionString conn-str))
         _ (.Open conn)
         cmd (System.Data.SqlClient.SqlCommand. 
                "select * from battingpost where yearId = 2010" conn)
         reader (.ExecuteReader cmd)]
     (while (.Read reader)
       (println (str "playerId: " (.GetString reader 2))))
     (.Close reader) 
     (.Close conn)))
     
