package hello

import (
	"encoding/json"
	"fmt"
	"net/http"
)

func HelloHandler(res http.ResponseWriter, req *http.Request) {

	// {"name": "Gaurav"}

	var reqBody struct {
		Name string
	}

	json.NewDecoder(req.Body).Decode(&reqBody)

	msg := "Hello, from Go by " + reqBody.Name + " !" // Type: string

	fmt.Fprintf(res, msg)
	// res.Write([]byte(msg))
}
