CREATE OR REPLACE VIEW BQFieldMapping AS (
	WITH DemographicsFieldMapping AS (
		SELECT * FROM ( VALUES
			('demographics', ARRAY[NULL::BIGINT], 'address', 'input-field', 'addresses', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'birthDate', 'input-field', 'birthday', 'date', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'email', 'input-field', 'emailAddress', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'givenName', 'input-field', 'firstName', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'gender', 'input-field', 'gender', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'jobTitle', 'input-field',  'jobTitle', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'familyName', 'input-field', 'lastName', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'additionalName', 'input-field', 'middleName', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'telephone', 'input-field', 'phones', 'text', NOW(), 'individual', false),
			('demographics', ARRAY[NULL::BIGINT], 'familyName', 'input-field', 'emailAddress', 'text', NOW(), 'individual', false)
		) AS TMP (
			context, dataSourceIds, displayName, displayType, fieldName, fieldType, modifiedDate, ownerType, repeatable_
		)
	),
	CustomFieldMapping AS (
		SELECT
			'custom' as context, datasourceids,
			CASE
				WHEN
				    rn = 1
				THEN
				    name
				ELSE
				    name || (rn - 1)
			END displayname,
			displaytype,
			CASE
				WHEN
				    displaytype IN ('checkbox', 'radio', 'selection-list')
				THEN
				    CONCAT(name, '_', datatype, '_array')
				ELSE
				    CONCAT(name, '_', datatype)
			END fieldname,
			datatype as fieldtype,
			modifieddate,
			ownertype,
			CASE
				WHEN
				    displaytype IN ('checkbox', 'radio', 'selection-list')
				THEN
				    TRUE
				ELSE
				    FALSE
			END repeatable_
		FROM
			(
				SELECT
					   *, row_number() OVER (partition by name, ownertype order by modifieddate asc) rn
				FROM
					(
						SELECT
							name, displaytype, datatype, max(modifieddate) as modifieddate,
							ARRAY_AGG(datasourceid) as datasourceids,
							CASE
								WHEN classname = 'com.liferay.portal.kernel.model.User' then 'individual'
								WHEN classname = 'com.liferay.portal.kernel.model.Organization' then 'organization'
							END ownertype
						FROM
							BQExpandoColumn
						GROUP BY
							name, datatype, displaytype, ownertype
					) TMP1
			) TMP2
	)
	SELECT
	       *
	FROM
		 DemographicsFieldMapping
	UNION ALL
	SELECT
	       *
	FROM
	     CustomFieldMapping
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
		SUM(1) as access
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
					COUNT(*) as count,
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
						ROW_NUMBER() OVER (PARTITION BY sessionId, channelId, userId ORDER BY eventDate ASC) AS rank,
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
						ROW_NUMBER() OVER (PARTITION BY sessionId, channelId, userId ORDER BY eventDate DESC) AS rank,
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
						LEAD(eventDate) OVER (PARTITION BY sessionId, userId, channelId ORDER BY eventDate) AS nextTime,
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