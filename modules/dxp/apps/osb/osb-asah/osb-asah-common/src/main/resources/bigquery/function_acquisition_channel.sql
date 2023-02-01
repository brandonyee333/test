CREATE OR REPLACE FUNCTION `$[AC_PROJECT_ID].acquisition_channel`(referrerUrl STRING, url STRING)
RETURNS STRING
LANGUAGE js
AS R"""
	try {
		var decodedUrl = decodeURIComponent(url.replaceAll('+', ' '));

		var queryParamSeparatorIndexOf = decodedUrl.indexOf('?');

		if (queryParamSeparatorIndexOf < 0) {
			return null;
		}

		const paidHostnames = ['googleadservices.com'];
		const paidMediums = ['cpc', 'paidsearch', 'ppc'];
		const searchHostnames = [
			'ask.com', 'baidu.com', 'bing.com', 'duckduckgo.com', 'google.com',
			'yahoo.com', 'yandex.com'
		];
		const socialHostnames = [
			'facebook.com', 'instagram.com', 'linkedin.com', 'pinterest.com',
			'snapchat.com', 't.co', 'tiktok.com', 'twitter.com', 'youtube.com'
		];
		const socialMediums = [
			'sm', 'social', 'social media', 'social network', 'social-media',
			'social-network'
		];

		var gclid = '';
		var medium = '';

		var queryParamsString = decodedUrl.substr(queryParamSeparatorIndexOf + 1);

		var queryParamsStringParts = queryParamsString.split('&');

		for (var i = 0; i < queryParamsStringParts.length; i++) {
			var queryParam = queryParamsStringParts[i].split('=');

			if (queryParam[0] === 'medium') {
				medium = queryParam[1];
			}
			else if (queryParam[0] === 'gclid') {
				gclid = queryParam[1];
			}
		}

		var hostname = '';

		try {
			hostname = url.match(":\/\/(www[0-9]?\.)?(.[^/:]+)")[2];
		}
		catch (error) {
		}

		var referrerHostname = '';

		try {
			referrerHostname = referrerUrl.match(":\/\/(www[0-9]?\.)?(.[^/:]+)")[2];
		}
		catch (error) {
		}

		if ((medium === 'organic') ||
			 searchHostnames.includes(referrerHostname)) {

			return 'organic';
		}

		if (paidMediums.includes(medium) || gclid ||
			paidHostnames.includes(referrerHostname)) {

			return 'paid';
		}

		if (socialMediums.includes(medium) ||
			socialHostnames.includes(referrerHostname)) {

			return 'social';
		}

		if (!referrerHostname) {
			return 'direct';
		}

		if ((medium == 'referral') || (hostname !== referrerHostname)) {
			return 'referral';
		}

		return null;
	}
	catch (error) {
		return null;
	}
""";