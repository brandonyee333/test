#!/bin/bash

PROJECT_ID=$(gcloud config get-value project)

function upgrade_event_asset_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_events_statement.sql > new_upgrade_event_update_statement.sql

	echo "Upgrade Event Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_event_update_statement.sql
}

function upgrade_blog_asset_daily_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" -e "s/\${asset_table}/blogdaily/g" upgrade_asset_daily_statement.sql > new_upgrade_asset_daily_statement.sql

	echo "Upgrade Blog Daily Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_asset_daily_statement.sql
}

function upgrade_document_library_asset_daily_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" -e "s/\${asset_table}/documentlibrarydaily/g" upgrade_asset_daily_statement.sql > new_upgrade_asset_daily_statement.sql

	echo "Upgrade Document Library Daily Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_asset_daily_statement.sql
}

function upgrade_form_asset_daily_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" -e "s/\${asset_table}/formdaily/g" upgrade_asset_daily_statement.sql > new_upgrade_asset_daily_statement.sql

	echo "Upgrade Form Daily Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_asset_daily_statement.sql
}

function upgrade_journal_asset_daily_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" -e "s/\${asset_table}/journaldaily/g" upgrade_asset_daily_statement.sql > new_upgrade_asset_daily_statement.sql

	echo "Upgrade Journal Daily Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_asset_daily_statement.sql
}

function upgrade_page_asset_daily_columns {
	local asah_project_id=${1}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_page_daily_statement.sql > new_upgrade_page_daily_statement.sql

	echo "Upgrade Page Daily Columns for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"

	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_page_daily_statement.sql
}

for i in $(bq ls --datasets=true --max_results=1000 | grep "asah" | grep -v "osbasah" | grep -v "_bkp" | awk '{$1=$1;print}')
do :
	upgrade_event_asset_columns $i
	upgrade_blog_asset_daily_columns $i
	upgrade_document_library_asset_daily_columns $i
	upgrade_form_asset_daily_columns $i
	upgrade_journal_asset_daily_columns $i
	upgrade_page_asset_daily_columns $i
done