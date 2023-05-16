MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.journaldaily` AS replica
USING
	(
		WITH
			WebContentEvent AS (
				SELECT
					Event.* EXCEPT(assetId, assetTitle),
					articleId.value AS assetId,
					COALESCE(articleTitle.value, '') AS assetTitle
				FROM
					`${PROJECT_ID}.${asah_project_id}.event` AS Event
					LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS articleId ON (
						Event.id = articleId.id AND articleId.name = 'articleId'
					)
					LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS articleTitle ON (
						Event.id = articleTitle.id AND articleTitle.name = 'title'
					)
				WHERE
					Event.applicationId = 'WebContent' AND
					Event.eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR) AND
					Event.eventId = 'webContentViewed' AND
					articleId.value IS NOT NULL
			)
		SELECT
			assetId,
			COALESCE(assetTitle, '') AS assetTitle,
			browserName,
			canonicalUrl,
			CAST(channelId AS INT64) AS channelId,
			city,
			country,
			deviceType,
			TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS eventDate,
			title AS pageTitle,
			platformName,
			region,
			userId,
			SUM(1) AS views
		FROM
			WebContentEvent
		GROUP BY
			assetId, assetTitle, browserName, canonicalUrl, channelId, city,
			country, TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}'),
			deviceType, platformName, region, title, userId
	) AS staging
ON (
	staging.assetId = replica.assetId AND
	COALESCE(staging.assetTitle, '') = COALESCE(replica.assetTitle, '') AND
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
		staging.country,
		staging.deviceType,
		staging.eventDate,
		staging.pageTitle,
		staging.platformName,
		staging.region,
		staging.userId,
		staging.views
	)