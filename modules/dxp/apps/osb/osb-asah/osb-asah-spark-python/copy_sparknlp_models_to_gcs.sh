#!/bin/bash

REMOTE_GCP_URI=gs://ac-interest-scores/resources
LOCAL_TEMP_FOLDER=build
SPARK_NLP_JAR_DOWNLOAD_PATH=https://s3.amazonaws.com/auxdata.johnsnowlabs.com/public/jars/spark-nlp-assembly-4.1.0.jar
SPARK_NLP_LOCAL_DESTINATION_FOLDER=spark-nlp-assembly-4.1.0.jar
LANGUAGE_DETECTION_DOWNLOAD_PATH=https://s3.amazonaws.com/auxdata.johnsnowlabs.com/public/models/ld_tatoeba_cnn_99_xx_2.7.0_2.4_1607183215533.zip
LANGAUGE_DETECTION_LOCAL_DESTINATION_FOLDER=ld_tatoeba_cnn_99/
POS_TAGGER_DOWNLOAD_PATH=https://s3.amazonaws.com/auxdata.johnsnowlabs.com/public/models/pos_anc_en_3.0.0_3.0_1614962126490.zip
POS_TAGGER_LOCAL_DESTINATION_FOLDER=pos_anc_en/

function download_model_file {
	echo ""
	echo "Downloading $1"
	echo ""

	base_file_name=$(basename $1)

	wget -O ${LOCAL_TEMP_FOLDER}/$base_file_name $1

	if [[ "$3" = "True" ]]
	then
		echo ""
		echo "Unzipping $base_file_name to $2"
		echo ""
		unzip -o ${LOCAL_TEMP_FOLDER}/$base_file_name -d ${LOCAL_TEMP_FOLDER}/$2
	fi
}

function main {
	echo "+===============+"
	echo "| Spark-NLP JAR |"
	echo "+===============+"

	download_model_file $SPARK_NLP_JAR_DOWNLOAD_PATH $SPARK_NLP_LOCAL_DESTINATION_FOLDER False

	push_to_gcp $SPARK_NLP_LOCAL_DESTINATION_FOLDER $REMOTE_GCP_URI

	echo "+=========================+"
	echo "| Language Detector Model |"
	echo "+=========================+"

	download_model_file $LANGUAGE_DETECTION_DOWNLOAD_PATH $LANGAUGE_DETECTION_LOCAL_DESTINATION_FOLDER True

	push_to_gcp $LANGAUGE_DETECTION_LOCAL_DESTINATION_FOLDER $REMOTE_GCP_URI

	echo "+==================+"
	echo "| POS Tagger Model |"
	echo "+==================+"

	download_model_file $POS_TAGGER_DOWNLOAD_PATH $POS_TAGGER_LOCAL_DESTINATION_FOLDER True

	push_to_gcp $POS_TAGGER_LOCAL_DESTINATION_FOLDER $REMOTE_GCP_URI
}

function push_to_gcp {
	echo ""
	echo "Pushing $1 to $2."
	echo ""

	gsutil -m cp -r ${LOCAL_TEMP_FOLDER}/$1 $2
}

main