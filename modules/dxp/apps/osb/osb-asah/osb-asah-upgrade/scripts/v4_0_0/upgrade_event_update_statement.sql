UPDATE `${PROJECT_ID}.${ASAH_PROJECT_ID}.event`
SET
	assetId = JSON_EXTRACT(context, '$.canonicalUrl'),
	assetTitle = JSON_EXTRACT(context, '$.title')
WHERE
	applicationId = 'Page' AND
	assetId IS NULL AND
	assetTitle IS NULL
)