#!/bin/bash

AIRFLOW_VERSION=2.2
REGION=us-west1
ZONE=us-west1-c
COMPOSER_ENVIRONMENT_NAME=ac-composer-${ZONE}
PROJECT_ID=$(gcloud config get-value project)

gcloud composer environments create ${COMPOSER_ENVIRONMENT_NAME} \
--project ${PROJECT_ID} \
--location ${REGION} \
--airflow-version=${AIRFLOW_VERSION} \
--disk-size 40GB \
--service-account ac-composer-admin@${PROJECT_ID}.iam.gserviceaccount.com \
--async