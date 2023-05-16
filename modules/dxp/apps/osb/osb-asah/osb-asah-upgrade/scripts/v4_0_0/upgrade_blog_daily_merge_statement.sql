MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.blogdaily` AS replica
USING
	(
		WITH BlogEvent AS (
			SELECT
				Event.* EXCEPT(assetId, assetTitle),
				entryId.value AS assetId,
				blogTitle.value as assetTitle
			FROM
				`${PROJECT_ID}.${asah_project_id}.event` AS Event
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS entryId ON (
				Event.id = entryId.id AND entryId.name = 'entryId' AND
				entryId.value IS NOT NULL AND entryId.value != ''
			)
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS blogTitle ON (
				Event.id = blogTitle.id AND blogTitle.name = 'title'
			)
			WHERE
				Event.applicationId = 'Blog' AND
				Event.eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
				Event.eventId IN ('blogClicked', 'blogDepthReached', 'blogViewed') AND
				entryId.value IS NOT NULL AND entryId.value != ''
		),
		BlogFinalizedEvent AS (
			SELECT
				BlogEvent.*
			FROM
				BlogEvent
			INNER JOIN `${PROJECT_ID}.${asah_project_id}.session` AS Session ON
				BlogEvent.sessionId = Session.id
			WHERE
				Session.sessionStart < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
		),
		CommentEvent AS (
			SELECT
				Event.*,
				classPK.value as assetId
			FROM
				`${PROJECT_ID}.${asah_project_id}.event` AS Event
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS className ON (
				Event.id = className.id AND className.name = 'className'
			)
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS classPK ON (
				Event.id = classPK.id AND classPK.name = 'classPK'
			)
			WHERE
				Event.applicationId = 'Comment' AND
				Event.eventId = 'posted' AND
				className.value = 'com.liferay.blogs.model.BlogsEntry' AND
				classPK.value IS NOT NULL
		),
		RatingsEvent AS (
			SELECT
				Event.*,
				classPK.value as assetId,
				CAST(score.value AS FLOAT64) as score
			FROM
				`${PROJECT_ID}.${asah_project_id}.event` AS Event
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS className ON (
				Event.id = className.id AND className.name = 'className'
			)
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS classPK ON (
				Event.id = classPK.id AND classPK.name = 'classPK'
			)
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS ratingType ON (
				Event.id = ratingType.id AND ratingType.name = 'ratingType'
			)
			LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS score ON (
				Event.id = score.id AND score.name = 'score'
			)
			WHERE
				Event.applicationId = 'Ratings' AND
				Event.eventId = 'VOTE' AND
				className.value = 'com.liferay.blogs.model.BlogsEntry' AND
				classPK.value IS NOT NULL AND
				ratingtype.value = 'stars'
		),
		BlogComments AS (
			SELECT
				assetId,
				canonicalUrl,
				SUM(1) as comments,
				channelId,
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') as normalizedEventDate,
				title as pageTitle,
				userId
			FROM
				CommentEvent
			GROUP BY
				assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
		),
		BlogRatings AS (
			SELECT
				assetId,
				canonicalUrl,
				channelId,
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') as normalizedEventDate,
				title AS pageTitle,
				SUM(1) AS ratings,
				SUM(score) AS ratingsScore,
				userId
			FROM
				RatingsEvent
			GROUP BY
				assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
		),
		BlogReadTimes AS (
			SELECT
				assetId,
				assetTitle,
				canonicalUrl,
				channelId,
				TIMESTAMP_TRUNC(maxEventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
				title AS pageTitle,
				SUM(readtime) AS readTime,
				userId
			FROM
				(
					SELECT
						assetId,
						assetTitle,
						canonicalUrl,
						channelId,
						MAX(CASE WHEN eventId != 'blogViewed' THEN eventDate END) AS maxEventDate,
						(
							UNIX_SECONDS(MAX(CASE WHEN eventId != 'blogViewed' THEN eventDate END)) -
							UNIX_SECONDS(MIN(eventDate))
						) AS readTime,
						sessionId,
						title,
						userId
					FROM
						BlogFinalizedEvent
					GROUP BY
						assetId, assetTitle, canonicalUrl, channelId, sessionId,
						title, userId
				) AS TMP
			WHERE
				maxEventDate IS NOT NULL
			GROUP BY
				assetId, assetTitle, canonicalUrl, channelId, normalizedEventDate,
				title, userId
		),
		BlogViewsAndClicks AS (
			SELECT
				assetId,
				assetTitle,
				browserName,
				canonicalUrl,
				channelId,
				SUM(
					CASE
						WHEN
							eventId = 'blogClicked'
						THEN
							1
						ELSE
							0
						END
				) AS clicks,
				city,
				country,
				TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
				deviceType,
				platformName,
				region,
				COUNT(DISTINCT(sessionId)) AS sessions,
				title AS pageTitle,
				userId,
				SUM(
					CASE
						WHEN
							eventId = 'blogViewed'
						THEN
							1
						ELSE
							0
						END
				) AS views
			FROM
				BlogEvent
			GROUP BY
				assetId, assetTitle, browserName, canonicalUrl, channelId, city,
				country, normalizedEventDate, deviceType, platformName,
				region, title, userId
		)
		SELECT
			BlogViewsAndClicks.assetId,
			BlogViewsAndClicks.assetTitle,
			BlogViewsAndClicks.browserName,
			BlogViewsAndClicks.canonicalUrl,
			CAST(BlogViewsAndClicks.channelId AS INT64) AS channelId,
			BlogViewsAndClicks.city,
			COALESCE(BlogViewsAndClicks.clicks, 0) AS clicks,
			COALESCE(BlogComments.comments, 0) AS comments,
			BlogViewsAndClicks.country,
			BlogViewsAndClicks.deviceType,
			BlogViewsAndClicks.normalizedEventDate AS eventDate,
			BlogViewsAndClicks.pageTitle,
			BlogViewsAndClicks.platformName,
			BlogRatings.ratings,
			BlogRatings.ratingsScore,
			BlogReadTimes.readTime * 1000 AS readTime,
			BlogViewsAndClicks.region,
			BlogViewsAndClicks.sessions,
			BlogViewsAndClicks.userId,
			COALESCE(BlogViewsAndClicks.views, 0) AS views
		FROM
			BlogViewsAndClicks
		LEFT JOIN BlogComments ON (
			BlogViewsAndClicks.assetId = BlogComments.assetId AND
			BlogViewsAndClicks.canonicalUrl = BlogComments.canonicalUrl AND
			BlogViewsAndClicks.channelId = BlogComments.channelId AND
			BlogViewsAndClicks.normalizedEventDate = BlogComments.normalizedEventDate AND
			BlogViewsAndClicks.pageTitle = BlogComments.pageTitle AND
			BlogViewsAndClicks.userId = BlogComments.userId
		)
		LEFT JOIN BlogRatings ON (
			BlogViewsAndClicks.assetId = BlogRatings.assetId AND
			BlogViewsAndClicks.canonicalUrl = BlogRatings.canonicalUrl AND
			BlogViewsAndClicks.channelId = BlogRatings.channelId AND
			BlogViewsAndClicks.normalizedEventDate = BlogRatings.normalizedEventDate AND
			BlogViewsAndClicks.pageTitle = BlogRatings.pageTitle AND
			BlogViewsAndClicks.userId = BlogRatings.userId
		)
		LEFT JOIN BlogReadTimes ON (
			BlogViewsAndClicks.assetId = BlogReadTimes.assetId AND
			BlogViewsAndClicks.assetTitle = BlogReadTimes.assetTitle AND
			BlogViewsAndClicks.canonicalUrl = BlogReadTimes.canonicalUrl AND
			BlogViewsAndClicks.channelId = BlogReadTimes.channelId AND
			BlogViewsAndClicks.normalizedEventDate = BlogReadTimes.normalizedEventDate AND
			BlogViewsAndClicks.pageTitle = BlogReadTimes.pageTitle AND
			BlogViewsAndClicks.userId = BlogReadTimes.userId
		)
	) AS staging
ON
	replica.assetId = staging.assetId AND
	replica.channelId = staging.channelId
WHEN NOT MATCHED BY TARGET THEN
	INSERT (
		`assetId`,
		`assetTitle`,
		`browserName`,
		`canonicalUrl`,
		`channelId`,
		`city`,
		`clicks`,
		`comments`,
		`country`,
		`deviceType`,
		`eventDate`,
		`pageTitle`,
		`platformName`,
		`ratings`,
		`ratingsScore`,
		`readTime`,
		`region`,
		`sessions`,
		`userId`,
		`views`
	)
	VALUES (
		staging.assetId,
		staging.assetTitle,
		staging.browserName,
		staging.canonicalUrl,
		staging.channelId,
		staging.city,
		staging.clicks,
		staging.comments,
		staging.country,
		staging.deviceType,
		staging.eventDate,
		staging.pageTitle,
		staging.platformName,
		staging.ratings,
		staging.ratingsScore,
		staging.readTime,
		staging.region,
		staging.sessions,
		staging.userId,
		staging.views
	)