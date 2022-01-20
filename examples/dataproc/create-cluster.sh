#!/usr/bin/env bash

gcloud dataproc clusters create \
  --region $GOOGLE_CLOUD_REGION \
  --master-machine-type n2-standard-2 \
  --master-boot-disk-size 500 \
  --num-workers 2 \
  --worker-machine-type n2-standard-2 \
  --worker-boot-disk-size 500 \
  --image-version 2.0-debian10 \
  --optional-components JUPYTER \
  --project $GOOGLE_CLOUD_PROJECT \
  --enable-component-gateway \
  --max-idle 1800s \
  demo-cluster

## Costs
  # - Dataproc
  # - Compute Engine Instance (3 * n2-standard-2 (2 vCPU + 8 GiB RAM))
  # - Persistent Disk [HDD/SDD] (3 * 500 GiB)

  # --enable-component-gateway
  # --num-masters 3 \
  # --master-boot-disk-type pd-ssd \
  # --worker-boot-disk-type pd-ssd \
  # --num-worker-local-ssds 1 \
  # --image-version 2.0-centos8 \
  # --expiration-time Thu Jan 20 2022 00:00:00 GMT+0530 (India Standard Time) \
  # --max-idle 7200s \
  # --max-age 72000s \ # 20 hours
