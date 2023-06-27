UPDATE `${PROJECT_ID}.${asah_project_id}.event`
SET
	assetId = TRIM(assetId),
	assetTitle = TRIM(assetTitle),
	description = TRIM(description),
	keywords = TRIM(keywords),
	title = TRIM(title)
WHERE
	true