WITH AssetEvent AS (
	SELECT
		CASE WHEN event.applicationId = 'Comment' THEN 'Blog' ELSE event.applicationId END AS applicationId,
		event.assetId,
		event.assetTitle,
		event.canonicalUrl,
		event.channelId,
		event.dataSourceId,
		event.eventDate,
		CASE WHEN event.eventId = 'posted' THEN 'commentPosted' ELSE event.eventId END AS eventId,
		event.title
	FROM
		`$[AC_PROJECT_ID].event` event
	LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS className ON (
		className.id = Event.id
	)
	WHERE
		(
			event.applicationId IN (
				'Blog', 'Document', 'Form', 'WebContent', 'Page'
			) AND
			event.eventId IN (
				'blogViewed', 'formViewed', 'formSubmitted', 'documentDownloaded',
				'documentPreviewed', 'webContentViewed', 'pageViewed'
			)
		) OR
		(
			Event.applicationId = 'Comment' AND
			Event.assetId IS NOT NULL AND
			Event.eventId = 'posted' AND
			className.name = 'className' AND
			className.value = 'com.liferay.blogs.model.BlogsEntry'
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