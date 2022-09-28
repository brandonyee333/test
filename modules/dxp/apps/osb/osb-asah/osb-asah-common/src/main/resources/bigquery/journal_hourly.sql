WITH
	WebContentEvent AS (
		SELECT
			Event.*,
			articleId.value AS assetId,
			articleTitle.value as assetTitle
		FROM
			`$[AC_PROJECT_ID].event` AS Event
			LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS articleId ON (
				Event.id = articleId.id AND articleId.name = 'articleId'
			)
			LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS articleTitle ON (
				Event.id = articleTitle.id AND articleTitle.name = 'title'
			)
		WHERE
			Event.applicationId = 'WebContent' AND
			Event.eventId = 'webContentViewed' AND
			articleId.value IS NOT NULL AND
			Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 49 HOUR)
	)
SELECT
	assetId,
	assetTitle,
	browserName,
	canonicalUrl,
	channelId,
	city,
	country,
	TIMESTAMP_TRUNC(eventDate, HOUR) as eventDate,
	deviceType,
	platformName,
	region,
	title as pageTitle,
	userId,
	SUM(1) as views
FROM
	WebContentEvent
GROUP BY
	assetId, assetTitle, browserName, canonicalUrl, channelId, city,
	country, TIMESTAMP_TRUNC(eventDate, HOUR), deviceType, platformName,
	region, title, userId