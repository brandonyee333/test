MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.pagedaily` AS replica
USING
	(
		SELECT
			SUM(bounce) AS bounce,
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			SUM(directAccess) AS directAccess,
			SUM(entrances) AS entrances,
			DATE_TRUNC(eventDate, DAY) AS eventDate,
			SUM(exits) exits,
			SUM(indirectAccess) AS indirectAccess,
			platformName,
			region,
			sessionId,
			SUM(timeOnPage) AS timeOnPage,
			title,
			userId,
			SUM(views) AS views
		FROM
			`{{ dag.default_args['ac_project_id'] }}.pagehourly`
		WHERE
			DATE(eventDate, '{{ dag.default_args['ac_project_time_zone_id'] }}') = '{{ data_interval_start.to_date_string() }}' AND
			sessionId IS NOT NULL
		GROUP BY
			browserName, canonicalUrl, channelId, city, country, deviceType,
			eventDate, platformName, region, sessionId, title, userId
	) AS staging
ON (
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
)

WHEN NOT MATCHED THEN
	INSERT (
		`bounce`,
		`browserName`,
		`canonicalUrl`,
		`channelId`,
		`city`,
		`country`,
		`deviceType`,
		`directAccess`,
		`entrances`,
		`eventDate`,
		`exits`,
		`indirectAccess`,
		`platformName`,
		`region`,
		`sessionId`,
		`timeOnPage`,
		`title`,
		`userId`,
		`views`
	)
	VALUES (
		staging.bounce,
		staging.browserName,
		staging.canonicalUrl,
		staging.channelId,
		staging.city,
		staging.country,
		staging.deviceType,
		staging.directAccess,
		staging.entrances,
		staging.eventDate,
		staging.exits,
		staging.indirectAccess,
		staging.platformName,
		staging.region,
		staging.sessionId,
		staging.timeOnPage,
		staging.title,
		staging.userId,
		staging.views
	)