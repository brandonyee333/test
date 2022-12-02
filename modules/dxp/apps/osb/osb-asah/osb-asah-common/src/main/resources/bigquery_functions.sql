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

COMMIT;

CREATE OR REPLACE FUNCTION search_term(search_query_params VARCHAR ARRAY, url VARCHAR) RETURNS VARCHAR AS $$
DECLARE
	decodedUrl VARCHAR := REPLACE(url, '+', ' ');
	query_param_separator INTEGER := POSITION('?' IN decodedUrl);
	search_query_param VARCHAR;
	search_query_param_value VARCHAR;
BEGIN
	IF (query_param_separator <= 0) THEN
		RETURN NULL;
	END IF;

	FOREACH search_query_param IN ARRAY search_query_params
		LOOP
			search_query_param_value = url_decode(extract_query_param(search_query_param, decodedUrl));

			IF (search_query_param_value != '') THEN
				RETURN search_query_param_value;
			END IF;
		END LOOP;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

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