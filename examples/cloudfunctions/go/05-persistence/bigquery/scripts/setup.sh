#!/usr/bin/env bash

gsutil mb -c regional -l ${GOOGLE_CLOUD_REGION} gs://${BUCKET_NAME}

bq mk $BQ_DATASET
bq mk $BQ_DATASET.$BQ_TABLE schema.json
