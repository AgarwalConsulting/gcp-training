package bq_sample

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"os"

	"cloud.google.com/go/bigquery"
	"google.golang.org/api/iterator"
)

var projectID = os.Getenv("GOOGLE_CLOUD_PROJECT")
var bqDataset = os.Getenv("BQ_DATASET")
var bqTable = os.Getenv("BQ_TABLE")

func ReadPublicDataSet(w http.ResponseWriter, req *http.Request) {
	c, err := bigquery.NewClient(context.Background(), projectID)
	if err != nil {
		fmt.Fprintln(w, "Unable to connect:", err)
		return
	}

	q := c.Query(fmt.Sprintf(`SELECT * FROM %s.%s.%s`, projectID, bqDataset, bqTable))

	it, err := q.Read(context.Background())

	if err != nil {
		fmt.Fprintln(w, "Unable to query:", err)
		return
	}

	var values []bigquery.Value
	err = it.Next(&values)

	for err == nil {
		fmt.Fprintf(w, "%#v\n", values)
		err = it.Next(&values)
	}

	if (err != nil) && (err != iterator.Done) {
		log.Println("Unable to iterate fully:", err)
		return
	}
}
