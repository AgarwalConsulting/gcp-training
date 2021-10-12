package sql_demo

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"

	"algogrit.com/sql_demo/entities"

	"algogrit.com/sql_demo/employees/repository"
	"algogrit.com/sql_demo/employees/service"
)

var (
	dbUser = "postgres" // e.g. 'my-db-user'
	dbName = "postgres" // e.g. 'my-database'
)

var instanceConnectionName, socketDir, dbPwd string

var v1Svc service.UserService

func init() {
	var isSet bool
	socketDir, isSet = os.LookupEnv("DB_SOCKET_DIR")
	if !isSet {
		socketDir = "/cloudsql"
	}

	instanceConnectionName = os.Getenv("INSTANCE_CONNECTION_NAME")
	dbPwd = os.Getenv("SQL_PASSWORD")

	var dbURI = fmt.Sprintf("user=%s password=%s database=%s host=%s/%s", dbUser, dbPwd, dbName, socketDir, instanceConnectionName)

	log.Printf("Connection string: %#v\n", dbURI)

	var userRepo = repository.NewSQLX(dbURI)
	v1Svc = service.NewV1(userRepo)
}

func EmployeesIndexHandler(w http.ResponseWriter, req *http.Request) {
	emp, err := v1Svc.Index()
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, err)
	}

	w.Header().Add("Content-Type", "application/json")
	json.NewEncoder(w).Encode(emp)
}

func EmployeeCreateHandler(w http.ResponseWriter, req *http.Request) {
	var newEmp entities.Employee
	err := json.NewDecoder(req.Body).Decode(&newEmp)

	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(w, err)
		return
	}

	emp, err := v1Svc.Create(newEmp)

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(*emp)
}
