WITH
	WebContentEvent AS (
		SELECT
			Event.*
		FROM
			`$[AC_PROJECT_ID].event` AS Event
		WHERE
			Event.applicationId = 'WebContent' AND
			Event.assetId IS NOT NULL AND
			Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			Event.eventId = 'webContentViewed'
	)
SELECT
	assetId,
	assetTitle,
	browserName,
	canonicalUrl,
	channelId,
	city,
	country,
	TIMESTAMP_TRUNC(eventDate, HOUR) AS eventDate,
	deviceType,
	platformName,
	region,
	title AS pageTitle,
	userId,
	SUM(1) AS views
FROM
	WebContentEvent
GROUP BY
	assetId, assetTitle, browserName, canonicalUrl, channelId, city,
	country, TIMESTAMP_TRUNC(eventDate, HOUR), deviceType, platformName,
	region, title, userId