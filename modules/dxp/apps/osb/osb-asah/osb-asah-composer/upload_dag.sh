#!/bin/bash

REGION=us-west1
ZONE=us-west1-c
COMPOSER_ENVIRONMENT_NAME=ac-composer-${ZONE}
PROJECT_ID=$(gcloud config get-value project)

cd src/main/python

for i in `ls`; do
gcloud composer environments storage dags import --project ${PROJECT_ID} \
--environment ${COMPOSER_ENVIRONMENT_NAME} \
--location ${REGION} \
--source ${i};
done

cd -

gcloud composer environments storage dags import --project ${PROJECT_ID} \
--environment ${COMPOSER_ENVIRONMENT_NAME} \
--location ${REGION} \
--source src/main/resources/