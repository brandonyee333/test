#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

COMPOSER_ENVIRONMENT_NAME=ac-composer-${REGION}-c

gcloud composer environments create ${COMPOSER_ENVIRONMENT_NAME} \
	--airflow-version=2.2 \
	--async \
	--disk-size 40GB \
	--location ${REGION} \
	--project ${PROJECT_ID} \
	--service-account ac-composer-admin@${PROJECT_ID}.iam.gserviceaccount.com