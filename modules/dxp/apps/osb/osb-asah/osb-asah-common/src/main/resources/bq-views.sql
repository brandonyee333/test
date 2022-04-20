CREATE OR REPLACE VIEW BQPageReferrers AS (
	SELECT
		dataSourceId,
		channelId,
		DATE_TRUNC('HOUR', eventDate) AS eventDate,
		canonicalUrl,
		referrer,
		userId,
		hostname(referrer) AS referrerHost,
	    canonical_url(referrer) AS referrerCanonicalUrl,
		acquisition_channel(referrer, url) AS acquisitionChannel,
		SUM(1) as access
	FROM
	     BQEvent
	WHERE
		applicationId = 'Page' AND
	    eventId = 'pageViewed'
	GROUP BY
	    dataSourceId, channelId, DATE_TRUNC('HOUR', eventDate), canonicalUrl,
	    userId, referrer, acquisitionChannel, referrerHost, referrerCanonicalUrl
);