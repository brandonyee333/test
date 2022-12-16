MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.identitychannelsummary` AS replica
USING
	(
		SELECT
			COUNT(*) AS activitiesCount,
			Event.channelId,
			MIN(Event.eventDate) AS createDate,
			TO_HEX(SHA256(Event.channelId || '-' || Event.userId)) AS id,
			Event.userId AS identityId,
			MAX(Identity.individualId) AS individualId,
			MAX(Event.eventDate) AS lastActivityDate,
			MAX(Event.eventDate) AS modifiedDate
		FROM
			`$[AC_PROJECT_ID].event` AS Event
		LEFT JOIN `$[AC_PROJECT_ID].identity` AS Identity ON (
			Event.userId = Identity.id
		)
		WHERE
			Event.eventDate BETWEEN '{{ data_interval_start.at(0, 0, 0) }}' AND '{{ data_interval_start.at(23, 59, 59) }}' AND
			Event.eventId IN (
				'blogClicked', 'blogViewed', 'documentDownloaded',
				'documentPreviewed', 'formSubmitted', 'formViewed', 'pageViewed',
				'posted', 'VOTE', 'webContentClicked', 'webContentViewed'
			)
		GROUP BY
			Event.channelId,
			Event.userId
	) AS staging
ON (
	staging.channelId = replica.channelId AND
	staging.identityId = replica.identityId
)

WHEN MATCHED THEN
	UPDATE SET
		replica.activitiesCount = replica.activitiesCount + staging.activitiesCount,
		replica.individualId = CASE WHEN staging.individualId IS NOT NULL THEN staging.individualId END,
		replica.lastActivityDate = staging.lastActivityDate,
		replica.modifiedDate = staging.modifiedDate
WHEN NOT MATCHED THEN
	INSERT (
		`activitiesCount`,
		`channelId`,
		`createDate`,
		`id`,
		`identityId`,
		`individualId`,
		`lastActivityDate`,
		`modifiedDate`
	)
	VALUES (
		staging.activitiesCount,
		staging.channelId,
		staging.createDate,
		staging.id,
		staging.identityId,
		staging.individualId,
		staging.lastActivityDate,
		staging.modifiedDate
	)