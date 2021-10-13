#!/usr/bin/env bash

gcloud functions deploy go-spanner-fn \
  --entry-point HelloSpanner \
  --runtime go116 \
  --memory 512MB --trigger-http \
  --allow-unauthenticated \
  --set-env-vars GOOGLE_CLOUD_PROJECT=$GOOGLE_CLOUD_PROJECT,SPANNER_INSTANCE_ID=instance-3

gcloud functions deploy go-spanner-ins-fn \
  --entry-point InsertSpanner \
  --runtime go116 \
  --memory 512MB --trigger-http \
  --allow-unauthenticated \
  --set-env-vars GOOGLE_CLOUD_PROJECT=$GOOGLE_CLOUD_PROJECT,SPANNER_INSTANCE_ID=instance-3
