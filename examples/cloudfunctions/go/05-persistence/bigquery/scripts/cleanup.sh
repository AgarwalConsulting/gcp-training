#!/usr/bin/env bash

yes | gcloud functions delete read-from-bq
yes | gcloud functions delete gcs-to-bq

yes | bq rm $BQ_DATASET.$BQ_TABLE
yes | bq rm $BQ_DATASET

yes | gsutil rb -f gs://${BUCKET_NAME}
