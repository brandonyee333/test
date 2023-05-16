#!/bin/bash

if [ ! -f project_time_zones ]
then
echo "File project_time_zones does not exist.";

exit
fi

PROJECT_ID=$(gcloud config get-value project)

function upgrade_blog_daily {
	local asah_project_id=${1}
	local asah_project_time_zone=${2}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" -e "s/\${asah_project_time_zone}/$asah_project_time_zone/g" upgrade_blog_daily_merge_statement.sql > new_upgrade_blog_daily.sql

	echo "Upgrade BlogDaily for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_blog_daily.sql
}

function upgrade_custom_asset_daily {
	local asah_project_id=${1}
	local asah_project_time_zone=${2}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${asah_project_time_zone}/$asah_project_time_zone/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_customasset_daily_merge_statement.sql > new_upgrade_customasset_daily.sql

	echo "Upgrade CustomAssetDaily for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_customasset_daily.sql
}

function upgrade_document_library_daily {
	local asah_project_id=${1}
	local asah_project_time_zone=${2}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${asah_project_time_zone}/$asah_project_time_zone/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_document_library_daily_merge_statement.sql > new_upgrade_document_library_daily.sql

	echo "Upgrade DocumentLibraryDaily for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_document_library_daily.sql
}

function upgrade_form_daily {
	local asah_project_id=${1}
	local asah_project_time_zone=${2}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${asah_project_time_zone}/$asah_project_time_zone/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_form_daily_merge_statement.sql > new_upgrade_form_daily.sql

	echo "Upgrade FormDaily for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_form_daily.sql
}

function upgrade_journal_daily {
	local asah_project_id=${1}
	local asah_project_time_zone=${2}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${asah_project_time_zone}/$asah_project_time_zone/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_journal_daily_merge_statement.sql > new_upgrade_journal_daily.sql

	echo "Upgrade JournalDaily for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_journal_daily.sql
}

function upgrade_page_daily {
	local asah_project_id=${1}
	local asah_project_time_zone=${2}

	sed -e "s/\${asah_project_id}/$asah_project_id/g" -e "s/\${asah_project_time_zone}/$asah_project_time_zone/g" -e "s/\${PROJECT_ID}/$PROJECT_ID/g" upgrade_page_daily_merge_statement.sql > new_upgrade_page_daily_merge_statement.sql

	echo "Upgrade Page for Project ID: ${PROJECT_ID}, Asah Project ID: ${asah_project_id}"
	bq --project_id ${PROJECT_ID} query --use_legacy_sql=false < new_upgrade_page_daily_merge_statement.sql
}

for i in $(bq ls --datasets=true --max_results=1000 | grep "asah" | grep -v "osbasah" | awk '{$1=$1;print}')
do :
project_time_zone=$(cat project_time_zones | grep "$i" | awk '{print $2}')

if [ -n "$project_time_zone" ]
then
	  upgrade_blog_daily $i $project_time_zone
	  upgrade_custom_asset_daily $i $project_time_zone
	  upgrade_document_library_daily $i $project_time_zone
	  upgrade_form_daily $i $project_time_zone
	  upgrade_journal_daily $i $project_time_zone
	  upgrade_page_daily $i $project_time_zone
else
	echo "Unable to find time zone for $i";
fi

done