#!/usr/bin/env bash

FILES_SOURCE=${GOOGLE_CLOUD_PROJECT}-files-source-$(date +%s)
gsutil mb -c regional -l ${GOOGLE_CLOUD_REGION} gs://${FILES_SOURCE}

bq mk mydataset
bq mk mydataset.mytable schema.json
