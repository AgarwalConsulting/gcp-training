#!/usr/bin/env bash

gcloud functions deploy read-from-bq \
  --runtime go116 --trigger-http \
  --entry-point ReadPublicDataSet \
  --allow-unauthenticated \
  --set-env-vars GOOGLE_CLOUD_PROJECT=${GOOGLE_CLOUD_PROJECT},BQ_DATASET=${BQ_DATASET},BQ_TABLE=${BQ_TABLE}
