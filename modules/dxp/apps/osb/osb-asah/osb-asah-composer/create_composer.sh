#!/bin/bash

LCP_PROJECT_ID=${1:=asahdev}
PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

COMPOSER_ENVIRONMENT_NAME=ac-composer-${LCP_PROJECT_ID}

gcloud composer environments create ${COMPOSER_ENVIRONMENT_NAME} \
	--airflow-configs=core-dagbag_import_timeout=120 \
	--airflow-version=2.2 \
	--async \
	--disk-size 30GB \
	--env-variables LCP_PROJECT_ID=${LCP_PROJECT_ID},GOOGLE_PROJECT_ID=${PROJECT_ID}\
	--location ${REGION} \
	--project ${PROJECT_ID} \
	--service-account ac-composer-admin@${PROJECT_ID}.iam.gserviceaccount.com