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
ORDER_BIGQUERY_TABLE=${PROJECT_ID}.riccardo.order
ORDER_PUBSUB_SUBSCRIPTION=projects/${PROJECT_ID}/subscriptions/${DXP_CLOUD_PROJECT}_dxp_entities_order
PRODUCT_BIGQUERY_TABLE=${PROJECT_ID}.riccardo.product
PRODUCT_PUBSUB_SUBSCRIPTION=projects/${PROJECT_ID}/subscriptions/${DXP_CLOUD_PROJECT}_dxp_entities_product
REGION=$(gcloud config get-value compute/region)
RUNNER=DataflowRunner
SHARD_COUNT=4
TRIGGER_ELEMENT_COUNT=200
TRIGGER_INTERVAL_DURATION=60

../gradlew clean assemble execute \
	-Dexec.args=" \
	--GCSBucket=${GCS_BUCKET} \
		--project=${PROJECT_ID} \
		--defaultPubsubSubscription=${DEFAULT_PUBSUB_SUBSCRIPTION} \
		--orderPubsubSubscription=${ORDER_PUBSUB_SUBSCRIPTION} \
		--productPubsubSubscription=${PRODUCT_PUBSUB_SUBSCRIPTION} \
		--orderBigqueryTable=${ORDER_BIGQUERY_TABLE} \
		--productBigqueryTable=${PRODUCT_BIGQUERY_TABLE} \
		--region=${REGION} \
		--runner=${RUNNER} \
		--shardCount=${SHARD_COUNT} \
		--triggerElementCount=${TRIGGER_ELEMENT_COUNT} \
		--triggerIntervalDuration=${TRIGGER_INTERVAL_DURATION}" \
	-Dexec.cleanupDaemonThreads=false \
	-Dexec.mainClass=${MAIN_CLASS_NAME}