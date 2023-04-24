WITH AssetEvent AS (
	SELECT
		CASE
		    WHEN
				Event.applicationId = 'Comment'
			THEN
				'Blog'
			ELSE
			    Event.applicationId
		END AS applicationId,
		Event.assetId,
		Event.assetTitle,
		Event.canonicalUrl,
		Event.channelId,
		Event.dataSourceId,
		Event.eventDate,
		CASE
			WHEN
				Event.eventId = 'posted'
			THEN
			    'commentPosted'
		    ELSE
				Event.eventId
		END AS eventId,
		Event.title
	FROM
		`$[AC_PROJECT_ID].event` Event
	LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS className ON (
		className.id = Event.id
	)
	WHERE
		(
			Event.applicationId IN (
				'Blog', 'Document', 'Form', 'WebContent', 'Page'
			) AND
			Event.eventId IN (
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