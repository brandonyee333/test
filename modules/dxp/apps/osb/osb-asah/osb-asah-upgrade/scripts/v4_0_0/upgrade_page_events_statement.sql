MERGE INTO
	`${PROJECT_ID}.${ASAH_PROJECT_ID}.event` AS replica
USING (
	SELECT
		AssetId.value AS assetId,
		AssetTitle.value AS assetTitle,
		Event.id
	FROM `${PROJECT_ID}.${ASAH_PROJECT_ID}.event` AS Event
	LEFT OUTER JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS AssetId
		ON (
			Event.id = AssetId.id AND
			AssetId.name IN (
				'articleId', 'assetId', 'classPK', 'entryId', 'fileEntryId',
				'formId'
			)
		)
	LEFT OUTER JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty`
		AS AssetTitle
	ON (
		Event.id = AssetTitle.id AND
		AssetTitle.name = 'title'
	)
	WHERE
		Event.applicationId IN (
			'Blog', 'Comment', 'Custom', 'Document', 'Form', 'Ratings',
			'WebContent'
		) AND
		Event.assetId IS NULL AND
		Event.assetTitle IS NULL AND
		Event.eventId != 'pageViewed'
) AS staging
ON (replica.id = staging.id)
WHEN MATCHED THEN
	UPDATE SET
		replica.assetId = staging.assetId,
		replica.assetTitle = staging.assetTitle