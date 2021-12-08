#!/bin/bash

export MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.StreamingIngestionPipeline
export PIPELINE_FOLDER=gs://ac-dataflow
export PROJECT_ID=$(gcloud config get-value project)
export REGION='us-central1'
export RUNNER=DataflowRunner

../gradlew clean compileJava execute \
-Dexec.args=" \
	--inputSubscription=projects/${PROJECT_ID}/subscriptions/dataflow_subscription \
	--outputDirectory=${PIPELINE_FOLDER}/output-data \
	--outputFileNamePrefix=analytics-events \
	--project=${PROJECT_ID} \
	--region=${REGION} \
	--runner=${RUNNER}"
	--stagingLocation=${PIPELINE_FOLDER}/staging \
	--tempLocation=${PIPELINE_FOLDER}/temp \
-Dexec.cleanupDaemonThreads=false \
-Dexec.mainClass=${MAIN_CLASS_NAME}