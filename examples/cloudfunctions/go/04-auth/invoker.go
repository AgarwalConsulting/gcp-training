package hello

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
)

const (
	projectID = "cm-payplil-2108"
	region    = "us-central1"
)

func getToken(fnName string) (token string, err error) {
	audience := region + "-" + projectID + ".cloudfunctions.net/" + fnName

	req, err := http.NewRequest("GET", "http://metadata.google.internal/computeMetadata/v1/instance/service-accounts/default/identity?audience="+audience, nil)

	if err != nil {
		return
	}

	req.Header.Add("Metadata-Flavor", "Google")

	resp, err := http.DefaultClient.Do(req)

	if err != nil {
		return
	}

	msg, err := ioutil.ReadAll(resp.Body)

	if err != nil {
		return
	}

	return string(msg), nil
}

func Invoker(w http.ResponseWriter, req *http.Request) {
	fnName := "hello-auth"
	tokenString, err := getToken(fnName)

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, "Unable to get token: ", err)
		return
	}

	log.Printf("Token string: %#v\n", tokenString)

	funcURL := "https://" + region + "-" + projectID + ".cloudfunctions.net/" + fnName
	r, err := http.NewRequest("GET", funcURL, nil)

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, "Unable to get token: ", err)
		return
	}
	r.Header.Add("Authorization", "bearer "+tokenString)
	resp, err := http.DefaultClient.Do(r)

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, "Unable to get: ", err)
		return
	}

	if resp.StatusCode != http.StatusOK {
		w.WriteHeader(resp.StatusCode)
	}

	msg, _ := ioutil.ReadAll(resp.Body)

	fmt.Fprintln(w, "forwarding:", string(msg))
	fmt.Fprintf(w, "%#v\n", resp.Header)
}
