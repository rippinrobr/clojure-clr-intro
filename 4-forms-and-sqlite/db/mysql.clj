(System.Reflection.Assembly/LoadWithPartialName "MySql.Data")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

(ns db.mysql
  (:import [MySql.Data.MySqlClient MySqlConnection MySqlCommand])
  (:import [System.Data DataTable]))

(defn- run-sql
  [conn sql]
  (let [cmd (MySqlCommand. sql conn)
	reader (.ExecuteReader cmd)
	dt (DataTable.)
	_ (.Load dt reader)
        _ (.Close reader)
	col-names (map #(keyword (.ColumnName %)) (.Columns dt))]
    (map #(zipmap col-names (.ItemArray %)) (.Rows dt))))

(defn get-tables
  [conn]
  (run-sql conn "show tables;"))

(defn get-columns
  [conn table-name]
  (run-sql conn (str "describe " table-name ";")))

(defn get-connection
  [con-str]
  (MySqlConnection. con-str))
  