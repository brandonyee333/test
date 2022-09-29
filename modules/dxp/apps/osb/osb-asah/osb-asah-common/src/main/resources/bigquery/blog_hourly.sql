WITH
	BlogEvent AS (
		SELECT
			Event.*,
			entryId.value AS assetId,
			blogTitle.value as assetTitle
		FROM
			`$[AC_PROJECT_ID].event` AS Event
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS entryId ON (
			Event.id = entryId.id AND entryId.name = 'entryId'
		)
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS blogTitle ON (
			Event.id = blogTitle.id AND blogTitle.name = 'title'
		)
		WHERE
			Event.applicationId = 'Blog' AND
			Event.eventId IN ('blogClicked', 'blogDepthReached', 'blogViewed') AND
			entryId.value IS NOT NULL
	),
	BlogFinalizedEvent AS (
		SELECT
			BlogEvent.*
		FROM
			BlogEvent
		INNER JOIN `$[AC_PROJECT_ID].session` AS Session ON
			BlogEvent.sessionId = Session.id
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
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			title as pageTitle,
			userId
		FROM
			CommentEvent
		GROUP BY
			assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
	),
	BlogRatings as (
		SELECT
			assetId,
			canonicalUrl,
			channelId,
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			title AS pageTitle,
			SUM(1) AS ratings,
			SUM(score) AS ratingsScore,
			userId
		FROM
			RatingsEvent
		GROUP BY
			assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
	),
	BlogReadTimes as (
		SELECT
			assetId,
			assetTitle,
			canonicalUrl,
			channelId,
			TIMESTAMP_TRUNC(maxEventDate, HOUR) AS normalizedEventDate,
			title as pageTitle,
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
			) TMP
		WHERE
			maxEventDate IS NOT NULL
		GROUP BY
			assetId, assetTitle, canonicalUrl, channelId, normalizedEventDate,
			title, userId
	),
	BlogViewsAndClicks as (
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
					END
			) AS clicks,
			city,
			country,
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			deviceType,
			platformName,
			region,
			COUNT(DISTINCT(sessionId)) AS sessions,
			title as pageTitle,
			userId,
			SUM(
				CASE
					WHEN
						eventId = 'blogViewed'
					THEN
						1
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
	BlogViewsAndClicks.normalizedEventDate as eventDate,
	BlogViewsAndClicks.pageTitle,
	BlogViewsAndClicks.platformName,
	BlogRatings.ratings,
	BlogRatings.ratingsScore,
	BlogReadTimes.readTime * 1000 as readTime,
	BlogViewsAndClicks.region,
	BlogViewsAndClicks.sessions,
	BlogViewsAndClicks.userId,
	COALESCE(BlogViewsAndClicks.views, 0) as views
FROM
	 BlogViewsAndClicks
LEFT JOIN BlogComments ON (
	 BlogViewsAndClicks.assetId = BlogComments.assetId and
	 BlogViewsAndClicks.canonicalUrl = BlogComments.canonicalUrl and
	 BlogViewsAndClicks.channelId = BlogComments.channelId and
	 BlogViewsAndClicks.normalizedEventDate = BlogComments.normalizedEventDate and
	 BlogViewsAndClicks.pageTitle = BlogComments.pageTitle and
	 BlogViewsAndClicks.userId = BlogComments.userId
)
LEFT JOIN BlogRatings ON (
	BlogViewsAndClicks.assetId = BlogRatings.assetId and
	BlogViewsAndClicks.canonicalUrl = BlogRatings.canonicalUrl and
	BlogViewsAndClicks.channelId = BlogRatings.channelId and
	BlogViewsAndClicks.normalizedEventDate = BlogRatings.normalizedEventDate and
	BlogViewsAndClicks.pageTitle = BlogRatings.pageTitle and
	BlogViewsAndClicks.userId = BlogRatings.userId
)
LEFT JOIN BlogReadTimes ON (
	BlogViewsAndClicks.assetId = BlogReadTimes.assetId and
	BlogViewsAndClicks.assetTitle = BlogReadTimes.assetTitle and
	BlogViewsAndClicks.canonicalUrl = BlogReadTimes.canonicalUrl and
	BlogViewsAndClicks.channelId = BlogReadTimes.channelId and
	BlogViewsAndClicks.normalizedEventDate = BlogReadTimes.normalizedEventDate and
	BlogViewsAndClicks.pageTitle = BlogReadTimes.pageTitle and
	BlogViewsAndClicks.userId = BlogReadTimes.userId
)