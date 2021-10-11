package main

import (
	"flag"
	"fmt"
	"log"
	"net/http"
	"os"

	empHTTP "algogrit.com/emp-server/users/http"
	"algogrit.com/emp-server/users/repository"
	"algogrit.com/emp-server/users/service"

	"github.com/gorilla/mux"
)

var port string

func init() {
	var ok bool
	if port, ok = os.LookupEnv("PORT"); !ok {
		port = "8000"
	}
}

func main() {
	repo := flag.String("repo", "inmem", "Selects the appropriate repository. Possible values: inmem, sqlite, postgres")

	flag.Parse()

	log.Println("Using ", *repo, "...")
	r := mux.NewRouter()

	r.HandleFunc("/hello", func(w http.ResponseWriter, req *http.Request) {
		msg := "Hello, World!"

		fmt.Fprintln(w, msg)
	})

	var userRepo repository.UserRepository

	switch *repo {
	case "inmem":
		userRepo = repository.NewInMemoUserRepository()
	case "sqlite":
		userRepo = repository.NewSQL("/tmp/employee.db")
	case "postgres":
		userRepo = repository.NewSQLX("postgres://gaurav@localhost:5432/postgres?sslmode=disable")
	}

	var userSvcV1 = service.NewV1(userRepo)
	var empHandler = empHTTP.NewHandler(userSvcV1)

	empHandler.Route(r)

	log.Println("Starting server on", port, "...")
	http.ListenAndServe(":"+port, r)
}
