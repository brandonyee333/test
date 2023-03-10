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
			'Blog', 'Comment', 'Document', 'Form', 'WebContent', 'Page'
		) AND
		event.eventId IN (
			'blogViewed', 'formViewed', 'formSubmitted', 'documentDownloaded',
			'documentPreviewed', 'webContentViewed', 'pageViewed', 'posted'
		)
)
SELECT
    applicationId,
	TO_HEX(SHA256(assetId)) AS id,
	assetId,
	assetTitle,
	channelId,
	dataSourceId,
    eventId,
	MAX(eventDate) as modifiedDate,
	COUNT(*) as count
FROM
	AssetEvent
GROUP BY
	applicationId,
	assetId,
	assetTitle,
	channelId,
	dataSourceId,
	eventId
