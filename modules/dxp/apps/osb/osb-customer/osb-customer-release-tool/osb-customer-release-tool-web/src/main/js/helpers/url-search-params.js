import 'core-js/library/fn/map';

/**
 * Parses the current URL for all the search parameters
 * @returns {Map<string,string>} The map of the search parameters as key value pairs
 */

export function getAllSearchParams() {
	const query = window.location.search.slice(1);

	const parameters = query.split('&');

	const searchParams = parameters.map(param => param.split('='));

	return new Map(searchParams);
}

/**
 * Finds the value of a given search parameter
 * @param {string} key - The string parameter of the search query
 * @returns {string} The string value of the passed in query parameter
 */

export function getSearchParamValue(key) {
	const parameters = getAllSearchParams();

	return parameters.get(key);
}