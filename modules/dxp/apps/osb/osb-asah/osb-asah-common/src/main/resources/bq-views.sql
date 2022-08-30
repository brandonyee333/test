create or replace view BQCustomAsset as (
										with
											CustomAssetEvent AS (
												select bqevent.*,
													   assetId.value as assetid,
													   formEnabled.value as formenabled,
													   coalesce(category.value, 'default') as category ,

													   encode(sha256( (assetId.value || coalesce(category.value, 'default') || bqevent.channelid)  ::bytea), 'hex') as assetprimarykey

												from bqevent

														 left join BQEventProperty as assetId
																   on (
																			   BQEvent.id = assetid.id
																		   and assetid.name = 'assetId'
																	   )
														 left join BQEventProperty as formEnabled
																   on (
																			   BQEvent.id = formEnabled.id
																		   and formEnabled.name = 'formEnabled'
																	   )
														 left join BQEventProperty as category
																   on (
																			   BQEvent.id = category.id
																		   and category.name = 'category'
																	   )

												where BQEvent.applicationid = 'Custom' and assetId.value is not null
											),
											CustomAssetFinalizedEvent AS
												(
													SELECT
														CustomAssetEvent.*
													FROM
														CustomAssetEvent
															INNER JOIN
														BQSession ON
																CustomAssetEvent.sessionId = BQSession.id
												),

											Metrics as (
												select assetprimarykey, channelId, DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,
													   sum( case WHEN eventId = 'assetClicked' then 1 else 0 end ) as clicks,
													   sum( case WHEN eventId = 'assetViewed' then 1 else 0 end ) as views,
													   sum( case WHEN eventId = 'assetDownloaded' then 1 else 0 end ) as downloads,
													   sum( case WHEN eventId = 'assetSubmitted' then 1 else 0 end ) as submissions,
													   count(distinct(sessionId)) as visitors
												from
													CustomAssetEvent

												group by assetprimarykey, normalizedEventDate, channelId
											),
											Abandoments as (
												select assetprimarykey, DATE_TRUNC('HOUR', eventDate) AS normalizedEventDate,

													   greatest (0, sum( case WHEN eventId = 'assetViewed' then 1 else 0 end ) - sum( case WHEN eventId = 'assetSubmitted' then 1 else 0 end )) as abandonments
												from
													CustomAssetFinalizedEvent
												where formenabled = 'true'

												group by assetprimarykey, normalizedEventDate

											),
											ReadTime as (


												select  assetprimarykey, DATE_TRUNC('HOUR', max_event) AS normalizedEventDate, sum(readtime) as readtime  from  (
																																									SELECT

																																										assetprimarykey,
																																										sessionid,

																																										max(eventDate) filter (where eventid != 'assetViewed') max_event, min(eventDate) min_event,
																																										EXTRACT(EPOCH from max(eventDate) filter (where eventid != 'assetViewed') ) - EXTRACT(EPOCH from min(eventDate)) as readtime

																																									FROM CustomAssetFinalizedEvent

																																									group by
																																										assetprimarykey, sessionId
																																								) T
												where DATE_TRUNC('HOUR', max_event) is not null
												group by assetprimarykey, normalizedEventDate

											),
											SubmissionTime as (

												select  assetprimarykey, DATE_TRUNC('HOUR', max_event) AS normalizedEventDate, sum(completiontime) as submissionstime  from
													(
														SELECT

															assetprimarykey,
															sessionid,

															min(eventDate) filter (where eventid = 'assetSubmitted') max_event, min(eventDate) filter (where eventid = 'assetViwed') min_event,
															EXTRACT(EPOCH from max(eventDate) filter (where eventid = 'assetSubmitted')) - EXTRACT(EPOCH from min(eventDate) filter (where eventid = 'assetViwed')) as completiontime

														FROM CustomAssetFinalizedEvent

														group by
															assetprimarykey, sessionId
													) T
												where DATE_TRUNC('HOUR', max_event) is not null
												group by assetprimarykey, normalizedEventDate

											)






										select m.assetprimarykey,
											   m.channelId,
											   m.normalizedeventdate as eventdate,
											   a.abandonments,
											   m.clicks,
											   m.views,
											   m.visitors,
											   m.downloads,
											   m.submissions,
											   coalesce (r.readtime,0)  * 1000 as readtime,
											   coalesce (s.submissionstime,0) * 1000 as submissionstime



										from Metrics m


												 left join Abandoments a on (m.assetprimarykey = a.assetprimarykey and m.normalizedEventDate = a.normalizedEventDate)
												 left join ReadTime r on (m.assetprimarykey = r.assetprimarykey and m.normalizedEventDate = r.normalizedEventDate)
												 left join SubmissionTime s on (m.assetprimarykey = s.assetprimarykey and m.normalizedEventDate = s.normalizedEventDate)
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