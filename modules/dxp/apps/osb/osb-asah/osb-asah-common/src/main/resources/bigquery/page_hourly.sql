WITH PageFinalizedEvent AS (
	SELECT
		Event.applicationId,
		COALESCE(Event.browserName, '') AS browserName,
		Event.canonicalUrl,
		Event.channelId,
		COALESCE(Event.city, '') AS city,
		COALESCE(Event.country, '') AS country,
		COALESCE(Event.deviceType, '') AS deviceType,
		Event.eventDate,
		Event.eventId,
		COALESCE(Event.platformName, '') AS platformName,
		Event.referrer,
		COALESCE(Event.region, '') AS region,
		Event.sessionId,
		Event.url,
		Event.userId
	FROM
		`$[AC_PROJECT_ID].event` AS Event
	INNER JOIN
		`$[AC_PROJECT_ID].session` AS Session ON
			Event.sessionId = Session.id
	WHERE
		Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
		Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
),
PageBounces AS (
	SELECT
		channelId,
		COUNT(*) AS count,
		SUM(
			CASE
				WHEN applicationId = 'Page' AND eventId = 'pageViewed'
			THEN
				1
			ELSE
				0
			END
		) AS pageViews,
		sessionId,
		userId
	FROM
		PageFinalizedEvent
	WHERE
		eventId NOT IN ('blogViewed', 'documentPreviewed', 'formViewed', 'pageLoaded', 'pageUnloaded', 'webContentViewed')
	GROUP BY
		channelId, sessionId, userId
),
PageEntrances AS (
	SELECT
		browserName,
		canonicalUrl,
		channelId,
		city,
		country,
		deviceType,
		rank AS entrances,
		TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
		platformName,
		region,
		sessionId,
		title,
		userId
	FROM (
		SELECT
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			eventDate,
			platformName,
			ROW_NUMBER() OVER (PARTITION BY sessionId, channelId, userId ORDER BY eventDate ASC) AS rank,
			region,
			sessionId,
			title,
			userId
		FROM
			PageFinalizedEvent
	) AS EventEntrance
	WHERE
		rank = 1
),
PageExits AS (
	SELECT
		browserName,
		canonicalUrl,
		channelId,
		city,
		country,
		deviceType,
		rank AS exits,
		TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
		platformName,
		region,
		sessionId,
		title,
		userId
	FROM (
		SELECT
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			eventDate,
			platformName,
			ROW_NUMBER() OVER (PARTITION BY sessionId, channelId, userId ORDER BY eventDate DESC) AS rank,
			region,
			sessionId,
			title,
			userId
		FROM
			PageFinalizedEvent
	) AS EventExit
	WHERE
		rank = 1
),
PageTimeOnPages AS (
	SELECT
		browserName,
		canonicalUrl,
		channelId,
		city,
		country,
		deviceType,
		TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
		platformName,
		region,
		sessionId,
		SUM(TIMESTAMP_DIFF(nextTime, eventDate, MILLISECOND)) AS timeOnPage,
		title,
		userId
	FROM (
		SELECT
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			eventDate,
			LEAD(eventDate) OVER (PARTITION BY sessionId, userId, channelId ORDER BY eventDate) AS nextTime,
			platformName,
			region,
			sessionId,
			title,
			userId
		FROM
			PageFinalizedEvent
	) AS EventTimeOnPage
	GROUP BY
		browserName, canonicalUrl, channelId, city, country, deviceType,
		normalizedEventDate, platformName, region, sessionId, title, userId
),
PageViews AS (
	SELECT
		browserName,
		canonicalUrl,
		channelId,
		city,
		country,
		deviceType,
		TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
		SUM(
			CASE
				WHEN
					referrer = ''
				THEN
					1
				ELSE
					0
			END
		) AS directAccess,
		SUM(
			CASE
				WHEN
					referrer != ''
				THEN
					1
				ELSE
					0
			END
		) AS indirectAccess,
		SUM(1) AS views,
		platformName,
		region,
		sessionId,
		title,
		userId
		FROM PageFinalizedEvent
	WHERE
		applicationId = 'Page' AND eventId = 'pageViewed'
	GROUP BY
		browserName, canonicalUrl, channelId, city, country, deviceType,
		normalizedEventDate, platformName, region, sessionId, title, userId
)

SELECT
	CASE
		WHEN
			PageBounces.count > 2 OR PageBounces.pageViews > 1
		THEN
			0
		ELSE
			1
	END AS bounce,
	PageTimeOnPages.browserName,
	PageTimeOnPages.canonicalUrl,
	PageTimeOnPages.channelId,
	PageTimeOnPages.city,
	PageTimeOnPages.country,
	PageTimeOnPages.deviceType,
	PageViews.directAccess,
	PageEntrances.entrances,
	PageTimeOnPages.normalizedEventDate AS eventDate,
	PageExits.exits,
	PageViews.indirectAccess,
	PageTimeOnPages.platformName,
	PageTimeOnPages.region,
	PageTimeOnPages.sessionId,
	PageTimeOnPages.timeOnPage,
	PageTimeOnPages.title,
	PageTimeOnPages.userId,
	PageViews.views
FROM
	PageTimeOnPages
LEFT JOIN PageBounces ON (
	PageTimeOnPages.channelId = PageBounces.channelId AND
	PageTimeOnPages.sessionId = PageBounces.sessionId AND
	PageTimeOnPages.userId = PageBounces.userId
)
LEFT JOIN PageEntrances ON (
	PageTimeOnPages.browserName = PageEntrances.browserName AND
	PageTimeOnPages.canonicalUrl =
	PageEntrances.canonicalUrl AND
	PageTimeOnPages.channelId = PageEntrances.channelId AND
	PageTimeOnPages.city = PageEntrances.city AND
	PageTimeOnPages.country = PageEntrances.country AND
	PageTimeOnPages.deviceType = PageEntrances.deviceType AND
	PageTimeOnPages.normalizedEventDate = PageEntrances.normalizedEventDate AND
	PageTimeOnPages.platformName = PageEntrances.platformName AND
	PageTimeOnPages.region = PageEntrances.region AND
	PageTimeOnPages.sessionId = PageEntrances.sessionId AND
	PageTimeOnPages.title = PageEntrances.title AND
	PageTimeOnPages.userId = PageEntrances.userId
)
LEFT JOIN PageExits ON (
	PageTimeOnPages.browserName = PageExits.browserName AND
	PageTimeOnPages.canonicalUrl = PageExits.canonicalUrl AND
	PageTimeOnPages.channelId = PageExits.channelId AND
	PageTimeOnPages.city = PageExits.city AND
	PageTimeOnPages.country = PageExits.country AND
	PageTimeOnPages.deviceType = PageExits.deviceType AND
	PageTimeOnPages.normalizedEventDate = PageExits.normalizedEventDate AND
	PageTimeOnPages.platformName = PageExits.platformName AND
	PageTimeOnPages.region = PageExits.region AND
	PageTimeOnPages.sessionId = PageExits.sessionId AND
	PageTimeOnPages.title = PageExits.title AND
	PageTimeOnPages.userId = PageExits.userId
)
LEFT JOIN PageViews ON (
	PageTimeOnPages.browserName = PageViews.browserName AND
	PageTimeOnPages.canonicalUrl = PageViews.canonicalUrl AND
	PageTimeOnPages.channelId = PageViews.channelId AND
	PageTimeOnPages.city = PageViews.city AND
	PageTimeOnPages.country = PageViews.country AND
	PageTimeOnPages.deviceType = PageViews.deviceType AND
	PageTimeOnPages.normalizedEventDate = PageViews.normalizedEventDate AND
	PageTimeOnPages.platformName = PageViews.platformName AND
	PageTimeOnPages.region = PageViews.region AND
	PageTimeOnPages.sessionId = PageViews.sessionId AND
	PageTimeOnPages.title = PageViews.title AND
	PageTimeOnPages.userId = PageViews.userId
)