SELECT
	channelId,
	dataSourceId,
	TIMESTAMP_TRUNC(eventDate, HOUR) AS eventDate,
	canonicalUrl,
	referrer,
	userId,
	REGEXP_SUBSTR(referrer, r':\/\/(?:www[0-9]?\.)?(.[^/:]+)') AS referrerHost,
	REGEXP_REPLACE(referrer, r'\?.[^#]+(#.*)?', '\\1') AS referrerCanonicalUrl,
	`$[AC_PROJECT_ID].acquisition_channel`(referrer, url) AS acquisitionChannel,
	SUM(1) AS access
FROM
	`$[AC_PROJECT_ID].event` AS Event
WHERE
	applicationId = 'Page' AND
	eventId = 'pageViewed'
GROUP BY
	channelId, dataSourceId, eventDate, canonicalUrl, referrer, userId,
	acquisitionChannel, referrerHost, referrerCanonicalUrl