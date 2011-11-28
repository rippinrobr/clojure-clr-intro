(ns mysql)
;; Loads the assembly that contains the MySql related 
;; objects.  The assembly cna be downloaded from
;; here: http://dev.mysql.com/downloads/connector/net/
(System.Reflection.Assembly/LoadWithPartialName "MySql.Data")

;; 
;; Pretty straitforward:
;; 1. Create the MySqlConnection by passing in the connection string
;; 2. Create the MySqlCommand object passing in the CommandText and
;;    the SqlConnection object that you want to use when running the
;;    command.
;; 3. Execute the command.  Since I'm doing a select I use the ExecuteReader
;;    method.  This allows me to read the results of my statement.
;; 4. While I have rows to read, read them and write out the player's id
;; 5. Clean up after myself by closing the reading and the connection
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