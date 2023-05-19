MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.customassetdaily` AS replica
USING
	(
		WITH
			CustomAssetEvent AS (
				SELECT
					Event.*,
					assetId.value AS assetId,
					TO_HEX(
						SHA256(
							CONCAT(
								assetId.value ||
								COALESCE(category.value, 'default') ||
								Event.channelId
							)
						)
					) AS assetPrimaryKey,
					COALESCE(category.value, 'default') AS category,
					formEnabled.value AS formEnabled
				FROM
					`${PROJECT_ID}.${asah_project_id}.event` Event
				LEFT JOIN (
					SELECT
						id,
						value
					FROM
						`${PROJECT_ID}.${asah_project_id}.eventproperty`
					WHERE
						name = 'assetId' AND
						DATE(eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}')
				) AS assetId ON (
					Event.id = assetid.id
				)
				LEFT JOIN (
					SELECT
						id,
						value
					FROM
						`${PROJECT_ID}.${asah_project_id}.eventproperty`
					WHERE
						name = 'category' AND
						DATE(eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}')
				) AS category ON (
					Event.id = category.id
				)
				LEFT JOIN (
					SELECT
						id,
						value
					FROM
						`${PROJECT_ID}.${asah_project_id}.eventproperty`
					WHERE
						name = 'formEnabled' AND
						DATE(eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}')
				) AS formEnabled ON (
					Event.id = formEnabled.id
				)
				WHERE
					Event.applicationid = 'Custom' AND
					DATE(Event.eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}') AND
					assetId.value IS NOT NULL
			),
			CustomAssetFinalizedEvent AS (
				SELECT
					CustomAssetEvent.*
				FROM
					CustomAssetEvent INNER JOIN `${PROJECT_ID}.${asah_project_id}.session` Session ON
					CustomAssetEvent.sessionId = Session.id
				WHERE
					DATE(Session.sessionStart, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}')
			),
			Metrics AS (
				SELECT
					assetPrimaryKey,
					channelId,
					SUM(
						CASE
							WHEN
								eventId = 'assetClicked'
							THEN
								1
							ELSE
								0
						END
						) AS clicks,
					SUM(
						CASE
							WHEN
								eventId = 'assetDownloaded'
							THEN
								1
							ELSE
								0
							END
					) AS downloads,
					TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
					SUM(
						CASE
							WHEN
								eventId = 'assetSubmitted'
							THEN
								1
							ELSE
								0
							END
					) AS submissions,
					SUM(
						CASE
							WHEN
								eventId = 'assetViewed'
							THEN
								1
							ELSE
								0
							END
					) AS views,
					COUNT(DISTINCT(sessionId)) AS sessions
				FROM
					CustomAssetEvent
				GROUP BY
					assetPrimaryKey,
					channelId,
					normalizedEventDate
			),
			Abandoments AS (
				SELECT
					GREATEST(
						0,
						SUM(
							CASE
								WHEN
									eventId = 'assetViewed' AND formEnabled = 'true'
								THEN
									1
								ELSE
									0
							END
						) -
						SUM(
							CASE
								WHEN
									eventId = 'assetSubmitted'
								THEN
									1
								ELSE
									0
							END
						)
					) AS abandonments,
					assetPrimaryKey,
					TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate
				FROM
					CustomAssetFinalizedEvent
				GROUP BY
					assetPrimaryKey,
					normalizedEventDate
			),
			ReadTime AS (
				SELECT
					assetPrimaryKey,
					TIMESTAMP_TRUNC(maxEventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
					SUM(readtime) AS readTime
				FROM
					(
						SELECT
							assetPrimaryKey,
							MAX(CASE WHEN eventId != 'assetViewed' THEN eventDate END) AS maxEventDate,
							UNIX_SECONDS(MAX(CASE WHEN eventId != 'assetViewed' THEN eventDate END)) - UNIX_SECONDS(MIN(eventDate)) AS readTime,
							sessionId
						FROM
							CustomAssetFinalizedEvent
						GROUP BY
							assetPrimaryKey,
							sessionId
					) AS TMP
				WHERE
					maxEventDate IS NOT NULL
				GROUP BY
					assetprimarykey,
					normalizedEventDate
			),
			SubmissionTime AS (
				SELECT
					assetPrimaryKey,
					TIMESTAMP_TRUNC(minSubmissionDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
					SUM(submissionTime) AS submissionsTime
				FROM
					(
						SELECT
							assetprimarykey,
							MIN(CASE WHEN eventId = 'assetSubmitted' THEN eventDate END) AS minSubmissionDate,
							sessionid,
							UNIX_SECONDS(MAX(CASE WHEN eventId = 'assetSubmitted' THEN eventDate END)) - UNIX_SECONDS(MIN(CASE WHEN eventId = 'assetViewed' THEN eventDate END)) AS submissionTime
						FROM
							CustomAssetFinalizedEvent
						GROUP BY
							assetPrimaryKey,
							sessionId
					) AS TMP
				WHERE
					minSubmissionDate IS NOT NULL
				GROUP BY
					assetPrimaryKey,
					normalizedEventDate
			)
		SELECT
			COALESCE(abandonments.abandonments, 0) AS abandonments,
			metrics.assetPrimaryKey,
			CAST(metrics.channelId AS INT64) AS channelId,
			metrics.clicks,
			metrics.downloads,
			metrics.normalizedEventDate AS eventDate,
			metrics.submissions,
			metrics.views,
			metrics.sessions,
			COALESCE (readTime.readtime, 0) * 1000 AS readTime,
			COALESCE (submissionTime.submissionstime, 0) * 1000 AS submissionsTime
		FROM
			Metrics metrics
		LEFT JOIN Abandoments abandonments ON (
			metrics.assetPrimaryKey = abandonments.assetprimarykey AND
			metrics.normalizedEventDate = abandonments.normalizedEventDate
		)
		LEFT JOIN ReadTime readTime ON (
			metrics.assetPrimaryKey = readTime.assetPrimaryKey AND
			metrics.normalizedEventDate = readTime.normalizedEventDate
		)
		LEFT JOIN SubmissionTime submissionTime ON (
			metrics.assetPrimaryKey = submissionTime.assetPrimaryKey AND
			metrics.normalizedEventDate = submissionTime.normalizedEventDate
		)
	) AS staging
ON (
	staging.assetPrimaryKey = replica.assetPrimaryKey AND
	staging.channelId = replica.channelId AND
	staging.eventDate = replica.eventDate
)

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