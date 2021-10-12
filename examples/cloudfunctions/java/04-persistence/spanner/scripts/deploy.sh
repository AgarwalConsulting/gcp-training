#!/usr/bin/env bash

gcloud functions deploy java-spanner-fn \
  --entry-point functions.HelloSpanner \
  --runtime java11 \
  --memory 512MB --trigger-http \
  --allow-unauthenticated \
  --set-env-vars SPANNER_INSTANCE=$SPANNER_INSTANCE,SPANNER_DATABASE=$SPANNER_DATABASE
