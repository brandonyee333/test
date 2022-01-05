#!/bin/bash

export PROJECT_ID=$(gcloud config get-value project)

export EVENT_PROPERTIES_TABLE_NAME=${PROJECT_ID}:osbasah.eventproperties
export EVENT_TABLE_NAME=${PROJECT_ID}:osbasah.event
export MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.StreamingIngestionPipeline
export PIPELINE_FOLDER=gs://ac-dataflow
export REGION='us-central1'
export RUNNER=DataflowRunner
export SESSION_TABLE_NAME=${PROJECT_ID}:osbasah.session
export SESSION_WINDOW_ALLOWED_LATENESS=5
export SESSION_WINDOW_GAP_DURATION=30

../gradlew clean compileJava execute \
-Dexec.args=" \
	--eventPropertiesTableName=${EVENT_PROPERTIES_TABLE_NAME} \
	--eventTableName=${EVENT_TABLE_NAME} \
	--inputSubscription=projects/${PROJECT_ID}/subscriptions/dataflow_subscription \
	--outputDirectory=${PIPELINE_FOLDER}/output-data \
	--outputFileNamePrefix=analytics-events \
	--project=${PROJECT_ID} \
	--region=${REGION} \
	--runner=${RUNNER} \
	--sessionTableName=${SESSION_TABLE_NAME} \
	--sessionWindowGapDuration=${SESSION_WINDOW_GAP_DURATION} \
	--sessionWindowAllowedLateness=${SESSION_WINDOW_ALLOWED_LATENESS} \
	--stagingLocation=${PIPELINE_FOLDER}/staging \
	--tempLocation=${PIPELINE_FOLDER}/temp" \
-Dexec.cleanupDaemonThreads=false \
-Dexec.mainClass=${MAIN_CLASS_NAME}