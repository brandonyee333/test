SELECT
	channelId,
	dataSourceId,
	TIMESTAMP_TRUNC(eventDate, HOUR) AS eventDate,
	canonicalUrl,
	referrer,
	userId,
	NET.REG_DOMAIN(referrer) AS referrerHost,
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