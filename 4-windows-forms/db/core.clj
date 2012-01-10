(System.Reflection.Assembly/LoadWithPartialName "System.Data")

(ns db.core
  (:import [System.Data.Common DbConnection DbCommand])
  (:import [System.Data DataTable]))

(defn run-sql
  [^DbConnection conn cmd]
  (if (= "Closed" (str (.State conn))) (.Open conn))
  (let [reader (.ExecuteReader cmd)
	dt (DataTable.)
	_ (.Load dt reader)
        _ (.Close reader)
	col-names (map #(keyword (.ColumnName %)) (.Columns dt))]
    (map #(zipmap col-names (.ItemArray %)) (.Rows dt))))