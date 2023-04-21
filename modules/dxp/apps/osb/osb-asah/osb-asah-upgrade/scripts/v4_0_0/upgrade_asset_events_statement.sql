UPDATE `${PROJECT_ID}.${asah_project_id}.event`
SET
	assetId = canonicalUrl,
	assetTitle = title
WHERE
	applicationId = 'Page' AND
	assetId IS NULL AND
	assetTitle IS NULL