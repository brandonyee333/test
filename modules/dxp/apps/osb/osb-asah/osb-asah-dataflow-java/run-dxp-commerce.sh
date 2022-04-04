#!/bin/bash

if [ "$#" -ne 1 ]
then
	echo "Usage: run-dxp-commerce.sh [dxp-cloud-project]"
	exit 1
fi

PROJECT_ID=$(gcloud config get-value project)

DXP_CLOUD_PROJECT=${1}
GCS_BUCKET=gs://${PROJECT_ID}-dataflow/
MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.dxp.DXPCommerceEntitiesIngestionPipeline
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
		--defaultPubsubSubscription=${DEFAULT_PUBSUB_SUBSCRIPTION} \
		--orderBigQueryTable=${ORDER_BIGQUERY_TABLE} \
		--orderPubsubSubscription=${ORDER_PUBSUB_SUBSCRIPTION} \
		--productBigQueryTable=${PRODUCT_BIGQUERY_TABLE} \
		--productPubsubSubscription=${PRODUCT_PUBSUB_SUBSCRIPTION} \
		--project=${PROJECT_ID} \
		--region=${REGION} \
		--runner=${RUNNER} \
		--shardCount=${SHARD_COUNT} \
		--triggerElementCount=${TRIGGER_ELEMENT_COUNT} \
		--triggerIntervalDuration=${TRIGGER_INTERVAL_DURATION}" \
	-Dexec.cleanupDaemonThreads=false \
	-Dexec.mainClass=${MAIN_CLASS_NAME}