(System.Reflection.Assembly/LoadWithPartialName "System.Data")
(System.Reflection.Assembly/LoadWithPartialName "Microsoft.SqlServer.Smo")

(ns db.sqlserver
  (:import [Microsoft.SqlServer.Management.Smo Column
	    DataType SqlDataType Server Database Table]))

(defn get-column-type
  [type-str]
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

(defn create-column
  [table col]
  (let [c (Column. table (:Field col) )]
     c))

(defn create-table
  [db table-name cols]
  (println "Creating the table" table-name)
  (let [table (Table. db table-name)]
    (println cols)
    table))
    ;;(.Create table)))

(defn get-database
  [server-name db-name]
  (let [server (Server. server-name)
	;; either catch FailedOperationException or check to see if it
	;; exists already
        db (Database. server db-name)]
    (.Create db)
    db))