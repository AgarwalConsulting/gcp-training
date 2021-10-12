#!/usr/bin/env bash

gcloud functions deploy go-spanner-fn \
  --entry-point HelloSpanner \
  --runtime go116 \
  --memory 512MB --trigger-http \
  --allow-unauthenticated
