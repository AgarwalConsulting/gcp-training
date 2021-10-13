#!/usr/bin/env bash

gcloud functions deploy HelloBigtable \
  --runtime go116 --trigger-http \
  --entry-point BigtableRead \
  --allow-unauthenticated
