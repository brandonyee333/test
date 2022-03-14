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
PUBSUB_SUBSCRIPTION=projects/${PROJECT_ID}/subscriptions/${DXP_CLOUD_PROJECT}_dxp_entities_default
REGION=$(gcloud config get-value compute/region)
RUNNER=DataflowRunner
SHARD_COUNT=4
TRIGGER_ELEMENT_COUNT=200
TRIGGER_INTERVAL_DURATION=60

../gradlew clean assemble execute \
-Dexec.args=" \
--GCSBucket=${GCS_BUCKET} \
	--project=${PROJECT_ID} \
	--pubsubSubscription=${PUBSUB_SUBSCRIPTION} \
	--region=${REGION} \
	--runner=${RUNNER} \
	--shardCount=${SHARD_COUNT} \
	--triggerElementCount=${TRIGGER_ELEMENT_COUNT} \
	--triggerIntervalDuration=${TRIGGER_INTERVAL_DURATION}" \
-Dexec.cleanupDaemonThreads=false \
-Dexec.mainClass=${MAIN_CLASS_NAME}