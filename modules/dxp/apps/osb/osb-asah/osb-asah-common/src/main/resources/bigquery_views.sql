CREATE OR REPLACE VIEW BQBlog AS (
	WITH
		BlogEvent AS (
			SELECT
				Event.*,
				entryId.value AS assetId,
				blogTitle.value AS assetTitle
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS entryId ON (
				Event.id = entryId.id AND entryId.name IN ('classPK', 'entryId')
			)
			LEFT JOIN BQEventProperty AS blogTitle ON (
				Event.id = blogTitle.id AND blogTitle.name = 'title'
			)
			LEFT JOIN BQEventProperty AS className ON (
				className.id = Event.id AND
				className.name = 'className' AND
				className.value = 'com.liferay.blogs.model.BlogsEntry'
			)
			WHERE
				(
					(
						Event.applicationId = 'Blog' AND
						Event.eventId IN ('blogClicked', 'blogDepthReached', 'blogViewed')
					) OR
					(
						Event.applicationId = 'Ratings' AND
						className.value IS NOT NULL
					)
				) AND
				entryId.value IS NOT NULL
		),
		BlogFinalizedEvent AS (
			SELECT
				BlogEvent.*
			FROM
				BlogEvent
			INNER JOIN BQSession AS Session ON
				BlogEvent.sessionId = Session.id
		),
		CommentEvent AS (
			SELECT
				Event.*,
				classPK.value AS assetId
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS className ON (
				Event.id = className.id AND className.name = 'className'
			)
			LEFT JOIN BQEventProperty AS classPK ON (
				Event.id = classPK.id AND classPK.name = 'classPK'
			)
			WHERE
				Event.applicationId = 'Comment' AND
				Event.eventId = 'posted' AND
				className.value = 'com.liferay.blogs.model.BlogsEntry' AND
				classPK.value IS NOT NULL
		),
		BlogComments AS (
			SELECT
				assetId,
				canonicalUrl,
				SUM(1) AS comments,
				channelId,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				title AS pageTitle,
				userId
			FROM
				CommentEvent
			GROUP BY
				assetId, canonicalUrl, channelId, normalizedEventDate, title,
				userId
		),
		RatingsEvent AS (
			SELECT
				Event.*,
				classPK.value AS assetId,
				CAST(score.value AS FLOAT) AS score
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS className ON (
				Event.id = className.id AND className.name = 'className'
			)
			LEFT JOIN BQEventProperty AS classPK ON (
				Event.id = classPK.id AND classPK.name = 'classPK'
			)
			LEFT JOIN BQEventProperty AS ratingType ON (
				Event.id = ratingType.id AND ratingType.name = 'ratingType'
			)
			LEFT JOIN BQEventProperty AS score ON (
				Event.id = score.id AND score.name = 'score'
			)
			WHERE
				Event.applicationId = 'Ratings' AND
				Event.eventId = 'VOTE' AND
				className.value = 'com.liferay.blogs.model.BlogsEntry' AND
				classPK.value IS NOT NULL AND
				ratingtype.value = 'stars'
		),
		BlogRatings AS (
			SELECT
				assetId,
				canonicalUrl,
				channelId,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				title AS pageTitle,
				SUM(1) AS ratings,
				score AS ratingsScore,
				userId
			FROM
				RatingsEvent AS RatingsEvent1
			WHERE
				RatingsEvent1.eventDate = (
					SELECT
					   MAX(RatingsEvent2.eventDate)
					FROM
						RatingsEvent RatingsEvent2
					WHERE
						RatingsEvent1.assetId = RatingsEvent2.assetId AND
						RatingsEvent1.userid = RatingsEvent2.userid
				) AND score >= 0
			GROUP BY
				assetId, canonicalUrl, channelId, normalizedEventDate, score,
				title, userId
		),
		BlogReadTimes AS (
			SELECT
				assetId,
				assetTitle,
				canonicalUrl,
				channelId,
				DATE_TRUNC('HOUR', maxEventDate) AS normalizedEventDate,
				title AS pageTitle,
				SUM(readtime) AS readTime,
				userId
			FROM
				(
					SELECT
						assetId,
						assetTitle,
						canonicalUrl,
						channelId,
						MAX(eventDate) FILTER (WHERE eventId != 'blogViewed') maxEventDate,
						(
							EXTRACT(EPOCH FROM MAX(eventDate) FILTER (WHERE eventId != 'blogViewed')) -
							EXTRACT(EPOCH FROM MIN(eventDate))
						) AS readTime,
						sessionId,
						title,
						userId
					FROM
						BlogFinalizedEvent
					GROUP BY
						assetId, assetTitle, canonicalUrl, channelId, sessionId,
						title, userId
				) AS TMP
			WHERE
				maxEventDate IS NOT NULL
			GROUP BY
				assetId, assetTitle, canonicalUrl, channelId, normalizedEventDate,
				title, userId
		),
		BlogViewsAndClicks AS (
			SELECT
				assetId,
				assetTitle,
				browserName,
				canonicalUrl,
				channelId,
				SUM(
					CASE
						WHEN
							eventId = 'blogClicked'
						THEN
							1
						END
				) AS clicks,
				city,
				country,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				deviceType,
				platformName,
				region,
				COUNT(DISTINCT(sessionId)) AS sessions,
				title AS pageTitle,
				userId,
				SUM(
					CASE
						WHEN
							eventId = 'blogViewed'
						THEN
							1
						END
				) AS views
			FROM
				BlogEvent
			GROUP BY
				assetId, assetTitle, browserName, canonicalUrl, channelId, city,
				country, normalizedEventDate, deviceType, platformName,
				region, title, userId
		)
	SELECT
		BlogViewsAndClicks.assetId,
		BlogViewsAndClicks.assetTitle,
		BlogViewsAndClicks.browserName,
		BlogViewsAndClicks.canonicalUrl,
		BlogViewsAndClicks.channelId,
		BlogViewsAndClicks.city,
		BlogViewsAndClicks.clicks,
		BlogComments.comments,
		BlogViewsAndClicks.country,
		BlogViewsAndClicks.deviceType,
		BlogViewsAndClicks.normalizedEventDate AS eventDate,
		BlogViewsAndClicks.pageTitle,
		BlogViewsAndClicks.platformName,
		BlogRatings.ratings,
		BlogRatings.ratingsScore,
		BlogReadTimes.readTime * 1000 AS readTime,
		BlogViewsAndClicks.region,
		BlogViewsAndClicks.sessions,
		BlogViewsAndClicks.userId,
		BlogViewsAndClicks.views
	FROM
		 BlogViewsAndClicks
	LEFT JOIN BlogComments ON (
		 BlogViewsAndClicks.assetId = BlogComments.assetId AND
		 BlogViewsAndClicks.canonicalUrl = BlogComments.canonicalUrl AND
		 BlogViewsAndClicks.channelId = BlogComments.channelId AND
		 BlogViewsAndClicks.normalizedEventDate = BlogComments.normalizedEventDate AND
		 BlogViewsAndClicks.pageTitle = BlogComments.pageTitle AND
		 BlogViewsAndClicks.userId = BlogComments.userId
	)
	LEFT JOIN BlogRatings ON (
		BlogViewsAndClicks.assetId = BlogRatings.assetId AND
		BlogViewsAndClicks.canonicalUrl = BlogRatings.canonicalUrl AND
		BlogViewsAndClicks.channelId = BlogRatings.channelId AND
		BlogViewsAndClicks.normalizedEventDate = BlogRatings.normalizedEventDate AND
		BlogViewsAndClicks.pageTitle = BlogRatings.pageTitle AND
		BlogViewsAndClicks.userId = BlogRatings.userId
	)
	LEFT JOIN BlogReadTimes ON (
		BlogViewsAndClicks.assetId = BlogReadTimes.assetId AND
		BlogViewsAndClicks.assetTitle = BlogReadTimes.assetTitle AND
		BlogViewsAndClicks.canonicalUrl = BlogReadTimes.canonicalUrl AND
		BlogViewsAndClicks.channelId = BlogReadTimes.channelId AND
		BlogViewsAndClicks.normalizedEventDate = BlogReadTimes.normalizedEventDate AND
		BlogViewsAndClicks.pageTitle = BlogReadTimes.pageTitle AND
		BlogViewsAndClicks.userId = BlogReadTimes.userId
	)
);

COMMIT;

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
			) AS TMP
			WHERE
				minSubmissionDate IS NOT NULL
			GROUP BY
				assetPrimaryKey,
				normalizedEventDate
		)
	SELECT
		abandonments.abandonments,
		metrics.assetPrimaryKey,
		metrics.channelId,
		metrics.clicks,
		metrics.downloads,
		metrics.normalizedEventDate AS eventDate,
		metrics.submissions,
		metrics.views,
		metrics.sessions,
		readTime.readtime * 1000 AS readTime,
		submissionTime.submissionstime * 1000 AS submissionsTime
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
				Event.*,
				classPK.value AS assetId
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS className ON (
				Event.id = className.id AND className.name = 'className'
			)
			LEFT JOIN BQEventProperty AS classPK ON (
				Event.id = classPK.id AND classPK.name = 'classPK'
			)
			WHERE
				Event.applicationId = 'Comment' AND
				Event.eventId = 'posted' AND
				className.value = 'com.liferay.document.library.kernel.model.DLFileEntry' AND
				classPK.value IS NOT NULL
		),
		DocumentEvent AS (
			SELECT
				Event.*,
				fileEntryId.value AS assetId,
				documentTitle.value AS assetTitle
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS fileEntryId ON (
				Event.id = fileEntryId.id AND fileEntryId.name in ('classPK', 'fileEntryId')
			)
			LEFT JOIN BQEventProperty AS documentTitle ON (
				Event.id = documentTitle.id AND documentTitle.name = 'title'
			)
			LEFT JOIN BQEventProperty AS className ON (
				className.id = Event.id AND
				className.name = 'className' AND
				className.value = 'com.liferay.document.library.kernel.model.DLFileEntry'
			)
			WHERE
				(
					(
						Event.applicationId = 'Document' AND
						Event.eventId IN ('documentDownloaded', 'documentPreviewed')
					) OR
					(
						Event.applicationId = 'Ratings' AND
						className.value IS NOT NULL
					)
				) AND
				fileEntryId.value IS NOT NULL
		),
		DocumentComments AS (
			SELECT
				assetId,
				canonicalUrl,
				channelId,
				SUM(1) AS comments,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				title AS pageTitle,
				userId
			FROM
				CommentEvent
			GROUP BY
				assetId, canonicalUrl, channelId, normalizedEventDate, title,
				userId
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
						ELSE
							0
					END
				) AS downloads,
				country,
				deviceType,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				platformName,
				SUM(
					CASE
						WHEN
							eventId = 'documentPreviewed'
						THEN
							1
						ELSE
							0
					END
				) AS previews,
				region,
				title AS pageTitle,
				userId
			FROM
				DocumentEvent
			GROUP BY
				assetId, assetTitle, browserName, canonicalUrl, channelId, city,
				country, deviceType, normalizedEventDate, platformName,
				region, title, userId
		),
		RatingsEvent AS (
			SELECT
				Event.*,
				classPK.value AS assetId,
				CAST(score.value AS FLOAT) AS score
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS className ON (
				Event.id = className.id AND className.name = 'className'
			)
			LEFT JOIN BQEventProperty AS classPK ON (
				Event.id = classPK.id AND classPK.name = 'classPK'
			)
			LEFT JOIN BQEventProperty AS ratingType ON (
				Event.id = ratingType.id AND ratingType.name = 'ratingType'
			)
			LEFT JOIN BQEventProperty AS score ON (
				Event.id = score.id AND score.name = 'score'
			)
			WHERE
				Event.applicationId = 'Ratings' AND
				Event.eventId = 'VOTE' AND
				className.value = 'com.liferay.document.library.kernel.model.DLFileEntry' AND
				classPK.value IS NOT NULL AND
				ratingtype.value = 'stars'
		),
		DocumentRatings AS (
			SELECT
				assetId,
				canonicalUrl,
				channelId,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				title AS pageTitle,
				SUM(1) AS ratings,
				score AS ratingsScore,
				userId
			FROM
				RatingsEvent AS RatingsEvent1
			WHERE
				RatingsEvent1.eventDate = (
					SELECT
						MAX(RatingsEvent2.eventDate)
					FROM
						RatingsEvent RatingsEvent2
					WHERE
						RatingsEvent1.assetId = RatingsEvent2.assetId AND
						RatingsEvent1.userid = RatingsEvent2.userid
				) AND score >= 0
			GROUP BY
				assetId, canonicalUrl, channelId, normalizedEventDate, score,
				title, userId
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
				datatype AS fieldType,
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
				) AS TMP1
			) AS TMP2
		),
		DemographicsFieldMapping AS (
			SELECT
				'demographics' context,
				ARRAY[]::BIGINT[] dataSourceIds,
				displayName,
				'input-field' displayType,
				fieldName,
				fieldType,
				now() modifiedDate,
				'individual' ownerType,
				false repeatable_
			FROM (
				VALUES
				('address', 'addresses', 'text'),
				('birthDate', 'birthday', 'date'),
				('email', 'emailAddress', 'text'),
				('givenName', 'firstName', 'text'),
				('gender', 'gender', 'text'),
				('jobTitle', 'jobTitle', 'text'),
				('familyName', 'lastName', 'text'),
				('additionalName', 'middleName', 'text'),
				('telephone', 'phones', 'text')
			) AS TMP (
				displayName,
				fieldName,
				fieldType
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

CREATE OR REPLACE VIEW BQForm AS (
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
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS formId ON (
				Event.id = formId.id AND formId.name = 'formId'
			)
			LEFT JOIN BQEventProperty AS formTitle ON (
				Event.id = formTitle.id AND formTitle.name = 'title'
			)
			WHERE
				Event.applicationId = 'Form' AND
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
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				platformName,
				region,
				title AS pageTitle,
				userId ,
				SUM(EXTRACT(EPOCH from eventDate) - EXTRACT(EPOCH from previousFormViewedEventDate)) submissionsTime
			FROM (
				SELECT
					*,
					MAX(eventDate) FILTER (WHERE eventId = 'formViewed')
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
							eventId = 'formViewed' AND
							Session.id IS NOT NULL
						THEN
							1
						ELSE
							0
					END
				) -
				SUM(
					CASE
						WHEN
							eventId = 'formSubmitted' AND
							Session.id IS NOT NULL
						THEN
						1
					ELSE
						0
				END
				)
			) AS abandonments,
		FormEvent.assetId,
		MAX(CASE WHEN FormEvent.eventId = 'formViewed' THEN FormEvent.assetTitle END) assetTitle,
		FormEvent.browserName,
		FormEvent.canonicalUrl,
		FormEvent.channelId,
		FormEvent.city,
		FormEvent.country,
		FormEvent.deviceType,
		DATE_TRUNC('HOUR', FormEvent.eventDate) AS eventDate,
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
		SUM(CASE WHEN FormEvent.eventId = 'formSubmitted' THEN 1 END) AS submissions,
		MAX(FormSubmissionTimes.submissionsTime) submissionsTime,
		FormEvent.title AS pageTitle,
		FormEvent.userId,
		SUM(CASE WHEN FormEvent.eventId = 'formViewed' THEN 1 END) AS views
	FROM
		FormEvent
	LEFT JOIN FormSubmissionTimes ON (
		FormEvent.assetId = FormSubmissionTimes.assetId AND
		FormEvent.canonicalUrl = FormSubmissionTimes.canonicalUrl AND
		FormEvent.channelId = FormSubmissionTimes.channelId AND
		DATE_TRUNC('HOUR', FormEvent.eventDate) = FormSubmissionTimes.normalizedEventDate AND
		FormEvent.title = FormSubmissionTimes.pageTitle AND
		FormEvent.userId = FormSubmissionTimes.userId
	)
	LEFT JOIN BQSession AS Session ON
		FormEvent.sessionId = Session.id
	GROUP BY
		FormEvent.assetId, FormEvent.browserName, FormEvent.canonicalUrl,
		FormEvent.channelId, FormEvent.city, FormEvent.country,
		FormEvent.deviceType, DATE_TRUNC('HOUR', FormEvent.eventDate),
		FormEvent.platformName, FormEvent.region, FormEvent.title, FormEvent.userId
);

COMMIT;

CREATE OR REPLACE VIEW BQIdentityActivity AS (
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
		BQEvent AS Event
	LEFT JOIN  BQIdentity AS Identity ON (
		Event.userId = Identity.id
	)
	GROUP BY
		Event.channelId,
		Event.dataSourceId,
		Event.eventId,
		Event.userId
);

COMMIT;

CREATE OR REPLACE VIEW BQJournal AS (
	WITH
		WebContentEvent AS (
			SELECT
				Event.*,
				articleId.value AS assetId,
				articleTitle.value AS assetTitle
			FROM
				BQEvent AS Event
			LEFT JOIN BQEventProperty AS articleId ON (
				Event.id = articleId.id AND articleId.name = 'articleId'
			)
			LEFT JOIN BQEventProperty AS articleTitle ON (
				Event.id = articleTitle.id AND articleTitle.name = 'title'
			)
			WHERE
				Event.applicationId = 'WebContent' AND
				Event.eventId = 'webContentViewed' AND
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
		DATE_TRUNC('HOUR', eventDate) AS eventDate,
		deviceType,
		platformName,
		region,
		title AS pageTitle,
		userId,
		SUM(1) AS views
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
		PageFinalizedEvent AS (
			SELECT
				BQEvent.*
			FROM
				BQEvent
			INNER JOIN
				BQSession ON
					BQEvent.sessionId = BQSession.id
		),
		PageBounces AS (
			SELECT
				channelId,
				COUNT(*) AS count,
				SUM(CASE WHEN applicationId = 'Page' AND eventId = 'pageViewed' THEN 1 ELSE 0 END) AS pageViews,
				sessionId,
				userId
			FROM
				PageFinalizedEvent
			WHERE
				eventId NOT IN ('blogViewed', 'documentPreviewed', 'formViewed', 'pageLoaded', 'pageUnloaded', 'webContentViewed')
			GROUP BY
				channelId,
				sessionId,
				userId
		),
		PageEntrances AS (
			SELECT
				browserName,
				canonicalUrl,
				channelId,
				city,
				country,
				deviceType,
				rank AS entrances,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				platformName,
				region,
				sessionId,
				title,
				userId
			FROM (
				SELECT
					*,
					ROW_NUMBER() OVER (
						PARTITION BY
							sessionId,
							channelId,
							userId
						ORDER BY
							eventDate ASC
					) AS rank
				FROM
					PageFinalizedEvent
			) AS EventEntrance
			WHERE
				rank = 1
		),
		PageExits AS (
			SELECT
				browserName,
				canonicalUrl,
				channelId,
				city,
				country,
				deviceType,
				rank AS exits,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				platformName,
				region,
				sessionId,
				title,
				userId
			FROM (
				SELECT
					*,
					ROW_NUMBER() OVER (
						PARTITION BY
							sessionId,
							channelId,
							userId
						ORDER BY
							eventDate DESC
					) AS rank
				FROM
					PageFinalizedEvent
			) AS EventExit
			WHERE
				rank = 1
		),
		PageTimeOnPages AS (
			SELECT
				browserName,
				canonicalUrl,
				channelId,
				city,
				country,
				deviceType,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				platformName,
				region,
				sessionId,
				SUM(EXTRACT(EPOCH FROM nextTime) - EXTRACT(EPOCH FROM eventDate)) * 1000 AS timeOnPage,
				title,
				userId
			FROM (
				SELECT
					*,
					LEAD(eventDate) OVER (
						PARTITION BY
							sessionId,
							userId,
							channelId
						ORDER BY
							eventDate
					) AS nextTime
				FROM
					PageFinalizedEvent
			) AS EventTimeOnPage
			GROUP BY
				browserName,
				canonicalUrl,
				channelId,
				city,
				country,
				deviceType,
				normalizedEventDate,
				platformName,
				region,
				sessionId,
				title,
				userId
		),
		PageViews AS (
			SELECT
				browserName,
				canonicalUrl,
				channelId,
				city,
				country,
				deviceType,
				SUM(
					CASE
						WHEN
							referrer = ''
						THEN
							1
						ELSE
							0
					END
				) AS directAccess,
				SUM(
					CASE
						WHEN
							referrer != ''
						THEN
							1
						ELSE
							0
					END
				) AS indirectAccess,
				DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
				SUM(1) AS views,
				platformName,
				region,
				sessionId,
				title,
				userId
			FROM
				PageFinalizedEvent
			WHERE
				applicationId = 'Page' AND eventId = 'pageViewed'
			GROUP BY
				browserName,
				canonicalUrl,
				channelId,
				city,
				country,
				deviceType,
				normalizedEventDate,
				platformName,
				region,
				sessionId,
				title,
				userId
		)
		SELECT
			CASE
				WHEN
					PageBounces.count > 2 OR PageBounces.pageViews > 1
				THEN
					0
				ELSE
					1
			END AS bounce,
			PageTimeOnPages.browserName,
			PageTimeOnPages.canonicalUrl AS canonicalUrl,
			PageTimeOnPages.channelId AS channelId,
			PageTimeOnPages.country,
			PageTimeOnPages.deviceType,
			PageViews.directAccess,
			PageEntrances.entrances,
			PageTimeOnPages.normalizedEventDate AS eventDate,
			PageExits.exits,
			PageViews.indirectAccess,
			PageTimeOnPages.platformName,
			PageTimeOnPages.region,
			PageTimeOnPages.sessionId AS sessionId,
			PageTimeOnPages.timeOnPage AS timeOnPage,
			PageTimeOnPages.title AS title,
			PageTimeOnPages.userId AS userId,
			PageViews.views
		FROM
			 PageTimeOnPages
		LEFT JOIN PageBounces ON (
			PageTimeOnPages.channelId = PageBounces.channelId AND
			PageTimeOnPages.sessionId = PageBounces.sessionId AND
			PageTimeOnPages.userId = PageBounces.userId
		)
		LEFT JOIN PageEntrances ON (
			PageTimeOnPages.browserName = PageEntrances.browserName AND
			PageTimeOnPages.canonicalUrl =
			PageEntrances.canonicalUrl AND
			PageTimeOnPages.channelId = PageEntrances.channelId AND
			PageTimeOnPages.city = PageEntrances.city AND
			PageTimeOnPages.country = PageEntrances.country AND
			PageTimeOnPages.deviceType = PageEntrances.deviceType AND
			PageTimeOnPages.normalizedEventDate = PageEntrances.normalizedEventDate AND
			PageTimeOnPages.platformName = PageEntrances.platformName AND
			PageTimeOnPages.region = PageEntrances.region AND
			PageTimeOnPages.sessionId = PageEntrances.sessionId AND
			PageTimeOnPages.title = PageEntrances.title AND
			PageTimeOnPages.userId = PageEntrances.userId
		)
		LEFT JOIN PageExits ON (
			PageTimeOnPages.browserName = PageExits.browserName AND
			PageTimeOnPages.canonicalUrl = PageExits.canonicalUrl AND
			PageTimeOnPages.channelId = PageExits.channelId AND
			PageTimeOnPages.city = PageExits.city AND
			PageTimeOnPages.country = PageExits.country AND
			PageTimeOnPages.deviceType = PageExits.deviceType AND
			PageTimeOnPages.normalizedEventDate = PageExits.normalizedEventDate AND
			PageTimeOnPages.platformName = PageExits.platformName AND
			PageTimeOnPages.region = PageExits.region AND
			PageTimeOnPages.sessionId = PageExits.sessionId AND
			PageTimeOnPages.title = PageExits.title AND
			PageTimeOnPages.userId = PageExits.userId
		)
		LEFT JOIN PageViews ON (
			PageTimeOnPages.browserName = PageViews.browserName AND
			PageTimeOnPages.canonicalUrl = PageViews.canonicalUrl AND
			PageTimeOnPages.channelId = PageViews.channelId AND
			PageTimeOnPages.city = PageViews.city AND
			PageTimeOnPages.country = PageViews.country AND
			PageTimeOnPages.deviceType = PageViews.deviceType AND
			PageTimeOnPages.normalizedEventDate = PageViews.normalizedEventDate AND
			PageTimeOnPages.platformName = PageViews.platformName AND
			PageTimeOnPages.region = PageViews.region AND
			PageTimeOnPages.sessionId = PageViews.sessionId AND
			PageTimeOnPages.title = PageViews.title AND
			PageTimeOnPages.userId = PageViews.userId
		)
 );