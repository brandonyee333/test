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
			`$[AC_PROJECT_ID].event` AS Event
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS formId ON (
			Event.id = formId.id AND formId.name = 'formId' AND
			formId.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
			formId.value IS NOT NULL
		)
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS formTitle ON (
			Event.id = formTitle.id AND formTitle.name = 'title' AND
			formTitle.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
		)
		WHERE
			Event.applicationId = 'Form' AND
			Event.eventDate > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR) AND
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
			TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
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
	TIMESTAMP_TRUNC(eventDate, HOUR) AS eventDate,
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
	FormEvent.platformName,
	FormEvent.region,
	FormEvent.title AS pageTitle,
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
	TIMESTAMP_TRUNC(eventDate, HOUR) = FormSubmissionTimes.normalizedEventDate AND
	FormEvent.title = FormSubmissionTimes.pageTitle AND
	FormEvent.userId = FormSubmissionTimes.userId)
LEFT JOIN `$[AC_PROJECT_ID].session` AS Session ON
	FormEvent.sessionId = Session.id AND
	Session.sessionStart > TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 48 HOUR)
GROUP BY
	assetId, browserName, canonicalUrl, channelId, city, country, deviceType,
	eventDate, platformName, region, title, userId