#!/usr/bin/env bash

gcloud functions deploy go-spanner-fn \
  --entry-point HelloSpanner \
  --runtime go116 \
  --memory 512MB --trigger-http \
  --allow-unauthenticated \
  --set-env-vars GOOGLE_CLOUD_PROJECT=$GOOGLE_CLOUD_PROJECT
