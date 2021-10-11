package hello

import (
	"fmt"
	"net/http"
)

const authUser = "ga"
const authPassword = "securepassword"

func BasicHello(w http.ResponseWriter, req *http.Request) {
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
