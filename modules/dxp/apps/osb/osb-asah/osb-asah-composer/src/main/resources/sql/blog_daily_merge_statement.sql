MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.blogdaily` AS replica
USING
	(
		SELECT
			assetId,
			assetTitle,
			browserName,
			canonicalUrl,
			channelId,
			city,
			SUM(clicks) AS clicks,
			SUM(comments) AS comments,
			country,
			deviceType,
			DATE_TRUNC(eventDate, DAY) AS eventDate,
			pageTitle,
			platformName,
			SUM(ratings) AS ratings,
			SUM(ratingsScore) AS ratingsScore,
			SUM(readTime) AS readTime,
			region,
			SUM(sessions) AS sessions,
			userId,
			SUM(views) AS views
		FROM
			`{{ dag.default_args['ac_project_id'] }}.bloghourly`
		WHERE
			eventDate BETWEEN '{{ data_interval_start.at(0, 0, 0) }}' AND '{{ data_interval_start.at(23, 59, 59) }}'
		GROUP BY
			assetId, assetTitle, browserName, canonicalUrl, channelId, city,
			country, eventDate, deviceType, pageTitle, platformName,
			region, userId
	) AS staging
ON (
	staging.assetId = replica.assetId AND
	staging.assetTitle = replica.assetTitle AND
	staging.browserName = replica.browserName AND
	staging.channelId = replica.channelId AND
	staging.city = replica.city AND
	staging.country = replica.country AND
	staging.deviceType = replica.deviceType AND
	staging.eventDate = replica.eventDate AND
	staging.pageTitle = replica.pageTitle AND
	staging.platformName = replica.platformName AND
	staging.region = replica.region AND
	staging.userId = replica.userId
)

WHEN NOT MATCHED THEN
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