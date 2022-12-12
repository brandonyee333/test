CREATE FUNCTION `$[AC_PROJECT_ID].search_term`(searchQueryParams ARRAY<STRING>, url STRING)
RETURNS STRING
LANGUAGE js
AS """
	var decodedUrl = decodeURIComponent(url.replaceAll('+', ' '));

	var queryParamSeparatorIndexOf = decodedUrl.indexOf("?");

	if (queryParamSeparatorIndexOf < 0) {
		return null;
	}

	var queryParams = {};

	var queryParamsString = decodedUrl.substr(queryParamSeparatorIndexOf + 1);

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
""";