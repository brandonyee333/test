#!/bin/bash

function main {
gcloud pubsub subscriptions create --project ${PROJECT_ID} --topic ${DXP_CLOUD_PROJECT}_analytics_events ${DXP_CLOUD_PROJECT}_analytics_events_dataflow
}

if [ "$#" -ne 1 ]
then
	echo "Usage: create_pubsub_subscriptions [dxp-cloud-project]"
	exit 1
fi

PROJECT_ID=$(gcloud config get-value project)
DXP_CLOUD_PROJECT=${1}

main