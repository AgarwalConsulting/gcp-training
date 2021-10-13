#!/usr/bin/env bash

yes | gcloud functions delete java-pubsub-fn

yes | gcloud pubsub topics delete $TOPIC_NAME
