MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.pagedaily` AS replica
USING
	(
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
				experimentId,
				COALESCE(platformName, '') AS platformName,
				referrer,
				COALESCE(region, '') AS region,
				sessionId,
				title,
				url,
				userId,
				variantId
			FROM
				`${PROJECT_ID}.${asah_project_id}.event` AS Event
			WHERE
				Event.eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR)
		),
		PageBounces AS (
			SELECT
				PageEvent.channelId,
				COUNT(*) AS count,
				COUNTIF(
					PageEvent.applicationId = 'Page' AND
					PageEvent.eventId = 'pageViewed'
				) AS pageViews,
				PageEvent.sessionId,
				PageEvent.userId
			FROM
				PageEvent
			INNER JOIN
				`${PROJECT_ID}.${asah_project_id}.session` AS Session ON
					PageEvent.sessionId = Session.id
			WHERE
				PageEvent.eventId NOT IN ('blogViewed', 'documentPreviewed', 'formViewed', 'pageLoaded', 'pageUnloaded', 'webContentViewed') AND
				Session.sessionStart < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR)
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
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
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
					`${PROJECT_ID}.${asah_project_id}.session` AS Session ON
						PageEvent.sessionId = Session.id
				WHERE
					Session.sessionStart < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR)
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
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
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
					`${PROJECT_ID}.${asah_project_id}.session` AS Session ON
						PageEvent.sessionId = Session.id
				WHERE
					Session.sessionStart < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR)
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
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
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
					`${PROJECT_ID}.${asah_project_id}.session` AS Session ON
						PageEvent.sessionId = Session.id
				WHERE
					Session.sessionStart < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR)
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
				COUNTIF(eventId = 'ctaClicked') ctaClicks,
				deviceType,
				COUNTIF(eventId = 'pageViewed' AND Referrer = '') AS directAccess,
				ANY_VALUE(experimentId) AS experimentId,
				COUNTIF(eventId = 'pageViewed' AND referrer != '') AS indirectAccess,
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
				platformName,
				COUNTIF(eventId = 'pageRead') reads,
				region,
				sessionId,
				title,
				userId,
				ANY_VALUE(variantId) AS variantId,
				COUNTIF(eventId = 'pageViewed') AS views
			FROM
				PageEvent
			WHERE
				applicationId = 'Page' AND
				eventId IN ('ctaClicked', 'pageRead', 'pageViewed')
			GROUP BY
				browserName, canonicalUrl, channelId, city, country, deviceType,
				normalizedEventDate, platformName, region, sessionId, title, userId
		),
		PageHourlyMetrics AS (
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
				PageViews.ctaClicks,
				PageViews.deviceType,
				PageViews.directAccess,
				PageEntrances.entrances,
				PageViews.normalizedEventDate AS eventDate,
				PageExits.exits,
				PageViews.experimentId,
				PageViews.indirectAccess,
				PageViews.platformName,
				PageViews.reads,
				PageViews.region,
				PageTimeOnPages.sessionId,
				PageTimeOnPages.timeOnPage,
				PageViews.title,
				PageTimeOnPages.userId,
				PageViews.variantId,
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
		)
		SELECT
			SUM(bounce) AS bounce,
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			SUM(ctaClicks) AS ctaClicks,
			deviceType,
			SUM(directAccess) AS directAccess,
			SUM(entrances) AS entrances,
			DATE_TRUNC(eventDate, DAY) AS eventDate,
			SUM(exits) exits,
			ANY_VALUE(experimentId) AS experimentId,
			SUM(indirectAccess) AS indirectAccess,
			platformName,
			SUM(reads) reads,
			region,
			sessionId,
			SUM(timeOnPage) AS timeOnPage,
			title,
			userId,
			ANY_VALUE(variantId) AS variantId,
			SUM(views) AS views
		FROM
			PageHourlyMetrics
		WHERE
			sessionId IS NOT NULL
		GROUP BY
			browserName, canonicalUrl, channelId, city, country, deviceType,
			eventDate, platformName, region, sessionId, title, userId
	) AS staging
ON
	COALESCE(staging.browserName, '') = COALESCE(replica.browserName, '') AND
	staging.canonicalUrl = replica.canonicalUrl AND
	staging.channelId = replica.channelId AND
	COALESCE(staging.city, '') = COALESCE(replica.city, '') AND
	COALESCE(staging.country, '') = COALESCE(replica.country, '') AND
	COALESCE(staging.deviceType, '') = COALESCE(replica.deviceType, '') AND
	staging.eventDate = replica.eventDate AND
	COALESCE(staging.platformName, '') = COALESCE(replica.platformName, '') AND
	COALESCE(staging.region, '') = COALESCE(replica.region, '') AND
	staging.sessionId = replica.sessionId AND
	staging.title = replica.title AND
	staging.userId = replica.userId
WHEN NOT MATCHED BY TARGET THEN
	INSERT (
		`bounce`,
		`browserName`,
		`canonicalUrl`,
		`channelId`,
		`city`,
		`country`,
		`ctaClicks`,
		`deviceType`,
		`directAccess`,
		`entrances`,
		`eventDate`,
		`exits`,
		`experimentId`,
		`indirectAccess`,
		`platformName`,
		`reads`,
		`region`,
		`sessionId`,
		`timeOnPage`,
		`title`,
		`userId`,
		`variantId`,
		`views`
	)
	VALUES (
		staging.bounce,
		staging.browserName,
		staging.canonicalUrl,
		staging.channelId,
		staging.city,
		staging.country,
		staging.ctaClicks,
		staging.deviceType,
		staging.directAccess,
		staging.entrances,
		staging.eventDate,
		staging.exits,
		staging.experimentId,
		staging.indirectAccess,
		staging.platformName,
		staging.reads,
		staging.region,
		staging.sessionId,
		staging.timeOnPage,
		staging.title,
		staging.userId,
		staging.variantId,
		staging.views
	)