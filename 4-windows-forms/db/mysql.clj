(System.Reflection.Assembly/LoadWithPartialName "MySql.Data")
(System.Reflection.Assembly/LoadWithPartialName "System.Data")

(ns db.mysql
  (:require [db.core :as core])
  (:import [MySql.Data.MySqlClient MySqlConnection MySqlCommand])
  (:import [System.Data.Common DbConnection DbCommand])
  (:import [System.Data DataTable]))

(def ^{:dynamic true :clr true} *db* {:connection nil :level 0})

(defn get-tables
  [^MySqlConnection conn]
  (core/run-sql conn (MySqlCommand. "show tables;" conn)))

(defn get-columns
  [^MySqlConnection conn table-name]
  (core/run-sql conn (MySqlCommand. (str "describe " table-name ";") conn)))

(defn get-connection
  [con-str]
  (MySqlConnection. con-str))