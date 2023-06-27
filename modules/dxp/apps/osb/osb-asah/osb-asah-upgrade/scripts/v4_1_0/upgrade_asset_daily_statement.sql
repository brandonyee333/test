UPDATE `${PROJECT_ID}.${asah_project_id}.${asset_table}`
SET
	assetId = TRIM(assetId),
	assetTitle = TRIM(assetTitle),
	pageTitle = TRIM(pageTitle)
WHERE
	true