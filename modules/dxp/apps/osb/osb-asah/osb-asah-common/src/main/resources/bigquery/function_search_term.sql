CREATE OR REPLACE FUNCTION `$[AC_PROJECT_ID].search_term`(searchQueryParams ARRAY<STRING>, url STRING)
RETURNS STRING
LANGUAGE js
AS """
	try {
		var decodedURL = url;
		
		var index = decodedURL.indexOf('+');

		while (index != -1) {
			decodedURL = decodedURL.replace('+', ' ')
 			
 			index = decodedURL.indexOf('+');
		}
	
		decodedURL = decodeURIComponent(decodedURL);

		var queryParamSeparatorIndexOf = decodedURL.indexOf("?");

		if (queryParamSeparatorIndexOf < 0) {
			return null;
		}

		var queryParams = {};

		var queryParamsString = decodedURL.substr(queryParamSeparatorIndexOf + 1);

		var queryParamsStringParts = queryParamsString.split("&");

		for ( var i = 0; i < queryParamsStringParts.length; i++) {
			var queryParam = queryParamsStringParts[i].split("=");

			queryParams[queryParam[0]] = queryParam[1];
		}

		for (var i = 0; i < searchQueryParams.length; i++) {
			var queryParamValue = queryParams[searchQueryParams[i]];

			if (queryParamValue != null) {
				return queryParamValue;
			}
		}

		return null;
	}
	catch (error) {
		return null;
	}
""";