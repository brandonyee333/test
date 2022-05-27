#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

COMPOSER_ENVIRONMENT_NAME=ac-composer-${REGION}-c

cd src/main/python

for i in `ls`;
do
	gcloud composer environments storage dags import \
		--environment ${COMPOSER_ENVIRONMENT_NAME} \
		--location ${REGION} \
		--project ${PROJECT_ID} \
		--source ${i};
done

cd -

gcloud composer environments storage dags import \
	--environment ${COMPOSER_ENVIRONMENT_NAME} \
	--location ${REGION} \
	--project ${PROJECT_ID} \
	--source src/main/resources/