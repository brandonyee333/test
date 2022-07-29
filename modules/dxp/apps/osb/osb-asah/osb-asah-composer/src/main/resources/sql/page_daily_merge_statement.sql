MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.pagedaily` AS replica
USING
	(
		SELECT
			*,
			TO_HEX(
				SHA256(
					CONCAT(channelId, '#', eventDate, '#', userId, '#', sessionId, '#', canonicalUrl)
				)
			) AS sha256HexId
		FROM `{{ dag.default_args['ac_project_id'] }}.pagehourly`
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
		`sessionDuration`,
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
		staging.sessionDuration,
		staging.sessionId,
		staging.timeOnPage,
		staging.title,
		staging.userId,
		staging.views
	)