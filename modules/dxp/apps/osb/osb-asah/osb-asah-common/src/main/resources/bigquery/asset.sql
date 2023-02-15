WITH AssetEvent AS (
	SELECT
		event.applicationId,
		event.assetId,
        event.assetTitle,
		event.canonicalUrl,
		event.channelId,
		event.dataSourceId,
		event.eventDate,
		event.eventId,
		event.title
	FROM
		`$[AC_PROJECT_ID].event` event
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
		SHA256(CONCAT(assetId, assetTitle))
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
