#!/usr/bin/env bash

gcloud functions deploy employees-index \
  --entry-point EmployeesIndexHandler \
  --runtime go116 --trigger-http \
  --allow-unauthenticated \
  --set-env-vars INSTANCE_CONNECTION_NAME=$INSTANCE_CONNECTION_NAME,SQL_PASSWORD=$SQL_PASSWORD

gcloud functions deploy employee-create \
  --entry-point EmployeeCreateHandler \
  --runtime go116 --trigger-http \
  --allow-unauthenticated \
  --set-env-vars INSTANCE_CONNECTION_NAME=$INSTANCE_CONNECTION_NAME,SQL_PASSWORD=$SQL_PASSWORD
