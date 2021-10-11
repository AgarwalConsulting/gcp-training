package hello

import (
	"fmt"
	"net/http"
	"os"
)

func BasicHello(w http.ResponseWriter, req *http.Request) {
	authUser, ok := os.LookupEnv("BASIC_AUTH_USERNAME")
	if !ok {
		authUser = "ga"
	}

	authPassword, ok := os.LookupEnv("BASIC_AUTH_PASSWORD")
	if !ok {
		authPassword = "securepassword"
	}

	w.Header().Set("WWW-Authenticate", "Basic realm=Enter username & password for this site.")

	ruser, rpassword, ok := req.BasicAuth()

	if ok && ruser == authUser && rpassword == authPassword {
		msg := "Hello, World"

		fmt.Fprintln(w, msg)
	} else {
		w.WriteHeader(http.StatusUnauthorized)
		fmt.Fprintln(w, "Unauthorized: Incorrect username or password")
		return
	}
}
