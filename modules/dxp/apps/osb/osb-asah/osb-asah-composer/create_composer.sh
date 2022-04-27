#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

COMPOSER_ENVIRONMENT_NAME==ac-composer-${REGION}

gcloud composer environments create ${COMPOSER_ENVIRONMENT_NAME} \
--project ${PROJECT_ID} \
--location ${REGION} \
--airflow-version=2.2 \
--disk-size 40GB \
--service-account ac-composer-admin@${PROJECT_ID}.iam.gserviceaccount.com \
--async