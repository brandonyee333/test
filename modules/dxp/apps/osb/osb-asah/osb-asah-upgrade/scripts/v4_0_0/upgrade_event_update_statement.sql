UPDATE `${PROJECT_ID}.${ASAH_PROJECT_ID}.event`
SET
	assetId = canonicalUrl,
	assetTitle = title
WHERE
	applicationId = 'Page' AND
	assetId IS NULL AND
	assetTitle IS NULL