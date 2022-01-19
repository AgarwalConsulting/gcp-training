#!/usr/bin/env bash

gcloud dataproc clusters delete \
  --region $GOOGLE_CLOUD_REGION \
  --project $GOOGLE_CLOUD_PROJECT \
  --num-workers 3 \
  demo-cluster
