CREATE OR REPLACE FUNCTION acquisition_channel(referrer_url TEXT, url TEXT) RETURNS TEXT AS $$
DECLARE
	medium TEXT;
	paid_mediums TEXT[] := ARRAY['cpc', 'paidsearch', 'ppc'];
	paid_hostnames TEXT[] :=  ARRAY['googleadservices.com'];
	referrer_hostname TEXT;
	search_hostnames TEXT[] := ARRAY[
		'ask.com', 'baidu.com', 'bing.com', 'duckduckgo.com', 'google.com',
		'yahoo.com', 'yandex.com'
		];
	social_mediums TEXT[] := ARRAY[
		'sm', 'social', 'social media', 'social network', 'social-media',
		'social-network'
		];
	social_hostnames TEXT[] := ARRAY[
		'facebook.com', 'instagram.com', 'linkedin.com', 'pinterest.com',
		'snapchat.com', 't.co', 'tiktok.com', 'twitter.com', 'youtube.com'
		];
BEGIN
	medium = url_decode(extract_query_param('medium', url));
	referrer_hostname = hostname(referrer_url);

	IF (medium = 'organic') OR (referrer_hostname = ANY(search_hostnames)) THEN

		RETURN 'organic';

	ELSIF (medium = ANY (paid_mediums)) OR
		  (extract_query_param('gclid', url) IS NOT NULL) OR
		  (referrer_hostname = ANY(paid_hostnames)) THEN

		RETURN 'paid';

	ELSIF (medium = ANY(social_mediums)) OR
		  (referrer_hostname = ANY(social_hostnames)) THEN

		RETURN 'social';

	ELSIF referrer_hostname IS NULL THEN

		RETURN 'direct';

	ELSIF (medium = 'referral') OR (hostname(url) != referrer_hostname) THEN

		RETURN 'referral';

	END IF;

	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

COMMIT;

CREATE OR REPLACE FUNCTION canonical_url(url TEXT) RETURNS TEXT AS $$
BEGIN
	RETURN REGEXP_REPLACE(url, '\?.[^#]+(#.*)?', '\1');
END;
$$ LANGUAGE plpgsql;

COMMIT;

CREATE OR REPLACE FUNCTION extract_query_param(param TEXT, url TEXT) RETURNS TEXT AS $$
BEGIN
	RETURN (regexp_matches(url, concat(param, '=(.[^&]+)') ))[1];
END;
$$ LANGUAGE plpgsql;

COMMIT;

CREATE OR REPLACE FUNCTION hostname(url TEXT) RETURNS TEXT AS $$
BEGIN
	RETURN (regexp_matches(url, ':\/\/(www[0-9]?\.)?(.[^/:]+)'))[2];
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pages_by_day(endDate TIMESTAMP, startDate TIMESTAMP, targetChannelId BIGINT, timeZoneId TEXT) RETURNS TABLE(bounces BIGINT, canonicalUrl TEXT, channelId BIGINT, entrances BIGINT, exits BIGINT, normalizedEventDate TIMESTAMP, pageViews BIGINT, sessionCount BIGINT, timeOnPage BIGINT, title TEXT) AS '
WITH
    FinalizedEvent AS
        (SELECT
            BQEvent.*
        FROM
            BQEvent
        INNER JOIN
            BQSession ON
                BQEvent.sessionId = BQSession.id
        WHERE
            BQEvent.channelId = targetChannelId AND
            eventDate BETWEEN startDate AND endDate),
    Bounces AS
        (SELECT
            channelId,
            COUNT(*) as count,
            DATE_TRUNC(''DAY'', eventDate AT TIME ZONE timeZoneId) AS normalizedEventDate,
            SUM(CASE WHEN applicationId = ''Page'' AND eventId = ''pageViewed'' THEN 1 ELSE 0 END) as pageViews,
            sessionId,
            userId
        FROM
            FinalizedEvent
        WHERE
            eventId NOT IN (''blogViewed'', ''documentPreviewed'', ''formViewed'', ''pageLoaded'', ''pageUnloaded'', ''webContentViewed'')
        GROUP BY
            channelId,
            normalizedEventDate,
            sessionId,
            userId),
    Entrances AS
        (SELECT
            canonicalUrl,
            channelId,
            rank AS entrance,
            DATE_TRUNC(''DAY'', eventDate AT TIME ZONE timeZoneId) AS normalizedEventDate,
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
            WHERE
                channelId = targetChannelId AND
                eventDate BETWEEN startDate AND endDate
        ) AS EventEntrance
        WHERE
            rank = 1),
    Exits AS
        (SELECT
            canonicalUrl,
            channelId,
            rank AS exit,
            DATE_TRUNC(''DAY'', eventDate AT TIME ZONE timeZoneId) AS normalizedEventDate,
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
            rank = 1),
    TimeOnPages AS
        (SELECT
            canonicalUrl,
            channelId,
            DATE_TRUNC(''DAY'', eventDate AT TIME ZONE timeZoneId) AS normalizedEventDate,
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
            userId),
    Views AS
        (SELECT
            canonicalUrl,
            channelId,
            DATE_TRUNC(''DAY'', eventDate AT TIME ZONE timeZoneId) AS normalizedEventDate,
            COUNT(*) as pageViews,
            sessionId,
            title,
            userId
        FROM
            FinalizedEvent
        WHERE
            applicationId = ''Page'' AND
            eventId = ''pageViewed''
        GROUP BY
            canonicalUrl,
            channelId,
            normalizedEventDate,
            sessionId,
            title,
            userId),
    Pages AS
        (SELECT
            (CASE WHEN Bounces.count > 2 OR Bounces.pageViews > 1 THEN 0 ELSE 1 END) AS bounce,
            TimeOnPages.canonicalUrl AS canonicalUrl,
            TimeOnPages.channelId AS channelId,
            entrance,
            exit,
            TimeOnPages.normalizedEventDate AS normalizedEventDate,
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
                TimeOnPages.normalizedEventDate = Bounces.normalizedEventDate AND
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
                TimeOnPages.userId = Views.userId)
SELECT
    SUM(bounce) AS bounces,
    canonicalUrl,
    channelId,
    SUM(entrance) AS entrances,
    SUM(exit) AS exits,
    normalizedEventDate,
    SUM(views) AS pageViews,
    COUNT(DISTINCT sessionId) AS sessionCount,
    SUM(timeOnPage) AS timeOnPage,
    title
FROM
    Pages
GROUP BY
    canonicalUrl,
    channelId,
    normalizedEventDate,
    title;
' LANGUAGE sql;

COMMIT;

CREATE OR REPLACE FUNCTION url_decode(input TEXT) RETURNS TEXT AS $$
DECLARE
	bin BYTEA = '';
	byte TEXT;
BEGIN
	FOR byte IN (
		select (regexp_matches(input, '(%..|.)', 'g'))[1]
	)
		LOOP
			IF length(byte) = 3 THEN
				bin = bin || decode(substring(byte, 2, 2), 'hex');
			ELSE
				bin = bin || byte::bytea;
			END IF;
		END LOOP;

	RETURN convert_from(bin, 'UTF8');
END;
$$ LANGUAGE plpgsql;

COMMIT;