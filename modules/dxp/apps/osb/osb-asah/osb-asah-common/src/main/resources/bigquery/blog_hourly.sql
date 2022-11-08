WITH
	BlogEvent AS (
		SELECT
			Event.*,
			entryId.value AS assetId,
			blogTitle.value as assetTitle
		FROM
			`$[AC_PROJECT_ID].event` AS Event
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS entryId ON (
			Event.id = entryId.id AND entryId.name IN ('classPK', 'entryId')
		)
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS blogTitle ON (
			Event.id = blogTitle.id AND blogTitle.name = 'title'
		)
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS className ON (
            Event.id = className.id AND
            className.name = 'className' AND
            className.value = 'com.liferay.blogs.model.BlogsEntry'
        )
		WHERE
			((
                Event.applicationId = 'Blog' AND
                Event.eventId IN ('blogClicked', 'blogDepthReached', 'blogViewed')
			) OR (
                Event.applicationId = 'Ratings' AND
                className.value IS NOT NULL
            )) AND
			Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			blogTitle.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			entryId.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
            entryId.value IS NOT NULL
	),
	BlogFinalizedEvent AS (
		SELECT
			BlogEvent.*
		FROM
			BlogEvent
		INNER JOIN `$[AC_PROJECT_ID].session` AS Session ON
			BlogEvent.sessionId = Session.id
		WHERE
			Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
	),
	CommentEvent AS (
		SELECT
			Event.*,
			classPK.value as assetId
		FROM
			`$[AC_PROJECT_ID].event` AS Event
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS className ON (
			Event.id = className.id AND className.name = 'className'
		)
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS classPK ON (
			Event.id = classPK.id AND classPK.name = 'classPK'
		)
		WHERE
			Event.applicationId = 'Comment' AND
			Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			Event.eventId = 'posted' AND
			className.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			className.value = 'com.liferay.blogs.model.BlogsEntry' AND
			classPK.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			classPK.value IS NOT NULL
	),
	BlogComments AS (
		SELECT
			assetId,
			canonicalUrl,
			SUM(1) as comments,
			channelId,
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			title as pageTitle,
			userId
		FROM
			CommentEvent
		GROUP BY
			assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
	),
	RatingsEvent AS (
        SELECT
            Event.*,
            classPK.value as assetId,
            CAST(score.value AS FLOAT64) as score
        FROM
            `$[AC_PROJECT_ID].event` AS Event
        LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS className ON (
            Event.id = className.id AND className.name = 'className'
        )
        LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS classPK ON (
            Event.id = classPK.id AND classPK.name = 'classPK'
        )
        LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS ratingType ON (
            Event.id = ratingType.id AND ratingType.name = 'ratingType'
        )
        LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS score ON (
            Event.id = score.id AND score.name = 'score'
        )
        WHERE
            Event.applicationId = 'Ratings' AND
			Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			Event.eventId = 'VOTE' AND
			className.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			className.value = 'com.liferay.blogs.model.BlogsEntry' AND
			classPK.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			classPK.value IS NOT NULL AND
			ratingType.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			ratingType.value = 'stars' AND
			score.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
    ),
	BlogRatings AS (
		SELECT
			assetId,
			canonicalUrl,
			channelId,
			TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
			title AS pageTitle,
			SUM(1) AS ratings,
			score AS ratingsScore,
			userId
		FROM
            RatingsEvent AS RatingsEvent1
        WHERE RatingsEvent1.eventDate = (
            SELECT MAX(RatingsEvent2.eventDate) FROM RatingsEvent RatingsEvent2
            WHERE
                RatingsEvent1.assetId = RatingsEvent2.assetId AND
                RatingsEvent1.userid = RatingsEvent2.userid
        ) AND score >= 0
		GROUP BY
			assetId, canonicalUrl, channelId, normalizedEventDate, score,
			title, userId
	),
	BlogReadTimes AS (
		SELECT
			assetId,
			assetTitle,
			canonicalUrl,
			channelId,
			TIMESTAMP_TRUNC(maxEventDate, HOUR) AS normalizedEventDate,
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
			TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
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
	BlogViewsAndClicks.channelId,
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