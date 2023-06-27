UPDATE `${PROJECT_ID}.${asah_project_id}.${asset_table}`
SET
	assetId = TRIM(assetId),
	assetTitle = TRIM(assetTitle),
	pageTitle = TRIM(pageTitle)
WHERE
	CONTAINS_SUBSTR(assetId, '\n') OR
	CONTAINS_SUBSTR(assetTitle, '\n') OR
	CONTAINS_SUBSTR(pageTitle, '\n');