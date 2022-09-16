CREATE OR REPLACE VIEW BQCustomAsset AS (
	WITH
		CustomAssetEvent AS (
			SELECT
				BQEvent.*,
				assetId.value AS assetId,
				ENCODE(
					SHA256(
						(
							assetId.value ||
							COALESCE(category.value, 'default') ||
							BQEvent.channelId
						)::BYTEA
					),
					'hex'
				) AS assetPrimaryKey,
				COALESCE(category.value, 'default') AS category,
				formEnabled.value AS formEnabled
			FROM
				BQEvent
				LEFT JOIN BQEventProperty AS assetId ON (
					BQEvent.id = assetid.id AND assetid.name = 'assetId'
				)
				LEFT JOIN BQEventProperty AS formEnabled ON (
					BQEvent.id = formEnabled.id AND
					formEnabled.name = 'formEnabled'
				)
				LEFT JOIN BQEventProperty AS category ON (
					BQEvent.id = category.id AND category.name = 'category'
				)
			WHERE
				BQEvent.applicationid = 'Custom' AND
				assetId.value IS NOT NULL
		),
		CustomAssetFinalizedEvent AS (
			SELECT
				CustomAssetEvent.*
			FROM
				CustomAssetEvent INNER JOIN BQSession ON
				    CustomAssetEvent.sessionId = BQSession.id
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
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
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
				                eventId = 'assetViewed'
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
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate
			FROM
				CustomAssetFinalizedEvent
			WHERE
				formenabled = 'true'
			GROUP BY
				assetPrimaryKey,
				normalizedEventDate
		),
		ReadTime AS (
			SELECT
				assetPrimaryKey,
				DATE_TRUNC('HOUR', maxEventDate) AS normalizedEventDate,
				SUM(readtime) AS readTime
			FROM
				(
					SELECT
						assetPrimaryKey,
						MAX(eventDate) filter (where eventid != 'assetViewed') maxEventDate,
						(
						    EXTRACT(EPOCH FROM MAX(eventDate) FILTER (WHERE eventId != 'assetViewed')) -
							EXTRACT(EPOCH FROM MIN(eventDate))
						) AS readTime,
						sessionId
					FROM
						CustomAssetFinalizedEvent
					GROUP BY
						assetPrimaryKey,
						sessionId
				 ) TMP
			WHERE
				maxEventDate IS NOT NULL
			GROUP BY
				assetprimarykey,
				normalizedEventDate
		),
		SubmissionTime AS (
			SELECT
				assetPrimaryKey,
				DATE_TRUNC('HOUR', minSubmissionDate) AS normalizedEventDate,
				SUM(submissionTime) AS submissionsTime
			FROM (
				SELECT
					assetprimarykey,
					MIN(eventDate) FILTER (WHERE eventid = 'assetSubmitted') AS minSubmissionDate,
					sessionid,
					(
						EXTRACT(
							EPOCH FROM MAX(eventDate) FILTER (WHERE eventid = 'assetSubmitted')
						) -
						EXTRACT(
							EPOCH FROM MIN(eventDate) FILTER (WHERE eventid = 'assetViewed')
						)
					) AS submissionTime
				FROM
				CustomAssetFinalizedEvent
				GROUP BY
					assetPrimaryKey,
					sessionId
			) TMP
			WHERE
				minSubmissionDate IS NOT NULL
			GROUP BY
				assetPrimaryKey,
				normalizedEventDate
		)
	SELECT
		COALESCE(abandonments.abandonments, 0) AS abandonments,
		metrics.assetPrimaryKey,
		metrics.channelId,
		metrics.clicks,
		metrics.downloads,
		metrics.normalizedEventDate AS eventDate,
		metrics.submissions,
		metrics.views,
		metrics.sessions,
		COALESCE(readTime.readtime, 0) * 1000 AS readTime,
		COALESCE(submissionTime.submissionstime, 0) * 1000 AS submissionsTime
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
);

COMMIT;

CREATE OR REPLACE VIEW BQDocumentLibrary AS (
	WITH
		CommentEvent AS (
			SELECT
				BQEvent.*,
				classPK.value as assetId
			FROM
				BQEvent
				LEFT JOIN BQEventProperty AS className ON (
						BQEvent.id = className.id AND className.name = 'className'
				)
				LEFT JOIN BQEventProperty AS classPK ON (
						BQEvent.id = classPK.id AND classPK.name = 'classPK'
				)
			WHERE
				BQEvent.applicationId = 'Comment' AND
				BQEvent.eventId = 'posted' AND
				className.value = 'com.liferay.document.library.kernel.model.DLFileEntry' AND
				classPK.value IS NOT NULL
		),
		DocumentEvent AS (
			SELECT
				BQEvent.*,
				fileEntryId.value AS assetId,
				documentTitle.value as assetTitle
			FROM
				BQEvent
			LEFT JOIN BQEventProperty AS fileEntryId ON (
				BQEvent.id = fileEntryId.id AND fileEntryId.name = 'fileEntryId'
			)
			LEFT JOIN BQEventProperty AS documentTitle ON (
				BQEvent.id = documentTitle.id AND documentTitle.name = 'title'
			)
			WHERE
				BQEvent.applicationId = 'Document' AND
				BQEvent.eventId IN ('documentDownloaded', 'documentPreviewed') AND
				fileEntryId.value IS NOT NULL
		),
		RatingEvent AS (
			SELECT
				BQEvent.*,
				classPK.value as assetId,
				CAST(score.value as FLOAT) as score
			FROM
				BQEvent
				LEFT JOIN BQEventProperty AS className ON (
					BQEvent.id = className.id AND className.name = 'className'
				)
				LEFT JOIN BQEventProperty AS classPK ON (
					BQEvent.id = classPK.id AND classPK.name = 'classPK'
				)
				LEFT JOIN BQEventProperty AS ratingType ON (
					BQEvent.id = ratingType.id AND ratingType.name = 'ratingType'
				)
				LEFT JOIN BQEventProperty AS score ON (
					BQEvent.id = score.id AND score.name = 'score'
				)
			WHERE
				BQEvent.applicationId = 'Ratings' AND
				BQEvent.eventId = 'VOTE' AND
				className.value = 'com.liferay.document.library.kernel.model.DLFileEntry' AND
				classPK.value IS NOT NULL AND
				ratingtype.value = 'stars'
		),
		DocumentComments AS (
			SELECT
				assetId,
				canonicalUrl,
				channelId,
				SUM(1) as comments,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				title as pageTitle,
				userId
			FROM
				CommentEvent
			GROUP BY
				assetId, browserName, canonicalUrl, channelId, city,
				country, normalizedEventDate, deviceType, platformName,
				region, title, userId
		),
		DocumentDownloadAndPreviews AS (
			SELECT
				assetId,
				assetTitle,
				browserName,
				canonicalUrl,
				channelId,
				city,
				SUM(
				    CASE
				        WHEN
				            eventId = 'documentDownloaded'
						THEN
				            1
					END
				) AS downloads,
				country,
				deviceType,
				DATE_TRUNC('HOUR', eventDate) as normalizedEventDate,
				platformName,
				SUM(
					CASE
						WHEN
							eventId = 'documentPreviewed'
						THEN
							1
					END
				) AS previews,
				region,
				title as pageTitle,
				userId
			FROM
				DocumentEvent
			GROUP BY
				assetId, assetTitle, browserName, canonicalUrl, channelId, city,
				country, deviceType, normalizedEventDate, platformName,
				region, title, userId
		),
		DocumentRatings AS (
			SELECT
				assetId,
				canonicalUrl,
				channelId,
				DATE_TRUNC('HOUR', eventDate) as normalizedEventDate,
				sum(1) AS ratings,
				SUM(score) AS ratingsScore,
				title AS pageTitle,
				userId
			FROM
				RatingEvent
			GROUP BY
				assetId, browserName, canonicalUrl, channelId, city,
				country, normalizedEventDate, deviceType, platformName,
				region, title, userId
		)
	SELECT
		DocumentDownloadAndPreviews.assetId,
		DocumentDownloadAndPreviews.assetTitle,
		DocumentDownloadAndPreviews.browserName,
		DocumentDownloadAndPreviews.canonicalUrl,
		DocumentDownloadAndPreviews.channelId,
		DocumentDownloadAndPreviews.city,
		DocumentComments.comments,
		DocumentDownloadAndPreviews.country,
		DocumentDownloadAndPreviews.deviceType,
		DocumentDownloadAndPreviews.downloads,
		DocumentDownloadAndPreviews.normalizedEventDate AS eventDate,
		DocumentDownloadAndPreviews.pageTitle,
		DocumentDownloadAndPreviews.platformName,
		DocumentDownloadAndPreviews.previews,
		DocumentRatings.ratings,
		DocumentRatings.ratingsScore,
		DocumentDownloadAndPreviews.region,
		DocumentDownloadAndPreviews.userId
	FROM
		DocumentDownloadAndPreviews
	LEFT JOIN DocumentRatings ON (
		DocumentDownloadAndPreviews.assetId = DocumentRatings.assetId AND
		DocumentDownloadAndPreviews.canonicalUrl = DocumentRatings.canonicalUrl AND
		DocumentDownloadAndPreviews.channelId = DocumentRatings.channelId AND
		DocumentDownloadAndPreviews.normalizedEventDate = DocumentRatings.normalizedEventDate AND
		DocumentDownloadAndPreviews.pageTitle = DocumentRatings.pageTitle AND
		DocumentDownloadAndPreviews.userId = DocumentRatings.userId
	)
	LEFT JOIN DocumentComments ON (
		DocumentDownloadAndPreviews.assetId = DocumentComments.assetId AND
		DocumentDownloadAndPreviews.canonicalUrl = DocumentComments.canonicalUrl AND
		DocumentDownloadAndPreviews.channelId = DocumentComments.channelId AND
		DocumentDownloadAndPreviews.normalizedEventDate = DocumentComments.normalizedEventDate AND
		DocumentDownloadAndPreviews.pageTitle = DocumentComments.pageTitle AND
		DocumentDownloadAndPreviews.userId = DocumentComments.userId
	)
);

COMMIT;

CREATE OR REPLACE VIEW BQFieldMapping AS (
	WITH
		CustomFieldMapping AS (
			SELECT
				'custom' AS context,
				dataSourceIds,
				CASE
					WHEN
						rowNumber = 1
					THEN
						name
					ELSE
						name || (rowNumber - 1)
				END displayName,
				displayType,
				CASE
					WHEN
						displaytype IN ('checkbox', 'radio', 'selection-list')
					THEN
						CONCAT(name, '_', datatype, '_array')
					ELSE
						CONCAT(name, '_', datatype)
				END fieldName,
				datatype as fieldType,
				modifiedDate,
				ownerType,
				CASE
					WHEN
						displaytype IN ('checkbox', 'radio', 'selection-list')
					THEN
						TRUE
					ELSE
						FALSE
				END repeatable_
			FROM (
				SELECT
					*,
					ROW_NUMBER() OVER (
						PARTITION BY
							name,
							ownerType
						ORDER BY
							modifiedDate ASC
					) AS rowNumber
				FROM (
					SELECT
						ARRAY_AGG(datasourceid) AS dataSourceIds,
						displayType,
						dataType,
						MAX(modifieddate) AS modifiedDate,
						name,
						CASE
							WHEN
								classname = 'com.liferay.portal.kernel.model.User'
							THEN
								'individual'
							WHEN
								classname = 'com.liferay.portal.kernel.model.Organization'
							THEN
								'organization'
						END ownerType
					FROM
						BQExpandoColumn
					GROUP BY
						name, dataType, displayType, ownerType
				) TMP1
			) TMP2
		),
		DemographicsFieldMapping AS (
			SELECT
				*
			FROM (
				VALUES
				('demographics', ARRAY[NULL::BIGINT], 'address', 'input-field', 'addresses', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'birthDate', 'input-field', 'birthday', 'date', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'email', 'input-field', 'emailAddress', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'givenName', 'input-field', 'firstName', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'gender', 'input-field', 'gender', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'jobTitle', 'input-field',  'jobTitle', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'familyName', 'input-field', 'lastName', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'additionalName', 'input-field', 'middleName', 'text', NOW(), 'individual', false),
				('demographics', ARRAY[NULL::BIGINT], 'telephone', 'input-field', 'phones', 'text', NOW(), 'individual', false)
			) AS TMP (
				context,
				dataSourceIds,
				displayName,
				displayType,
				fieldName,
				fieldType,
				modifiedDate,
				ownerType,
				repeatable_
			)
		)

	SELECT
		*
	FROM
		CustomFieldMapping
	UNION ALL
	SELECT
		*
	FROM
		DemographicsFieldMapping
);

COMMIT;

CREATE OR REPLACE VIEW BQJournal AS (
	WITH
		WebContentEvent AS (
			SELECT
				BQEvent.*,
				articleId.value AS assetId,
				articleTitle.value as assetTitle
			FROM
				BQEvent
			LEFT JOIN BQEventProperty AS articleId ON (
				BQEvent.id = articleId.id AND articleId.name = 'articleId'
			)
			LEFT JOIN BQEventProperty AS articleTitle ON (
				BQEvent.id = articleTitle.id AND articleTitle.name = 'title'
			)
			WHERE
				BQEvent.applicationId = 'WebContent' AND
				BQEvent.eventId = 'webContentViewed' AND
				articleId.value IS NOT NULL
		)
	SELECT
		assetId,
		assetTitle,
		browserName,
		canonicalUrl,
		channelId,
		city,
		country,
		DATE_TRUNC('HOUR', eventDate) as eventDate,
		deviceType,
		platformName,
		region,
		title as pageTitle,
		userId,
		SUM(1) as views
	FROM
		WebContentEvent
	GROUP BY
		assetId, assetTitle, browserName, canonicalUrl, channelId, city,
		country, DATE_TRUNC('HOUR', eventDate), deviceType, platformName,
		region, title, userId
);

COMMIT;

CREATE OR REPLACE VIEW BQPageReferrers AS (
	SELECT
		dataSourceId,
		channelId,
		DATE_TRUNC('HOUR', eventDate) AS eventDate,
		canonicalUrl,
		referrer,
		userId,
		hostname(referrer) AS referrerHost,
		canonical_url(referrer) AS referrerCanonicalUrl,
		acquisition_channel(referrer, url) AS acquisitionChannel,
		SUM(1) AS access
	FROM
		BQEvent
	WHERE
		applicationId = 'Page' AND
		eventId = 'pageViewed'
	GROUP BY
		dataSourceId, channelId, DATE_TRUNC('HOUR', eventDate), canonicalUrl,
		userId, referrer, acquisitionChannel, referrerHost, referrerCanonicalUrl
);

COMMIT;

CREATE OR REPLACE VIEW BQPage AS (
	WITH
		FinalizedEvent AS
			(
				SELECT
					BQEvent.*
				FROM
					BQEvent
				INNER JOIN
					BQSession ON
						BQEvent.sessionId = BQSession.id
			),
		Bounces AS
			(
				SELECT
					channelId,
					COUNT(*) AS count,
					SUM(CASE WHEN applicationId = 'Page' AND eventId = 'pageViewed' THEN 1 ELSE 0 END) as pageViews,
					sessionId,
					userId
				FROM
					FinalizedEvent
				WHERE
					eventId NOT IN ('blogViewed', 'documentPreviewed', 'formViewed', 'pageLoaded', 'pageUnloaded', 'webContentViewed')
				GROUP BY
					channelId,
					sessionId,
					userId
			),
		Entrances AS
			(
				SELECT
					canonicalUrl,
					channelId,
					rank AS entrance,
					DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
					sessionId,
					title,
					userId
				FROM (
					SELECT
						canonicalUrl,
						channelId,
						eventDate,
						ROW_NUMBER() OVER (
							PARTITION BY
								sessionId,
								channelId,
								userId
							ORDER BY
								eventDate ASC
						) AS rank,
						sessionId,
						title,
						userId
					FROM
						FinalizedEvent
				) AS EventEntrance
				WHERE
					rank = 1
			),
		Exits AS
			(
				SELECT
					canonicalUrl,
					channelId,
					rank AS exit,
					DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
					sessionId,
					title,
					userId
				FROM (
					SELECT
						canonicalUrl,
						channelId,
						eventDate,
						ROW_NUMBER() OVER (
							PARTITION BY
								sessionId,
								channelId,
								userId
							ORDER BY
								eventDate DESC
						) AS rank,
						sessionId,
						title,
						userId
					FROM
						FinalizedEvent
				) AS EventExit
				WHERE
					rank = 1
			),
		TimeOnPages AS
			(
				SELECT
					canonicalUrl,
					channelId,
					DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
					sessionId,
					SUM(EXTRACT(EPOCH FROM nextTime) - EXTRACT(EPOCH FROM eventDate)) * 1000 AS timeOnPage,
					title,
					userId
				FROM (
					SELECT
						canonicalUrl,
						channelId,
						eventDate,
						LEAD(eventDate) OVER (
							PARTITION BY
								sessionId,
								userId,
								channelId
							ORDER BY
								eventDate
						) AS nextTime,
						sessionId,
						title,
						userId
					FROM
						FinalizedEvent
				) AS EventTimeOnPage
				GROUP BY
					canonicalUrl,
					channelId,
					normalizedEventDate,
					sessionId,
					title,
					userId
			),
		Views AS
			(
				SELECT
					canonicalUrl,
					channelId,
					DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
					COUNT(*) as pageViews,
					sessionId,
					title,
					userId
				FROM
					FinalizedEvent
				WHERE
					applicationId = 'Page' AND
					eventId = 'pageViewed'
				GROUP BY
					canonicalUrl,
					channelId,
					normalizedEventDate,
					sessionId,
					title,
					userId
			)
	SELECT
		(CASE WHEN Bounces.count > 2 OR Bounces.pageViews > 1 THEN 0 ELSE 1 END) AS bounce,
		TimeOnPages.canonicalUrl AS canonicalUrl,
		TimeOnPages.channelId AS channelId,
		entrance,
		TimeOnPages.normalizedEventDate AS eventDate,
		exit,
		TimeOnPages.sessionId AS sessionId,
		TimeOnPages.timeOnPage AS timeOnPage,
		TimeOnPages.title AS title,
		TimeOnPages.userId AS userId,
		Views.pageViews AS views
	FROM
		TimeOnPages
	LEFT JOIN
		Bounces ON
			TimeOnPages.channelId = Bounces.channelId AND
			TimeOnPages.sessionId = Bounces.sessionId AND
			TimeOnPages.userId = Bounces.userId
	LEFT JOIN
		Entrances ON
			TimeOnPages.canonicalUrl = Entrances.canonicalUrl AND
			TimeOnPages.channelId = Entrances.channelId AND
			TimeOnPages.normalizedEventDate = Entrances.normalizedEventDate AND
			TimeOnPages.sessionId = Entrances.sessionId AND
			TimeOnPages.title = Entrances.title AND
			TimeOnPages.userId = Entrances.userId
	LEFT JOIN
		Exits ON
			TimeOnPages.canonicalUrl = Exits.canonicalUrl AND
			TimeOnPages.channelId = Exits.channelId AND
			TimeOnPages.normalizedEventDate = Exits.normalizedEventDate AND
			TimeOnPages.sessionId = Exits.sessionId AND
			TimeOnPages.title = Exits.title AND
			TimeOnPages.userId = Exits.userId
	LEFT JOIN
		Views ON
			TimeOnPages.canonicalUrl = Views.canonicalUrl AND
			TimeOnPages.channelId = Views.channelId AND
			TimeOnPages.normalizedEventDate = Views.normalizedEventDate AND
			TimeOnPages.sessionId = Views.sessionId AND
			TimeOnPages.title = Views.title AND
			TimeOnPages.userId = Views.userId
);