MERGE INTO
	`${PROJECT_ID}.${ASAH_PROJECT_ID}.documentlibrarydaily` AS replica
USING
	(
		WITH
			CommentEvent AS (
				SELECT
					Event.*,
					classPK.value AS assetId
				FROM
					`${PROJECT_ID}.${ASAH_PROJECT_ID}.event` AS Event
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS className ON (
						Event.id = className.id AND className.name = 'className'
					)
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS classPK ON (
						Event.id = classPK.id AND classPK.name = 'classPK'
					)
				WHERE
					Event.applicationId = 'Comment' AND
					Event.eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR) AND
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
					`${PROJECT_ID}.${ASAH_PROJECT_ID}.event` AS Event
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS fileEntryId ON (
						Event.id = fileEntryId.id AND fileEntryId.name = 'fileEntryId'
					)
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS documentTitle ON (
						Event.id = documentTitle.id AND documentTitle.name = 'title'
					)
				WHERE
					Event.applicationId = 'Document' AND
					Event.eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR) AND
					Event.eventId IN ('documentDownloaded', 'documentPreviewed') AND
					fileEntryId.value IS NOT NULL
			),
			RatingEvent AS (
				SELECT
					Event.*,
					classPK.value AS assetId,
					CAST(score.value AS FLOAT64) AS score
				FROM
					`${PROJECT_ID}.${ASAH_PROJECT_ID}.event` AS Event
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS className ON (
						Event.id = className.id AND className.name = 'className'
					)
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS classPK ON (
						Event.id = classPK.id AND classPK.name = 'classPK'
					)
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS ratingType ON (
						Event.id = ratingType.id AND ratingType.name = 'ratingType'
					)
					LEFT JOIN `${PROJECT_ID}.${ASAH_PROJECT_ID}.eventproperty` AS score ON (
						Event.id = score.id AND score.name = 'score'
					)
				WHERE
					Event.applicationId = 'Ratings' AND
					Event.eventDate < TIMESTAMP_SUB(CURRENT_TIMESTAMP(), INTERVAL 24 HOUR) AND
					Event.eventId = 'VOTE' AND
					className.value = 'com.liferay.document.library.kernel.model.DLFileEntry' AND
					classPK.value IS NOT NULL AND
					ratingtype.value = 'stars'
			),
			DocumentComments AS (
				SELECT
					assetId,
					canonicalUrl,
					channelId,
					SUM(1) AS comments,
					TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
					title AS pageTitle,
					userId
				FROM
					CommentEvent
				GROUP BY
					assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
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
					TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
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
			DocumentRatings AS (
				SELECT
					assetId,
					canonicalUrl,
					channelId,
					TIMESTAMP_TRUNC(eventDate, HOUR) AS normalizedEventDate,
					SUM(1) AS ratings,
					SUM(score) AS ratingsScore,
					title AS pageTitle,
					userId
				FROM
					RatingEvent
				GROUP BY
					assetId, canonicalUrl, channelId, normalizedEventDate, title, userId
			)
		SELECT
			DocumentDownloadAndPreviews.assetId,
			DocumentDownloadAndPreviews.assetTitle,
			DocumentDownloadAndPreviews.browserName,
			DocumentDownloadAndPreviews.canonicalUrl,
			CAST(DocumentDownloadAndPreviews.channelId AS INT64) AS channelId,
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
	) AS staging
ON (
	staging.assetId = replica.assetId AND
	staging.assetTitle = replica.assetTitle AND
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
		`assetId`,
		`assetTitle`,
		`browserName`,
		`canonicalUrl`,
		`channelId`,
		`city`,
		`comments`,
		`country`,
		`deviceType`,
		`downloads`,
		`eventDate`,
		`pageTitle`,
		`platformName`,
		`previews`,
		`ratings`,
		`ratingsScore`,
		`region`,
		`userId`
	)
	VALUES (
		staging.assetId,
		staging.assetTitle,
		staging.browserName,
		staging.canonicalUrl,
		staging.channelId,
		staging.city,
		staging.comments,
		staging.country,
		staging.deviceType,
		staging.downloads,
		staging.eventDate,
		staging.pageTitle,
		staging.platformName,
		staging.previews,
		staging.ratings,
		staging.ratingsScore,
		staging.region,
		staging.userId
	)