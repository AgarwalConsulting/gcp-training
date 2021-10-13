#!/usr/bin/env bash

gcloud functions deploy HelloPubSub \
  --runtime go116 \
  --trigger-topic demo-topic
