MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.pagedaily` AS replica
USING
	(
		SELECT
			sum(bounce) bounce,
			canonicalUrl,
			channelId,
			sum(entrance) entrance,
			eventDate,
			sum(exit) exit,
			sessionId,
			TO_HEX(
				SHA256(
					CONCAT(channelId, '#', eventDate, '#', userId, '#', sessionId, '#', canonicalUrl)
				)
			) AS sha256HexId,
			sum(timeOnPage) timeOnPage,
			title,
			userId,
			sum(views) views
		FROM
		(
			SELECT
				bounce,
				canonicalUrl,
				channelId,
				entrance,
				DATE_TRUNC(eventDate, DAY) eventDate,
				exit,
				sessionId,
				timeOnPage,
				title,
				userId,
				views
			FROM `{{ dag.default_args['ac_project_id'] }}.pagehourly`
		) pagehourly
		GROUP BY
			channelId,
			canonicalUrl,
			title,
			userId,
			eventDate,
			sessionId
	) AS staging
ON
	staging.channelId = replica.channelId AND
	staging.eventDate = replica.eventDate AND
	staging.userId = replica.userId AND
	staging.sessionId = replica.sessionId AND
	staging.canonicalUrl = replica.canonicalUrl
WHEN NOT MATCHED THEN
	INSERT (
		`bounce`,
		`channelId`,
		`canonicalUrl`,
		`entrance`,
		`eventDate`,
		`exit`,
		`id`,
		`sessionId`,
		`timeOnPage`,
		`title`,
		`userId`,
		`views`
	)
	VALUES (
		staging.bounce,
		staging.channelId,
		staging.canonicalUrl,
		staging.entrance,
		staging.eventDate,
		staging.exit,
		staging.sha256HexId,
		staging.sessionId,
		staging.timeOnPage,
		staging.title,
		staging.userId,
		staging.views
	)