package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	empHTTP "algogrit.com/sql_demo/employees/http"
	"algogrit.com/sql_demo/employees/repository"
	"algogrit.com/sql_demo/employees/service"

	"github.com/gorilla/mux"
)

var (
	dbUser = "postgres" // e.g. 'my-db-user'
	dbPort = "5432"     // e.g. '5432'
	dbName = "postgres" // e.g. 'my-database'
)

var port, dbTCPHost, dbPwd string

func init() {
	var ok bool
	if port, ok = os.LookupEnv("PORT"); !ok {
		port = "8000"
	}

	dbTCPHost = os.Getenv("SQL_DB_HOST")
	dbPwd = os.Getenv("SQL_PASSWORD")
}

func main() {
	r := mux.NewRouter()

	r.HandleFunc("/hello", func(w http.ResponseWriter, req *http.Request) {
		msg := "Hello, World!"

		fmt.Fprintln(w, msg)
	})

	var dbURI = fmt.Sprintf("host=%s user=%s password=%s port=%s database=%s", dbTCPHost, dbUser, dbPwd, dbPort, dbName)
	userRepo := repository.NewSQLX(dbURI)

	// var localDBURI = "postgres://gaurav@localhost:5432/postgres?sslmode=disable"
	// userRepo := repository.NewSQLX(localDBURI)

	var userSvcV1 = service.NewV1(userRepo)
	var empHandler = empHTTP.NewHandler(userSvcV1)

	empHandler.Route(r)

	log.Println("Starting server on", port, "...")
	http.ListenAndServe(":"+port, r)
}
