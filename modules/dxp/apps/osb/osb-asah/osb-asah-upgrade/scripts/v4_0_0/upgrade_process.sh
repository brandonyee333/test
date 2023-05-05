#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)

function upgrade_event_asset_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_asset_events_statement.sql > new_upgrade_event_update_statement.sql

	echo "Upgrade Event Asset Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_update_statement.sql

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_page_events_statement.sql > new_upgrade_event_merge_statement.sql

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_merge_statement.sql
}

function upgrade_event_experiment_id_column {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_event_experiment_id_column_statement.sql > new_upgrade_event_experiment_id_column_statement.sql

	echo "Upgrade Event ExperimentId Column for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_experiment_id_column_statement.sql
}

function upgrade_identity_activity_summary {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_identity_activity_summary_merge_statement.sql > new_upgrade_identity_activity_summary_merge_statement.sql

	echo "Upgrade Identity Activity Summary for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_identity_activity_summary_merge_statement.sql
}

function upgrade_session_urls {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_session_merge_statement.sql > new_upgrade_session_merge_statement.sql

	echo "Upgrade Session for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_session_merge_statement.sql
}

for i in $(bq ls --datasets=true --max_results=1000 | grep "asah" | grep -v "osbasah" | awk '{$1=$1;print}')
do :
	upgrade_event_asset_columns $i
	upgrade_event_experiment_id_column $i
	upgrade_identity_activity_summary $i
	upgrade_session_urls $i
done