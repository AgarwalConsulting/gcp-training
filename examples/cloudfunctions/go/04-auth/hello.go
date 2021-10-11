package hello

import (
	"fmt"
	"net/http"
)

func Hello(w http.ResponseWriter, req *http.Request) {
	fmt.Fprintln(w, "Hello, from a simple function - 2!")
}
