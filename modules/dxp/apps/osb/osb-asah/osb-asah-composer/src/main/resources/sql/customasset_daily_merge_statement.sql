MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.customassetdaily` AS replica
USING
	(
		SELECT
			sum(abandonments) as abandonments,
			assetPrimaryKey,
			channelId,
			sum(clicks) as clicks,
			sum(downloads) as downloads,
			DATE_TRUNC(eventDate, DAY) as eventDate,
			sum(readTime) as readTime,
			sum(sessions) as sessions,
			sum(submissions) as submissions,
			sum(submissionsTime) as submissionsTime,
			sum(views) as views
		FROM
			`{{ dag.default_args['ac_project_id'] }}.customassethourly`
		WHERE
			eventDate BETWEEN '{{ data_interval_start.at(0, 0, 0) }}' AND '{{ data_interval_start.at(23, 59, 59) }}'
		GROUP BY
			assetPrimaryKey, channelId, eventDate
	) AS staging
ON
	staging.assetPrimaryKey = replica.assetPrimaryKey AND
	staging.channelId = replica.channelId AND
	staging.eventDate = replica.eventDate
WHEN NOT MATCHED THEN
	INSERT (
		`abandonments`,
		`assetPrimaryKey`,
		`channelId`,
		`clicks`,
		`downloads`,
		`eventDate`,
		`readTime`,
		`sessions`,
		`submissions`,
		`submissionsTime`,
		`views`
	)
	VALUES (
		staging.abandonments,
		staging.assetPrimaryKey,
		staging.channelId,
		staging.clicks,
		staging.downloads,
		staging.eventDate,
		staging.readTime,
		staging.sessions,
		staging.submissions,
		staging.submissionsTime,
		staging.views
	)