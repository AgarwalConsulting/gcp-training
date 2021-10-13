#!/usr/bin/env bash

gcloud functions deploy read-from-bq \
  --runtime go116 --trigger-http \
  --entry-point ReadPublicDataSet \
  --allow-unauthenticated \
  --set-env-vars GOOGLE_CLOUD_PROJECT=${GOOGLE_CLOUD_PROJECT},BQ_DATASET=${BQ_DATASET},BQ_TABLE=${BQ_TABLE}

gcloud functions deploy gcs-to-bq \
  --runtime go116 --entry-point HelloGCS \
  --trigger-resource gs://${BUCKET_NAME} \
  --trigger-event google.storage.object.finalize \
  --set-env-vars GOOGLE_CLOUD_PROJECT=${GOOGLE_CLOUD_PROJECT},BQ_DATASET=${BQ_DATASET},BQ_TABLE=${BQ_TABLE}
