#!/bin/bash

export LOCATION=$(gcloud config get-value compute/region)
export PROJECT_ID=$(gcloud config get-value project)

# Analytics events

gsutil mb -l ${LOCATION} -p ${PROJECT_ID} gs://${PROJECT_ID}-analytics-events

# Dataflow

gsutil mb -l ${LOCATION} -p ${PROJECT_ID} gs://${PROJECT_ID}-dataflow