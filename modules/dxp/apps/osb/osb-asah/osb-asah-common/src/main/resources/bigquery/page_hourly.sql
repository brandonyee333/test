WITH PageEvent AS (
	SELECT
		applicationId,
		COALESCE(browserName, '') AS browserName,
		canonicalUrl,
		channelId,
		COALESCE(city, '') AS city,
		COALESCE(country, '') AS country,
		COALESCE(deviceType, '') AS deviceType,
		eventDate,
		eventId,
		COALESCE(platformName, '') AS platformName,
		referrer,
		COALESCE(region, '') AS region,
		sessionId,
		title,
		url,
		userId
	FROM
		`$[AC_PROJECT_ID].event` AS Event
	WHERE
		eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
),
PageBounces AS (
	SELECT
		PageEvent.channelId,
		COUNT(*) AS count,
		SUM(
			CASE
				WHEN PageEvent.applicationId = 'Page' AND PageEvent.eventId = 'pageViewed'
			THEN
				1
			ELSE
				0
			END
		) AS pageViews,
		PageEvent.sessionId,
		PageEvent.userId
	FROM
		PageEvent
	INNER JOIN
		`$[AC_PROJECT_ID].session` AS Session ON
			PageEvent.sessionId = Session.id
	WHERE
		PageEvent.eventId NOT IN ('blogViewed', 'documentPreviewed', 'formViewed', 'pageLoaded', 'pageUnloaded', 'webContentViewed') AND
		Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
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
			PageEvent.browserName,
			PageEvent.canonicalUrl,
			PageEvent.channelId,
			PageEvent.city,
			PageEvent.country,
			PageEvent.deviceType,
			PageEvent.eventDate,
			PageEvent.platformName,
			ROW_NUMBER() OVER (PARTITION BY PageEvent.sessionId, PageEvent.channelId, PageEvent.userId ORDER BY PageEvent.eventDate ASC) AS rank,
			PageEvent.region,
			PageEvent.sessionId,
			PageEvent.title,
			PageEvent.userId
		FROM
			PageEvent
        INNER JOIN
            `$[AC_PROJECT_ID].session` AS Session ON
                PageEvent.sessionId = Session.id
        WHERE
		    Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
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
			PageEvent.browserName,
			PageEvent.canonicalUrl,
			PageEvent.channelId,
			PageEvent.city,
			PageEvent.country,
			PageEvent.deviceType,
			PageEvent.eventDate,
			PageEvent.platformName,
			ROW_NUMBER() OVER (PARTITION BY PageEvent.sessionId, PageEvent.channelId, PageEvent.userId ORDER BY PageEvent.eventDate DESC) AS rank,
			PageEvent.region,
			PageEvent.sessionId,
			PageEvent.title,
			PageEvent.userId
		FROM
			PageEvent
        INNER JOIN
            `$[AC_PROJECT_ID].session` AS Session ON
                PageEvent.sessionId = Session.id
        WHERE
		    Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
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
			PageEvent.browserName,
			PageEvent.canonicalUrl,
			PageEvent.channelId,
			PageEvent.city,
			PageEvent.country,
			PageEvent.deviceType,
			PageEvent.eventDate,
			LEAD(PageEvent.eventDate) OVER (PARTITION BY PageEvent.sessionId, PageEvent.userId, PageEvent.channelId ORDER BY PageEvent.eventDate) AS nextTime,
			PageEvent.platformName,
			PageEvent.region,
			PageEvent.sessionId,
			PageEvent.title,
			PageEvent.userId
		FROM
			PageEvent
        INNER JOIN
            `$[AC_PROJECT_ID].session` AS Session ON
                PageEvent.sessionId = Session.id
        WHERE
		    Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
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
	FROM
	    PageEvent
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
	PageViews.browserName,
	PageViews.canonicalUrl,
	PageViews.channelId,
	PageViews.city,
	PageViews.country,
	PageViews.deviceType,
	PageViews.directAccess,
	PageEntrances.entrances,
	PageViews.normalizedEventDate AS eventDate,
	PageExits.exits,
	PageViews.indirectAccess,
	PageViews.platformName,
	PageViews.region,
	PageTimeOnPages.sessionId,
	PageTimeOnPages.timeOnPage,
	PageViews.title,
	PageTimeOnPages.userId,
	PageViews.views
FROM
	PageViews
LEFT JOIN PageBounces ON (
	PageViews.channelId = PageBounces.channelId AND
	COALESCE(PageViews.sessionId, '') = COALESCE(PageBounces.sessionId, '') AND
	PageViews.userId = PageBounces.userId
)
LEFT JOIN PageEntrances ON (
	PageViews.browserName = PageEntrances.browserName AND
	PageViews.canonicalUrl = PageEntrances.canonicalUrl AND
	PageViews.channelId = PageEntrances.channelId AND
	PageViews.city = PageEntrances.city AND
	PageViews.country = PageEntrances.country AND
	PageViews.deviceType = PageEntrances.deviceType AND
	PageViews.normalizedEventDate = PageEntrances.normalizedEventDate AND
	PageViews.platformName = PageEntrances.platformName AND
	PageViews.region = PageEntrances.region AND
	COALESCE(PageViews.sessionId, '') = COALESCE(PageEntrances.sessionId, '') AND
	PageViews.title = PageEntrances.title AND
	PageViews.userId = PageEntrances.userId
)
LEFT JOIN PageExits ON (
	PageViews.browserName = PageExits.browserName AND
	PageViews.canonicalUrl = PageExits.canonicalUrl AND
	PageViews.channelId = PageExits.channelId AND
	PageViews.city = PageExits.city AND
	PageViews.country = PageExits.country AND
	PageViews.deviceType = PageExits.deviceType AND
	PageViews.normalizedEventDate = PageExits.normalizedEventDate AND
	PageViews.platformName = PageExits.platformName AND
	PageViews.region = PageExits.region AND
	COALESCE(PageViews.sessionId, '') = COALESCE(PageExits.sessionId, '') AND
	PageViews.title = PageExits.title AND
	PageViews.userId = PageExits.userId
)
LEFT JOIN PageTimeOnPages ON (
	PageViews.browserName = PageTimeOnPages.browserName AND
	PageViews.canonicalUrl = PageTimeOnPages.canonicalUrl AND
	PageViews.channelId = PageTimeOnPages.channelId AND
	PageViews.city = PageTimeOnPages.city AND
	PageViews.country = PageTimeOnPages.country AND
	PageViews.deviceType = PageTimeOnPages.deviceType AND
	PageViews.normalizedEventDate = PageTimeOnPages.normalizedEventDate AND
	PageViews.platformName = PageTimeOnPages.platformName AND
	PageViews.region = PageTimeOnPages.region AND
	COALESCE(PageViews.sessionId, '') = COALESCE(PageTimeOnPages.sessionId, '') AND
	PageViews.title = PageTimeOnPages.title AND
	PageViews.userId = PageTimeOnPages.userId
)