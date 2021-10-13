#!/usr/bin/env bash

gcloud pubsub topics publish $TOPIC_NAME --message $1
