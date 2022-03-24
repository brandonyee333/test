#!/bin/bash

if [ "$#" -ne 1 ]
then
	echo "Usage: run [dxp-cloud-project]"
	exit 1
fi

PROJECT_ID=$(gcloud config get-value project)

DXP_CLOUD_PROJECT=${1}
MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.event.EventIngestionPipeline
OUTPUT_FOLDER=gs://${PROJECT_ID}-analytics-events
PIPELINE_FOLDER=gs://${PROJECT_ID}-dataflow
REGION=$(gcloud config get-value compute/region)
RUNNER=DataflowRunner
SESSION_WINDOW_ALLOWED_LATENESS=5
SESSION_WINDOW_GAP_DURATION=30

../gradlew clean assemble execute \
	-Dexec.args=" \
		--inputSubscription=projects/${PROJECT_ID}/subscriptions/${DXP_CLOUD_PROJECT}_analytics_events_dataflow \
		--jobName=eventingestionpipeline-marcellustavares-20220214-155ebd6
		--outputDirectory=${OUTPUT_FOLDER} \
		--outputFileNamePrefix=analytics-events \
		--project=${PROJECT_ID} \
		--region=${REGION} \
		--runner=${RUNNER} \
		--sessionWindowGapDuration=${SESSION_WINDOW_GAP_DURATION} \
		--sessionWindowAllowedLateness=${SESSION_WINDOW_ALLOWED_LATENESS} \
		--tempLocation=${PIPELINE_FOLDER}/temp" \
	-Dexec.cleanupDaemonThreads=false \
	-Dexec.mainClass=${MAIN_CLASS_NAME}