#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

LANGUAGE_DETECTOR=ld_tatoeba_cnn_99_xx_2.7.0_2.4_1607183215533.zip
POS_TAGGER=pos_anc_en_3.0.0_3.0_1614962126490.zip
REMOTE_GCP_URI=gs://${PROJECT_ID}-dataproc-${REGION}/resources/johnsnowlabs
S3_DOWNLOAD_URI=https://s3.amazonaws.com/auxdata.johnsnowlabs.com/public/models
TMP_FOLDER=~/.asah/interest-score

function copy_to_gcp {
	local source_url=${1}
	local destination_url=${2}

	echo ""
	echo "Copying ${source_url} to ${destination_url}."
	echo ""

	gsutil -m -q cp -r ${source_url} ${destination_url}
}

function download_file {
	local source_file=${1}
	local destination_file=${2}

	echo ""
	echo "Downloading ${source_file} to ${destination_file}."
	echo ""

	wget -O ${destination_file} ${source_file}
}

function extract_file {
	local source_file=${1}
	local destination_file=${2}

	echo ""
	echo "Extracting ${source_file}."
	echo ""

	unzip -q -o ${source_file} -d ${destination_file}
}

mkdir -p ${TMP_FOLDER}

download_file ${S3_DOWNLOAD_URI}/${LANGUAGE_DETECTOR} ${TMP_FOLDER}/${LANGUAGE_DETECTOR}

extract_file ${TMP_FOLDER}/${LANGUAGE_DETECTOR} ${TMP_FOLDER}/ld_tatoeba_cnn_99

copy_to_gcp ${TMP_FOLDER}/ld_tatoeba_cnn_99/ ${REMOTE_GCP_URI}/ld_tatoeba_cnn_99

download_file ${S3_DOWNLOAD_URI}/${POS_TAGGER} ${TMP_FOLDER}/${POS_TAGGER}

extract_file ${TMP_FOLDER}/${POS_TAGGER} ${TMP_FOLDER}/pos_anc_en

copy_to_gcp ${TMP_FOLDER}/pos_anc_en/ ${REMOTE_GCP_URI}/pos_anc_en