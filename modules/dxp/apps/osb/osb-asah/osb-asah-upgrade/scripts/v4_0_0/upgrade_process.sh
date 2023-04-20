#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)

function upgrade_asset_events {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_asset_events_statement.sql > new_upgrade_event_update_statement.sql

	echo "Upgrade Asset Events for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_update_statement.sql
}

function upgrade_identity_activity_summary {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_identity_activity_summary_merge_statement.sql > new_upgrade_identity_activity_summary_merge_statement.sql

	echo "Upgrade Identity Activity Summary for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_identity_activity_summary_merge_statement.sql
}

function upgrade_page_events {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_page_events_statement.sql > new_upgrade_event_merge_statement.sql

	echo "Upgrade Page Events for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_merge_statement.sql
}

function upgrade_session {
	local ASAH_PROJECT_ID=${1}

	sed -e "s/\${ASAH_PROJECT_ID}/$ASAH_PROJECT_ID/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_session_merge_statement.sql > new_upgrade_session_merge_statement.sql

	echo "Upgrade Session for Project ID: ${PROJECT_ID}, Asah Project ID: ${ASAH_PROJECT_ID}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_session_merge_statement.sql
}

for i in $(bq ls --datasets=true --max_results=1000 | grep "asah" | grep -v "osbasah" | awk '{$1=$1;print}')
do :
	upgrade_asset_events $i
	upgrade_identity_activity_summary $i
	upgrade_page_events $i
	upgrade_session $i
done