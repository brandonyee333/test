CREATE FUNCTION `$[AC_PROJECT_ID].getsearchterm`(url STRING, searchTerms ARRAY<STRING>)
RETURNS STRING
LANGUAGE js
AS """
	var decodedUrl = decodeURI(url);
	
	var queryParamSeparatorIndexOf = decodedUrl.indexOf("?");

	if (queryParamSeparatorIndexOf < 0) {
		return null;
	}

	var queryParamsString = decodedUrl.substr(queryParamSeparatorIndexOf + 1);

	var queryParamsStringParts = queryParamsString.split("&");

	var queryParams = {};

	for ( var i = 0; i < queryParamsStringParts.length; i++) {
		var queryParam = queryParamsStringParts[i].split("=");

		queryParams[queryParam[0]] = queryParam[1];
	}

	for (var i = 0; i < searchTerms.length; i++) {
		var queryParamValue = queryParams[searchTerms[i]];

		if (queryParamValue != null) {
			return queryParamValue;
		}
	}

	return null;
""";