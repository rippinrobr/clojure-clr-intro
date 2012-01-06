(ns db.sqlite)

(defn create-table
  [table-name cols]
  (println "table-name: " table-name " cols: " cols))

(defn get-connection
  [conn-str]
  (println "Simulated sqlite/get-connection"))