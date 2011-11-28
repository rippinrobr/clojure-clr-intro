(ns mysql)
(System.Reflection.Assembly/LoadWithPartialName "MySql.Data")

(defn my-run-it
  [] 
  (let [
        conn-str (str "SERVER=localhost;DATABASE=bdb_post_2010;UID=rob-clr;PASSWORD=rob-clr;")
        conn (MySql.Data.MySqlClient.MySqlConnection. conn-str)
        cmd (MySql.Data.MySqlClient.MySqlCommand.  
              "select * from battingpost where yearId = 2010"
              conn)
        _ (.Open conn)
        reader (.ExecuteReader cmd)]
       (while (.Read reader)
         (println (str "playerId: " (.GetString reader 2))))
       (.Close reader)
       (.Close conn)))