(System.Reflection.Assembly/LoadWithPartialName "System.Data")
(System.Reflection.Assembly/LoadFile "c:\\code\\clojure-clr-intro\\libs\\Microsoft.SqlServer.Smo.dll")

(ns db.sqlserver
  (:import [Microsoft.SqlServer.Management.Smo Server Database Table]))
  

(defn create-table
  [db table-name cols]
  (println "Creating the table" table-name)
  (let [table (Table. db table-name)]
    (.Create table)))

(defn get-database
  [server-name db-name]
  (let [server (Server. server-name)
        db (Database. server db-name)]
    (.Create db)
    db))