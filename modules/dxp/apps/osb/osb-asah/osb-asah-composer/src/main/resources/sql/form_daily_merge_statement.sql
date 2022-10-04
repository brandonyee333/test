MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.formdaily` AS replica
USING
	(
		SELECT
			SUM(abandonments) AS abandonments,
			assetId,
			assetTitle,
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			DATE_TRUNC(eventDate, DAY) AS eventDate,
			pageTitle,
			platformName,
			region,
			SUM(submissions) AS submissions,
			SUM(submissionsTime) AS submissionsTime,
			userId,
			SUM(views) AS views
		FROM
			`{{ dag.default_args['ac_project_id'] }}.formhourly`
		WHERE
			eventDate BETWEEN '{{ data_interval_start.at(0, 0, 0) }}' AND '{{ data_interval_start.at(23, 59, 59) }}'
		GROUP BY
			assetId, assetTitle, browserName, canonicalUrl, channelId, city,
			country, deviceType, eventDate, pageTitle, platformName, region,
			userId
	) AS staging
ON (
	staging.assetId = replica.assetId AND
	staging.assetTitle = replica.assetTitle AND
	COALESCE(staging.browserName, '') = COALESCE(replica.browserName, '') AND
	staging.channelId = replica.channelId AND
	COALESCE(staging.city, '') = COALESCE(replica.city, '') AND
	COALESCE(staging.country, '') = COALESCE(replica.country, '') AND
	COALESCE(staging.deviceType, '') = COALESCE(replica.deviceType, '') AND
	staging.eventDate = replica.eventDate AND
	staging.pageTitle = replica.pageTitle AND
	COALESCE(staging.platformName, '') = COALESCE(replica.platformName, '') AND
	COALESCE(staging.region, '') = COALESCE(replica.region, '') AND
	staging.userId = replica.userId
)

WHEN NOT MATCHED THEN
	INSERT (
		`abandonments`,
		`assetId`,
		`assetTitle`,
		`browserName`,
		`canonicalUrl`,
		`channelId`,
		`city`,
		`country`,
		`deviceType`,
		`eventDate`,
		`pageTitle`,
		`platformName`,
		`region`,
		`submissions`,
		`submissionsTime`,
		`userId`,
		`views`
	)
	VALUES (
		staging.abandonments,
		staging.assetId,
		staging.assetTitle,
		staging.browserName,
		staging.canonicalUrl,
		staging.channelId,
		staging.city,
		staging.country,
		staging.deviceType,
		staging.eventDate,
		staging.pageTitle,
		staging.platformName,
		staging.region,
		staging.submissions,
		staging.submissionsTime,
		staging.userId,
		staging.views
	)