#!/bin/bash

export PROJECT_ID=$(gcloud config get-value project)
export REGION='us-central1'
export PIPELINE_FOLDER=gs://ac-dataflow
export MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.StreamingIngestionPipeline
export RUNNER=DataflowRunner

../gradlew clean compileJava execute \
-Dexec.mainClass=${MAIN_CLASS_NAME} \
-Dexec.cleanupDaemonThreads=false \
-Dexec.args=" \
--project=${PROJECT_ID} \
--region=${REGION} \
--inputSubscription=projects/${PROJECT_ID}/subscriptions/dataflow_subscription \
--outputDirectory=${PIPELINE_FOLDER}/output-data \
--outputFileNamePrefix=analytics-events \
--stagingLocation=${PIPELINE_FOLDER}/staging \
--tempLocation=${PIPELINE_FOLDER}/temp \
--runner=${RUNNER}"