#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

COMPOSER_ENVIRONMENT_NAME==ac-composer-${REGION}

cd src/main/python

for i in `ls`;
do
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