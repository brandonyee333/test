#!/bin/bash

LOCATION=$(gcloud config get-value compute/region)
PROJECT_ID=$(gcloud config get-value project)

# Dataset

bq mk --project_id ${PROJECT_ID} --data_location=${LOCATION} osbasah

# Tables

bq mk --project_id ${PROJECT_ID} --clustering_fields="projectId,channelId,applicationId,eventId" --time_partitioning_field='eventDate' --schema=event_schema.json -t osbasah.event
bq mk --project_id ${PROJECT_ID} --clustering_fields="projectId,channelId" --time_partitioning_field='eventDate' --schema=eventproperty_schema.json -t osbasah.eventproperty
bq mk --project_id ${PROJECT_ID} --clustering_fields="projectId,channelId" --time_partitioning_field='sessionStart' --schema=session_schema.json -t osbasah.session