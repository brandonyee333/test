#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)
REGION=$(gcloud config get-value compute/region)

EMBEDDINGS_FILE_NAME=roberta_base_en_3.1.0_2.4_1621523388696.zip
EMBEDDINGS_MODEL_LANG=en
EMBEDDINGS_MODEL_NAME=roberta_base
LANGUAGE_DETECTOR_FILE_NAME=ld_tatoeba_cnn_99_xx_2.7.0_2.4_1607183215533.zip
LANGUAGE_DETECTOR_MODEL_LANG=xx
LANGUAGE_DETECTOR_MODEL_NAME=ld_tatoeba_cnn_99
NER_FILE_NAME=ner_ontonotes_roberta_base_en_3.2.0_2.4_1628078208687.zip
NER_MODEL_LANG=en
NER_MODEL_NAME=ner_ontonotes_roberta_base
POS_TAGGER_FILE_NAME=pos_anc_en_3.0.0_3.0_1614962126490.zip
POS_TAGGER_MODEL_LANG=en
POS_TAGGER_MODEL_NAME=pos_anc
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

	if [ -e ${destination_file} ]
	then
		echo ""
		echo "${destination_file} already exists. Skipping download"
		echo ""

		return
	fi

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

function upload_model {
	local file_name=${1}
	local model_lang=${2}
	local model_name=${3}

	download_file ${S3_DOWNLOAD_URI}/${file_name} ${TMP_FOLDER}/${file_name}

	extract_file ${TMP_FOLDER}/${file_name} ${TMP_FOLDER}/${model_name}_${model_lang}

	copy_to_gcp ${TMP_FOLDER}/${model_name}_${model_lang} ${REMOTE_GCP_URI}/${model_name}_${model_lang}
}

mkdir -p ${TMP_FOLDER}

upload_model ${EMBEDDINGS_FILE_NAME} ${EMBEDDINGS_MODEL_LANG} ${EMBEDDINGS_MODEL_NAME}

upload_model ${LANGUAGE_DETECTOR_FILE_NAME} ${LANGUAGE_DETECTOR_MODEL_LANG} ${LANGUAGE_DETECTOR_MODEL_NAME}

upload_model ${NER_FILE_NAME} ${NER_MODEL_LANG} ${NER_MODEL_NAME}

upload_model ${POS_TAGGER_FILE_NAME} ${POS_TAGGER_MODEL_LANG} ${POS_TAGGER_MODEL_NAME}