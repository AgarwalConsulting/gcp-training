#!/usr/bin/env bash

gcloud functions deploy java-pubsub-fn \
  --entry-point com.algogrit.app.HelloPubSub \
  --runtime java11 \
  --memory 512MB \
  --trigger-topic $TOPIC_NAME
