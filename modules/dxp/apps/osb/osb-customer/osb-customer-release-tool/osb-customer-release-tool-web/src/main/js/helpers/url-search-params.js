import 'core-js/library/fn/array/from';
import 'core-js/library/fn/map';

/**
 * Parses the current URL for all the search parameters
 * @returns {Map<string,string>} The map of the search parameters as key value pairs
 */

export function getAllSearchParams() {
	const query = window.location.search.slice(1);

	if (query) {
		const parametersArray = query.split('&');

		const searchParams = parametersArray.map(param => param.split('='));

		return new Map(searchParams);
	} else {
		return new Map();
	}
}

/**
 * Finds the value of a given search parameter
 * @param {string} key - The string parameter of the search query
 * @returns {string} The string value of the provided query parameter
 */

export function getSearchParamValue(key) {
	const parameters = getAllSearchParams();

	return parameters.get(key);
}

/**
 * Updates the search parameters with the provided parameter name and value.
 * If the search parameter doesn't exist, create it.
 * If the search parameter already exists, replace the old value with the new value.
 * If the search parameter has a null value, remove the search parameter.
 * @param {string} key - The string parameter of the search query
 * @param {(string|number)} value - The string parameter of the search query
 */

export function setSearchParam(key, value) {
	const parameters = getAllSearchParams();

	if (key && value) {
		parameters.set(key, value);
	} else if (!value && parameters.has(key)) {
		parameters.delete(key);
	}

	updateSearchQuery(parameters);
}

/**
 * Updates the querystring of the URL
 * @param {Map<string,string>} map - A map of all the querystring key value pairs
 */

export function updateSearchQuery(map) {
	const searchParams = Array.from(map);

	const parametersArray = searchParams.map(param => param.join('='));

	const query = parametersArray.join('&');

	if (history.replaceState) {
		const location = window.location;

		const newURL = `${location.protocol}//${location.host}${location.pathname}?${query}${location.hash}`;

		window.history.replaceState(null, null, newURL);
	} else {
		window.location.search = `?${query}`;
	}
}