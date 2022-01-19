#!/usr/bin/env bash

# gsutil cat gs://dataproc-examples/pyspark/hello-world/hello-world.py

gcloud dataproc jobs submit pyspark \
    --cluster=demo-cluster  \
    --region=$GOOGLE_CLOUD_REGION \
    gs://dataproc-examples/pyspark/hello-world/hello-world.py
