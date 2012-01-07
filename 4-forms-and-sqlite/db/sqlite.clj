(System.Reflection.Assembly/LoadWithPartialName "System.Data")


(ns db.sqlserver)
  

(defn create-table
  [table-name cols]
  (println "table-name: " table-name " cols: " cols))

(defn get-connection
  [conn-str]
  (System.Data.SqlClient.SqlConnection. conn-str))