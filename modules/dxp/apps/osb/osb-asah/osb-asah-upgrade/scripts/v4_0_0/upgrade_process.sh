#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)

function upgrade_session {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_session_merge_statement.sql > new_upgrade_session_merge_statement.sql

	echo "Upgrade Session for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_session_merge_statement.sql
}

for i in $(bq ls --datasets=true --max_results=1000 | grep "asah" | grep -v "osbasah" | awk '{$1=$1;print}')
do :
	upgrade_session $i
done