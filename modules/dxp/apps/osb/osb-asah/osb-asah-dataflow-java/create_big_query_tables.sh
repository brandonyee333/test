#!/bin/bash

export PROJECT_ID=$(gcloud config get-value project)
export DATA_LOCATION='us-west1'

# Dataset

bq mk --project_id ${PROJECT_ID} --data_location=${DATA_LOCATION} osbasah

# Tables

bq mk --project_id ${PROJECT_ID} --clustering_fields="projectId,channelId" --time_partitioning_field='eventDate' --schema=event_schema.json -t osbasah.event
bq mk --project_id ${PROJECT_ID} --clustering_fields="projectId,channelId" --time_partitioning_field='eventDate' --schema=eventproperty_schema.json -t osbasah.eventproperty
bq mk --project_id ${PROJECT_ID} --clustering_fields="projectId,channelId" --time_partitioning_field='sessionStart' --schema=session_schema.json -t osbasah.session