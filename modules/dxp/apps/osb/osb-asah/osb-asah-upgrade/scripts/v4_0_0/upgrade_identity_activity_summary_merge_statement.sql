MERGE INTO
	`${PROJECT_ID}.${ASAH_PROJECT_ID}.identityactivitysummary` AS replica
USING
	(
		SELECT
			COUNT(*) AS activitiesCount,
			Event.channelId,
			Event.dataSourceId,
			Event.eventId,
			MIN(Event.eventDate) AS firstActivityDate,
			Event.userId AS identityId,
			MAX(Identity.individualId) AS individualId,
			MAX(Event.eventDate) AS lastActivityDate
		FROM
			`${PROJECT_ID}.${ASAH_PROJECT_ID}.event` AS Event
		LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.identity` AS Identity ON (
			Event.userId = Identity.id
		)
		WHERE
			Event.eventDate < TIMESTAMP_TRUNC(CURRENT_TIMESTAMP(), DAY)
		GROUP BY
			Event.channelId,
			Event.dataSourceId,
			Event.eventId,
			Event.userId
	) AS staging
ON (
	staging.channelId = replica.channelId AND
	staging.dataSourceId = replica.dataSourceId AND
	staging.eventId = replica.eventId AND
	staging.identityId = replica.identityId
)

WHEN NOT MATCHED THEN
	INSERT (
		`activitiesCount`,
		`channelId`,
		`dataSourceId`,
		`eventId`,
		`firstActivityDate`,
		`identityId`,
		`individualId`,
		`lastActivityDate`
	)
	VALUES (
		staging.activitiesCount,
		staging.channelId,
		staging.dataSourceId,
		staging.eventId,
		staging.firstActivityDate,
		staging.identityId,
		staging.individualId,
		staging.lastActivityDate
	)