#!/usr/bin/env bash

curl -H "projectID: $GOOGLE_CLOUD_PROJECT" -H "instanceID: myinstance" -H "tableID: test-table" \
  https://us-central1-cm-payplil-2108.cloudfunctions.net/HelloBigtable
