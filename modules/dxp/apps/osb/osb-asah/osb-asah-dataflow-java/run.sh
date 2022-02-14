#!/bin/bash

if [ "$#" -ne 1 ]
then
	echo "Usage: run [dxp-cloud-project]"
	exit 1
fi

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

DXP_CLOUD_PROJECT=${1}
EVENT_PROPERTY_TABLE_NAME=${PROJECT_ID}:osbasah.eventproperty
EVENT_TABLE_NAME=${PROJECT_ID}:osbasah.event
MAIN_CLASS_NAME=com.liferay.osb.asah.dataflow.ingestion.StreamingIngestionPipeline
OUTPUT_FOLDER=gs://${PROJECT_ID}-analytics-events
PIPELINE_FOLDER=gs://${PROJECT_ID}-dataflow
RUNNER=DataflowRunner
SESSION_TABLE_NAME=${PROJECT_ID}:osbasah.session
SESSION_WINDOW_ALLOWED_LATENESS=5
SESSION_WINDOW_GAP_DURATION=30

function main {
../gradlew clean compileJava execute \
-Dexec.args=" \
	--eventPropertyTableName=${EVENT_PROPERTY_TABLE_NAME} \
	--eventTableName=${EVENT_TABLE_NAME} \
	--inputSubscription=projects/${PROJECT_ID}/subscriptions/${DXP_CLOUD_PROJECT}_analytics_events_dataflow \
	--jobName=streamingingestionpipeline-marcellustavares-20220209-2058dc9
	--outputDirectory=${OUTPUT_FOLDER} \
	--outputFileNamePrefix=analytics-events \
	--project=${PROJECT_ID} \
	--region=${REGION} \
	--autoscalingAlgorithm=THROUGHPUT_BASED \
	--maxNumWorkers=5 \
	--runner=${RUNNER} \
	--sessionTableName=${SESSION_TABLE_NAME} \
	--sessionWindowGapDuration=${SESSION_WINDOW_GAP_DURATION} \
	--sessionWindowAllowedLateness=${SESSION_WINDOW_ALLOWED_LATENESS} \
	--tempLocation=${PIPELINE_FOLDER}/temp" \
-Dexec.cleanupDaemonThreads=false \
-Dexec.mainClass=${MAIN_CLASS_NAME}
}