package spanner

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"
	"time"

	"cloud.google.com/go/spanner"
	"sigs.k8s.io/controller-runtime/pkg/client"
)

type Album struct {
	SingerID   int    `json:"singerID" spanner:"SingerId"`
	AlbumID    int    `json:"albumID" spanner:"AlbumId"`
	AlbumTitle string `json:"albumTitle"`
}

func InsertSpanner(w http.ResponseWriter, req *http.Request) {
	var newAlbum Album

	json.NewDecoder(req.Body).Decode(&newAlbum)

	clientOnce.Do(func() {
		var projectID = os.Getenv("GOOGLE_CLOUD_PROJECT")
		var instanceID = os.Getenv("SPANNER_INSTANCE_ID")
		var databaseID = "example-db"

		var database = fmt.Sprintf("projects/%s/instances/%s/databases/%s", projectID, instanceID, databaseID)

		var err error

		ctx, _ := context.WithTimeout(context.Background(), 10*time.Second)

		client, err = spanner.NewClient(ctx, database)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
			fmt.Fprintln(w, "Unable to client:", err)
			log.Println(client)
			return
		}
	})

	m, err := spanner.InsertOrUpdateStruct("albums", newAlbum)

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, "Unable to create mutation:", err)
		return
	}

	commitTimestamp, err := client.Apply(context.Background(), []*spanner.Mutation{m})

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, "Unable to commit mutation:", err)
		return
	}

	fmt.Fprintln(w, "Committed transaction successfully:", commitTimestamp)
}
