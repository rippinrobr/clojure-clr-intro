(System.Reflection.Assembly/LoadWithPartialName "System.Data")
(System.Reflection.Assembly/LoadWithPartialName "Microsoft.SqlServer.Smo")

(ns db.sqlserver
  (:import [Microsoft.SqlServer.Management.Smo Column
	    DataType SqlDataType Server Database Table]))

(defn get-column-type
  [type-str]
  (println "type-str: " type-str)
   (cond
     (> (.IndexOf type-str "smallint") -1) (DataType/SmallInt)
     (> (.IndexOf type-str "int") -1) (DataType/Int)
     (> (.IndexOf type-str "varchar") -1)
       (let [[matched-str size] (re-matches #".*\((\d+)\).*" type-str)]
         (if (not (nil? size))
	    (DataType/VarChar size)
	    (DataType/VarChar 255)))
     (> (.IndexOf type-str "char") -1)
       (let [[matched-str size] (re-matches #".*\((\d+)\).*" type-str)]
         (if (not (nil? size))
	   (DataType/Char size)
	   (DataType/Char 32)))
     (> (.IndexOf type-str "double") -1)
       (let [[matched-str size prec] (re-matches #".*\((\d+),\((\d+)\).*" type-str)]
         (if (not (nil? size))
	   (DataType/Numeric size prec)
	   (DataType/Numeric 4 1)))
     (> (.IndexOf type-str "enum") -1)
       (DataType/Char 1)  
     (> (.IndexOf type-str "date") -1)
       (DataType/DateTime)
    :else
      (println "Unknown DataType: " type-str)))

(defn nullable?
  [col]
  (println (:Null col))
  (if (nil? (:Null col))
    false
    (if (= "NO" (:Null col)) false true)))

(defn create-column
  [table col]
  (let [data-type (get-column-type (:Type col))
	c (Column. table (:Field col) data-type)]
    (set! (.Nullable c) (nullable? col))
    (if (not= System.DBNull (class (:Default col)))
      (set! (.Default c) (:Default col)))
     c))

(defn create-table
  [db table-name cols]
  (println "Creating the table" table-name)
  (let [table (Table. db table-name)]
    (doseq [col cols] (.Add (.Columns table) (create-column table col)))
    (.Create table)))

(defn get-database
  [server-name db-name]
  (let [server (Server. server-name)
	;; either catch FailedOperationException or check to see if it
	;; exists already
        db (Database. server db-name)]
    (.Create db)
    db))