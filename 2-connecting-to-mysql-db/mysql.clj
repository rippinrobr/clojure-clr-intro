(ns mysql)
(System.Reflection.Assembly/LoadWithPartialName "MySql.Data")

(def conn-str 
     "SERVER=localhost;DATABASE=bdb_post_2010;UID=rob-clr;PASSWORD=rob-clr;")
(def conn (MySql.Data.MySqlClient.MySqlConnection. conn-str))

(defn run-it
  [] 
  (.Open conn)
  (let [cmd (MySql.Data.MySqlClient.MySqlCommand.  
              "select * from battingpost where yearId = 2010"
              conn)
        reader (.ExecuteReader cmd)]
       (while (.Read reader)
         (println (str "playerId: " (.GetString reader 2))))
       (.Close reader))
       (.Close conn))