#!/usr/bin/env bash

gcloud dataproc jobs submit spark \
  --region $GOOGLE_CLOUD_REGION \
  --cluster demo-cluster \
  --class org.apache.spark.examples.SparkPi \
  --jars file:///usr/lib/spark/examples/jars/spark-examples.jar -- 1000

  # --async \
