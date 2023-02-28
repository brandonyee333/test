#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)

function merge_event {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_event_merge_statement.sql > new_upgrade_event_merge_statement.sql

	echo "Upgrade Event(merge) for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_merge_statement.sql
}

function update_event {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_event_update_statement.sql > new_upgrade_event_update_statement.sql

	echo "Upgrade Event(update) for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_update_statement.sql
}

function upgrade_session {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_session_merge_statement.sql > new_upgrade_session_merge_statement.sql

	echo "Upgrade Session for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_session_merge_statement.sql
}

for i in $(bq ls --datasets=true --max_results=1000 | grep "asah" | grep -v "osbasah" | awk '{$1=$1;print}')
do :
	merge_event $i
	update_event $i
	upgrade_session $i
done