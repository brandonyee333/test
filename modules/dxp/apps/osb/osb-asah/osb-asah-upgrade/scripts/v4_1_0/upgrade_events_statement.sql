UPDATE `${PROJECT_ID}.${asah_project_id}.event`
SET
	assetId = TRIM(assetId),
	assetTitle = TRIM(assetTitle),
	description = TRIM(description),
	keywords = TRIM(keywords),
	title = TRIM(title)
WHERE
	CONTAINS_SUBSTR(assetId, '\n') OR
	CONTAINS_SUBSTR(assetTitle, '\n') OR
	CONTAINS_SUBSTR(description, '\n') OR
	CONTAINS_SUBSTR(keywords, '\n') OR
	CONTAINS_SUBSTR(title, '\n')