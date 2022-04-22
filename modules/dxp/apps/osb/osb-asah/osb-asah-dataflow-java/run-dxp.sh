#!/bin/bash

if [ "$#" -ne 1 ]
then
	echo "Usage: run-dxp.sh [dxp-cloud-project]"
	exit 1
fi

PROJECT_ID=$(gcloud config get-value project)

DXP_CLOUD_PROJECT=${1}
GCS_BUCKET=gs://${PROJECT_ID}-dataflow/
MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.dxp.DXPEntitiesIngestionPipeline
DEFAULT_PUBSUB_SUBSCRIPTION=projects/${PROJECT_ID}/subscriptions/${DXP_CLOUD_PROJECT}_dxp_entities_default
REGION=$(gcloud config get-value compute/region)
RUNNER=DataflowRunner

../gradlew clean assemble execute \
	-Dexec.args=" \
	--GCSBucket=${GCS_BUCKET} \
		--pubsubSubscription=${DEFAULT_PUBSUB_SUBSCRIPTION} \
		--project=${PROJECT_ID} \
		--region=${REGION} \
		--runner=${RUNNER}" \
	-Dexec.cleanupDaemonThreads=false \
	-Dexec.mainClass=${MAIN_CLASS_NAME}