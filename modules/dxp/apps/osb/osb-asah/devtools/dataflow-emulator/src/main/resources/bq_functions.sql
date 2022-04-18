CREATE OR REPLACE FUNCTION acquisition_channel(referrer TEXT, url TEXT) RETURNS TEXT AS $$
DECLARE
	gclid TEXT;
	hostname TEXT;
	hostname_regex TEXT :=  ':\/\/(www[0-9]?\.)?(.[^/:]+)';
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
	hostname := (regexp_matches(url, hostname_regex))[2];
	referrer_hostname := (regexp_matches(referrer, hostname_regex))[2];
	gclid := url_decode((regexp_matches(url, 'gclid=(.[^&]+)'))[1]);
	medium := url_decode((regexp_matches(referrer, 'utm_medium=(.[^&]+)'))[1]);

	IF (medium = 'organic') OR (referrer_hostname = ANY(search_hostnames)) THEN

		RETURN 'organic';

	ELSIF (medium = ANY (paid_mediums)) OR (gclid IS NOT NULL) OR
		  (referrer_hostname = ANY(paid_hostnames)) THEN

		RETURN 'paid';

	ELSIF (medium = ANY(social_mediums)) OR
		  (referrer_hostname = ANY(social_hostnames)) THEN

		RETURN 'social';

	ELSIF referrer_hostname IS NULL THEN

		RETURN 'direct';

	ELSIF (medium = 'referral') OR (hostname != referrer_hostname) THEN

		RETURN 'referral';

	END IF;


	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION url_decode(input text) RETURNS TEXT AS $$
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