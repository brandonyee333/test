MERGE INTO
	`${PROJECT_ID}.${asah_project_id}.formdaily` AS replica
USING
	(
		WITH
			FormEvent AS (
				SELECT
					formId.value AS assetId,
					formTitle.value AS assetTitle,
					Event.browserName,
					Event.canonicalUrl,
					Event.channelId,
					Event.city,
					Event.country,
					Event.deviceType,
					Event.eventDate,
					Event.eventId,
					Event.platformName,
					Event.region,
					Event.sessionId,
					Event.title,
					Event.userId
				FROM
					`${PROJECT_ID}.${asah_project_id}.event` AS Event
				LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS formId ON (
					Event.id = formId.id AND formId.name = 'formId' AND
					DATE(formId.eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}') AND
					formId.value IS NOT NULL
				)
				LEFT JOIN `${PROJECT_ID}.${asah_project_id}.eventproperty` AS formTitle ON (
					Event.id = formTitle.id AND formTitle.name = 'title' AND
					DATE(formTitle.eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}')
				)
				WHERE
					Event.applicationId = 'Form' AND
					DATE(Event.eventDate, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}') AND
					Event.eventId IN ('formSubmitted', 'formViewed') AND
					formId.value IS NOT NULL
			),
			FormSubmissionTimes AS (
				SELECT
					assetId,
					browserName,
					canonicalUrl,
					channelId,
					city,
					country,
					deviceType,
					TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS normalizedEventDate,
					platformName,
					region,
					title AS pageTitle,
					userId ,
					SUM(UNIX_SECONDS(eventDate) - UNIX_SECONDS(previousFormViewedEventDate)) * 1000 submissionsTime
				FROM (
					SELECT
						*,
						MAX(CASE WHEN eventId = 'formViewed' THEN eventDate END)
						OVER (
							PARTITION BY
								assetId, channelId, canonicalUrl, sessionId, title
							ORDER BY
								eventDate ASC
							ROWS UNBOUNDED PRECEDING
						) AS previousFormViewedEventDate
					FROM
						FormEvent
				 ) AS TMP
				WHERE
					eventId = 'formSubmitted'
				GROUP BY
					assetId, browserName, canonicalUrl, channelId, city,
					country, deviceType, normalizedEventDate, platformName,
					region, title, userId
			)
		SELECT
			GREATEST(
				0,
				SUM(
					CASE
						WHEN
							eventId = 'formViewed' AND Session.id IS NOT NULL
						THEN
							1
						ELSE
							0
					END
				) -
				SUM(
					CASE
						WHEN
							eventId = 'formSubmitted' AND Session.id IS NOT NULL
						THEN
							1
						ELSE
							0
					END
				)
			) AS abandonments,
			FormEvent.assetId,
			COALESCE(MAX(FormEvent.assetTitle), '') AS assetTitle,
			FormEvent.browserName,
			FormEvent.canonicalUrl,
			CAST(FormEvent.channelId AS INT64) AS channelId,
			FormEvent.city,
			FormEvent.country,
			FormEvent.deviceType,
			TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') AS eventDate,
			SUM(
				CASE
					WHEN
						eventId = 'formViewed' AND
						Session.id IS NOT NULL
					THEN
						1
					ELSE
						0
				END
			) AS finalizedFormViews,
			FormEvent.title AS pageTitle,
			FormEvent.platformName,
			FormEvent.region,
			SUM(CASE WHEN eventId = 'formSubmitted' THEN 1 END) AS submissions,
			MAX(FormSubmissionTimes.submissionsTime) AS submissionsTime,
			FormEvent.userId,
			SUM(CASE WHEN eventId = 'formViewed' THEN 1 END) AS views
		FROM
			FormEvent
		LEFT JOIN FormSubmissionTimes ON (
			FormEvent.assetId = FormSubmissionTimes.assetId AND
			FormEvent.canonicalUrl = FormSubmissionTimes.canonicalUrl AND
			FormEvent.channelId = FormSubmissionTimes.channelId AND
			TIMESTAMP_TRUNC(eventDate, DAY, '${asah_project_time_zone}') = FormSubmissionTimes.normalizedEventDate AND
			FormEvent.title = FormSubmissionTimes.pageTitle AND
			FormEvent.userId = FormSubmissionTimes.userId)
		LEFT JOIN `${PROJECT_ID}.${asah_project_id}.session` AS Session ON
			FormEvent.sessionId = Session.id AND
			DATE(Session.sessionStart, '${asah_project_time_zone}') < CURRENT_DATE('${asah_project_time_zone}')
		GROUP BY
			assetId, browserName, canonicalUrl, channelId, city, country, deviceType,
			eventDate, platformName, region, title, userId
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
		`finalizedFormViews`,
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
		staging.finalizedFormViews,
		staging.pageTitle,
		staging.platformName,
		staging.region,
		staging.submissions,
		staging.submissionsTime,
		staging.userId,
		staging.views
	)