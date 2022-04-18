CREATE OR REPLACE VIEW BQPageReferrers AS (
	SELECT
		dataSourceId, channelId, DATE_TRUNC('HOUR', eventDate) AS eventDate,
		canonicalUrl, (ARRAY_AGG(title order by eventDate desc))[1] AS title,
		referrer, acquisition_channel(referrer, url) as acquisitionChannel,
		COUNT(id) as access
	FROM
		BQEvent
	WHERE
		applicationId = 'Page' AND
		eventid = 'pageViewed'
	GROUP BY
		dataSourceId, channelId, date, canonicalUrl, referrer,
	    acquisitionChannel
	);