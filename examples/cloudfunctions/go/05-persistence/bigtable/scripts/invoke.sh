#!/usr/bin/env bash

curl -H "projectID: $GOOGLE_CLOUD_PROJECT" -H "instanceID: test-instance" -H "tableID: test-table" \
  https://us-central1-cm-payplil-2108.cloudfunctions.net/HelloBigtable
