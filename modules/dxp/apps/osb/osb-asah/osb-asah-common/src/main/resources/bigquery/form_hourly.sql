WITH
	FormEvent AS (
		SELECT
			Event.*,
			formId.value AS assetId,
			formTitle.value as assetTitle
		FROM
			`$[AC_PROJECT_ID].event` AS Event
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS formId ON (
			Event.id = formId.id AND formId.name = 'formId'
		)
		LEFT JOIN `$[AC_PROJECT_ID].eventproperty` AS formTitle ON (
			Event.id = formTitle.id AND formTitle.name = 'title'
		)
		WHERE
			Event.applicationId = 'Form' AND
			Event.eventId IN ('formSubmitted', 'formViewed')
	),
	FormFinalizedEvent AS (
		SELECT
			FormEvent.*
		FROM
			FormEvent INNER JOIN `$[AC_PROJECT_ID].session` AS Session ON
				FormEvent.sessionId = Session.id
	),
	FormAbandonments AS (
		SELECT
			GREATEST(
				0,
				SUM(
					CASE
						WHEN
							eventId = 'formViewed'
						THEN
							1
						ELSE
							0
					END
				) -
				SUM(
					CASE
						WHEN
							eventId = 'formSubmitted'
						THEN
							1
						ELSE
							0
					END
				)
			) AS abandonments,
			assetId,
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			platformName,
			region,
			title as pageTitle,
			userId
		FROM
			FormFinalizedEvent
		GROUP BY
			assetId, browserName, canonicalUrl, channelId, city,
			country, deviceType, normalizedEventDate, platformName,
			region, title, userId
	),
	FormSubmissions AS (
		SELECT
			assetId,
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			platformName,
			region,
			SUM(1) AS submissions,
			title as pageTitle,
			userId
		FROM
			FormEvent
		WHERE
				eventId = 'formSubmitted'
		GROUP BY
			assetId, browserName, canonicalUrl, channelId, city,
			country, deviceType, normalizedEventDate, platformName,
			region, title, userId
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
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			platformName,
			region,
			title as pageTitle,
			userId ,
			SUM(UNIX_SECONDS(eventDate) - UNIX_SECONDS(previousFormViewedEventDate)) submissionsTime
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
		 ) TMP
		WHERE
			eventId = 'formSubmitted'
		GROUP BY
			assetId, browserName, canonicalUrl, channelId, city,
			country, deviceType, normalizedEventDate, platformName,
			region, title, userId
	),
	FormViews AS (
		SELECT
			assetId,
			assetTitle,
			browserName,
			canonicalUrl,
			channelId,
			city,
			country,
			deviceType,
			TIMESTAMP_TRUNC(eventDate, HOUR) as normalizedEventDate,
			platformName,
			region,
			title as pageTitle,
			userId,
			SUM(1) AS views
		FROM
			FormEvent
		WHERE
			eventId = 'formViewed'
		GROUP BY
			assetId, assetTitle, browserName, canonicalUrl, channelId, city,
			country, deviceType, normalizedEventDate, platformName,
			region, title, userId
	)
SELECT
	FormAbandonments.abandonments,
	FormViews.assetId,
	FormViews.assetTitle,
	FormViews.browserName,
	FormViews.canonicalUrl,
	FormViews.channelId,
	FormViews.city,
	FormViews.country,
	FormViews.deviceType,
	FormViews.normalizedEventDate AS eventDate,
	FormViews.pageTitle,
	FormViews.platformName,
	FormViews.region,
	FormSubmissions.submissions,
	FormSubmissionTimes.submissionsTime * 1000 as submissionsTime,
	FormViews.userId,
	FormViews.views
FROM
	FormViews
LEFT JOIN FormAbandonments ON (
	FormViews.assetId = FormAbandonments.assetId AND
	FormViews.canonicalUrl = FormAbandonments.canonicalUrl AND
	FormViews.channelId = FormAbandonments.channelId AND
	FormViews.normalizedEventDate = FormAbandonments.normalizedEventDate AND
	FormViews.pageTitle = FormAbandonments.pageTitle AND
	FormViews.userId = FormAbandonments.userId
)
LEFT JOIN FormSubmissions ON (
	FormViews.assetId = FormSubmissions.assetId AND
	FormViews.canonicalUrl = FormSubmissions.canonicalUrl AND
	FormViews.channelId = FormSubmissions.channelId AND
	FormViews.normalizedEventDate = FormSubmissions.normalizedEventDate AND
	FormViews.pageTitle = FormSubmissions.pageTitle AND
	FormViews.userId = FormSubmissions.userId
)
LEFT JOIN FormSubmissionTimes ON (
	FormViews.assetId = FormSubmissionTimes.assetId AND
	FormViews.canonicalUrl = FormSubmissionTimes.canonicalUrl AND
	FormViews.channelId = FormSubmissionTimes.channelId AND
	FormViews.normalizedEventDate = FormSubmissionTimes.normalizedEventDate AND
	FormViews.pageTitle = FormSubmissionTimes.pageTitle AND
	FormViews.userId = FormSubmissionTimes.userId
)