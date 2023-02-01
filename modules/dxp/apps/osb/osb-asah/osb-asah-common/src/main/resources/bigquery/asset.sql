WITH AssetEvent AS (
	SELECT
		event.applicationId,
		event.canonicalUrl,
		event.channelId,
		event.dataSourceId,
		event.eventDate,
		event.eventId,
		event.title,
		CASE
			WHEN
				assetTitle IS NOT NULL
			THEN
				assetTitle.value
			WHEN
				event.eventId = 'pageViewed'
			THEN
				event.title
			END AS assetTitle,
		CASE
			WHEN
				assetId IS NOT NULL
			THEN
				assetId.value
			WHEN
				event.eventId = 'pageViewed'
			THEN
				event.canonicalUrl
		END AS assetId
	FROM
		`$[AC_PROJECT_ID].event` event
	LEFT JOIN `$[AC_PROJECT_ID].eventproperty` assetId ON (
		event.id = assetId.id AND
		assetId.name IN (
			'articleId', 'classPK', 'entryId', 'fileEntryId', 'formId'
		)
	)
	LEFT JOIN `$[AC_PROJECT_ID].eventproperty` assetTitle ON (
		event.id = assetTitle.id AND assetTitle.name = 'title'
	)
   WHERE
		event.applicationId IN (
			'Blog', 'Custom', 'Document', 'Form', 'WebContent', 'Page'
		) AND
		event.eventId IN (
			'blogViewed', 'formViewed', 'documentDownloaded',
			'documentPreviewed', 'webContentViewed', 'pageViewed'
		)
)
SELECT
	TO_HEX(
		SHA256(CONCAT(dataSourceId, assetId, assetTitle))
	) AS id,
	assetId,
	assetTitle,
	applicationId AS assetType,
	channelId,
	dataSourceId,
	MAX(eventDate) as modifiedDate,
	COUNT(*) as views
FROM
	AssetEvent
GROUP BY
	assetId,
	assetTitle,
	assetType,
	channelId,
	dataSourceId
